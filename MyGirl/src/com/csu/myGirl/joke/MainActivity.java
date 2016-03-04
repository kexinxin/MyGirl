package com.csu.myGirl.joke;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.csu.myGirl.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private int[] jokeContentId = new int[]{R.id.joke_title,R.id.joke_content};
	private Handler hander = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			ListView  jokeList = (ListView) findViewById(R.id.joke_list);
			List<Map<String,Object>> listItems = (List<Map<String,Object>>)msg.obj;
			SimpleAdapter simpleAdapter = new  SimpleAdapter(MainActivity.this, listItems,
					R.layout.single_content,
					new String[]{"title","content"}, 
					new int[]{R.id.joke_title,R.id.joke_content});
			jokeList.setAdapter(simpleAdapter);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.joke_activity_main);
		
		final List<Map<String,Object>> listItems= new ArrayList<Map<String,Object>>();
		/////////////////////////////////////
		ApiStoreSDK.init(this.getApplicationContext(), "fd4f5bec71df62b33e0a6a8c1850a4ba");
		Parameters para = new Parameters();
		para.put("page", 2);
		ApiStoreSDK.execute("http://apis.baidu.com/hihelpsme/chinajoke/getjokelist", 
			ApiStoreSDK.GET,
			para, 
			new  ApiCallBack(){
				@Override
				public void onError(int arg0, String arg1, Exception arg2) {
					// TODO Auto-generated method stub
					super.onError(arg0, arg1, arg2);
				}
				@Override
				public void onComplete() {
					// TODO Auto-generated method stub
					super.onComplete();
				}
				@Override
				public void onSuccess(int arg0, String arg1) {
					// TODO Auto-generated method stub
					super.onSuccess(arg0, arg1);
					Message msg = new Message();
					JSONObject dataJson = new JSONObject(arg1);
					JSONObject res_body = dataJson.getJSONObject("res_body");
					JSONArray jokeList = res_body.getJSONArray("JokeList");
					for(int i=0;i<jokeList.length();i++){
						
						JSONObject info = jokeList.getJSONObject(i);
						String jokeTitle = info.getString("JokeTitle");
						String jokeContent = info.getString("JokeContent");
						Map<String,Object> item = new HashMap<String, Object>();
						item.put("title", jokeTitle);
						item.put("content", jokeContent);
						listItems.add(item);
					}
					msg.obj = listItems;
					hander.sendMessage(msg);
				}
		}
		);
		//for
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
