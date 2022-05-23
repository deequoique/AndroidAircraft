package com.example.androidaircraft.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidaircraft.R;
import com.google.android.material.textfield.TextInputEditText;

public class InputActivity extends AppCompatActivity {
//    private TextInputEditText input;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id_input);
//        input = new TextInputEditText(this);
    }
//    public void Confirm(View view) {
//        String c = input.getText().toString();
//    }
}
