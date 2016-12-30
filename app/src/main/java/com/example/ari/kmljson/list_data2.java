package com.example.ari.kmljson;

import android.app.ListActivity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class list_data2 extends ListActivity {
    private static String URL = "https://ariganesha.000webhostapp.com/prola_server/contact.php";
    private String TAG_DATA = "data";
    private String TAG_NAMA = "nama_lokasi";
    private String TAG_LAT = "lat";
    private String TAG_LONG = "longi";
    String nama,lat,longg;

    JSONArray contacts = null;
    JSONparser parser = new JSONparser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        JSONObject json = parser.getJSONFromUrl(URL);

        try{
            contacts = json.getJSONArray(TAG_DATA);
            for(int i = 0; i < contacts.length(); i++){
                JSONObject data = contacts.getJSONObject(i);
                 nama = data.getString(TAG_NAMA);
                 lat = data.getString(TAG_LAT);
                longg = data.getString(TAG_LONG);
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(TAG_NAMA, nama);
                map.put(TAG_LAT, lat);
                map.put(TAG_LONG, longg);
                contactList.add(map);
                Toast.makeText(getApplication(),"Nama Lokasi : "+ nama +"\n Latitude : "+ lat +"\n Longitudinal : "+ longg, Toast.LENGTH_LONG).show();
            //GeoPoint titikAwal = new GeoPoint(lat,longg);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(this, contactList, R.layout.activity_list_data2, new String[]{TAG_NAMA, TAG_LAT, TAG_LONG}, new int[]{R.id.nama, R.id.email, R.id.longgg});
        setListAdapter(adapter);
    }
}
