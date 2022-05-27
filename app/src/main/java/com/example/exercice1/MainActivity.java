package com.example.exercice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst=findViewById(R.id.lst);
        String json = loadJsonFromRaw(R.raw.etablissement);

        try {
            JSONObject obj = new JSONObject(json);

            String rs = obj.getString("raisonSociale");
            double cap = obj.getDouble("capital");
            String adrs = obj.getString("adresse");
            ArrayList<String> s =new ArrayList<>();
            JSONArray arr = obj.getJSONArray("filieres");

            for(int i=0;i<arr.length();i++){
                JSONObject obk=arr.getJSONObject(i);

                s.add(obk.getInt("code")+obk.getString("nomComplet")+obk.getString("duree"));

            }
            ArrayAdapter d =new ArrayAdapter(this,R.layout.activity_main,s);
            lst.setAdapter(d);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public String loadJsonFromRaw(int resId) {
        String json = "";
        try {
            InputStream input = getResources().openRawResource(resId);
            int taille = 0;
            taille = input.available();
            byte[] content = new byte[taille];
            input.read(content);
            input.close();
            json = new String(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
