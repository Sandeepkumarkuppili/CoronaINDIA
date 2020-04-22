package com.project.sandeep.coronaindia;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import static com.project.sandeep.coronaindia.MainActivity.*;
import static com.project.sandeep.coronaindia.MainActivity.ind;
import static com.project.sandeep.coronaindia.Result.*;
import static com.project.sandeep.coronaindia.Result.fdata;
import static com.project.sandeep.coronaindia.Result.tdata;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    String ori="";
    String sp="";
    int tot;
    String line2="";
    String data2="";
    String overall="";
    //JSONArray ja;
    String cname;
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url=new URL("https://api.covid19india.org/state_district_wise.json");
            URL url2=new URL("https://api.rootnet.in/covid19-in/unofficial/covid19india.org/statewise");

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));

            HttpURLConnection httpURLCon= (HttpURLConnection) url2.openConnection();
            InputStream inputStr=httpURLCon.getInputStream();
            BufferedReader bufferedRead= new BufferedReader(new InputStreamReader(inputStr));

            while(line2 !=null){
                line2=bufferedRead.readLine();
                data2=data2+line2;
            }

            JSONObject jsonRootObject2=new JSONObject(data2);

            JSONObject dobj= (JSONObject) jsonRootObject2.optJSONObject("data");

            JSONObject over=dobj.getJSONObject("total");

            overall= (String) "Confirmed : "+over.get("confirmed")+"\n"+
                    "Recovered : "+over.get("recovered");

            String line ="";
            String cname=Result.cname;
            while(line !=null){
                line=bufferedReader.readLine();
                data=data+line;
            }
            JSONObject jsonRootObject=new JSONObject(data);

            JSONObject ja= jsonRootObject.optJSONObject(cname);

            JSONObject dd=ja.optJSONObject("districtData");

            Iterator<String> keys = dd.keys();
            String key= String.valueOf(dd.keys().next());
            tot=0;
            for(int i=0;i<dd.length();i++)
            {
                String t=keys.next();
                JSONObject temp= dd.optJSONObject(t);
                sp= (String) t+" : "+temp.get("confirmed")+"\n";
                             /*"total cases "+jo.get("confirmed")+"\n"+
                             "deaths "+jo.get("deaths")+"\n"+
                             "recovered "+jo.get("recovered")+"\n";*/
                tot += temp.getInt("confirmed");
                ori=ori+sp+"\n";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //ind.setText(overall);
        country.setText("INDIA \n"+overall);
        tdata.setText(Result.cname.toUpperCase()+"\n"+" Total cases:"+String.valueOf(tot));
        fdata.setText(ori);
    }
}

