package com.example.jpushdemo.adapter;

import java.util.List;

import com.example.jpushdemo.MainActivity;
import com.example.jpushdemo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter {

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.View, int)
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		((ViewPager) container).addView(views.get(position), 0);
        if (position == views.size() - 1) {
            ImageView mStartWeiboImageButton = (ImageView) container
                    .findViewById(R.id.iv_start);
            mStartWeiboImageButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    goHome();

                }

            });
        }
        return views.get(position);
	}

	private void goHome() {
		// TODO Auto-generated method stub
		// 跳转
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
	}

	private void setGuided() {
		// TODO Auto-generated method stub
		SharedPreferences preferences = activity.getSharedPreferences(
                SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        // 存入数据
        editor.putBoolean("isFirstIn", false);
        // 提交修改
        editor.commit();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(views.get(position));
	}

	private List<View> views;
    private Activity activity;
    
    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    
    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (views != null) {
            return views.size();
        }
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}

}
