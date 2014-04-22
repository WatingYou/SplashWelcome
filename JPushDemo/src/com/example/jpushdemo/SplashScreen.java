package com.example.jpushdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashScreen extends Activity {

	// Splash screen timer
	boolean isFirstIn = false;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    // �ӳ�3��
    private static final long SPLASH_DELAY_MILLIS = 3000;

    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    
    /**
     * Handler:��ת����ͬ����
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case GO_HOME:
                goHome();
                break;
            case GO_GUIDE:
                goGuide();
                break;
            }
            super.handleMessage(msg);
        }
    };
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		init();
	}
	
	private void init() {
        // ��ȡSharedPreferences����Ҫ������
        // ʹ��SharedPreferences����¼�����ʹ�ô���
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬��true��ΪĬ��ֵ
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // �жϳ�����ڼ������У�����ǵ�һ����������ת���������棬������ת��������
        if (!isFirstIn) {
            // ʹ��Handler��postDelayed������3���ִ����ת��MainActivity
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }
	
	private void goHome() {
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        SplashScreen.this.startActivity(intent);
        SplashScreen.this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(SplashScreen.this, GuidActivity.class);
        SplashScreen.this.startActivity(intent);
        SplashScreen.this.finish();
    }
}
