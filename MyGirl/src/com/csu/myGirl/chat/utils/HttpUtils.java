package com.csu.myGirl.chat.utils;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import java.net.URL;
import java.util.Date;

import net.sf.json.JSONObject;

import android.widget.Toast;

import com.csu.myGirl.chat.MainActivity;
import com.csu.myGirl.chat.bean.ChatMessage;
import com.csu.myGirl.chat.bean.ChatMessage.Type;

public class HttpUtils {

	static String httpUrl = "http://apis.baidu.com/turing/turing/turing";
	static String key = "key=879a6cb3afb84dbf4fc84a1df2ab7319&info=";
	static String userid = "&userid=eb2edb736";

	public static ChatMessage sendMessage(String msg)
			throws UnsupportedEncodingException {
		ChatMessage chatMessage = new ChatMessage();
		String text="";
		if(msg.equals("我喜欢你")){
			text="我也比较喜欢自己";
		}else if(msg.equals("我们结婚吧")){
			text="我还小不能结婚的.....";
		}else{			
			//这里可以添加业务，通过分词技术判断敏感词
			
/////////////////////////////////////////////////////////////////////////		
			msg = URLEncoder.encode(msg, "UTF-8");
			String httpArg = key + msg + userid;
			httpArg = new String(httpArg.getBytes(), "utf-8");
			System.out.println(httpArg);
			String result = request(httpUrl, httpArg);
			JSONObject object = new JSONObject(result);
			text = object.getString("text");
/////////////////////////////////////////////////////////////////
		}

		chatMessage.setMsg(text);
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.INCOMING);
		return chatMessage;

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
}
