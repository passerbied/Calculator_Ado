package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.util.Log;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public final class ConScience {
	private static final String tag = "choui_cal";

	private ConScience() {
	}

	public static String ConScience2Num(String str) {
		int len = Integer.parseInt(str.substring(getNum(str), getNum(str) + 1));
		String bit = "1";
		for (int i = 0; i < len; i++) {
			bit += "0";
		}
		// BigDecimal bitResult = new BigDecimal(bit);
		// BigDecimal result = bitResult.multiply(getDecimal(str));
		BigDecimal last = new BigDecimal(bit).multiply(getDecimal(str))
				.setScale(1, 1);
		return FormatCurrency(last.toString(), 2);
	}

	public static int getNum(String str) {
		return str.indexOf('E') + 1;
	}

	public static BigDecimal getDecimal(String str) {

		return new BigDecimal(str.substring(0, str.indexOf('E')));
	}

	public static String FormatCurrency(String s, int len) {
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = 0.0;
		try {
			num = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			Log.i(tag, "FormatCurrency Error");
		}

		if (len == 0) {
			formater = new DecimalFormat("###,###");

		} else {
			StringBuffer buff = new StringBuffer();
			buff.append("###,###.");
			for (int i = 0; i < len; i++) {
				buff.append("#");
			}
			formater = new DecimalFormat(buff.toString());
		}
		String result = formater.format(num);
		result = result.replace(",", "'");
		return result;
	}

	/**
	 * カンマ追加
	 * 
	 * @param temp
	 * @return
	 */
	public static String addKanma(String temp1) {
		// 小数点桁数
		int len = 0;
		String temp;
		String temp2;
		temp1 = temp1.replace("'", "");
		int temp1_index = temp1.indexOf(".");
		if (temp1_index > -1) {
			if(temp1.startsWith("-0.")){
				return temp1;
			}else{
				 temp = temp1.substring(0, temp1_index);
				 temp2 = temp1.substring(temp1_index,temp1.length());
				 temp = ConScience.FormatCurrency(temp, len);
				 temp= temp + temp2;
			}
			 
		}else{
			temp = temp1;
			temp = ConScience.FormatCurrency(temp, len);
		}
		
//		if (temp1_index > 3 || temp1_index == -1) {
//			if (temp1_index > -1) {
//				if (temp1_index == temp.length() - 1) {
//					temp = temp + "1";
//					len = 1;
//					temp = ConScience.FormatCurrency(temp, len);
//					temp = temp.substring(0, temp.length() - 1);
//				} else {
//					len = temp.length() - temp1_index;
//					temp = ConScience.FormatCurrency(temp, len);
//				}
//
//			} else {
//				temp = ConScience.FormatCurrency(temp, len);
//			}
//		}
		return temp;
	}
}
