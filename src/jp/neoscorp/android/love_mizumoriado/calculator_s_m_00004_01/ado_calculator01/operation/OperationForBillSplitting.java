package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.operation;

import jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils.ConScience;
import android.util.Log;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */
public class OperationForBillSplitting {
	private static final String tag = "choui";

	private String total = "";
	private String num = "";
	private String high_num = "";
	private String high_money = "";

	/**
	 * 
	 * @param total
	 * @param num
	 * @param high_num
	 * @param high_money
	 */
	public OperationForBillSplitting(String total, String num, String high_num,
			String high_money) {
		this.total = total;
		this.num = num;
		this.high_num = high_num;
		this.high_money = high_money;
	}

	public String getResult1() {
		double n_result1 = 0;
		double n_result2 = 0;
		double n_high_money = 0;
		String str_result = "";
		try {
			n_result2 = Double.valueOf(getResult2().replace("'", ""));
			n_high_money = Double.valueOf(high_money.replace("'", ""));
			n_result1 = n_result2 - n_high_money;
			str_result = ConScience.FormatCurrency(String.valueOf(n_result1)
					.toString(), 0);
		} catch (NumberFormatException e) {
		} catch (NullPointerException e) {
		}

		return str_result;
	}

	public String getResult2() {
		double n_result2 = 0;
		double n_high_money = 0;
		double n_high_num = 0;
		double n_Total = 0;
		double n_num = 0;
		String str_result = "";
		try {
			n_high_money = Double.valueOf(high_money);
			n_high_num = Double.valueOf(high_num);
			n_num = Double.valueOf(num);
			n_Total = Double.valueOf(total);
			double temp = (n_Total + (double) n_high_money
					* (double) (n_num - n_high_num))
					/ (double) n_num;
			// Log.i(tag, "temp=" + temp);
			n_result2 = Math.ceil(temp);
			str_result = ConScience.FormatCurrency(String.valueOf(n_result2)
					.toString(), 0);
		} catch (NumberFormatException e) {

		} catch (NullPointerException e) {
		}

		// Log.i(tag, "getResult2()=" + n_result2);

		return str_result;
	}

	public String getResult3() {
		double n_result3 = 0;
		double n_result1 = 0;
		double n_result2 = 0;
		double n_Total = 0;
		double n_num = 0;
		double n_high_num = 0;
		double n_high_money = 0;
		String str_result = "";
		try {
			n_Total = Double.valueOf(total);
			n_num = Double.valueOf(num);
			n_high_num = Double.valueOf(high_num);
			n_high_money = Double.valueOf(high_money);
			n_result1 = Double.valueOf(getResult1().replace("'", ""));
			n_result2 = Double.valueOf(getResult2().replace("'", ""));
			// 多人 = 总人
			if (n_num == n_high_num || n_high_num == 0 || n_high_money == 0) {
				n_result3 = Double.valueOf(getResultForSame().replace("'", ""))
						* n_num;
			} else if (n_result1 <= 0) { // 少钱 < 0
				n_result3 = Double.valueOf(getResultForSame().replace("'", ""))
						* n_high_num;
			} else {
				n_result3 = n_result1 * (n_num - n_high_num) + n_result2
						* n_high_num;
			}
			str_result = ConScience.FormatCurrency(String.valueOf(n_result3)
					.toString(), 0);
		} catch (NumberFormatException e) {

		} catch (NullPointerException e) {
		}

		// Log.i(tag, "getResult3()=" + n_result3 + "n_Total=" + n_Total
		// + "n_num=" + n_num);

		return str_result;

	}

	public String getResult4() {
		double n_num = 0;
		double n_result4 = 0;
		double n_result1 = 0;
		double n_result2 = 0;
		double n_high_num = 0;
		double n_Total = 0;
		double n_high_money = 0;
		String str_result = "";
		try {
			n_num = Double.valueOf(num);
			n_high_num = Double.valueOf(high_num);
			n_Total = Double.valueOf(total);
			n_high_money = Double.valueOf(high_money);

			n_result1 = Double.valueOf(getResult1().replace("'", ""));
			n_result2 = Double.valueOf(getResult2().replace("'", ""));
			// 多人 = 总人
			if (n_num == n_high_num || n_high_num == 0 || n_high_money == 0) {
				n_result4 = Double.valueOf(getResultForSame().replace("'", ""))
						* n_num - n_Total;
			} else if (n_result1 <= 0) { // 少钱 < 0
				n_result4 = Double.valueOf(getResultForSame().replace("'", ""))
						* n_high_num - n_Total;
			} else {
				n_result4 = n_result1 * (n_num - n_high_num) + n_result2
						* n_high_num - n_Total;
			}
			str_result = ConScience.FormatCurrency(String.valueOf(n_result4)
					.toString(), 0);
		} catch (NumberFormatException e) {

		} catch (NullPointerException e) {
		}

		return str_result;
	}

	public String getResultForSame() {
		double n_result = 0;
		double n_high_money = 0;
		double n_high_num = 0;
		double n_Total = 0;
		double n_num = 0;
		double n_result1 = 0;
		String str_result = "";
		double temp;
		try {
			n_high_money = Double.valueOf(high_money);
			n_high_num = Double.valueOf(high_num);
			n_num = Double.valueOf(num);
			n_Total = Double.valueOf(total);

			n_result1 = Double.valueOf(getResult1().replace("'", ""));
			// 多人 = 总人
			if (n_num == n_high_num || n_high_num == 0 || n_high_money == 0) {
				temp = (double) n_Total / (double) n_num;
				n_result = (double) Math.ceil(temp);
			} else if (n_result1 <= 0) { // 少钱 < 0
				temp = (double) n_Total / (double) n_high_num;
				n_result = (double) Math.ceil(temp);
			}

			str_result = ConScience.FormatCurrency(String.valueOf(n_result)
					.toString(), 0);
		} catch (NumberFormatException e) {

		} catch (NullPointerException e) {
		}

		return str_result;
	}

}
