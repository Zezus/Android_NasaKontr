package com.example.nasa_kontr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button getBtn;
    Date date1;
    TextView textView;
    String json;
    String s;
    RequestQueue mQueue;
    String date;
    String explanation;
    String media_type;
    String service_version;
    String title;
    String url;

    String date2;
    String explanation2;
    String media_type2;
    String service_version2;
    String title2;
    String url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.am_datePicker);
        getBtn = findViewById(R.id.am_button_get);
        textView = findViewById(R.id.am_textView);
        mQueue = Volley.newRequestQueue(this);


        getBtn.setOnClickListener(view -> {
            date1 = new Date

                    (datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth());
            try {
                date1 = new SimpleDateFormat("yyyy-mm-dd").parse(date1.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), date1.toString(), Toast.LENGTH_LONG).show();
            new NetAsyncTask().execute("https://api.nasa.gov/planetary/apod?api_key=02w697sj1HAG7Hs4FDEcUMWLnTTdqdi6CymypBz4");


        });

    }

    private void jsonParse(String s) throws JSONException {
        JSONObject mainObject = new JSONObject(s);
        date = mainObject.getString("date");
        explanation = mainObject.getString("explanation");
        media_type = mainObject.getString("media_type");
        service_version = mainObject.getString("service_version");
        title = mainObject.getString("title");
        url = mainObject.getString("url");
    }

    class NetAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .get()
                    .build();
            Response response;

            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;


            //это не используется
           /* StringBuilder result = new StringBuilder();
            String urlAsString = strings[0];
            URL url = null;

            try {
                url = new URL(urlAsString);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(stream));
                while ((bufferedReader.read()) != -1){
                    result.append(bufferedReader.readLine());
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();*/
        }

        @Override
        protected void onPostExecute(String s) {
            json = s;
            try {
                jsonParse(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            date2 = date;
            explanation2 = explanation;
            media_type2 = media_type;
            service_version2 = service_version;
            title2 = title;
            url2 = url;

            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            intent.putExtra("date", date2);
            intent.putExtra("title", title2);
            startActivity(intent);

        }
    }
}
