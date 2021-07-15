package com.avatye.example.publisher.cr

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.avatye.cashroulette.CashRoulettePublisher
import com.avatye.cashroulette.business.model.GenderType
import com.avatye.cashroulette.business.model.user.Profile
import com.avatye.example.publisher.cr.databinding.ActivityMainBinding
import com.avatye.library.support.helper.SupportHelper

class MainActivity : AppCompatActivity() {

    private val vb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        // set profile
        val androidID = SupportHelper.androidID
        CashRoulettePublisher.setProfile(Profile(userID = androidID, birthYear = 1979, gender = GenderType.MALE))
        // click event
        vb.launchButton.setOnClickListener {
            CashRoulettePublisher.launch(
                context = this,
                publisherID = App.publisherID,
                publisherAppKey = App.publisherAppKey,
                adid = "8c8ba40c-0f04-43cc-9a3a-53fb0fccb5d6",
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

        vb.launchBanner.setOnClickListener {
            CashRoulettePublisher.start(this)
        }
    }

}