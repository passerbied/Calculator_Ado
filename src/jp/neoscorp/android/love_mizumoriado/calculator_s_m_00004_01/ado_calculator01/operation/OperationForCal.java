package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.Arith;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.ConScience;

import android.util.Log;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class OperationForCal {
	private static final String tag = "choui_cal";
	private RoundingMode roundingMode = RoundingMode.HALF_UP;
	private int precision = 9;

	public String getResult(ArrayList<String> arrList) {
		String str_result = "0";
		if (arrList.size() == 2) {
			str_result = getResult_2(arrList);
		} else if (arrList.size() == 1) {
			str_result = arrList.get(0).replace("'", "");
		} else if (arrList.size() == 3) {
			str_result = getResult_3(arrList);
		}

		String str1 = "";
		String str2 = "";
		int index_dot = str_result.indexOf(".");
		int length = str_result.length();
		//Log.i(tag, "str_result=" + str_result);
		// マイナスが無い場合
		if (str_result.indexOf("-") == -1) {
			if (index_dot > 10) {
				str_result = str_result.substring(0, index_dot);
				str1 = str_result.substring(0, index_dot - 10);
				str2 = str_result.substring(index_dot - 10, 10);
				str_result = str1 + "." + str2 + "E";
			} else if (index_dot >= 0 && index_dot < 10 && length >= 11) {
				str_result = str_result.substring(0, 11);
				// 最後の０じゃない数字の位置
				int n = index_dot;
				for (int i = index_dot; i < 11; i++) {
					if (!"0".equals(str_result.substring(i, i + 1))) {
						n = i;
					}
				}
				str_result = str_result.substring(0, n + 1);
			} else if (index_dot >= 0 && index_dot == 10 && length >= 11) {
				str_result = str_result.substring(0, 10);
			} else if (index_dot == -1 && length > 10) {
				str_result.substring(0, 10);
				str1 = str_result.substring(0, length - 10);
				str2 = str_result.substring(length - 10, 10);
				str_result = str1 + "." + str2 + "E";
			}
		}
		// マイナスがある場合
		else {
			if (index_dot > 11) {
				str_result = str_result.substring(0, index_dot);
				str1 = str_result.substring(0, index_dot - 10);
				str2 = str_result.substring(index_dot - 10, 11);
				str_result = str1 + "." + str2 + "E";
			} else if (index_dot >= 0 && index_dot < 11 && length >= 12) {
				str_result = str_result.substring(0, 12);
				// 最後の０じゃない数字の位置
				int n = index_dot;
				for (int i = index_dot; i < 12; i++) {
					if (!"0".equals(str_result.substring(i, i + 1))) {
						n = i;
					}
				}
				str_result = str_result.substring(0, n + 1);
			} else if (index_dot >= 0 && index_dot == 11 && length >= 12) {
				str_result = str_result.substring(0, 11);
			} else if (index_dot == -1 && length > 11) {
				str_result.substring(0, 11);
				str1 = str_result.substring(0, length - 10);
				str2 = str_result.substring(length - 10, 11);
				str_result = str1 + "." + str2 + "E";
			}
		}

		try {
			if (".".equals(str_result.substring(str_result.length() - 1,
					str_result.length()))) {
				str_result = str_result.substring(0, str_result.length() - 1);
			}
		} catch (StringIndexOutOfBoundsException e) {
		}

		if (str_result.indexOf("E") == -1) {
			// 少数点桁数
			int len = 0;
			if (str_result.indexOf(".") > -1) {
				len = str_result.length() - str_result.indexOf(".");
			}
			Log.i(tag, "len=" + len);
			str_result = str_result.replace("'", "");
			str_result = ConScience.FormatCurrency(str_result, len);
		}
		return str_result;
	}

	public String getResult_2(ArrayList<String> arrList) {
		BigDecimal result = new BigDecimal("0");
		BigDecimal temp0 = new BigDecimal("0");
		String temp1 = "";
		String str_result = "";
		try {
			temp0 = new BigDecimal(arrList.get(0).toString().replace("'", ""));
			temp1 = arrList.get(1).toString();
		} catch (NumberFormatException e) {
		} catch (IndexOutOfBoundsException e) {
		}

//		if (temp1 == "+") {
//			result = temp0.add(temp0);
//		} else if (temp1 == "-") {
//			result = temp0.subtract(temp0);
//		} else if (temp1 == "*") {
//			result = temp0.multiply(temp0);
//		} else if (temp1 == "/") {
//			if (temp0.equals(0.0)) {
//				return "1";
//			}
//
//			try {
//				result = temp0.divide(temp0);
//			} catch (ArithmeticException e) {
//				return "0E";
//			}
//		}
		result = temp0;
		java.text.DecimalFormat form = new java.text.DecimalFormat(
				"####0.000000000 ");

		if ((result.doubleValue() >= 0 && result.doubleValue() < 0.000000001)
				|| (result.doubleValue() <= 0 && result.doubleValue() > -0.000000001)) {
			str_result = "0";
		} else {
			str_result = form.format(result);
		}

		return str_result;
	}

	public String getResult_3(ArrayList<String> arrList) {
		String temp1 = "";
		BigDecimal result = new BigDecimal("0");
		BigDecimal temp0 = new BigDecimal("0");
		BigDecimal temp2 = new BigDecimal("0");
		String str_result = "";
		try {
			temp0 = new BigDecimal(arrList.get(0).toString().replace("'", ""));
			temp2 = new BigDecimal(arrList.get(2).toString().replace("'", ""));
			temp1 = arrList.get(1).toString();
		} catch (NumberFormatException e) {
		} catch (IndexOutOfBoundsException e) {
		}

		if (temp1 == "+") {
			result = temp0.add(temp2);
		} else if (temp1 == "-") {
			result = temp0.subtract(temp2);
		} else if (temp1 == "*") {
			result = temp0.multiply(temp2);
		} else if (temp1 == "/") {
			if (temp2.doubleValue() == 0.0) {
				return "0E";
			} else {
				try {
					result = temp0.divide(temp2, precision);
				} catch (ArithmeticException e) {
					result = temp0.divide(temp2, precision, roundingMode);
				} catch (IllegalArgumentException e) {
					result = temp0.divide(temp2, 10, roundingMode);
					int index = result.toString().indexOf(".");
					if(index>-1 && index<=10){
					result = Arith.round_for_decimal(result, 10-index);
					}
				}
			}
		}
		java.text.DecimalFormat form = new java.text.DecimalFormat(
				"####0.000000000 ");

		if ((result.doubleValue() >= 0 && result.doubleValue() < 0.000000001)
				|| (result.doubleValue() <= 0 && result.doubleValue() > -0.000000001)) {
			str_result = "0";
		} else {
			str_result = form.format(result);
		}

		return str_result;
	}
}
