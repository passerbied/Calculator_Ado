package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01;

import java.util.Timer;
import java.util.TimerTask;

import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.customize.RepeatingImageButton;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.operation.OperationForDiscount;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.Arith;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.ConScience;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.dipAdapter;
import jp.primeworks.android.flamingo.activity.AboutActivity;
import jp.primeworks.android.flamingo.activity.FlamingoFragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.WindowManager.BadTokenException;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class DiscountActivity extends FlamingoFragmentActivity implements
		OnTouchListener {
	private static String tag = "choui";
	private Context mContext;
	private RepeatingImageButton btn_min, btn_max;
	private SeekBar seekbar1, seekbar2, seekbar3;
	private TextView edit_genne, txt_1, txt_2, txt_3, txt_4, txt_5, txt_6,
			txt_7, edit_result1, edit_result2, txt_per;
	private LinearLayout linearlayout_1, rabbit, bellow_Half, ly_result01;
	private Button txt_Percent;
	private View layout, popView;
	private PopupWindow popup, popupWindow;
	private int progressTemp = 50;
	private int progressTemp2;
	private int percent = 50;
	private String str_Genne = "0";
	private String result1 = "0";
	private String result2 = "0";
	private boolean flag = true;
	private Timer timer;
	private String memory = "0";
	private Typeface btry;
	private LinearLayout ly_1, ly_2, LinearLayout_01, ly01, ly02, ly03, ly04,
			linearlayout_4, linearlayout_5, linearLayout1_1, linearlayout_2,
			linearlayout_3, ly_result02, ly_btnMax, ly_btnMin;
	private RelativeLayout relativeLayout1;
	private Double screenHeight, screenWidth, prop;
	private int viewflag, screenflag;
	private DisplayMetrics dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.discount);
		ReadSharedPreferences();
		// str_Genne = "0";
		btry = Typeface.createFromAsset(getAssets(),
				"font/cal_A-OTF-ShinMGoPro-DeBold.otf");
		mContext = DiscountActivity.this;
		WindowManager manage = getWindowManager();
		Display display = manage.getDefaultDisplay();
		screenHeight = (Double) (display.getHeight() / 1.0);
		screenWidth = (Double) (display.getWidth() / 1.0);
		prop = Arith.div(screenHeight, screenWidth, 2);
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		adaptLayout();
		initUI();
		// Log.i(tag, "OnCreate---Dis");
		flag = true;
	}

	protected void adaptLayout() {
		// String maker = android.os.Build.MANUFACTURER;
		// Log.i(tag, "maker=" + maker);
		popView = View.inflate(DiscountActivity.this, R.layout.popupwindow,
				null);
		LinearLayout_01 = (LinearLayout) popView
				.findViewById(R.id.LinearLayout_01);
		relativeLayout1 = (RelativeLayout) popView
				.findViewById(R.id.relativeLayout1);
		ly01 = (LinearLayout) popView.findViewById(R.id.ly01);
		ly02 = (LinearLayout) popView.findViewById(R.id.ly02);
		ly03 = (LinearLayout) popView.findViewById(R.id.ly03);
		ly04 = (LinearLayout) popView.findViewById(R.id.ly04);

		txt_7 = (TextView) findViewById(R.id.txt_7);

		ly_result01 = (LinearLayout) findViewById(R.id.ly_result01);
		ly_result02 = (LinearLayout) findViewById(R.id.ly_result02);

		linearlayout_1 = (LinearLayout) this.findViewById(R.id.linearlayout_1);
		linearLayout1_1 = (LinearLayout) this
				.findViewById(R.id.linearLayout1_1);

		linearlayout_2 = (LinearLayout) this.findViewById(R.id.linearLayout_2);
		linearlayout_3 = (LinearLayout) this.findViewById(R.id.linearLayout_3);

		linearlayout_4 = (LinearLayout) this.findViewById(R.id.linearLayout_4);
		linearlayout_5 = (LinearLayout) this.findViewById(R.id.linearLayout_5);

		ly_btnMin = (LinearLayout) this.findViewById(R.id.ly_btnMin);
		ly_btnMax = (LinearLayout) this.findViewById(R.id.ly_btnMax);

		edit_genne = (TextView) findViewById(R.id.edt_genne);
		edit_result1 = (TextView) findViewById(R.id.edt_result01);
		edit_result2 = (TextView) findViewById(R.id.edt_result02);

		seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
		seekbar3 = (SeekBar) findViewById(R.id.seekBar3);

		LinearLayout.LayoutParams params_0 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params_1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params1_1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_3 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_4 = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params4_1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_5 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params5_1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		RelativeLayout.LayoutParams params_SeekBtn = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		RelativeLayout.LayoutParams params_pop1 = new RelativeLayout.LayoutParams(
				dipAdapter.dip2px(this, 260), LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_pop2 = new LinearLayout.LayoutParams(
				dipAdapter.dip2px(this, 265), LayoutParams.WRAP_CONTENT);

		if (screenWidth > 710.0 && screenWidth < 730.0) {
			screenflag = 3;
			// params2.leftMargin = 320;
			// params3.topMargin = dipAdapter.dip2px(this, 10);
			seekbar1.setVisibility(View.GONE);
			seekbar2.setVisibility(View.GONE);
			seekbar3.setVisibility(View.VISIBLE);

			params_0.leftMargin = dipAdapter.dip2px(this, 50);
			params_0.topMargin = dipAdapter.dip2px(this, 22);
			linearlayout_1.setLayoutParams(params_0);

			params1_1.rightMargin = dipAdapter.dip2px(this, 10);
			linearLayout1_1.setLayoutParams(params1_1);

			params_2.leftMargin = dipAdapter.dip2px(this, 50);
			params_2.topMargin = dipAdapter.dip2px(this, 25);
			linearlayout_2.setLayoutParams(params_2);

			params_3.topMargin = dipAdapter.dip2px(this, 60);
			params_3.rightMargin = dipAdapter.dip2px(this, 5);
			params_3.leftMargin = dipAdapter.dip2px(this, 5);
			linearlayout_3.setLayoutParams(params_3);

			params_4.leftMargin = dipAdapter.dip2px(this, 160);
			params_4.topMargin = dipAdapter.dip2px(this, 50);
			linearlayout_4.setLayoutParams(params_4);

			params4_1.rightMargin = dipAdapter.dip2px(this, 20);
			params4_1.topMargin = dipAdapter.dip2px(this, 20);

			ly_result02.setLayoutParams(params4_1);

			params_5.leftMargin = dipAdapter.dip2px(this, 60);
			params_5.topMargin = dipAdapter.dip2px(this, 40);
			linearlayout_5.setLayoutParams(params_5);

			params5_1.rightMargin = dipAdapter.dip2px(this, 140);
			params5_1.topMargin = dipAdapter.dip2px(this, 20);
			ly_result01.setLayoutParams(params5_1);

			params_SeekBtn.height = dipAdapter.dip2px(this, 65);

			ly_btnMax.setLayoutParams(params_SeekBtn);
			ly_btnMin.setLayoutParams(params_SeekBtn);

			params_pop1.width = dipAdapter.dip2px(this, 280);
			params_pop1.height = dipAdapter.dip2px(this, 255);
			params_pop1.topMargin = dipAdapter.dip2px(this, 16);
			params_pop2.topMargin = dipAdapter.dip2px(this, 30);
			params_pop2.width = dipAdapter.dip2px(this, 300);
			params_pop2.height = dipAdapter.dip2px(this, 255);

			LinearLayout_01.setLayoutParams(params_pop1);
			LinearLayout_01.setPadding(dipAdapter.dip2px(this, 20),
					dipAdapter.dip2px(this, 5), dipAdapter.dip2px(this, 11),
					dipAdapter.dip2px(this, 0));
			relativeLayout1.setLayoutParams(params_pop2);

		} else if (screenWidth > 630.0 && screenWidth < 650.0) {
			screenflag = 1;

			seekbar1.setVisibility(View.VISIBLE);
			seekbar2.setVisibility(View.GONE);
			seekbar3.setVisibility(View.GONE);
			
			params_0.leftMargin = dipAdapter.dip2px(this, 40);
			params_0.topMargin = dipAdapter.dip2px(this, 16);
			linearlayout_1.setLayoutParams(params_0);

			params1_1.rightMargin = dipAdapter.dip2px(this, 5);
			linearLayout1_1.setLayoutParams(params1_1);

			params_2.leftMargin = dipAdapter.dip2px(this, 40);
			params_2.topMargin = dipAdapter.dip2px(this, 15);

			linearlayout_2.setLayoutParams(params_2);

			params_pop1.width = dipAdapter.dip2px(this, 245);
			params_pop1.height = dipAdapter.dip2px(this, 225);
			params_pop1.topMargin = dipAdapter.dip2px(this, 14);
			params_pop2.topMargin = dipAdapter.dip2px(this, 30);
			params_pop2.width = dipAdapter.dip2px(this, 265);
			params_pop2.height = dipAdapter.dip2px(this, 225);

			LinearLayout_01.setLayoutParams(params_pop1);
			LinearLayout_01.setPadding(dipAdapter.dip2px(this, 16),
					dipAdapter.dip2px(this, 5), dipAdapter.dip2px(this, 7),
					dipAdapter.dip2px(this, 0));
			relativeLayout1.setLayoutParams(params_pop2);

		} else if (screenWidth > 530.0 && screenWidth < 550.0) {
			screenflag = 2;
			seekbar1.setVisibility(View.GONE);
			seekbar2.setVisibility(View.VISIBLE);
			seekbar3.setVisibility(View.GONE);

			params_0.leftMargin = dipAdapter.dip2px(this, 50);
			params_0.topMargin = dipAdapter.dip2px(this, 22);
			linearlayout_1.setLayoutParams(params_0);

			params1_1.rightMargin = dipAdapter.dip2px(this, 10);
			linearLayout1_1.setLayoutParams(params1_1);

			params_2.leftMargin = dipAdapter.dip2px(this, 50);
			params_2.topMargin = dipAdapter.dip2px(this, 25);

			linearlayout_2.setLayoutParams(params_2);

			params_3.topMargin = dipAdapter.dip2px(this, 60);
			params_3.rightMargin = dipAdapter.dip2px(this, 5);
			params_3.leftMargin = dipAdapter.dip2px(this, 5);
			linearlayout_3.setLayoutParams(params_3);

			params_4.leftMargin = dipAdapter.dip2px(this, 160);
			params_4.topMargin = dipAdapter.dip2px(this, 50);
			linearlayout_4.setLayoutParams(params_4);

			params4_1.rightMargin = dipAdapter.dip2px(this, 20);
			params4_1.topMargin = dipAdapter.dip2px(this, 20);

			ly_result02.setLayoutParams(params4_1);

			params_5.leftMargin = dipAdapter.dip2px(this, 60);
			params_5.topMargin = dipAdapter.dip2px(this, 40);
			linearlayout_5.setLayoutParams(params_5);

			params5_1.rightMargin = dipAdapter.dip2px(this, 140);
			params5_1.topMargin = dipAdapter.dip2px(this, 20);
			ly_result01.setLayoutParams(params5_1);

			params_SeekBtn.height = dipAdapter.dip2px(this, 65);

			ly_btnMax.setLayoutParams(params_SeekBtn);
			ly_btnMin.setLayoutParams(params_SeekBtn);

			params_pop1.width = dipAdapter.dip2px(this, 285);
			params_pop1.height = dipAdapter.dip2px(this, 255);
			params_pop1.topMargin = dipAdapter.dip2px(this, 16);
			params_pop2.topMargin = dipAdapter.dip2px(this, 30);
			params_pop2.width = dipAdapter.dip2px(this, 305);
			params_pop2.height = dipAdapter.dip2px(this, 255);

			LinearLayout_01.setLayoutParams(params_pop1);
			LinearLayout_01.setPadding(dipAdapter.dip2px(this, 20),
					dipAdapter.dip2px(this, 5), dipAdapter.dip2px(this, 11),
					dipAdapter.dip2px(this, 0));
			relativeLayout1.setLayoutParams(params_pop2);
		} else {
			screenflag = 1;
			seekbar1.setVisibility(View.VISIBLE);
			seekbar2.setVisibility(View.GONE);
			seekbar3.setVisibility(View.GONE);

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Log.i(tag, "onResume---Dis");
		ReadSharedPreferences();
		edit_genne.setText(str_Genne);
		flag = true;
		timer = new Timer();
		timer.schedule(new initPopupWindow(), 10);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Log.i(tag, "onPause---Dis");
		timer.cancel();
		flag = false;
		if (popup != null && popup.isShowing()) {
			popup.dismiss();
			popup = null;
		}
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			popupWindow = null;
		}
		WriteSharedPreferences();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Log.i(tag, "onRestoreInstanceState----Dis");
		// this.finish();
	}

	@Override
	protected void onDestroy() {
		// Log.i(tag, "onDestroy---Dis");
		super.onDestroy();
		SharedPreferences SP_Waribiki = getSharedPreferences("waribiki", 0);
		Editor editor = SP_Waribiki.edit();
		editor.putString("total", "0");
		editor.commit();
		SharedPreferences SP_Cal = getSharedPreferences("cal", 0);
		Editor editor1 = SP_Cal.edit();
		editor1.clear();
		editor1.commit();
		SharedPreferences SP_Warikann = getSharedPreferences("warikann", 0);
		Editor editor2 = SP_Warikann.edit();
		editor2.clear();
		editor2.commit();
	}

	private void ReadSharedPreferences() {
		SharedPreferences SP_Waribiki = getSharedPreferences("waribiki", 0);
		percent = SP_Waribiki.getInt("percent", 50);

		SharedPreferences SP_Cal = getSharedPreferences("cal", 0);
		Boolean flg = SP_Cal.getBoolean("flg2", false);
		if (flg) {
			str_Genne = SP_Cal.getString("total", "0");
		} else {
			str_Genne = SP_Waribiki.getString("total", "0");
		}
		memory = str_Genne;
	}

	private void WriteSharedPreferences() {

		SharedPreferences SP_Waribiki = getSharedPreferences("waribiki", 0);
		Editor editor = SP_Waribiki.edit();
		editor.putString("total", edit_genne.getText().toString());
		if (screenflag == 1) {
			editor.putInt("percent", seekbar1.getProgress());
		} else if (screenflag == 2) {
			editor.putInt("percent", seekbar2.getProgress());
		} else {
			editor.putInt("percent", seekbar3.getProgress());
		}

		editor.commit();

		SharedPreferences SP_Cal = getSharedPreferences("cal", 0);
		Editor editor1 = SP_Cal.edit();
		editor1.putBoolean("flg2", false);
		editor1.commit();

	}

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				showPercentPop();
				break;
			}
		};
	};

	private class initPopupWindow extends TimerTask {
		@Override
		public void run() {

			Message message = new Message();
			message.what = 1;
			mHandler.sendMessage(message);

		}
	}

	private TextWatcher watcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String total = edit_genne.getText().toString();
			total = total.replace("'", "");
			int percent = 0;
			if (screenflag == 1) {
				percent = seekbar1.getProgress();
			} else if (screenflag == 2) {
				percent = seekbar2.getProgress();
			} else {
				percent = seekbar3.getProgress();
			}

			getResult(total, percent);
			setResultInView();
		}

	};

	public void initUI() {
		layout = View
				.inflate(DiscountActivity.this, R.layout.percent_pop, null);

		linearlayout_1 = (LinearLayout) findViewById(R.id.linearlayout_1);
		txt_Percent = (Button) layout.findViewById(R.id.txt_Percent);
		txt_Percent.setTypeface(btry);
		txt_per = (TextView) layout.findViewById(R.id.txt_2);
		txt_per.setTypeface(btry);
		// edit_genne = (TextView) findViewById(R.id.edt_genne);
		edit_genne.setTypeface(btry);
		edit_genne.setText(str_Genne);
		edit_genne.addTextChangedListener(watcher);
		edit_genne.setOnTouchListener((OnTouchListener) this);

		// edit_result1 = (TextView) findViewById(R.id.edt_result01);
		edit_result1.setText(result1);
		edit_result1.setTypeface(btry);
		// edit_result2 = (TextView) findViewById(R.id.edt_result02);
		edit_result2.setText(result2);
		edit_result2.setTypeface(btry);

		txt_1 = (TextView) findViewById(R.id.txt_1);
		txt_1.setTypeface(btry);
		txt_2 = (TextView) findViewById(R.id.txt_2);
		txt_2.setTypeface(btry);
		txt_3 = (TextView) findViewById(R.id.txt_3);
		txt_3.setTypeface(btry);
		txt_4 = (TextView) findViewById(R.id.txt_4);
		txt_4.setTypeface(btry);
		txt_5 = (TextView) findViewById(R.id.txt_5);
		txt_5.setTypeface(btry);
		txt_6 = (TextView) findViewById(R.id.txt_6);
		txt_6.setTypeface(btry);
		// txt_7 = (TextView) findViewById(R.id.txt_7);
		txt_7.setTypeface(btry);

		btn_min = (RepeatingImageButton) findViewById(R.id.btn_min);
		btn_min.setRepeatListener(maxListener, 500);
		btn_min.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int n = 0;
				String total;
				int percent = 0;
				if (screenflag == 1) {
					n = seekbar1.getProgress();
					n = n - 1;
					progressTemp = n;
					seekbar1.setProgress(progressTemp);
					total = edit_genne.getText().toString();
					percent = seekbar1.getProgress();
				} else if (screenflag == 2) {
					n = seekbar2.getProgress();
					n = n - 1;
					progressTemp = n;
					seekbar2.setProgress(progressTemp);
					total = edit_genne.getText().toString();
					percent = seekbar2.getProgress();
				} else {
					n = seekbar3.getProgress();
					n = n - 1;
					progressTemp = n;
					seekbar3.setProgress(progressTemp);
					total = edit_genne.getText().toString();
					percent = seekbar3.getProgress();
				}

				getResult(total, percent);
				setResultInView();
				showPercentPop();
			}

		});
		btn_max = (RepeatingImageButton) findViewById(R.id.btn_max);
		btn_max.setRepeatListener(minListener, 500);
		btn_max.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int n = 0;
				String total;
				int percent = 0;
				if (screenflag == 1) {
					n = seekbar1.getProgress();
					n = n + 1;
					progressTemp = n;
					seekbar1.setProgress(progressTemp);
					total = edit_genne.getText().toString();
					percent = seekbar1.getProgress();
				} else if (screenflag == 2) {
					n = seekbar2.getProgress();
					n = n + 1;
					progressTemp = n;
					seekbar2.setProgress(progressTemp);
					total = edit_genne.getText().toString();
					percent = seekbar2.getProgress();
				} else {
					n = seekbar3.getProgress();
					n = n + 1;
					progressTemp = n;
					seekbar3.setProgress(progressTemp);
					total = edit_genne.getText().toString();
					percent = seekbar3.getProgress();
				}

				getResult(total, percent);
				setResultInView();
				showPercentPop();
			}

		});

		if (screenflag == 1) {
			seekbar1.setProgress(percent);

			seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onProgressChanged(SeekBar arg0, int arg1,
						boolean arg2) {
					String total = edit_genne.getText().toString();
					int percent = seekbar1.getProgress();
					getResult(total, percent);
					setResultInView();
					if (flag) {
						showPercentPop();
					}
				}

				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					progressTemp2 = seekbar1.getProgress();
				}

				@Override
				public void onStopTrackingTouch(SeekBar arg0) {

					setSeekBar();

				}

			});
		} else if (screenflag == 2) {
			seekbar2.setProgress(percent);

			seekbar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onProgressChanged(SeekBar arg0, int arg1,
						boolean arg2) {
					String total = edit_genne.getText().toString();
					int percent = seekbar2.getProgress();
					getResult(total, percent);
					setResultInView();
					if (flag) {
						showPercentPop();
					}
				}

				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					progressTemp2 = seekbar2.getProgress();
				}

				@Override
				public void onStopTrackingTouch(SeekBar arg0) {

					setSeekBar();

				}

			});
		} else {
			seekbar3.setProgress(percent);

			seekbar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				@Override
				public void onProgressChanged(SeekBar arg0, int arg1,
						boolean arg2) {
					String total = edit_genne.getText().toString();
					int percent = seekbar3.getProgress();
					getResult(total, percent);
					setResultInView();
					if (flag) {
						showPercentPop();
					}
				}

				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					progressTemp2 = seekbar3.getProgress();
				}

				@Override
				public void onStopTrackingTouch(SeekBar arg0) {

					setSeekBar();

				}

			});
		}

	}

	private RepeatingImageButton.RepeatListener minListener = new RepeatingImageButton.RepeatListener() {
		public void onRepeat(View v, long howlong, int repcnt) {
			scanForward(repcnt, howlong);
		}

	};

	private RepeatingImageButton.RepeatListener maxListener = new RepeatingImageButton.RepeatListener() {
		public void onRepeat(View v, long howlong, int repcnt) {
			scanBackward(repcnt, howlong);
		}

	};

	private void scanBackward(int repcnt, long howlong) {
		int n = 0;
		String total;
		int percent = 0;
		if (screenflag == 1) {
			n = seekbar1.getProgress();
			n = n - 1;
			progressTemp = n;
			seekbar1.setProgress(progressTemp);
			total = edit_genne.getText().toString();
			percent = seekbar1.getProgress();
		} else if (screenflag == 2) {
			n = seekbar2.getProgress();
			n = n - 1;
			progressTemp = n;
			seekbar2.setProgress(progressTemp);
			total = edit_genne.getText().toString();
			percent = seekbar2.getProgress();
		} else {
			n = seekbar3.getProgress();
			n = n - 1;
			progressTemp = n;
			seekbar3.setProgress(progressTemp);
			total = edit_genne.getText().toString();
			percent = seekbar3.getProgress();
		}

		getResult(total, percent);
		setResultInView();
		showPercentPop();
	}

	private void scanForward(int repcnt, long howlong) {
		int n = 0;
		String total;
		int percent = 0;
		if (screenflag == 1) {
			n = seekbar1.getProgress();
			n = n + 1;
			progressTemp = n;
			seekbar1.setProgress(progressTemp);
			total = edit_genne.getText().toString();
			percent = seekbar1.getProgress();
		} else if (screenflag == 2) {
			n = seekbar2.getProgress();
			n = n + 1;
			progressTemp = n;
			seekbar2.setProgress(progressTemp);
			total = edit_genne.getText().toString();
			percent = seekbar2.getProgress();
		} else {
			n = seekbar3.getProgress();
			n = n + 1;
			progressTemp = n;
			seekbar3.setProgress(progressTemp);
			total = edit_genne.getText().toString();
			percent = seekbar3.getProgress();
		}

		getResult(total, percent);
		setResultInView();
		showPercentPop();

	}

	private void showPercentPop() {
		if (layout != null && popup == null && flag) {

			popup = new PopupWindow(layout, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		try {
			if (!this.isFinishing()
					&& (popupWindow == null || !popupWindow.isShowing())
					&& flag) {
				int percent = 0;
				if (screenflag == 1) {
					popup.showAtLocation(findViewById(R.id.seekBar1),
							Gravity.CENTER, 0, 0);
					percent = seekbar1.getProgress();
				} else if (screenflag == 2) {
					popup.showAtLocation(findViewById(R.id.seekBar2),
							Gravity.CENTER, 0, 0);
					percent = seekbar2.getProgress();
				} else {
					popup.showAtLocation(findViewById(R.id.seekBar3),
							Gravity.CENTER, 0, 0);
					percent = seekbar3.getProgress();
				}
				int offset_x = 0;
				int offset_y = 0;
				int width = 0;
				int height = 0;
				if (screenHeight > 1100.0) {

					offset_x = percent * 5;
					offset_y = 0 - dipAdapter.dip2px(this, 124);
					width = -1;
					height = -1;
				} else if (screenHeight > 810.0 && screenHeight < 865.0) {
					offset_x = (int) (percent * 3.1);
					offset_y = -175;
					width = -1;
					height = -1;
				} else if (screenHeight > 860.0 && screenHeight < 970.0) {
					if (screenWidth > 530.0 && screenWidth < 550.0) {

						offset_x = (int) (percent * 3.7);
						offset_y = -185;
					} else {
						offset_x = (int) (percent * 4.2);
						offset_y = -235;
					}
					width = -1;
					height = -1;
				} else {
					offset_x = (int) (percent * 3.2);
					offset_y = -177;
					width = -1;
					height = -1;
				}
				txt_Percent.setText(percent + "");
				if (screenflag == 1) {
					popup.update(findViewById(R.id.seekBar1), offset_x,
							offset_y, width, height);
				} else if (screenflag == 2) {
					popup.update(findViewById(R.id.seekBar2), offset_x,
							offset_y, width, height);
				} else {
					popup.update(findViewById(R.id.seekBar3), offset_x,
							offset_y, width, height);
				}
			}
		} catch (BadTokenException e) {
		}

	}

	public int getPixelsX(int x) {
		return dm.widthPixels * x / 480;
	}

	public int getPixelsY(int y) {
		return dm.heightPixels * y / 854;
	}

	private void getResult(String total, int percent) {

		total = total.replace("'", "");
		OperationForDiscount op = new OperationForDiscount(total, percent);
		result1 = op.getResult1();
		result2 = op.getResult2();

	}

	private void setResultInView() {
		edit_result1.setText(result1);
		edit_result2.setText(result2);
	}

	private void setSeekBar() {
		int progress = 0;
		if (screenflag == 1) {
			progress = seekbar1.getProgress();
		} else if (screenflag == 2) {
			progress = seekbar2.getProgress();
		} else {
			progress = seekbar3.getProgress();
		}
		if (4 < progress - progressTemp2 && progress - progressTemp2 <= 14) {
			if (progressTemp2 <= 90) {
				progressTemp = progressTemp + 10;
			} else {
				progressTemp = 100;
			}

		} else if (14 < progress - progressTemp2
				&& progress - progressTemp2 <= 24) {
			if (progressTemp <= 80) {
				progressTemp = progressTemp + 20;
			} else {
				progressTemp = 100;
			}
		} else if (24 < progress - progressTemp2
				&& progress - progressTemp2 <= 34) {
			if (progressTemp <= 70) {
				progressTemp = progressTemp + 30;
			} else {
				progressTemp = 100;
			}
		} else if (34 < progress - progressTemp2
				&& progress - progressTemp2 <= 44) {
			if (progressTemp <= 60) {
				progressTemp = progressTemp + 40;
			} else {
				progressTemp = 100;
			}
		} else if (44 < progress - progressTemp2
				&& progress - progressTemp2 <= 54) {
			if (progressTemp <= 50) {
				progressTemp = progressTemp + 50;
			} else {
				progressTemp = 100;
			}
		} else if (54 < progress - progressTemp2
				&& progress - progressTemp2 <= 64) {
			if (progressTemp <= 40) {
				progressTemp = progressTemp + 60;
			} else {
				progressTemp = 100;
			}
		} else if (64 < progress - progressTemp2
				&& progress - progressTemp2 <= 74) {
			if (progressTemp <= 30) {
				progressTemp = progressTemp + 70;
			} else {
				progressTemp = 100;
			}
		} else if (74 < progress - progressTemp2
				&& progress - progressTemp2 <= 84) {
			if (progressTemp <= 20) {
				progressTemp = progressTemp + 80;
			} else {
				progressTemp = 100;
			}
		} else if (84 < progress - progressTemp2
				&& progress - progressTemp2 <= 94) {
			if (progressTemp <= 10) {
				progressTemp = progressTemp + 90;
			} else {
				progressTemp = 100;
			}
		} else if (94 < progress - progressTemp2
				&& progress - progressTemp2 <= 100) {
			progressTemp = 100;
		}

		else if (5 < progressTemp2 - progress && progressTemp2 - progress <= 15) {
			if (progressTemp >= 10) {
				progressTemp = progressTemp - 10;
			} else {
				progressTemp = 0;
			}
		} else if (15 < progressTemp2 - progress
				&& progressTemp2 - progress <= 25) {
			if (progressTemp >= 20) {
				progressTemp = progressTemp - 20;
			} else {
				progressTemp = 0;
			}
		} else if (25 < progressTemp2 - progress
				&& progressTemp2 - progress <= 35) {
			if (progressTemp >= 30) {
				progressTemp = progressTemp - 30;
			} else {
				progressTemp = 0;
			}
		} else if (35 < progressTemp2 - progress
				&& progressTemp2 - progress <= 55) {
			if (progressTemp >= 30) {
				progressTemp = progressTemp - 30;
			} else {
				progressTemp = 0;
			}
		} else if (55 < progressTemp2 - progress
				&& progressTemp2 - progress <= 55) {
			if (progressTemp >= 40) {
				progressTemp = progressTemp - 40;
			} else {
				progressTemp = 0;
			}
		} else if (55 < progressTemp2 - progress
				&& progressTemp2 - progress <= 65) {
			if (progressTemp >= 50) {
				progressTemp = progressTemp - 50;
			} else {
				progressTemp = 0;
			}
		} else if (65 < progressTemp2 - progress
				&& progressTemp2 - progress <= 75) {
			if (progressTemp >= 60) {
				progressTemp = progressTemp - 60;
			} else {
				progressTemp = 0;
			}
		} else if (75 < progressTemp2 - progress
				&& progressTemp2 - progress <= 85) {
			if (progressTemp >= 70) {
				progressTemp = progressTemp - 70;
			} else {
				progressTemp = 0;
			}
		} else if (85 < progressTemp2 - progress
				&& progressTemp2 - progress <= 95) {
			if (progressTemp >= 80) {
				progressTemp = progressTemp - 80;
			} else {
				progressTemp = 0;
			}
		} else if (95 < progressTemp2 - progress
				&& progressTemp2 - progress <= 100) {
			progressTemp = 0;
		}
		progressTemp = (progressTemp % 10) >= 5 ? (progressTemp - progressTemp
				% 10 + 10) : (progressTemp - progressTemp % 10);
		if (screenflag == 1) {
			seekbar1.setProgress(progressTemp);
		} else if (screenflag == 2) {
			seekbar2.setProgress(progressTemp);
		} else {
			seekbar3.setProgress(progressTemp);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (viewflag == R.id.txt_Percent) {
			progressTemp = Integer.parseInt(txt_Percent.getText().toString());
			if (screenflag == 1) {
				seekbar1.setProgress(progressTemp);
			} else if (screenflag == 2) {
				seekbar2.setProgress(progressTemp);
			} else {
				seekbar3.setProgress(progressTemp);
			}
			showPercentPop();
		}
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (v.getId() == R.id.edt_genne && event.getAction() != 1) {
			viewflag = R.id.edt_genne;
			if (popupWindow == null) {
				popupWindow = new PopupWindow(popView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			}
			popupWindow.setFocusable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
			showPopWindow();

		}
		return true;
	}

	public void showPopWindow() {
		if (popupWindow != null) {
			if (screenWidth > 630.0 && screenWidth < 650.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_genne),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(360));
			} else if (screenWidth > 710.0 && screenWidth < 730.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_genne),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(299));
			} else if (screenWidth > 530.0 && screenWidth < 550.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_genne),
						Gravity.NO_GRAVITY, getPixelsX(50), getPixelsY(301));
			} else if (screenHeight > 790.0 && screenHeight < 810.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_genne),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(355));
			} else {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_genne),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(332));
			}
		}
	}

	public void onClick_inputPercent(View v) {
		viewflag = R.id.txt_Percent;
		if (popupWindow == null) {
			popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
		showPopWindow();

	}

	public boolean checkLength(int flag) {
		if (flag == R.id.edt_genne) {
			String temp = edit_genne.getText().toString().replace("'", "");
			temp = temp.replace(".", "");
			temp = temp.replace("-", "");
			if (temp.length() < 10) {
				return true;
			}
			return false;
		} else {
			String temp = txt_Percent.getText().toString();
			if (temp.length() < 3) {
				return true;
			}
			return false;
		}

	}

	public void onClickListener_Input(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.button_0:
			if (checkLength(viewflag)) {
				inputNum("0", viewflag);
			}
			break;
		case R.id.button_1:
			if (checkLength(viewflag)) {
				inputNum("1", viewflag);
			}
			break;
		case R.id.button_2:
			if (checkLength(viewflag)) {
				inputNum("2", viewflag);
			}
			break;
		case R.id.button_3:
			if (checkLength(viewflag)) {
				inputNum("3", viewflag);
			}
			break;
		case R.id.button_4:
			if (checkLength(viewflag)) {
				inputNum("4", viewflag);
			}
			break;
		case R.id.button_5:
			if (checkLength(viewflag)) {
				inputNum("5", viewflag);
			}
			break;
		case R.id.button_6:
			if (checkLength(viewflag)) {
				inputNum("6", viewflag);
			}
			break;
		case R.id.button_7:
			if (checkLength(viewflag)) {
				inputNum("7", viewflag);
			}
			break;
		case R.id.button_8:
			if (checkLength(viewflag)) {
				inputNum("8", viewflag);
			}
			break;
		case R.id.button_9:
			if (checkLength(viewflag)) {
				inputNum("9", viewflag);
			}
			break;
		case R.id.button_close:
			popupWindow.dismiss();
			memory = edit_genne.getText().toString();

			break;
		case R.id.button_ac:
			delete(2, viewflag);
			break;
		case R.id.button_delete:
			delete(1, viewflag);
			break;

		}
	}

	private void delete(int flg, int viewflag) {
		if (viewflag == R.id.edt_genne) {
			String str = edit_genne.getText().toString().replace("'", "");
			if (str.length() > 1) {
				str = str.substring(0, str.length() - 1);
			} else {
				str = "0";
			}
			if (flg == 1) {
				edit_genne.setText(ConScience.addKanma(str));
			} else {
				edit_genne.setText("0");
			}
		} else {
			String str = txt_Percent.getText().toString();
			if (str.length() > 1) {
				str = str.substring(0, str.length() - 1);
			} else {
				str = "0";
			}
			if (flg == 1) {
				txt_Percent.setText(str);
			} else {
				txt_Percent.setText("0");
			}
		}

	}

	private void inputNum(String input, int flag) {
		String str = edit_genne.getText().toString();
		String str_per = txt_Percent.getText().toString();
		if (flag == R.id.edt_genne) {
			if ("0".equals(str)) {
				edit_genne.setText(ConScience.addKanma(input));
			} else {
				str += input;
				edit_genne.setText(ConScience.addKanma(str));
			}
		} else {
			if ("0".equals(str_per)) {
				txt_Percent.setText(input);
			} else {
				if (Integer.parseInt(str_per) < 10) {
					str_per += input;

				} else if (Integer.parseInt(str_per) == 10) {
					if ("0".equals(input)) {
						str_per += input;
					}
				}
				txt_Percent.setText(str_per);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);

	}

}
