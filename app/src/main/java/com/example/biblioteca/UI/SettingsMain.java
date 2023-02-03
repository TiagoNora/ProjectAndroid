package com.example.biblioteca.UI;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.biblioteca.Helper.Utils;
import com.example.biblioteca.R;

public class SettingsMain extends AppCompatActivity {

    NumberPicker np1, np2,np3,np4;
    TextView selectedIp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);

        selectedIp = (TextView) findViewById(R.id.ipAddress);
        selectedIp.setText("Selected: "+ Utils.getIPAddress(SettingsMain.this));

        int ip1 = Utils.getIPNumber(this, Utils.IP1);
        np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        np1.setMaxValue(255);
        np1.setMinValue(0);
        np1.setValue(ip1);

        int ip2 =  Utils.getIPNumber(this, Utils.IP2);
        np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        np2.setMaxValue(255);
        np2.setMinValue(0);
        np2.setValue(ip2);

        int ip3 =  Utils.getIPNumber(this, Utils.IP3);


        np3 = (NumberPicker) findViewById(R.id.numberPicker3);
        np3.setMaxValue(255);
        np3.setMinValue(0);
        np3.setValue(ip3);

        int ip4 =  Utils.getIPNumber(this, Utils.IP4);

        np4 = (NumberPicker) findViewById(R.id.numberPicker4);
        np4.setMaxValue(255);
        np4.setMinValue(0);
        np4.setValue(ip4);

        Button bt = (Button)findViewById(R.id.buttonSet);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setWSAddress(SettingsMain.this,np1.getValue(),np2.getValue(),np3.getValue(),np4.getValue());
                selectedIp.setText("Selected: "+ np1.getValue() +"." + np2.getValue() +"."+ np3.getValue() +"."+ np4.getValue());
            }
        });
    }
}