package com.csu.myGirl.walk;


import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;


public class DemoApplication extends Application {
	
    private static DemoApplication mInstance = null;
    BMapManager mBMapManager = null;

	@Override
    public void onCreate() {
	    super.onCreate();
		mInstance = this;
		initEngineManager(this);
	}
	
	public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(new MyGeneralListener())) {
            Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                    "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
	}
	
	public static DemoApplication getInstance() {
		return mInstance;
	}
	
	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
    static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetPermissionState(int iError) {
        	//非零值表示key验证未通过
            if (iError != 0) {
                //授权Key错误：
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                        "请在AndoridManifest.xml中输入正确的授权Key,并检查您的网络连接是否正常！error: "+iError, Toast.LENGTH_LONG).show();
            }
            else{
            	Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                        "key认证成功", Toast.LENGTH_LONG).show();
            }
        }
    }
}