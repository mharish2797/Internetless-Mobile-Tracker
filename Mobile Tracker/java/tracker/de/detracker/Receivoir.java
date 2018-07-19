package tracker.de.detracker;
/*
* Created by Harish on 17-Feb-2016
*/
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class Receivoir extends BroadcastReceiver {


    public LocationManager locationManager;
    String mob="9123456780",mob1="9786543210";
    public String lats="",longs="",text="google";
    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public Receivoir() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(ACTION)){
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++){
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage message : messages){

                    String strMessageFrom = message.getDisplayOriginatingAddress();
                    String strMessageBody = message.getDisplayMessageBody();
                    if((strMessageFrom.contains(mob)||strMessageFrom.contains(mob1))&&strMessageBody.contains("Harish")) {
                       // Toast.makeText(context, "Mobility: " + strMessageFrom, LENGTH_LONG).show();
                        ConnectivityManager conmgr=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo=conmgr.getActiveNetworkInfo();
                        if(true) {
                        GPSTracker gps;
                            gps = new GPSTracker(context);

                            // check if GPS enabled

                            if (gps.canGetLocation()) {

                                double latit = gps.getLatitude();

                                double longit = gps.getLongitude();
                                lats=String.valueOf(latit);
                                longs=String.valueOf(longit);
                               // Toast.makeText(context,lats+" ~ "+longit, LENGTH_LONG).show();
                                text="www.google.co.in/maps/@"+lats+","+longs+",15z?hl=en";
                                SmsManager smsManager=SmsManager.getDefault();
                                smsManager.sendTextMessage(mob, null, text, null, null);
                            } else {


                                gps.showSettingsAlert();

                            }



                        }}

                    }
                }
            }
        }



}


