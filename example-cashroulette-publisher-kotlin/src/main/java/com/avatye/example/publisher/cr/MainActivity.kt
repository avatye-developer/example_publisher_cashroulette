package com.avatye.example.publisher.cr

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
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
        // parameter value
        val appUserID = "publisher-example@avatye.com"
        val publisherID = App.publisherID
        val publisherAppKey = App.publisherAppKey
        val aaid = "00000000-0000-0000-0000-009876543210"
        // set appUserID
        CashRoulettePublisher.setAppUserID(appUserID)
        // click event
        vb.launchButton.setOnClickListener {
            CashRoulettePublisher.launch(
                context = this,
                publisherID = App.publisherID,
                publisherAppKey = App.publisherAppKey,
                aaid = aaid,
                listener = object : CashRoulettePublisher.ILaunchCallback {
                    override fun onSuccess() {
                        vb.launchResult.text = "onSuccess"
                        vb.launchBanner.isVisible = true
                        vb.launchButton.isInvisible = true
                    }

                    override fun onFailure() {
                        vb.launchResult.text = "onFailure"
                        vb.launchBanner.isVisible = false
                        vb.launchButton.isInvisible = false
                    }
                })
        }
        // entry banner
        vb.launchBanner.setOnClickListener {
            CashRoulettePublisher.start(this)
        }
    }

}