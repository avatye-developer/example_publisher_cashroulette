package com.avatye.example.publisher.cr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.avatye.cashroulette.CashRoulettePublisher;
import com.avatye.example.publisher.cr.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final ActivityMainBinding vb = ActivityMainBinding.inflate(LayoutInflater.from(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vb.getRoot());
        // parameter value
        final String appUserID = "publisher-example@avatye.com";
        final String publisherID = App.publisherID;
        final String publisherAppKey = App.publisherAppKey;
        final String aaid = "00000000-0000-0000-0000-009876543210";
        // set appUserID
        CashRoulettePublisher.setAppUserID(appUserID);
        // click event
        vb.launchButton.setOnClickListener(v -> {
            CashRoulettePublisher.launch(this, App.publisherID, App.publisherAppKey, aaid, new CashRoulettePublisher.ILaunchCallback() {
                @Override
                public void onSuccess() {
                    vb.launchResult.setText("onSuccess");
                    vb.launchBanner.setVisibility(View.VISIBLE);
                    vb.launchButton.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure() {
                    vb.launchResult.setText("onFailure");
                    vb.launchBanner.setVisibility(View.GONE);
                    vb.launchButton.setVisibility(View.VISIBLE);
                }
            });
        });

        vb.launchBanner.setOnClickListener(v -> {
            CashRoulettePublisher.start(this);
        });
    }
}