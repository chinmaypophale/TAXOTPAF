package com.dexterlabs.taxotpaf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    Button button, button2;
    String msg="Payment Successful",temp;
    boolean flag=false;
    PermissionManager permissionManager;
    TextView textView, tv,textTemp;
    String trackUser;
    int i = 0;
    String PaySuc;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



//        trackUser = getIntent().getStringExtra("trackNameOne");
//        //Temp
//        if (trackUser!=null) {
//            SharedPreferences.Editor editorSave = getSharedPreferences("Save", MODE_PRIVATE).edit();
//            editorSave.putString("name", trackUser);
//            editorSave.apply();
//        }
//
//        updateOne();


        Intent intent = getIntent();
        String message = intent.getStringExtra("ConfirmMessage");

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.buttonInfo);
        textView = (TextView) findViewById(R.id.textView6);



        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(Main3Activity.this);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this,PaymentInfo.class);
                startActivity(i);
            }
        });

        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean first = preferences.getBoolean("FirstTime",true);

        if (!first) {
            Intent i = new Intent(Main3Activity.this,MainActivity.class);
            startActivity(i);
        }


        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag) {
//                        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
                        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("FirstTime",false);
                        editor.apply();
                        Intent i = new Intent(Main3Activity.this, MainActivity.class);
                      //  i.putExtra("trackNameTwo",temp);
                        startActivity(i);
                    }
                    else if (textView.getText().toString().equals("Payment Successful")) {
                        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("FirstTime",false);
                        editor.apply();
                        Intent i = new Intent(Main3Activity.this, MainActivity.class);
                        //i.putExtra("trackNameTwo",temp);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(Main3Activity.this, "Please pay to avail the service", Toast.LENGTH_SHORT).show();
                    }
                }

            });


        if (message!=null) {
                textView.setText(msg);
                flag = true;
                SharedPreferences sp = getSharedPreferences("key1", MODE_PRIVATE);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue1", textView.getText().toString());
                sedt.apply();
        }

        update();


    }

//    private void updateOne() {
//        SharedPreferences prefs = getSharedPreferences("Save", MODE_PRIVATE);
//        temp = prefs.getString("name","");
//    }

    private void update() {
        SharedPreferences sp = getSharedPreferences("key1", MODE_PRIVATE);
        PaySuc = sp.getString("textvalue1","");
        textView.setText(PaySuc);
    }



//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionManager.checkResult(requestCode,permissions,grantResults);
        ArrayList<String> granted = permissionManager.getStatus().get(0).granted;
        ArrayList<String> denied = permissionManager.getStatus().get(0).denied;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
//        Intent intent = new Intent(Main3Activity.this,StartActivity.class);
//        startActivity(intent);
    }


}

