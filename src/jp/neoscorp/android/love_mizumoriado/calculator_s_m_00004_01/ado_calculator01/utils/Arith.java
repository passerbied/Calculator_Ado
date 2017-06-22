package jp.neoscorp.android.love_mizumoriado.calculator_s_m_00004_01.ado_calculator01.utils;

import java.math.BigDecimal;

import android.util.Log;

/**
 * @author i-chou
 * @version 1.0
 * @since 2012-07-01
 */

public final class Arith {
	// 割り算小数のデフォルト精度
	private static final int DEF_DIV_SCALE = 2;

	// 初期化できない
	private Arith() {
	}

	/**
	 * 精確的な足し算
	 * 
	 * @param v1
	 *            加数1
	 * @param v2
	 *            加数2
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 精確的な引き算
	 * 
	 * @param v1
	 *            元数
	 * @param v2
	 *            引き数
	 * @return 引き算の結果
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 精確的な掛け算
	 * 
	 * @param v1
	 *            元数
	 * @param v2
	 *            掛け数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * デフォルトの割り算
	 * 
	 * @param v1
	 *            元数
	 * @param v2
	 *            割数
	 * @return 結果
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 指定した精度の割り算
	 * 
	 * @param v1
	 *            元数
	 * @param v2
	 *            割数
	 * @param scale
	 *            精度
	 * @return 結果
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 四捨五入
	 * 
	 * @param v
	 *            元数
	 * @param scale
	 *            精度
	 * @return 結果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 四捨五入
	 * 
	 * @param v
	 *            元数
	 * @param scale
	 *            精度
	 * @return 結果
	 */
	public static BigDecimal round_for_decimal(BigDecimal v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal one = new BigDecimal("1");
		return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 余数計算
	 * 
	 * @param v1
	 *            元数
	 * @param v2
	 *            割数
	 * @return 結果
	 */
	public static int mod(double v1, double v2) {
		int result = 0;
		int b1 = (int) v1;
		int b2 = (int) v2;
		try {
			result = b1 % b2;
		} catch (Exception e) {
		}
		return result;
	}
}
