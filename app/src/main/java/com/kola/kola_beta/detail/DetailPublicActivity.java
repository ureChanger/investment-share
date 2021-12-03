package com.kola.kola_beta.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.kola.kola_beta.R;

public class DetailPublicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_publication);

        Intent intent = getIntent();

        TextView tv_public = findViewById(R.id.tv_public);
        tv_public.setText(intent.getStringExtra("id_public"));

    }
}