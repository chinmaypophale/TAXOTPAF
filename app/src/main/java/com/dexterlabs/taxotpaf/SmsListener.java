package com.dexterlabs.taxotpaf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

public class SmsListener extends BroadcastReceiver  {

    DatabaseReference databaseMessageSave;
    boolean flag = true;
    int c = 0,c1 = 0;
    int itd=0,itd1=0;
    String itdmes = "",itdmes2 = "";
    String mes1="",mes2="";
    public SmsListener() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {

                String messageBody = smsMessage.getMessageBody();

                String address = smsMessage.getOriginatingAddress();


                Log.i("sms", "body: " + messageBody);
                Log.i("sms", "address: " + address);

                String message = "[" + address + "] " + messageBody;

                String number = context.getSharedPreferences("data", Context.MODE_PRIVATE).getString("number", "");
                if (number == "") {
                    Log.i("sms", "phone number not set. ignore this one.");
                    return;
                }


                String number2 = context.getSharedPreferences("data2", Context.MODE_PRIVATE).getString("number2", "");
                if (number2 == "") {
                    Log.i("sms", "phone number not set. ignore this one.");
                    return;
                }

                else if(address.matches("[a-zA-Z][a-zA-Z]-[a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z]"))
                {
                    if (address.toUpperCase().contains("GST")) {
                        if (smsMessage.getMessageBody().toUpperCase().contains("OTP")) {
//                            SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
//                            SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
                            if (smsMessage.getMessageBody().length()<=135) {
                                SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
                                SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
                            }

                            else {
                                String mesArr[] = smsMessage.getMessageBody().split(" ");
                                c = mesArr.length;
                                c1 = mesArr.length/2;
                                for (int i = 0;i<c1;i++) {
                                    mes1 = mes1 + mesArr[i] + " ";
                                }

                                for (int j = c1;j<c;j++) {
                                    mes2 = mes2 + mesArr[j] + " ";
                                }

                                SmsManager.getDefault().sendTextMessage(number, null, mes1, null, null);
                                SmsManager.getDefault().sendTextMessage(number2, null, mes1, null, null);

                                SmsManager.getDefault().sendTextMessage(number, null, mes2, null, null);
                                SmsManager.getDefault().sendTextMessage(number2, null, mes2, null, null);

                            }
                        }

                    }

                    else if (address.toUpperCase().contains("ITD")) {
                        if (smsMessage.getMessageBody().toUpperCase().contains("OTP")) {

                            if (smsMessage.getMessageBody().length()<=130) {
                                SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
                                SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
                            }

                            else {
                                String itdmesArr[] = smsMessage.getMessageBody().split(" ");
                                itd = itdmesArr.length;
                                itd1 = itdmesArr.length/2;
                                for (int i = 0;i<itd1;i++) {
                                    itdmes = itdmes + itdmesArr[i] + " ";
                                }

                                for (int j = itd1;j<itd;j++) {
                                    itdmes2 = itdmes2 + itdmesArr[j] + " ";
                                }

                                SmsManager.getDefault().sendTextMessage(number, null, itdmes, null, null);
                                SmsManager.getDefault().sendTextMessage(number2, null, itdmes2, null, null);

                                SmsManager.getDefault().sendTextMessage(number, null, itdmes, null, null);
                                SmsManager.getDefault().sendTextMessage(number2, null, itdmes2, null, null);

                            }
                        }

                    }

                    else if (address.toUpperCase().contains("WAY")) {
                        if (smsMessage.getMessageBody().toUpperCase().contains("SERVICE WORKING EFFICIENTLY")) {
                            //SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
//                            SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
                            SmsManager.getDefault().sendTextMessage("9067368461",null,message,null,null);
                        }
//                        else if (smsMessage.getMessageBody().toUpperCase().contains("OTP")) {
//                            if (smsMessage.getMessageBody().length()<=135) {
//                                SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
//                                SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
//                            }
//
//                            else {
//                                String mesArr[] = smsMessage.getMessageBody().split(" ");
//                                c = mesArr.length;
//                                c1 = mesArr.length/2;
//                                for (int i = 0;i<c1;i++) {
//                                    mes1 = mes1 + mesArr[i] + " ";
//                                }
//
//                                for (int j = c1;j<c;j++) {
//                                    mes2 = mes2 + mesArr[j] + " ";
//                                }
//
//                                SmsManager.getDefault().sendTextMessage(number, null, mes1, null, null);
//                                SmsManager.getDefault().sendTextMessage(number2, null, mes1, null, null);
//
//                                SmsManager.getDefault().sendTextMessage(number, null, mes2, null, null);
//                                SmsManager.getDefault().sendTextMessage(number2, null, mes2, null, null);
//
//                            }
                            //SmsManager.getDefault().sendTextMessage("7276718815",null,message,null,null);
                    }

                }


                else if (address.toUpperCase().contains("GST")) {
                    if (smsMessage.getMessageBody().toUpperCase().contains("OTP")) {
                        if (smsMessage.getMessageBody().length()<=130) {
                            SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
                            SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
                        }

                        else {
                            String simGstArr[] = smsMessage.getMessageBody().split(" ");
                            int simGst = simGstArr.length;
                            int simGst2 = simGstArr.length / 2;
                            String simGstmes="", simGstmes2="";

                            for (int p=0;p<simGst2;p++) {
                                simGstmes = simGstmes + simGstArr[p] + " ";
                            }

                            for (int q=simGst2;q<simGst;q++) {
                                simGstmes2 = simGstmes2 + simGstArr[q] + " ";
                            }

                            SmsManager.getDefault().sendTextMessage(number, null, simGstmes, null, null);
                            SmsManager.getDefault().sendTextMessage(number2, null, simGstmes2, null, null);

                            SmsManager.getDefault().sendTextMessage(number, null, simGstmes, null, null);
                            SmsManager.getDefault().sendTextMessage(number2, null, simGstmes2, null, null);
                        }
                    }
                }


                else if (address.toUpperCase().contains("ITDEFL")) {
                    if (smsMessage.getMessageBody().toUpperCase().contains("OTP")) {
                        if (smsMessage.getMessageBody().length()<=130) {
                            SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
                            SmsManager.getDefault().sendTextMessage(number2, null, message, null, null);
                        }

                        else {
                            String simItdArr[] = smsMessage.getMessageBody().split(" ");
                            int simItd = simItdArr.length;
                            int simItd2 = simItdArr.length / 2;
                            String simItdmes="", simItdmes2="";

                            for (int p=0;p<simItd2;p++) {
                                simItdmes = simItdmes + simItdArr[p] + " ";
                            }

                            for (int q=simItd2;q<simItd;q++) {
                                simItdmes2 = simItdmes2 + simItdArr[q] + " ";
                            }

                            SmsManager.getDefault().sendTextMessage(number, null, simItdmes, null, null);
                            SmsManager.getDefault().sendTextMessage(number2, null, simItdmes2, null, null);

                            SmsManager.getDefault().sendTextMessage(number, null, simItdmes, null, null);
                            SmsManager.getDefault().sendTextMessage(number2, null, simItdmes2, null, null);
                        }
                    }
                }

                Log.i("sms", "sending to " + number);

                Log.i("sms", "message send:" + message);

            }
        }
    }

//    private String getContactName(String address, Context context) {
//        ContentResolver cr = context.getContentResolver();
//        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
//        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
//        if (cursor == null) {
//            return null;
//        }
//        String contactName = null;
//        if (cursor.moveToFirst()) {
//            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
//        }
//
//        if (cursor != null && !cursor.isClosed()) {
//            cursor.close();
//        }
//
//        return contactName;
//    }
}
