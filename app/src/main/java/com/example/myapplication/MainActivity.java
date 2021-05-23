package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

  private final ActivityResultLauncher<String[]> requestPermissionLauncher =
      registerForActivityResult(
          new ActivityResultContracts.RequestMultiplePermissions(),
          permissions -> {
            for (boolean isGranted : permissions.values()) {
              if (!isGranted) {
                finish();
              }
            }
          });

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!Helper.hasPermissions(getApplicationContext(), RECORD_AUDIO, WRITE_EXTERNAL_STORAGE)) {
      requestPermissionLauncher.launch(new String[] {RECORD_AUDIO, WRITE_EXTERNAL_STORAGE});
    }

    setContentView(R.layout.activity_main);
  }
}
