package com.avatye.example.publisher.cr

import androidx.multidex.MultiDexApplication
import com.avatye.cashroulette.CashRoulettePublisher

class App : MultiDexApplication() {

    companion object {
        const val publisherID = "6aeb9028f1aa42aaa8aa8a3f10f36f0c"
        const val publisherAppKey = "cash_roulette_publisher"
    }

    override fun onCreate() {
        super.onCreate()
        CashRoulettePublisher.initialize(
            application = this,
            publisherID = publisherID,
            publisherAppKey = publisherAppKey,
            log = true
        )
    }

}