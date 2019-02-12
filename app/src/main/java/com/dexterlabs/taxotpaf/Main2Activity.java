package com.dexterlabs.taxotpaf;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.TimeZone;

public class Main2Activity extends AppCompatActivity {
EditText editText2,editText3,number,caNm;
TextView textViewDate, textPolicay;
String date,finalDate,year,month,sendName;
Button button;
boolean flag = false;
   DatabaseReference databaseUser;
int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.button);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        caNm = (EditText) findViewById(R.id.editCaNumber);
        number = (EditText) findViewById(R.id.phoneNumber);
        textViewDate = (TextView) findViewById(R.id.textView7);
        textPolicay = (TextView) findViewById(R.id.textView14);
        textViewDate.setVisibility(View.INVISIBLE);
        databaseUser = FirebaseDatabase.getInstance().getReference("User");

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/d/E",Locale.US);
  //      String strDate = sdf.format(calendar);
        date = String.valueOf(calendar.get(Calendar.DATE));
         month = String.valueOf(calendar.get(Calendar.MONTH)+1);
         year = String.valueOf(calendar.get(Calendar.YEAR));
         finalDate = date+" / "+month+" / "+year;
        textViewDate.setText(finalDate);

        textPolicay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intpolicy = new Intent(Main2Activity.this, PrivacyPolicy.class);
                startActivity(intpolicy);
            }
        });

        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean second = preferences.getBoolean("secondTime",true);

        if (!second) {
            Intent i = new Intent(Main2Activity.this,Main3Activity.class);
            startActivity(i);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUser();
                if (flag){
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("Terms and Condition")
                        .setMessage("I accept automatic forward of my one time tax passwords to the registered number.\n Bank OTP and Aadhar OTP shall not be forwarded and no personal information via the application is misused")
                        .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putBoolean("secondTime",false);
                                editor.apply();
                                dialog.dismiss();
                                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                                //intent.putExtra("trackNameOne",sendName);
                                startActivity(intent);
                            }
                        })
                        .create().show();
            }
        }

        });
    }

    private void addUser() {
        String name = editText2.getText().toString();
        sendName = name;
        String email = editText3.getText().toString();
        String phoneNumber = number.getText().toString();
        String nameCa = caNm.getText().toString();

        String id = databaseUser.push().getKey();

        if (name.equals("")) {
            editText2.setError("Please enter your full name");
            editText2.requestFocus();
            return;
        }

        if (email.equals("")) {
            editText3.setError("Please enter proper Email Address");
            editText3.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText3.setError("Please enter proper Email Address");
            editText3.requestFocus();
            return;
        }

        if (phoneNumber.length()!=10) {
            number.setError("Please enter 10 digit phone number");
            number.requestFocus();
            return;
        }

        if (nameCa.equals("")) {
            caNm.setError("Please enter your CA's name");
            caNm.requestFocus();
            return;
        }

        User user = new User(id,name,email,finalDate,phoneNumber,nameCa);
        databaseUser.child(id).setValue(user);
        flag = true;

    }

}
