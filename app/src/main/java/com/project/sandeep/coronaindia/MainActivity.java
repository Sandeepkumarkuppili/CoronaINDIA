package com.project.sandeep.coronaindia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    public static TextView ind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=findViewById(R.id.states);
    }
    public void result(View view) {
        String s=String.valueOf(sp.getSelectedItem());
        {
            Intent i=new Intent(this,Result.class);
            i.putExtra("key2",s);
            startActivity(i);}
    }
}
