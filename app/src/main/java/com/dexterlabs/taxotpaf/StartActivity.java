package com.dexterlabs.taxotpaf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
String mes = "Payment Successful";
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(StartActivity.this,Main3Activity.class);
                in.putExtra("ConfirmMessage",mes);
                startActivity(in);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(StartActivity.this,Main3Activity.class);
        in.putExtra("ConfirmMessage",mes);
        startActivity(in);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Intent in = new Intent(StartActivity.this,Main3Activity.class);
        in.putExtra("ConfirmMessage",mes);
        startActivity(in);
    }
}
