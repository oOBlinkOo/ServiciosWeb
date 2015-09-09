package com.newsfeed;



import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaFragmentos extends ListFragment {
	private JSONArray news;
	ArrayList<String> titleNews2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	       
	    }
	@Override
	public void  onActivityCreated(Bundle savesInstanceState){		 
		super.onActivityCreated(savesInstanceState);
//		 String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//			        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//			        "Linux", "OS/2" };

//		ArrayList<String> aux = callService();

callService();
		titleNews2=titleNews2;
		// ArrayAdapter<String> adapter2 = new ArrayAdapter<String>();
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, titleNews2);
		 setListAdapter(adapter);
	}



	public void callService(){
//		ArrayList<String> aux;

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://rancherita.com.mx/apiv2/fgt9bbcd8dgb99/news", new AsyncHttpResponseHandler() {

			//aqui va todo el codigo, es lo que pasa si hubo succes en el servicio web
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {

				try
				{
					Log.i("Entro al ingresar", "esta falladno");
					String obj  = new String(response); //el response es el json
					news = new JSONArray(obj);
					ArrayList<String> titleNews = new ArrayList<String>();
					for(int i = 0; i < news.length(); i++)
					{
						JSONObject individualNew = (JSONObject)news.get(i);
						titleNews.add(individualNew.getString("title"));
					}
					Log.i("Entro al ingresar", "esta falladno");
//					titleNews2=titleNews;
					titleNews2=titleNews;


//					ListAdapter newsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_view, titleNews);
//					ListView newsListView = (ListView)findViewById(R.id.newsListView);
//					newsListView.setAdapter(newsAdapter);



				}
				catch(JSONException e)
				{
					Log.i("Entro al ingresar", "esta falladno");
//					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG). show();
				}

			}

			@Override
			public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
				Log.i("Entro al ingresar", "esta falladno");
//				Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
			}



		});

	}




//	private void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		//aqui cargo un objeto de una libreria externa
//		AsyncHttpClient client = new AsyncHttpClient();
//		client.get("http://rancherita.com.mx/apiv2/fgt9bbcd8dgb99/news", new AsyncHttpResponseHandler()
//		{
//			//aqui va todo el codigo, es lo que pasa si hubo succes en el servicio web
//			@Override
//			public void onSuccess(int statusCode, Header[] headers, byte[] response)
//			{
//
//				try
//				{
//					String obj  = new String(response); //el response es el json
//					news = new JSONArray(obj);
//					ArrayList<String> titleNews = new ArrayList<String>();
//					for(int i = 0; i < news.length(); i++)
//					{
//						JSONObject individualNew = (JSONObject)news.get(i);
//						titleNews.add(individualNew.getString("title"));
//					}
//
//					ListAdapter newsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_view, titleNews);
//					ListView newsListView = (ListView)findViewById(R.id.newsListView);
//					newsListView.setAdapter(newsAdapter);
//
//					//JSONObject  ob = new JSONObject(obj);
//					//JSONArray ar = ob.getJSONArray("");
//					//JSONObject ob = (JSONObject)ar.get(0);
//					String titlesString = "";
//					//for(int i = 0; i < titles.length; i++)
//					//{
//					//    titlesString += titles[i];
//					//}
//
//				}
//				catch(JSONException e)
//				{
//					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG). show();
//				}
//
//			}
//
//			@Override
//			public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable)
//			{
//				Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG). show();
//			}
//		});
//	}



	@Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    FragmentoDetalle fragment = (FragmentoDetalle) getFragmentManager().findFragmentById(R.id.detailFragment);
	    if (fragment != null && fragment.isInLayout()) {
	      fragment.setText(item);
	    } else {
	      Intent intent = new Intent(getActivity().getApplicationContext(),FragmentoDetalle.class);
	      intent.putExtra("value", item);
	      intent.putExtra("position",position);
	      startActivity(intent);
	    }	
	}
	
}