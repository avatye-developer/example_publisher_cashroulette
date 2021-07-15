package com.avatye.example.publisher.cr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.avatye.cashroulette.CashRoulettePublisher;
import com.avatye.cashroulette.business.model.GenderType;
import com.avatye.cashroulette.business.model.user.Profile;
import com.avatye.example.publisher.cr.databinding.ActivityMainBinding;
import com.avatye.library.support.helper.SupportHelper;

public class MainActivity extends AppCompatActivity {

    private final ActivityMainBinding vb = ActivityMainBinding.inflate(LayoutInflater.from(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(vb.getRoot());

        // set profile
        final String androidID = SupportHelper.INSTANCE.getAndroidID();
        CashRoulettePublisher.setProfile(new Profile(androidID, 1979, GenderType.MALE));
        // click event
        vb.launchButton.setOnClickListener(v -> {
            CashRoulettePublisher.launch(
                    this,
                    App.publisherID,
                    App.publisherAppKey,
                    "8c8ba40c-0f04-43cc-9a3a-53fb0fccb5d6",
                    new CashRoulettePublisher.ILaunchCallback() {
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
                    }
            );
        });


        vb.launchBanner.setOnClickListener(v -> {
            CashRoulettePublisher.start(this);
        });
    }
}