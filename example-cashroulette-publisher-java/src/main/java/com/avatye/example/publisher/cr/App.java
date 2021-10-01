package com.avatye.example.publisher.cr;

import androidx.multidex.MultiDexApplication;

import com.avatye.cashroulette.CashRoulettePublisher;

public class App extends MultiDexApplication {

    public static final String publisherID = "6aeb9028f1aa42aaa8aa8a3f10f36f0c";
    public static final String publisherAppKey = "cash_roulette_publisher";

    @Override
    public void onCreate() {
        super.onCreate();
        CashRoulettePublisher.initialize(this, App.publisherID, App.publisherAppKey, true);
    }
    
}