package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01;

import java.util.ArrayList;

import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.customize.MyToast;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.operation.OperationForCal;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.Arith;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.ConScience;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.dipAdapter;
import jp.primeworks.android.flamingo.Flamingo;
import jp.primeworks.android.flamingo.activity.AboutActivity;
import jp.primeworks.android.flamingo.activity.FlamingoActivity;
import jp.primeworks.android.flamingo.activity.FlamingoFragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class CalculatorActivity extends FlamingoFragmentActivity {
	private final static String tag = "choui";

	private Context mContext;
	private TextView edt_1,txt_copy,txt_paste;
	private ImageButton button_c;
	private ImageView image_1, m_Cal_icon;
	private Button button_copy, button_paste;
	private ArrayList<String> arr_Operation = new ArrayList<String>();
	private String str_Input = "0";
	private String str_Result = "0";
	private String str_Memory = "0";
	private boolean isEnterOperator = false;
	private boolean isEnterEqual = true;
	private boolean isEnterNum = false;
	private boolean isError = false;
	private Typeface btry;
	private DisplayMetrics dm;
	private LinearLayout ly_main1, ly_main2, ly_main3, ly_main4, ly_main5,
			ly_copy, ly_anime, ly_calbtns;
	private Double screenHeight, screenWidth;
	private AnimationDrawable animationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btry = Typeface.createFromAsset(getAssets(),
				"font/cal_A-OTF-ShinMGoPro-DeBold.otf");
		WindowManager manage = getWindowManager();
		Display display = manage.getDefaultDisplay();
		screenHeight = (Double) (display.getHeight() / 1.0);
		screenWidth = (Double) (display.getWidth() / 1.0);
		// Log.i(tag, "screenHeight="+screenHeight +
		// "screenWidth="+screenWidth);
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mContext = CalculatorActivity.this;
		initUI();
		adaptLayout();
		animationDrawable = (AnimationDrawable) image_1.getBackground();
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
		Editor editor3 = SP_Warikann.edit();
		editor3.clear();
		editor3.commit();
	}

	/**
	 * スクリンサイズによって画面初期設定
	 */
	protected void adaptLayout() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_copy = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_anime = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_cal = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		if (screenWidth > 710.0 && screenWidth < 730.0) {
			params_copy.width = dipAdapter.dip2px(this, 200);
			params_copy.height = dipAdapter.dip2px(this, 47);
			params_copy.topMargin = dipAdapter.dip2px(this, 20);
			ly_copy.setLayoutParams(params_copy);
			params_anime.width = dipAdapter.dip2px(this, 140);
			params_anime.height = dipAdapter.dip2px(this, 110);
			params_anime.leftMargin = dipAdapter.dip2px(this, 2);
			params_anime.topMargin = dipAdapter.dip2px(this, 0);
			ly_anime.setLayoutParams(params_anime);
			params.height = dipAdapter.dip2px(this, 54);
			params.topMargin = dipAdapter.dip2px(this, 2);
			params_cal.leftMargin = dipAdapter.dip2px(this, 30);
			params_cal.rightMargin = dipAdapter.dip2px(this, 15);
			params_cal.topMargin = dipAdapter.dip2px(this, 8);
			ly_calbtns.setLayoutParams(params_cal);
			edt_1.setTextSize(37);
			txt_copy.setPadding(58, 48, 0, 0);
			txt_paste.setPadding(53, 48, 0, 0);
		} else if (screenWidth > 530.0 && screenWidth < 550.0) {
			params_copy.width = dipAdapter.dip2px(this, 200);
			params_copy.height = dipAdapter.dip2px(this, 47);
			params_copy.topMargin = dipAdapter.dip2px(this, 20);
			ly_copy.setLayoutParams(params_copy);
			params_anime.width = dipAdapter.dip2px(this, 140);
			params_anime.height = dipAdapter.dip2px(this, 108);
			params_anime.leftMargin = dipAdapter.dip2px(this, 2);
			params_anime.topMargin = dipAdapter.dip2px(this, 0);
			ly_anime.setLayoutParams(params_anime);
			params.height = dipAdapter.dip2px(this, 53);
			params.topMargin = dipAdapter.dip2px(this, 2);
			params_cal.leftMargin = dipAdapter.dip2px(this, 30);
			params_cal.rightMargin = dipAdapter.dip2px(this, 15);
			params_cal.topMargin = dipAdapter.dip2px(this, 12);
			ly_calbtns.setLayoutParams(params_cal);
			edt_1.setTextSize(37);
			txt_copy.setPadding(45, 35, 0, 0);
			txt_paste.setPadding(40, 35, 0, 0);
		} else if (screenWidth > 630.0 && screenWidth < 650.0) {
			params.height = dipAdapter.dip2px(this, 47);
			params.topMargin = dipAdapter.dip2px(this, 2);
		} else {
			params.height = dipAdapter.dip2px(this, 47);
			params.topMargin = dipAdapter.dip2px(this, 2);
		}
		ly_main1.setLayoutParams(params);
		ly_main2.setLayoutParams(params);
		ly_main3.setLayoutParams(params);
		ly_main4.setLayoutParams(params);
		ly_main5.setLayoutParams(params);
	}

	/**
	 * 画面初期化
	 */
	private void initUI() {
		ly_copy = (LinearLayout) this.findViewById(R.id.ly_copy);
		ly_anime = (LinearLayout) this.findViewById(R.id.ly_anime);
		ly_calbtns = (LinearLayout) this.findViewById(R.id.ly_calbtns);

		ly_main1 = (LinearLayout) this.findViewById(R.id.ly_main1);
		ly_main2 = (LinearLayout) this.findViewById(R.id.ly_main2);
		ly_main3 = (LinearLayout) this.findViewById(R.id.ly_main3);
		ly_main4 = (LinearLayout) this.findViewById(R.id.ly_main4);
		ly_main5 = (LinearLayout) this.findViewById(R.id.ly_main5);

		image_1 = (ImageView) findViewById(R.id.image_1);
		image_1.setBackgroundResource(R.drawable.animation);
		m_Cal_icon = (ImageView) findViewById(R.id.cal_icon);

		edt_1 = (TextView) findViewById(R.id.txt_1);
		edt_1.setTypeface(btry);
		button_copy = (Button) findViewById(R.id.button_copy);
		button_copy.setTypeface(btry);
		button_paste = (Button) findViewById(R.id.button_paste);
		button_paste.setTypeface(btry);
		txt_copy = (TextView)findViewById(R.id.txt_copy);
		txt_paste = (TextView)findViewById(R.id.txt_paste);
		txt_copy.setTypeface(btry);
		txt_paste.setTypeface(btry);
		
		button_c = (ImageButton) findViewById(R.id.button_c);
		button_c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startAnimation();

				String temp = edt_1.getText().toString();
				if (!isError && temp.length() > 1) {
					str_Input = temp.substring(0, temp.length() - 1);
					if (str_Input.endsWith(".")) {
						str_Input = str_Input.replace(".", "");
					}
					setArrayList(str_Input, 1);
					isEnterEqual = false;
					isEnterOperator = false;
					isEnterNum = true;
					isError = false;
					showInput();
				} else if (isError) {
					str_Input = "0";
					setOperatoerIcon(0);
					setArrayList(str_Input, 1);
					isEnterEqual = false;
					isEnterOperator = false;
					isEnterNum = true;
					isError = false;
					showInput();
				} else {
					str_Input = "0";
					// setOperatoerIcon(0);
					setArrayList(str_Input, 1);
					isEnterEqual = false;
					isEnterOperator = false;
					isEnterNum = true;
					isError = false;
					showInput();
				}
			}

		});
		button_c.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				str_Input = "0";
				if (isError) {
					setOperatoerIcon(0);
				}
				setArrayList(str_Input, 1);
				isEnterEqual = false;
				isEnterOperator = false;
				isEnterNum = true;
				isError = false;
				showInput();

				return true;
			}

		});
		showInput();
	}

	/**
	 * コピー事件
	 * 
	 * @param v
	 */
	public void onClickListener_copy(View v) {
		startAnimation();
		if (!isError) {
			str_Memory = edt_1.getText().toString();
			MyToast.showMessage(mContext, R.string.Copy_OK, Toast.LENGTH_SHORT);
		}

	}

	/**
	 * ペーストイベント
	 * 
	 * @param v
	 */
	public void onClickListener_paste(View v) {
		startAnimation();
		try {
			if (Double.valueOf(str_Memory) == 0.0) {
				str_Memory = "0";
			}
		} catch (NumberFormatException e) {

		}

		// if (!"0".equals(str_Memory)) {
		str_Input = str_Memory;
		setArrayList(str_Input, 1);
		if (isError) {
			setOperatoerIcon(0);
		}
		isEnterEqual = false;
		isEnterOperator = false;
		isEnterNum = true;
		isError = false;
		showInput();

		MyToast.showMessage(mContext, R.string.Paste_OK, Toast.LENGTH_SHORT);

		// }
	}

	/**
	 * +/-イベント
	 * 
	 * @param v
	 */
	public void onClickListener_change(View v) {
		startAnimation();
		if (!isError && !isEnterOperator) {
			String temp = edt_1.getText().toString();
			if (temp.indexOf("-") == -1 && !"0".equals(temp)) {
				str_Input = "-" + temp;
			} else {
				str_Input = temp.replace("-", "");
			}

			setArrayList(str_Input, 1);
			if (isError) {
				setOperatoerIcon(0);
			}
			isEnterEqual = false;
			isEnterOperator = false;
			isEnterNum = true;
			isError = false;
			showInput();
		}
	}

	/**
	 * ACイベント
	 * 
	 * @param v
	 */
	public void onClickListener_ac(View v) {
		startAnimation();
		str_Input = "0";
		str_Result = "0";
		showResult();
		isEnterEqual = true;
		isEnterOperator = false;
		isError = false;
		arr_Operation.clear();
		setOperatoerIcon(0);
	}

	/**
	 * 数字イベント
	 * 
	 * @param v
	 */
	public void onClickListener_num(View v) {
		startAnimation();
		if (checkLength() || !isEnterNum) {
			int id = v.getId();
			switch (id) {
			case R.id.button_0:
				if (isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "0";
				} else if (!isZero()) {
					str_Input += "0";
				}
				break;
			case R.id.button_1:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "1";
				} else {
					str_Input += "1";
				}
				break;
			case R.id.button_2:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {

					str_Input = "2";
				} else {
					str_Input += "2";
				}
				break;
			case R.id.button_3:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "3";
				} else {
					str_Input += "3";
				}
				break;
			case R.id.button_4:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "4";
				} else {
					str_Input += "4";
				}
				break;
			case R.id.button_5:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "5";
				} else {
					str_Input += "5";
				}
				break;
			case R.id.button_6:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "6";
				} else {
					str_Input += "6";
				}
				break;
			case R.id.button_7:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "7";
				} else {
					str_Input += "7";
				}
				break;
			case R.id.button_8:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "8";
				} else {
					str_Input += "8";
				}
				break;
			case R.id.button_9:
				if (isZero() || isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "9";
				} else {
					str_Input += "9";
				}
				break;
			case R.id.button_dot:
				if (isEnterOperator || isEnterEqual || !isEnterNum) {
					str_Input = "0.";
				} else if (canInputDot()) {
					str_Input += ".";
				}
				break;
			}
			setArrayList(str_Input, 1);
			if (isError || isEnterEqual) {
				setOperatoerIcon(0);
			}
			isEnterEqual = false;
			isEnterOperator = false;
			isEnterNum = true;
			isError = false;
			showInput();
		}
	}

	/**
	 * 符号イベント
	 * 
	 * @param v
	 */
	public void onClickListener_operate(View v) {
		startAnimation();
		if (!isError) {
			int id = v.getId();
			String temp = "";
			int optFlag = 0;
			switch (id) {
			case R.id.button_plus:
				optFlag = 1;
				temp = "+";
				break;
			case R.id.button_sub:
				optFlag = 2;
				temp = "-";
				break;
			case R.id.button_divsion:
				optFlag = 3;
				temp = "/";
				break;
			case R.id.button_x:
				optFlag = 4;
				temp = "*";
				break;
			}

			setArrayList(temp, 2);
			setOperatoerIcon(optFlag);
			isEnterEqual = false;
			isEnterOperator = true;
			isEnterNum = false;

		}

	}

	/**
	 * =イベント
	 * 
	 * @param v
	 */
	public void onClickListener_equal(View v) {
		startAnimation();
		if (!isError && !isEnterEqual) {
			OperationForCal op = new OperationForCal();
			str_Result = op.getResult(arr_Operation);
			showResult();
			// arr_Operation.clear();
			setOperatoerIcon(5);
			try {
				arr_Operation.set(0, str_Result);
			} catch (IndexOutOfBoundsException e) {
			}
			isEnterEqual = true;
			isEnterNum = false;
			if (str_Result.indexOf("-") == -1 && str_Result.indexOf(".") == -1
					&& str_Result.indexOf("E") == -1) {
				WriteSharedPreferences();
			}
		}
	}

	/**
	 * %イベント
	 * 
	 * @param v
	 */
	public void onClickListener_per(View v) {
		startAnimation();
		if (!isError) {
			setArrayList("%", 3);
		}
	}
	
	/**
	 * 計算式をリストに追加
	 * 
	 * @param str
	 *            数字或者符号的字符串
	 * @param flag
	 *            1：数字 2：运算符号 3：百分号
	 */
	public void setArrayList(String str, int flag) {
		int int_Size = arr_Operation.size();
		OperationForCal op = new OperationForCal();
		try {
			// 数字
			if (flag == 1) {
				if (int_Size == 0) {
					arr_Operation.add(0, str);
				} else if (int_Size == 1) {
					arr_Operation.set(0, str);
				} else if (int_Size == 2) {
					if (isEnterEqual) {
						arr_Operation.clear();
						arr_Operation.add(0, str);
					} else {
						arr_Operation.add(2, str);
					}
				} else if (int_Size == 3) {
					if (isEnterOperator) {
						arr_Operation.set(2, str);
					} else if (isEnterEqual) {
						arr_Operation.clear();
						arr_Operation.add(0, str);
					} else {
						arr_Operation.set(2, str);
					}
				}
				// 符号
			} else if (flag == 2) {
				if (int_Size == 0) {
					arr_Operation.add(0, "0");
					arr_Operation.add(1, str);
				} else if (int_Size == 1) {
					arr_Operation.add(1, str);
				} else if (int_Size == 2) {
					arr_Operation.set(1, str);
				} else if (int_Size == 3) {
					if (isEnterEqual) {
						arr_Operation.clear();
						arr_Operation.add(0, str_Result);
						arr_Operation.add(1, str);
					} else if (isEnterNum) {
						// arr_Operation.set(0, str_Result);
						str_Result = op.getResult(arr_Operation);
						showResult();
						arr_Operation.set(0, str_Result);
						arr_Operation.set(1, str);
					} else if (isEnterOperator) {
						arr_Operation.set(1, str);
					} else {
						str_Result = op.getResult(arr_Operation);
						showResult();
						arr_Operation.clear();
						arr_Operation.add(0, str_Result);
						arr_Operation.add(1, str);
					}

				}
				// %
			} else if (flag == 3 && (int_Size == 3)) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(0, arr_Operation.get(0));
				temp.add(1, "*");
				double n = 0;
				try {
					n = Double.parseDouble(arr_Operation.get(2).toString());
				} catch (NumberFormatException e) {
				}
				n = n / 100;
				temp.add(2, String.valueOf(n));
				if ("+".equals(arr_Operation.get(1))
						|| "-".equals(arr_Operation.get(1))) {
					arr_Operation.set(2, op.getResult(temp));
				} else {
					arr_Operation.set(2, temp.get(2));
				}

			}
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	/**
	 * 入力数字表示(カンマ付き)
	 */
	public void showInput() {
		edt_1.setText(ConScience.addKanma(str_Input));
	}

	/**
	 * 計算結果表示()
	 */
	public void showResult() {
		edt_1.setText(str_Result);
	}

	/**
	 * 小数点入力可能チェック
	 * 
	 * @return
	 */
	public boolean canInputDot() {
		if (str_Input.indexOf(".") == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ０入力可能チェック
	 * 
	 * @return
	 */
	public boolean isZero() {
		if ("0".equals(str_Input)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 符号エアコン表示
	 * 
	 * @param opt
	 *            　符号標識
	 */
	public void setOperatoerIcon(int opt) {
		Resources res = getResources();
		Drawable img_icon = null;
		switch (opt) {
		case 0:
			img_icon = null;
			break;
		case 1:
			img_icon = res.getDrawable(R.drawable.cal_icon01);
			break;
		case 2:
			img_icon = res.getDrawable(R.drawable.cal_icon02);
			break;
		case 3:
			img_icon = res.getDrawable(R.drawable.cal_icon04);
			break;
		case 4:
			img_icon = res.getDrawable(R.drawable.cal_icon03);
			break;
		case 5:
			img_icon = res.getDrawable(R.drawable.cal_icon05);
			break;
		}
		String result = edt_1.getText().toString();
		if (result.indexOf("E") > -1) {
			img_icon = res.getDrawable(R.drawable.cal_icon06);
			result = result.replace("E", "");
			edt_1.setText(ConScience.addKanma(result));
			isError = true;
		} else {
			isError = false;
		}

		m_Cal_icon.setBackgroundDrawable(img_icon);
	}

	/**
	 * 長さをチェック
	 * 
	 * @return
	 */
	public boolean checkLength() {
		String temp = edt_1.getText().toString().replace("'", "");
		temp = temp.replace(".", "");
		temp = temp.replace("-", "");
		if (temp.length() < 10) {
			return true;
		}
		return false;
	}

	/**
	 * データ保存
	 */
	private void WriteSharedPreferences() {
		SharedPreferences SP_Cal = getSharedPreferences("cal", 0);
		Editor editor = SP_Cal.edit();
		editor.putString("total", edt_1.getText().toString());
		editor.putBoolean("flg1", true);
		editor.putBoolean("flg2", true);
		editor.commit();

	}

	/**
	 * アニメ起動
	 */
	public void startAnimation() {
		if (animationDrawable.isRunning()) {
			animationDrawable.stop();
			animationDrawable.start();
		} else {
			animationDrawable.start();
		}
	}
	
}
