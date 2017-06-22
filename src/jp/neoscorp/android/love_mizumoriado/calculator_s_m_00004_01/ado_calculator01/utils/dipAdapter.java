package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils;

import android.content.Context;

public class dipAdapter {
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	public static int px2dip(Context context, float px) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}
}
