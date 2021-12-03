package com.kola.kola_beta.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kola.kola_beta.R;

public class DetailEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_editor);

        TextView tv_editor = findViewById(R.id.tv_editor);
        Toast.makeText(this, Integer.toString(getIntent().getIntExtra("id_editor", 0)), Toast.LENGTH_SHORT).show();
        tv_editor.setText(getIntent().getStringExtra("id_editor"));
    }
}