package com.kola.kola_beta.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.kola.kola_beta.R;

public class DetailPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        Intent intent = getIntent();

        TextView tv_post = findViewById(R.id.tv_post);
        tv_post.setText(intent.getStringExtra("id_post"));
    }
}