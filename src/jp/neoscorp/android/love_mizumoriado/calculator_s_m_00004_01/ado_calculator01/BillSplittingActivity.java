package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01;

import java.util.Timer;

import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.customize.MyToast;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.operation.OperationForBillSplitting;
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
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class BillSplittingActivity extends FlamingoFragmentActivity implements
		OnTouchListener {
	private static String tag = "choui";
	private Context mContext;
	private TextView edt_kinngaku, edt_ninnzuu, edt_ooku_hito,
			edt_ookukinngaku, edt_result1, edt_result2, edt_result3,
			edt_result4, txt_1, txt_2, txt_3, txt_4, txt_5, txt_6, txt_7,
			txt_8, txt_9, txt_10, txt_11, txt_12, txt_13, txt_14, txt_15,
			txt_16;
	private String str_total_money = "0";
	private String str_num = "0";
	private String str_high_num = "0";
	private String str_high_money = "0";
	private String result1 = "0";
	private String result2 = "0";
	private String result3 = "0";
	private String result4 = "0";
	private String memory1;
	private View popView;
	private PopupWindow popupWindow;
	private TextView editText;
	private Typeface btry;
	private LinearLayout ly_1, ly_2, LinearLayout_01;
	private Double screenHeight, screenWidth, prop;
	private boolean isPaused = false;
	private RelativeLayout relativeLayout1;
	private DisplayMetrics dm;

	// private String memory1 = "0";
	// private String memory2 = "0";
	// private String memory3 = "0";
	// private String memory4 = "0";
	// private boolean isVisibility = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billsplitting);
		ReadSharedPreferences();
		btry = Typeface.createFromAsset(getAssets(),
				"font/cal_A-OTF-ShinMGoPro-DeBold.otf");
		mContext = BillSplittingActivity.this;
		WindowManager manage = getWindowManager();
		Display display = manage.getDefaultDisplay();
		screenHeight = (Double) (display.getHeight() / 1.0);
		screenWidth = (Double) (display.getWidth() / 1.0);
		prop = Arith.div(screenHeight, screenWidth, 2);
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		initUI();
		adaptLayout();
	}

	@Override
	protected void onResume() {
		super.onResume();
		isPaused = false;
		ReadSharedPreferences();
		edt_kinngaku.setText(str_total_money);
		editText = edt_kinngaku;
		if (!isPaused && (popupWindow == null || !popupWindow.isShowing())) {
			showErrorMassage();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		isPaused = true;
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
		}
		WriteSharedPreferences();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Log.i(tag, "onRestoreInstanceState----Bill");
		// this.finish();
	}

	@Override
	protected void onDestroy() {
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
		// this.finish();
	}

	/**
	 * スクリーンによってアダプト
	 */
	protected void adaptLayout() {
		LinearLayout_01 = (LinearLayout) popView
				.findViewById(R.id.LinearLayout_01);
		relativeLayout1 = (RelativeLayout) popView
				.findViewById(R.id.relativeLayout1);
		ly_1 = (LinearLayout) this.findViewById(R.id.ly_1);
		ly_2 = (LinearLayout) this.findViewById(R.id.ly_2);

		LinearLayout linearLayout1 = (LinearLayout) this
				.findViewById(R.id.linearLayout1);
		LinearLayout linearLayout1_1 = (LinearLayout) this
				.findViewById(R.id.linearLayout1_1);
		LinearLayout linearLayout2 = (LinearLayout) this
				.findViewById(R.id.linearLayout2);
		LinearLayout linearLayout2_1 = (LinearLayout) this
				.findViewById(R.id.linearLayout2_1);
		LinearLayout linearLayout3 = (LinearLayout) this
				.findViewById(R.id.linearLayout3);
		LinearLayout linearLayout3_1 = (LinearLayout) this
				.findViewById(R.id.linearLayout3_1);
		LinearLayout linearLayout4 = (LinearLayout) this
				.findViewById(R.id.linearLayout4);
		LinearLayout linearLayout4_1 = (LinearLayout) this
				.findViewById(R.id.linearLayout4_1);
		LinearLayout ly_result1 = (LinearLayout) this
				.findViewById(R.id.ly_result1);
		LinearLayout ly_result2 = (LinearLayout) this
				.findViewById(R.id.ly_result2);

		LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params01 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params1_1 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		RelativeLayout.LayoutParams params_pop1 = new RelativeLayout.LayoutParams(
				dipAdapter.dip2px(this, 260), LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_pop2 = new LinearLayout.LayoutParams(
				dipAdapter.dip2px(this, 265), LayoutParams.WRAP_CONTENT);

		if (screenWidth > 710.0 && screenWidth < 730.0) {
			params0.leftMargin = dipAdapter.dip2px(this, 60);
			params0.topMargin = dipAdapter.dip2px(this, 22);
			params1.leftMargin = dipAdapter.dip2px(this, 60);
			params1.topMargin = dipAdapter.dip2px(this, 23);
			params1_1.rightMargin = dipAdapter.dip2px(this, 10);
			linearLayout1.setLayoutParams(params0);
			linearLayout1_1.setLayoutParams(params1_1);
			linearLayout2.setLayoutParams(params1);
			linearLayout2_1.setLayoutParams(params1_1);
			linearLayout3.setLayoutParams(params1);
			linearLayout3_1.setLayoutParams(params1_1);
			linearLayout4.setLayoutParams(params1);
			linearLayout4_1.setLayoutParams(params1_1);

			params2.leftMargin = dipAdapter.dip2px(this, 67);
			params2.topMargin = dipAdapter.dip2px(this, 29);
			ly_result1.setLayoutParams(params2);
			ly_result2.setLayoutParams(params2);

			params3.leftMargin = dipAdapter.dip2px(this, 156);
			params3.topMargin = dipAdapter.dip2px(this, 43);
			ly_1.setLayoutParams(params3);

			params4.leftMargin = dipAdapter.dip2px(this, 156);
			params4.topMargin = dipAdapter.dip2px(this, 17);
			ly_2.setLayoutParams(params4);

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
		} else if (screenWidth > 530.0 && screenWidth < 550.0) {
			params0.leftMargin = dipAdapter.dip2px(this, 60);
			params0.topMargin = dipAdapter.dip2px(this, 22);
			params1.leftMargin = dipAdapter.dip2px(this, 60);
			params1.topMargin = dipAdapter.dip2px(this, 23);
			params1_1.rightMargin = dipAdapter.dip2px(this, 10);
			linearLayout1.setLayoutParams(params0);
			linearLayout1_1.setLayoutParams(params1_1);
			linearLayout2.setLayoutParams(params1);
			linearLayout2_1.setLayoutParams(params1_1);
			linearLayout3.setLayoutParams(params1);
			linearLayout3_1.setLayoutParams(params1_1);
			linearLayout4.setLayoutParams(params1);
			linearLayout4_1.setLayoutParams(params1_1);

			params2.leftMargin = dipAdapter.dip2px(this, 70);
			params2.topMargin = dipAdapter.dip2px(this, 25);
			ly_result1.setLayoutParams(params2);
			ly_result2.setLayoutParams(params2);

			params3.leftMargin = dipAdapter.dip2px(this, 157);
			params3.topMargin = dipAdapter.dip2px(this, 47);
			ly_1.setLayoutParams(params3);

			params4.leftMargin = dipAdapter.dip2px(this, 157);
			params4.topMargin = dipAdapter.dip2px(this, 18);
			ly_2.setLayoutParams(params4);

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

		} else if (screenWidth > 630.0 && screenWidth < 650.0) {
			params0.leftMargin = dipAdapter.dip2px(this, 50);
			params0.topMargin = dipAdapter.dip2px(this, 16);
			params1.leftMargin = dipAdapter.dip2px(this, 50);
			params1.topMargin = dipAdapter.dip2px(this, 18);
			params1_1.rightMargin = dipAdapter.dip2px(this, 5);
			linearLayout1.setLayoutParams(params0);
			linearLayout1_1.setLayoutParams(params1_1);
			linearLayout2.setLayoutParams(params1);
			linearLayout2_1.setLayoutParams(params1_1);
			linearLayout3.setLayoutParams(params1);
			linearLayout3_1.setLayoutParams(params1_1);
			linearLayout4.setLayoutParams(params1);
			linearLayout4_1.setLayoutParams(params1_1);

			params2.leftMargin = dipAdapter.dip2px(this, 60);
			params2.topMargin = dipAdapter.dip2px(this, 22);
			ly_result1.setLayoutParams(params2);
			ly_result2.setLayoutParams(params2);

			params3.leftMargin = dipAdapter.dip2px(this, 138);
			params3.topMargin = dipAdapter.dip2px(this, 38);
			ly_1.setLayoutParams(params3);

			params4.leftMargin = dipAdapter.dip2px(this, 138);
			params4.topMargin = dipAdapter.dip2px(this, 12);
			ly_2.setLayoutParams(params4);

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
		} else {
		}

	}

	/**
	 * 画面初期化
	 */
	public void initUI() {

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
		txt_7 = (TextView) findViewById(R.id.txt_7);
		txt_7.setTypeface(btry);
		txt_8 = (TextView) findViewById(R.id.txt_8);
		txt_8.setTypeface(btry);
		txt_9 = (TextView) findViewById(R.id.txt_9);
		txt_9.setTypeface(btry);
		txt_10 = (TextView) findViewById(R.id.txt_10);
		txt_10.setTypeface(btry);
		txt_11 = (TextView) findViewById(R.id.txt_11);
		txt_11.setTypeface(btry);
		txt_12 = (TextView) findViewById(R.id.txt_12);
		txt_12.setTypeface(btry);
		txt_13 = (TextView) findViewById(R.id.txt_13);
		txt_13.setTypeface(btry);
		txt_14 = (TextView) findViewById(R.id.txt_14);
		txt_14.setTypeface(btry);
		txt_15 = (TextView) findViewById(R.id.txt_15);
		txt_15.setTypeface(btry);
		txt_16 = (TextView) findViewById(R.id.txt_16);
		txt_16.setTypeface(btry);
		edt_kinngaku = (TextView) findViewById(R.id.edt_kinngaku);
		edt_kinngaku.setOnTouchListener((OnTouchListener) this);
		edt_kinngaku.setTypeface(btry);
		edt_kinngaku.setText(str_total_money);
		edt_ninnzuu = (TextView) findViewById(R.id.edt_ninnzuu);
		edt_ninnzuu.setOnTouchListener((OnTouchListener) this);
		edt_ninnzuu.setText(str_num);
		edt_ninnzuu.setTypeface(btry);
		edt_ooku_hito = (TextView) findViewById(R.id.edt_ooku_hito);
		edt_ooku_hito.setOnTouchListener((OnTouchListener) this);
		edt_ooku_hito.setText(str_high_num);
		edt_ooku_hito.setTypeface(btry);
		edt_ookukinngaku = (TextView) findViewById(R.id.edt_ookukinngaku);
		edt_ookukinngaku.setOnTouchListener((OnTouchListener) this);
		edt_ookukinngaku.setText(str_high_money);
		edt_ookukinngaku.setTypeface(btry);
		edt_result1 = (TextView) findViewById(R.id.edt_result1);
		edt_result1.setText(result1);
		edt_result1.setTypeface(btry);
		edt_result2 = (TextView) findViewById(R.id.edt_result2);
		edt_result2.setText(result2);
		edt_result2.setTypeface(btry);
		edt_result3 = (TextView) findViewById(R.id.edt_result3);
		edt_result3.setText(result3);
		edt_result3.setTypeface(btry);
		edt_result4 = (TextView) findViewById(R.id.edt_result4);
		edt_result4.setText(result4);
		edt_result4.setTypeface(btry);
		edt_kinngaku.addTextChangedListener(watcher);
		edt_ninnzuu.addTextChangedListener(watcher);
		edt_ooku_hito.addTextChangedListener(watcher);
		edt_ookukinngaku.addTextChangedListener(watcher);

		popView = View.inflate(mContext, R.layout.popupwindow, null);

	}

	/**
	 * テキスト監視
	 */
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
			str_total_money = edt_kinngaku.getText().toString()
					.replace("'", "");
			str_num = edt_ninnzuu.getText().toString().replace("'", "");
			str_high_money = edt_ookukinngaku.getText().toString()
					.replace("'", "");
			str_high_num = edt_ooku_hito.getText().toString().replace("'", "");

			if ("".equals(str_total_money) || str_total_money == null) {
				str_total_money = "0";
			}
			if ("".equals(str_num) || str_num == null) {
				str_num = "0";
			}
			if ("".equals(str_high_num) || str_high_num == null) {
				str_high_num = "0";
			}
			if ("".equals(str_high_money) || str_high_money == null) {
				str_high_money = "0";
			}
			try {
				getResult(str_total_money, str_num, str_high_num,
						str_high_money);
				setResultInView();
			} catch (Exception e) {
				Log.i(tag, "" + e.toString());
			}

		}

	};

	/**
	 * フォーカス変更イベント（キーボード隠し次第）
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (!isPaused && (popupWindow == null || !popupWindow.isShowing())) {
			showErrorMassage();
		}
		super.onWindowFocusChanged(hasFocus);
	}

	/**
	 * エラーメッセージ表示
	 */
	private void showErrorMassage() {
		String total_money = edt_kinngaku.getText().toString().replace("'", "");
		String num = edt_ninnzuu.getText().toString().replace("'", "");
		String high_num = edt_ooku_hito.getText().toString().replace("'", "");
		String high_money = edt_ookukinngaku.getText().toString()
				.replace("'", "");
		if (editText != null) {
			switch (editText.getId()) {
			case R.id.edt_kinngaku:
				if (Double.valueOf(total_money) < Double.valueOf(num)) {
					result1 = "0";
					result2 = "0";
					result3 = "0";
					result4 = "0";
					MyToast.showMessage(mContext, R.string.Error4);
				}
				if (Double.valueOf(total_money) < Double.valueOf(high_money)) {
					result1 = "0";
					result2 = "0";
					result3 = "0";
					result4 = "0";
					MyToast.showMessage(mContext, R.string.Error5);
				}
				break;
			case R.id.edt_ninnzuu:
				if (Double.valueOf(total_money) < Double.valueOf(num)) {
					result1 = "0";
					result2 = "0";
					result3 = "0";
					result4 = "0";
					MyToast.showMessage(mContext, R.string.Error6);

				}
				if (Double.valueOf(num) < Double.valueOf(high_num)) {
					result1 = "0";
					result2 = "0";
					result3 = "0";
					result4 = "0";
					MyToast.showMessage(mContext, R.string.Error7);
				}
				break;
			case R.id.edt_ooku_hito:
				if (Double.valueOf(high_num) > Double.valueOf(num)) {
					result1 = "0";
					result2 = "0";
					result3 = "0";
					result4 = "0";
					MyToast.showMessage(mContext, R.string.Error8);

				}
				break;
			case R.id.edt_ookukinngaku:
				if (Double.valueOf(high_money) > Double.valueOf(total_money)) {
					result1 = "0";
					result2 = "0";
					result3 = "0";
					result4 = "0";
					MyToast.showMessage(mContext, R.string.Error9);
				}
				break;
			}
		}
		setResultInView();

	}

	/**
	 * 結果取得
	 * 
	 * @param total
	 *            金額
	 * @param num
	 *            　人数
	 * @param high_num
	 *            　多く払う人数
	 * @param high_money
	 *            　多く払う金額
	 */
	private void getResult(String total, String num, String high_num,
			String high_money) {

		OperationForBillSplitting op = new OperationForBillSplitting(total,
				num, high_num, high_money);
		if ("0".equals(num)) {
			result1 = "0";
			result2 = "0";
			result3 = "0";
			result4 = "0";
		} else if (Double.valueOf(high_num) > Double.valueOf(num)) {
		} else {
			// 多く払う人＝人数
			if (num.equals(high_num)) {
				result1 = "0";
				result2 = op.getResultForSame();
			} else {
				if ("0".equals(high_num)) {
					// 多人=0，由总人支付，数额显示在少额里
					result1 = op.getResultForSame();
					result2 = "0";
				} else if ("0".equals(high_money)) {
					// 多钱=0，由总人支付
					result1 = op.getResultForSame();
					result2 = op.getResultForSame();
				} else {
					result1 = op.getResult1();
					result2 = op.getResult2();
				}
			}
			result3 = op.getResult3();
			result4 = op.getResult4();
			try {
				// Log.i(tag, "result1=" + result1);
				result1 = result1.replace("'", "");
				if (Double.valueOf(result1) < 0) {
					result1 = "0";
					result2 = op.getResultForSame();
					result3 = op.getResult3();
				}
				try {
					result1 = ConScience.FormatCurrency(result1, 0);
				} catch (NullPointerException e) {
				}
			} catch (NumberFormatException e) {
			}
		}
	}

	/**
	 * 結果表示
	 */
	private void setResultInView() {
		edt_result1.setText(result1);
		edt_result2.setText(result2);
		edt_result3.setText(result3);
		edt_result4.setText(result4);
	}

	/**
	 * キーボード押下イベント
	 * 
	 * @param v
	 */
	public void onClickListener_Input(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.button_0:
			if (checkLength(editText)) {
				inputNum("0", editText);
			}
			break;
		case R.id.button_1:
			if (checkLength(editText)) {
				inputNum("1", editText);
			}
			break;
		case R.id.button_2:
			if (checkLength(editText)) {
				inputNum("2", editText);
			}
			break;
		case R.id.button_3:
			if (checkLength(editText)) {
				inputNum("3", editText);
			}
			break;
		case R.id.button_4:
			if (checkLength(editText)) {
				inputNum("4", editText);
			}
			break;
		case R.id.button_5:
			if (checkLength(editText)) {
				inputNum("5", editText);
			}
			break;
		case R.id.button_6:
			if (checkLength(editText)) {
				inputNum("6", editText);
			}
			break;
		case R.id.button_7:
			if (checkLength(editText)) {
				inputNum("7", editText);
			}
			break;
		case R.id.button_8:
			if (checkLength(editText)) {
				inputNum("8", editText);
			}
			break;
		case R.id.button_9:
			if (checkLength(editText)) {
				inputNum("9", editText);
			}
			break;
		case R.id.button_close:
			popupWindow.dismiss();
			// saveMemory(editText);
			break;
		case R.id.button_ac:
			delete(editText, 2);
			break;
		case R.id.button_delete:
			delete(editText, 1);
			break;

		}
	}

	/**
	 * 数字削除
	 * 
	 * @param v
	 * @param flg
	 */
	private void delete(TextView v, int flg) {
		String str = "";

		str = v.getText().toString().replace("'", "");
		if (str.length() > 1) {
			str = str.substring(0, str.length() - 1);
		} else {
			str = "0";
		}
		if (flg == 1) {
			v.setText(ConScience.addKanma(str));
		} else {
			v.setText("0");
		}

	}

	/**
	 * 数字入力
	 * 
	 * @param input
	 * @param v
	 */
	private void inputNum(String input, TextView v) {
		String str = v.getText().toString();
		if ("0".equals(str)) {
			str = input;
		} else {
			str += input;
		}
		v.setText(ConScience.addKanma(str));
	}

	/**
	 * 桁数チェック
	 * 
	 * @param v
	 * @return
	 */
	public boolean checkLength(TextView v) {
		String temp = "";
		boolean result = false;
		switch (v.getId()) {
		case R.id.edt_kinngaku:
			temp = edt_kinngaku.getText().toString().replace("'", "");
			if (temp.length() < 10) {
				result = true;
			} else {
				result = false;
			}
			break;
		case R.id.edt_ninnzuu:
			temp = edt_ninnzuu.getText().toString().replace("'", "");
			if (temp.length() < 10) {
				result = true;
			} else {
				result = false;
			}
			break;
		case R.id.edt_ooku_hito:
			temp = edt_ooku_hito.getText().toString().replace("'", "");
			if (temp.length() < 10) {
				result = true;
			} else {
				result = false;
			}
			break;
		case R.id.edt_ookukinngaku:
			temp = edt_ookukinngaku.getText().toString().replace("'", "");
			if (temp.length() < 10) {
				result = true;
			} else {
				result = false;
			}
			break;
		}
		return result;

	}

	@Override
	public boolean onTouch(View v, MotionEvent arg1) {
		if ((v.getId() == R.id.edt_kinngaku || v.getId() == R.id.edt_ninnzuu
				|| v.getId() == R.id.edt_ooku_hito || v.getId() == R.id.edt_ookukinngaku)
				&& arg1.getAction() != 1) {

			editText = (TextView) v;

			if (popupWindow == null) {
				popupWindow = new PopupWindow(popView,
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			}
			popupWindow.setFocusable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

			if (screenWidth > 630.0 && screenWidth < 650.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_kinngaku),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(360));
			} else if (screenWidth > 710.0 && screenWidth < 730.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_kinngaku),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(299));
			} else if (screenWidth > 530.0 && screenWidth < 550.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_kinngaku),
						Gravity.NO_GRAVITY, getPixelsX(50), getPixelsY(301));
			} else if (screenHeight > 790.0 && screenHeight < 810.0) {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_kinngaku),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(355));
			} else {
				popupWindow.showAtLocation(
						((Activity) mContext).findViewById(R.id.edt_kinngaku),
						Gravity.NO_GRAVITY, getPixelsX(55), getPixelsY(332));
			}

		}
		return false;
	}

	public int getPixelsX(int x) {
		return dm.widthPixels * x / 480;
	}

	public int getPixelsY(int y) {
		return dm.heightPixels * y / 854;
	}

	/**
	 * 
	 * データ読入
	 */
	private void ReadSharedPreferences() {
		SharedPreferences SP_Warikann = getSharedPreferences("warikann", 0);
		SharedPreferences SP_Cal = getSharedPreferences("cal", 0);

		Boolean flg = SP_Cal.getBoolean("flg1", false);
		if (flg) {
			str_total_money = SP_Cal.getString("total", "0");
		} else {
			str_total_money = SP_Warikann.getString("total", "0");
		}
		str_num = SP_Warikann.getString("str_num", "0");
		str_high_num = SP_Warikann.getString("str_high_num", "0");
		str_high_money = SP_Warikann.getString("str_high_money", "0");

		memory1 = str_total_money;

	}

	/**
	 * データ保存
	 */
	private void WriteSharedPreferences() {
		SharedPreferences SP_Warikann = getSharedPreferences("warikann", 0);
		Editor editor = SP_Warikann.edit();
		editor.putString("total", edt_kinngaku.getText().toString());
		editor.putString("str_num", edt_ninnzuu.getText().toString());
		editor.putString("str_high_num", edt_ooku_hito.getText().toString());
		editor.putString("str_high_money", edt_ookukinngaku.getText()
				.toString());
		editor.commit();

		SharedPreferences SP_Cal = getSharedPreferences("cal", 0);
		Editor editor1 = SP_Cal.edit();
		editor1.putBoolean("flg1", false);
		editor1.commit();

	}

}
