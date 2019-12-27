package com.chandannbhat.upport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TemporaryActivity extends AppCompatActivity {

    EditText url_et;
    Button submit;
    String url_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);

        url_et = (EditText)findViewById(R.id.et_url);
        submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                url_string = url_et.getText().toString();
                //Toast.makeText(TemporaryActivity.this, url_string, Toast.LENGTH_SHORT).show();
                Intent next = new Intent(TemporaryActivity.this,MainActivity.class);
                next.putExtra("web_url",url_string);
                startActivity(next);
            }
        });
    }
}
