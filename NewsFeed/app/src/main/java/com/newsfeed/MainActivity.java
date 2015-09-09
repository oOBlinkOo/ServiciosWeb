package com.newsfeed;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private JSONArray news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aqui cargo un objeto de una libreria externa
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://rancherita.com.mx/apiv2/fgt9bbcd8dgb99/news", new AsyncHttpResponseHandler()
        {
            //aqui va todo el codigo, es lo que pasa si hubo succes en el servicio web
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response)
            {

                try
                {
                    String obj  = new String(response); //el response es el json
                    news = new JSONArray(obj);
                    ArrayList<String> titleNews = new ArrayList<String>();
                    for(int i = 0; i < news.length(); i++)
                    {
                        JSONObject individualNew = (JSONObject)news.get(i);
                        titleNews.add(individualNew.getString("title"));
                    }

                    ListAdapter newsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_view, titleNews);
                    ListView newsListView = (ListView)findViewById(R.id.newsListView);
                    newsListView.setAdapter(newsAdapter);

                    //JSONObject  ob = new JSONObject(obj);
                    //JSONArray ar = ob.getJSONArray("");
                    //JSONObject ob = (JSONObject)ar.get(0);
                    String titlesString = "";
                    //for(int i = 0; i < titles.length; i++)
                    //{
                    //    titlesString += titles[i];
                    //}

                }
                catch(JSONException e)
                {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG). show();
                }

            }

            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable)
            {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG). show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
