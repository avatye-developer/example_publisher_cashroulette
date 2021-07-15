package com.avatye.example.publisher.cr

import androidx.multidex.MultiDexApplication
import com.avatye.cashroulette.CashRoulettePublisher

class App : MultiDexApplication() {

    companion object {
        const val publisherID: String = "1b865260495246e58ecf77777961f881"
        const val publisherAppKey: String = "cash_roulette"
    }

    override fun onCreate() {
        super.onCreate()
        CashRoulettePublisher.initialize(application = this, publisherID = publisherID, publisherAppKey = publisherAppKey, log = true)
    }
    
}