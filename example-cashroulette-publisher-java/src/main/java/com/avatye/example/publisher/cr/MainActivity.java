package com.avatye.example.publisher.cr;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.avatye.cashroulette.CashRoulettePublisher;
import com.avatye.example.publisher.cr.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.vb = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(vb.getRoot());
        setAuthViewBind();
        // click event
        vb.authButton.setOnClickListener(v -> {
            final String authAppUserId = Objects.requireNonNull(vb.authAppUserId.getText()).toString();
            if (authAppUserId.isEmpty()) {
                Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                CashRoulettePublisher.setAppUserID(authAppUserId);
                setAuthViewBind();
            }
        });
        vb.launchButton.setOnClickListener(v -> {
            CashRoulettePublisher.launch(
                    this,
                    App.publisherID,
                    App.publisherAppKey,
                    "00000000-0000-0000-0000-009876543210",
                    new CashRoulettePublisher.ILaunchCallback() {
                        @Override
                        public void onSuccess() {
                            vb.launchResult.setText("Launch: Success");
                            vb.launchBanner.setVisibility(View.VISIBLE);
                            vb.launchBannerText.setVisibility(View.GONE);
                            vb.launchResultText.setVisibility(View.VISIBLE);
                            vb.launchButton.setEnabled(false);
                        }

                        @Override
                        public void onFailure() {
                            vb.launchResult.setText("Launch: Failure");
                            vb.launchBanner.setVisibility(View.GONE);
                            vb.launchBannerText.setVisibility(View.VISIBLE);
                            vb.launchResultText.setVisibility(View.GONE);
                            vb.launchButton.setEnabled(true);
                        }
                    });
        });

        vb.launchBanner.setOnClickListener(v -> {
            final String appUserID = CashRoulettePublisher.getAppUserID();
            if (TextUtils.isEmpty(appUserID)) {
                Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                CashRoulettePublisher.start(this);
            }
        });
    }

    private void setAuthViewBind() {
        final String appUserID = CashRoulettePublisher.getAppUserID();
        if (appUserID.isEmpty()) {
            vb.wrapAuth.setVisibility(View.VISIBLE);
            vb.wrapLaunch.setVisibility(View.GONE);
        } else {
            vb.wrapAuth.setVisibility(View.GONE);
            vb.wrapLaunch.setVisibility(View.VISIBLE);
            vb.launchAppUserId.setText(appUserID);
        }
    }
}