package com.csu.myGirl.sen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.csu.myGirl.R;
import com.csu.myGirl.chat.bean.ChatMessage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	static String httpUrl = "http://apis.baidu.com/gushi/lovebible/say1";
	static String httpArg = "count=1&fmt=0";
	private Handler myHandler;
	private Bitmap bitmap;
	private ImageView myImg;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			TextView textView = (TextView) findViewById(R.id.sen_textview);
			String text = (String) msg.obj;
			System.out.println(text);
			textView.setText(text);
		};

	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sen_main);
		new Thread() {
			public void run() {
				String text;
				String jsonResult = request(httpUrl, httpArg);
				JSONObject object = new JSONObject(jsonResult);
				JSONArray dataArray = object.getJSONArray("data");
				JSONObject data = dataArray.getJSONObject(0);
				text = data.getString("title");
				System.out.println(text);
				Message m = Message.obtain();
				m.obj = text;
				mHandler.sendMessage(m);
			}
		}.start();
//		myImg=(ImageView)findViewById(R.id.sen_imageView);
//		myHandler=new Handler()
//		{
//			public void handleMessage(android.os.Message msg)
//			{
//				if(msg.what==0x1122)
//				{
//					myImg.setImageBitmap(bitmap);
//				}
//			};
//		};
//		new Thread()
//		{
//			public void run()
//			{
//				URL url;
//				try {
//					
//					String httpUrl = "http://apis.baidu.com/showapi_open_bus/pic/pic_search";
//					String httpArg = "type=4002&page=1";
//					String jsonResult = request(httpUrl, httpArg);
//					JSONObject object = new JSONObject(jsonResult);
//					JSONObject showapi_res_body=object.getJSONObject("showapi_res_body");
//					JSONObject pagebean=showapi_res_body.getJSONObject("pagebean");
//					JSONArray contentlist = pagebean.getJSONArray("contentlist");
//					JSONObject pi=contentlist.getJSONObject(2);
//					JSONArray piList = pi.getJSONArray("list");
//					int i=new Random().nextInt(piList.length());
//					JSONObject pic=piList.getJSONObject(i);
//					String url1=pic.getString("middle");
//					
//					
//					url = new URL(url1);
//					
//				
//				InputStream is=url.openStream();
//				bitmap=BitmapFactory.decodeStream(is);
//				is.close();
//				} catch (Exception e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
//				myHandler.sendEmptyMessage(0x1122);
//							
//			};
//			
//		}.start();
		
	}

	public void Clickee(View source) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey",
					"2ffbcae4d025b6c109af30d7de2d7c09");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// download image from network using @urladdress
	
	private Drawable loadImageFromNetwork(String urladdr) {

		// TODO Auto-generated method stub

		Drawable drawable = null;

		try {

			// judge if has picture locate or not according to filename

			drawable = Drawable.createFromStream(new URL(urladdr).openStream(),
					"image.jpg");

		} catch (IOException e) {

			Log.d("test", e.getMessage());

		}

		if (drawable == null) {

			Log.d("test", "null drawable");

		} else {

			Log.d("test", "not null drawable");

		}

		return drawable;

	}
}
