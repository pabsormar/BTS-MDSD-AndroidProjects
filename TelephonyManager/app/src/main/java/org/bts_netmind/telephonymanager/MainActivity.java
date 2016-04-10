package org.bts_netmind.telephonymanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private TextView info_TxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.info_TxtView = (TextView) this.findViewById(R.id.textViewInfo);

        // Accessing the 'TELEPHONY_SERVICE'
        TelephonyManager myTelManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        //Calling the methods of TelephonyManager the returns the information
        String IMEINumber = myTelManager.getDeviceId();
        String subscriberID = myTelManager.getDeviceId();
        String SIMSerialNumber = myTelManager.getSimSerialNumber();
        String networkCountryISO = myTelManager.getNetworkCountryIso();
        String SIMCountryISO = myTelManager.getSimCountryIso();
        String softwareVersion = myTelManager.getDeviceSoftwareVersion();
        String line1Number = myTelManager.getLine1Number();

        //Get the phone type
        String strphoneType = "";

        int phoneType = myTelManager.getPhoneType();

        switch (phoneType)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType = "NONE";
                break;
        }

        //getting information if phone is in roaming
        boolean isRoaming = myTelManager.isNetworkRoaming();

        String info="Phone Details:\n";
        info += "\n IMEI number: " + IMEINumber;
        info += "\n Subscriber ID: " + subscriberID;
        info += "\n Sim serial number: " + SIMSerialNumber;
        info += "\n Network country ISO: " + networkCountryISO;
        info += "\n SIM Country ISO: " + SIMCountryISO;
        info += "\n Software version: " + softwareVersion;
        info += "\n Line 1 number: " + line1Number;
        info += "\n Phone network type: " + strphoneType;
        info += "\n In roaming?: " + isRoaming;

        //displaying the information in the textView
        this.info_TxtView.setText(info);
    }
}
