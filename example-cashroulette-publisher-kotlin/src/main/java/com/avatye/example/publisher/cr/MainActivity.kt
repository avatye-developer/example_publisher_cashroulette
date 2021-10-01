package com.avatye.example.publisher.cr

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.avatye.cashroulette.CashRoulettePublisher
import com.avatye.example.publisher.cr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val vb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        setAuthViewBind()
        // click event
        vb.authButton.setOnClickListener {
            if (vb.authAppUserId.text.isNullOrEmpty()) {
                Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                val appUserID = vb.authAppUserId.text.toString().trim()
                CashRoulettePublisher.setAppUserID(appUserID)
                setAuthViewBind()
            }
        }
        vb.launchButton.setOnClickListener {
            CashRoulettePublisher.launch(
                context = this,
                publisherID = App.publisherID,
                publisherAppKey = App.publisherAppKey,
                aaid = "00000000-0000-0000-0000-009876543210",
                listener = object : CashRoulettePublisher.ILaunchCallback {
                    override fun onSuccess() {
                        vb.launchResult.text = "Launch: Success"
                        vb.launchBanner.isVisible = true
                        vb.launchBannerText.isVisible = false
                        vb.launchResultText.isVisible = true
                        vb.launchButton.isEnabled = false
                    }

                    override fun onFailure() {
                        vb.launchResult.text = "Launch: Failure"
                        vb.launchBanner.isVisible = false
                        vb.launchBannerText.isVisible = true
                        vb.launchResultText.isVisible = false
                        vb.launchButton.isEnabled = true
                    }
                })
        }

        vb.launchBanner.setOnClickListener {
            val appUserID = CashRoulettePublisher.getAppUserID()
            if (appUserID.isNullOrEmpty()) {
                Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                CashRoulettePublisher.start(this)
            }
        }
    }

    private fun setAuthViewBind() {
        val appUserID = CashRoulettePublisher.getAppUserID()
        if (appUserID.isEmpty()) {
            vb.wrapAuth.isVisible = true
            vb.wrapLaunch.isVisible = false
        } else {
            vb.wrapAuth.isVisible = false
            vb.wrapLaunch.isVisible = true
            vb.launchAppUserId.text = appUserID
        }
    }

}