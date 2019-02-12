package com.dexterlabs.taxotpaf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Cancel extends AppCompatActivity {

    String cancel = "SERVICE STOPPED";
    Button can;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        can = (Button) findViewById(R.id.cancel);

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Cancel.this,MainActivity.class);
                in.putExtra("CancelMessage",cancel);
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(Cancel.this,MainActivity.class);
        in.putExtra("CancelMessage",cancel);
        startActivity(in);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Intent in = new Intent(Cancel.this,MainActivity.class);
        in.putExtra("CancelMessage",cancel);
        startActivity(in);
    }
}
