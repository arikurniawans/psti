package com.example.ari.kmljson;

import android.app.ListActivity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.HashMap;

public class list_data2 extends ListActivity {
    private static String url = "https://ariganesha.000webhostapp.com/probal/tampil.php";
    private static final String TAG_PLAYLIST = "lihat";
    private static final String TAG_ID = "id_lokasi";
    private static final String TAG_NAMA = "nama_lokasi";
    private static final String TAG_LAT = "latitude";
    private static final String TAG_LONG = "longitude";
    private static final String TAG_ALAMAT = "alamat";
    private static final String TAG_PARAM = "data_parameter";
    private JSONparser myJSON;
    private JSONObject jURL;
    private JSONArray jArray;
    String myJSonString;

    private HashMap<String, String> hash;
    private ListAdapter adapter;
    private ArrayList<HashMap<String, String>> playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        myJSonString = intent.getStringExtra(list_data2.TAG_PLAYLIST);

        try {
            jArray = jURL.getJSONArray(TAG_PLAYLIST);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject job = new JSONObject(myJSonString);
                job = jArray.getJSONObject(i);
                String id = job.getString("id_lokasi");
                String nama = job.getString("nama_lokasi");
                String alm = job.getString("alamat");
                String lt = job.getString("latitude");
                String lg = job.getString("longitude");
                String param = job.getString("data_parameter");

                HashMap<String, String> hash = new HashMap<String, String>();
                hash.put(TAG_ID, id);
                hash.put(TAG_NAMA, nama);

                playlist.add(hash);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
