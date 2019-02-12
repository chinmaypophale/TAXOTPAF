package com.dexterlabs.taxotpaf;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ConfirmationPayment extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);

        if (messages.getDisplayOriginatingAddress().contains("9075640060") || messages.getDisplayOriginatingAddress().contains("9028740031") || messages.getDisplayOriginatingAddress().contains("9822531812")) {
            if (messages.getMessageBody().contains("START")) {
                //SmsManager.getDefault().sendTextMessage("9028740031",null,"Service Started Successfully",null,null);
                Intent smsIntent = new Intent(context, StartActivity.class);

                smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(smsIntent);
            }

            else if (messages.getMessageBody().contains("STOP")) {

                PackageManager pm  = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, SmsListener.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                Toast.makeText(context, "cancelled", Toast.LENGTH_LONG).show();

                Intent smsCancelIntent = new Intent(context, Cancel.class);

                smsCancelIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(smsCancelIntent);

            }
//
            else if (messages.getMessageBody().contains("RESUME")) {
                PackageManager pm  = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, SmsListener.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
                Toast.makeText(context, "activated", Toast.LENGTH_LONG).show();

                Intent smsCancelIntent = new Intent(context, Resume.class);

                smsCancelIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(smsCancelIntent);
            }

        }

    }
}