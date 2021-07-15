package com.avatye.example.publisher.cr;

import androidx.multidex.MultiDexApplication;

import com.avatye.cashroulette.CashRoulettePublisher;

public class App extends MultiDexApplication {

    public static final String publisherID = "1b865260495246e58ecf77777961f881";
    public static final String publisherAppKey = "cash_roulette";

    @Override
    public void onCreate() {
        super.onCreate();
        CashRoulettePublisher.initialize(this, App.publisherID, App.publisherAppKey, true);
    }
    
}