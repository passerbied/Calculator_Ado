package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.operation;

import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.Arith;
import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.ConScience;
import android.util.Log;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class OperationForDiscount {
	private static final String tag = "choui";
	private String total = null;
	private int percent;

	public OperationForDiscount(String total, int percent) {
		this.total = total;
		this.percent = percent;
	}

	public String getResult1() {
		Double result = 0.0;
		String str_result = "";
		Double d_Tatol = null;
		Double d_percent = null;
		try {
			d_Tatol = Double.valueOf(total);
			d_percent = (double) percent / 100;
			result = Arith.mul(d_Tatol, d_percent);
			result = Arith.round(result, 0);
			str_result = ConScience.FormatCurrency(result.toString(), 0);
			if (".0".equals(str_result.substring(str_result.length() - 2,
					str_result.length()))) {
				str_result = str_result.substring(0, str_result.length() - 2);
			}
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		} catch (IndexOutOfBoundsException e) {
		}

		return str_result;
	}

	public String getResult2() {
		Double result = 0.0;
		String str_result = "";
		Double result1 = 0.0;
		Double d_Tatol = null;
		try {
			result1 = Double.valueOf(getResult1().replace("'", ""));
			d_Tatol = Double.valueOf(total);
			result = Arith.sub(d_Tatol, result1);
			str_result = ConScience.FormatCurrency(result.toString(), 0);
			if (".0".equals(str_result.substring(str_result.length() - 2,
					str_result.length()))) {
				str_result = str_result.substring(0, str_result.length() - 2);
			}
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		} catch (IndexOutOfBoundsException e) {
		}

		return str_result;
	}
}
