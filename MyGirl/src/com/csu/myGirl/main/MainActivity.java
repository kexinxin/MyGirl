package com.csu.myGirl.main;



import com.csu.myGirl.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
    }
    public void ClickChat(View source){
    	Intent intent=new Intent(MainActivity.this,com.csu.myGirl.chat.MainActivity.class);
    	startActivity(intent);
    }
    public void ClickWalk(View source){
    	Intent intent=new Intent(MainActivity.this,com.csu.myGirl.walk.BMapApiDemoMain.class);
    	startActivity(intent);
    }
    public void Clicksen(View source){
    	Intent intent=new Intent(MainActivity.this,com.csu.myGirl.sen.MainActivity.class);
    	startActivity(intent);
    }
    public void GetJoke(View source){
    	Intent intent=new Intent(MainActivity.this,com.csu.myGirl.joke.MainActivity.class);
    	startActivity(intent);
    }
}