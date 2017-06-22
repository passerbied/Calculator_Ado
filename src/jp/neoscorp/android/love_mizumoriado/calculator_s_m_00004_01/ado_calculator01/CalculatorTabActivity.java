package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01;

import java.lang.reflect.Field;

import jp.primeworks.android.flamingo.Flamingo;
import jp.primeworks.android.flamingo.activity.FlamingoActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.ViewFlipper;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class CalculatorTabActivity extends TabActivity {
	private final static String tag = "choui";
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private int currentView = 0;
	private static int maxTabIndex = 2;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	private ImageView mTabBtn1, mTabBtn2, mTabBtn3;
	private TabHost mTabHost;
	private LinearLayout mAppBgLayout;
	private float statusBarH = 0;
	private Double screenHeight, screenWidth, prop;
	private float density, y1, y2;
	private boolean isTab3;
	private int n_tab;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabmain);
		getDisplayMetrics();
		getStatusBarHeight();
		//Log.i(tag, "OnCreate---tab");
		initUI();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("n_Tab", n_tab);
		//Log.i(tag, "onRestoreInstanceState----Tab");

	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		n_tab = savedInstanceState.getInt("n_Tab");
		//Log.i(tag, "onRestoreInstanceState----Tab");
		initUI();
		// this.finish();
	}

	public void getDisplayMetrics() {

		WindowManager manage = getWindowManager();
		mAppBgLayout = (LinearLayout) findViewById(R.id.appbackground);
		Display display = manage.getDefaultDisplay();
		screenHeight = (Double) (display.getHeight() / 1.0);
		screenWidth = (Double) (display.getWidth() / 1.0);

		if (screenWidth > 710.0 && screenWidth < 730.0) {
			y1 = (float) 375.0;
			y2 = (float) 550.0;
		} else if (screenWidth > 530.0 && screenWidth < 550.0) {
			y1 = (float) 305.0;
			y2 = (float) 410.0;
		} else if (screenWidth > 630.0 && screenWidth < 650.0) {
			y1 = (float) 375.0;
			y2 = (float) 525.0;
		} else {
			y1 = (float) 280.0;
			y2 = (float) 400.0;
		}
	}

	private void getStatusBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarH = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void setBackgroundImg(int nTab) {
		int bgId = 0;
		if (nTab == 0) {
			bgId = R.drawable.cal_bg01;
		} else if (nTab == 1) {
			bgId = R.drawable.cal_bg02;
		} else if (nTab == 2) {
			bgId = R.drawable.cal_bg03;
		}
		Bitmap bgBmp = getImageCroped(bgId);
		mAppBgLayout.setBackgroundDrawable(new BitmapDrawable(bgBmp));

	}

	private Bitmap getImageCroped(int resId) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
		float bmpW = bitmap.getWidth();
		float bmpH = bitmap.getHeight();

		float scaleH = bmpH / bmpW * screenWidth.intValue();
		Bitmap newBmp = Bitmap.createScaledBitmap(bitmap,
				screenWidth.intValue(), (int) scaleH, true);
//		bitmap.recycle();
//		if (bitmap != null) {
//			bitmap = null;
//		}
		// Log.i(tag, "scaleH=" + scaleH);
		// Log.i(tag, "screenHeight=" + screenHeight +"screenWidth="+
		// screenWidth);
		Bitmap retBmp = Bitmap.createBitmap(newBmp, 0, 0,
				screenWidth.intValue(), screenHeight.intValue()
						- (int) statusBarH);

//		newBmp.recycle();
//		if (newBmp != null) {
//			newBmp = null;
//		}
		return retBmp;
	}

	private void initUI() {
		mTabHost = getTabHost();
		mTabHost.addTab(mTabHost.newTabSpec("menu0").setIndicator("tab_menu0")
				.setContent(new Intent(this, CalculatorActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("menu1").setIndicator("tab_menu1")
				.setContent(new Intent(this, BillSplittingActivity.class)));
		mTabHost.addTab(mTabHost.newTabSpec("menu2").setIndicator("tab_menu2")
				.setContent(new Intent(this, DiscountActivity.class)));

		mTabBtn1 = (ImageView) findViewById(R.id.tab_menu1);
		mTabBtn2 = (ImageView) findViewById(R.id.tab_menu2);
		mTabBtn3 = (ImageView) findViewById(R.id.tab_menu3);

		setCurrentTab(n_tab);

		mTabBtn1.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				setCurrentTab(0);
			}
		});
		mTabBtn2.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				setCurrentTab(1);
			}
		});
		mTabBtn3.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				setCurrentTab(2);
			}
		});

		gestureDetector = new GestureDetector(new MyGestureDetector());
	}

	private void setCurrentTab(int tabId) {
		switch (tabId) {
		case 0:
			n_tab = 0;
			isTab3 = false;
			mTabBtn1.setImageResource(R.drawable.cal_ind01_on);
			mTabBtn2.setImageResource(R.drawable.cal_ind02_off);
			mTabBtn3.setImageResource(R.drawable.cal_ind03_off);
			break;
		case 1:
			n_tab = 1;
			isTab3 = false;
			mTabBtn1.setImageResource(R.drawable.cal_ind01_off);
			mTabBtn2.setImageResource(R.drawable.cal_ind02_on);
			mTabBtn3.setImageResource(R.drawable.cal_ind03_off);
			break;
		case 2:
			n_tab = 2;
			isTab3 = true;
			mTabBtn1.setImageResource(R.drawable.cal_ind01_off);
			mTabBtn2.setImageResource(R.drawable.cal_ind02_off);
			mTabBtn3.setImageResource(R.drawable.cal_ind03_on);
			break;
		}
		setBackgroundImg(tabId);
		mTabHost.setCurrentTab(tabId);
	}

	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// Log.i(tag, "Y=" + e1.getY());
			float x_1 = e1.getX();
			float y_1 = e1.getY();
			float x_2 = e2.getX();
			float y_2 = e2.getY();
			try {
				if (Math.abs(y_1 - y_2) > SWIPE_MAX_OFF_PATH
						|| (isTab3 && y1 < y_1 && y2 > y_1))
					return false;
				if (x_1 - x_2 > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					if (currentView == maxTabIndex) {
						currentView = 0;
					} else {
						currentView++;
					}
					setBackgroundImg(currentView);
					setCurrentTab(currentView);
				} else if (x_2 - x_1 > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					if (currentView == 0) {
						currentView = maxTabIndex;
					} else {
						currentView--;
					}
					setBackgroundImg(currentView);
					setCurrentTab(currentView);
				}
			} catch (Exception e) {
			}
			return true;
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(1);
		}
		return super.dispatchTouchEvent(event);

	}

}