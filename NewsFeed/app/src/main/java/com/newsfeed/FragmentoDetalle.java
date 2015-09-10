package com.newsfeed;

import android.app.Fragment;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class FragmentoDetalle extends Fragment {
	private JSONArray news;
	ArrayList<String> titleNews2;

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.e("Test","hello" );
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
		View view = inflater.inflate(R.layout.detalle2,container,false);
		return view;
	}
	public void setText(String item){
callService2(item);
//		TextView view= (TextView)getView().findViewById(R.id.detailsText);
//		view.setText(item);
	}


	public void callService2(final String item){
//		ArrayList<String> aux;

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://rancherita.com.mx/apiv2/fgt9bbcd8dgb99/news", new AsyncHttpResponseHandler() {

			//aqui va todo el codigo, es lo que pasa si hubo succes en el servicio web
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {

				try {
					String obj = new String(response); //el response es el json
					news = new JSONArray(obj);
					ArrayList<String> titleNews = new ArrayList<String>();
					for (int i = 0; i < news.length(); i++) {
						JSONObject individualNew = (JSONObject) news.get(i);
						if (individualNew.getString("title").equals(item)){


							TextView view= (TextView)getView().findViewById(R.id.titulo);
							TextView view2= (TextView)getView().findViewById(R.id.fecha);
							TextView view3= (TextView)getView().findViewById(R.id.hora);
							TextView view4= (TextView)getView().findViewById(R.id.descripcion);

//							ImageView view4 = (ImageView)getView().findViewById(R.id.imageView2);
							view.setText(individualNew.getString("title"));
							view2.setText(individualNew.getString("fecha"));
							view3.setText(individualNew.getString("hora"));

							String str = individualNew.getString("content");
							Spanned sp = Html.fromHtml(str);
//							view4.setText(individualNew.getString("content"));
							view4.setText(sp);


						}

					}





				} catch (JSONException e) {
					Log.i("Entro al ingresar", "esta falladno");
//					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG). show();
				}

			}

			@Override
			public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
				Log.i("Entro al ingresar", "esta falladno");

			}


		});

	}

}
