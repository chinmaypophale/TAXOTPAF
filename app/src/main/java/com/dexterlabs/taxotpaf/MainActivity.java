package com.dexterlabs.taxotpaf;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText2;
    int count = 0;
    String PaySuc;
    String name;
    DatabaseReference trackDatabaseUser;
    TextView textView8,textView11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackDatabaseUser = FirebaseDatabase.getInstance().getReference("TrackUser");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        String message = intent.getStringExtra("CancelMessage");

        //name = getIntent().getStringExtra("trackNameTwo");

        textView8 = (TextView) findViewById(R.id.textView8);
        textView11 = (TextView) findViewById(R.id.textView11);

//        edate = getIntent().getStringExtra("Date");
//        emonth = getIntent().getStringExtra("Month");
//        eyear = getIntent().getStringExtra("Year");
//        int year = Integer.parseInt(eyear)+1;
//        eyear = String.valueOf(year);
//        efinal = edate+" / "+emonth+" / "+eyear;
//
//        textView8.setText(efinal);

        String number = getSharedPreferences("data", Context.MODE_PRIVATE).getString("number", "");
        String number2 = getSharedPreferences("data2", Context.MODE_PRIVATE).getString("number2", "");


        Log.d("log","number: " + number);
        editText = (EditText) findViewById(R.id.edit_phone_number);
        editText.setText(number, TextView.BufferType.EDITABLE);

        editText2 = (EditText) findViewById(R.id.edit_phone_number2);
        editText2.setText(number2, TextView.BufferType.EDITABLE);

        Button button = (Button) findViewById(R.id.btnSendSMS);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (editText.getText().toString().isEmpty() || editText.getText().toString().length()!=10) {
                        editText.setError("Please enter proper number");
                        editText.requestFocus();
                        return;
                    }

                    if (editText2.getText().toString().isEmpty() || editText2.getText().toString().length()!=10) {
                        editText2.setError("Please enter proper number");
                        editText2.requestFocus();
                        return;
                    }


                    String number = editText.getText().toString();
                    String number2 = editText2.getText().toString();

//                String id = trackDatabaseUser.push().getKey();

//                TwoNumberStore twoNumberStore = new TwoNumberStore(id,name,number,number2);
//                trackDatabaseUser.child(id).setValue(twoNumberStore);

                    Toast.makeText(getApplicationContext(), "Your Service has started Successfully", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                    SharedPreferences.Editor editor2 = getSharedPreferences("data2", Context.MODE_PRIVATE).edit();


                    editor.putString("number", number);
                    editor.commit();

                    editor2.putString("number2", number2);
                    editor2.commit();

                    String message = "Confirmation Message: Service has started on " + number;
                    Log.i("sms", "message send:" + message);
                    SmsManager.getDefault().sendTextMessage(number, null, message, null, null);

                    String message2 = "Confirmation Message: Service has started on " + number2;
                    Log.i("sms", "message send:" + message2);
                    SmsManager.getDefault().sendTextMessage(number2, null, message2, null, null);

            }
        });

        if (message!=null) {
            if (message.equals("SERVICE STOPPED")) {
                textView11.setText("SERVICE HAS BEEN CANCELLED");
                button.setVisibility(View.INVISIBLE);
                SharedPreferences sp = getSharedPreferences("keyValue", MODE_PRIVATE);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("value", textView11.getText().toString());
                sedt.commit();
            }

            else if (message.equals("SERVICE RESUMED")) {
                textView11.setText("SERVICE HAS BEEN STARTED");
                SharedPreferences sp = getSharedPreferences("keyValue", MODE_PRIVATE);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("value", textView11.getText().toString());
                sedt.commit();
            }

        }
        update();


    }


    //    public void sendSMS(View v)
//    {
//        EditText editText = (EditText) findViewById(R.id.edit_phone_number);
//
//
//    }


//    boolean doubleBackToExitPressedOnce = false;
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//
//    }

    private void update() {
        SharedPreferences sp = getSharedPreferences("keyValue", MODE_PRIVATE);
        PaySuc = sp.getString("value","After entering two numbers, click on the button and enjoy the service");
        textView11.setText(PaySuc);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
