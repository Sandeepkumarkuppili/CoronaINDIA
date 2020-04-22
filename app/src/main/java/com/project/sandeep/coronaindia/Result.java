package com.project.sandeep.coronaindia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    public static String cname;
    public static TextView country;
    public static TextView fdata,tdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        country=findViewById(R.id.con);
        fdata=findViewById(R.id.fdata);
        tdata=findViewById(R.id.tdata);

        cname=getIntent().getStringExtra("key2");

        fetchData process=new fetchData();
        process.execute();

    }
}
