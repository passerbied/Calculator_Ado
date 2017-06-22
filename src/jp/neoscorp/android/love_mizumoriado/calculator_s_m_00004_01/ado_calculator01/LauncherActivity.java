package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import jp.primeworks.android.flamingo.Flamingo;
import jp.primeworks.android.flamingo.activity.FlamingoActivity;
import jp.primeworks.android.flamingo.activity.FlamingoFragmentActivity;

public class LauncherActivity extends FlamingoFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();

//		Flamingo flamingo = new Flamingo(this);
//		if (!flamingo.isValidApplication() && !isAuthorizeError()) {
//			showDialog(FlamingoActivity.DIALOG_AUTHORIZE);
//			Log.i("choui", "NG");
//		}
//
//		else if (flamingo.isValidApplication() || !isAuthorizeError()) {
			Intent resultValue = new Intent();
			Log.i("choui", "OK");
			resultValue.setClass(this, CalculatorTabActivity.class);
			startActivity(resultValue);
			finish();
//		}

	}
}
