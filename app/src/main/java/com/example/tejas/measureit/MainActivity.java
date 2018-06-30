package com.example.tejas.measureit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    private static final String TAG = "MainActivity";
    int value = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BTNmyProjects = (Button) findViewById(R.id.BTNmyProjects);
        Button BTNcalibrate = (Button) findViewById(R.id.BTNcalibrate);
        Button BTNtemplate = (Button) findViewById(R.id.BTNtemplate);

        BTNmyProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ProjectListActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        BTNcalibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CalibrationActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        BTNtemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TemplatesActivity.class);
                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

    }

}
