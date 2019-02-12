package com.dexterlabs.taxotpaf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Resume extends AppCompatActivity {
Button resume;
String resumeMessage = "SERVICE RESUMED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        resume = (Button) findViewById(R.id.resume);

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Resume.this,MainActivity.class);
                in.putExtra("CancelMessage",resumeMessage);
                startActivity(in);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent in = new Intent(Resume.this,MainActivity.class);
        in.putExtra("CancelMessage",resumeMessage);
        startActivity(in);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Intent in = new Intent(Resume.this,MainActivity.class);
        in.putExtra("CancelMessage",resumeMessage);
        startActivity(in);
    }
}
