package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

//        button.setOnClickListener(v -> {
//            Log.d("MainActivity", "button clicked.");
//        });
        button.setOnClickListener(this);

        Button img_btn = findViewById(R.id.change_img_btn);

        img_btn.setOnClickListener(this);

        Button progressBarBtn = findViewById(R.id.progress_bar_btn);
        progressBarBtn.setOnClickListener(this);

        Button addProgressBtn = findViewById(R.id.add_progress_btn);
        addProgressBtn.setOnClickListener(this);

        Button alertDialogBtn = findViewById(R.id.alert_dialog_btn);
        alertDialogBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (R.id.button == v.getId()) {
            EditText editText = findViewById(R.id.editText);
            String inputText = editText.getText().toString();
            Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show();
        } else if (R.id.change_img_btn == v.getId()) {
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        } else if (R.id.progress_bar_btn == v.getId()) {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            if (progressBar.getVisibility() == View.VISIBLE) {
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        } else if (R.id.add_progress_btn == v.getId()) {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(progressBar.getProgress() + 10);
        } else if (R.id.alert_dialog_btn == v.getId()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("This is Dialog");
            builder.setMessage("Something important.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", ((dialog, which) -> {}));
            builder.setNegativeButton("Cancel", ((dialog, which) -> {}));
            builder.show();
        }
    }
}