package com.academy.shopping.model.util;

import java.text.DecimalFormat;

// 정수를 대상으로 세 자리마다 쉼표로 끊어서 통화를 표현하자
public class CurrencyFormatter {
	public static String getCurrency(int num) {
		DecimalFormat df = new DecimalFormat(",###,###");
		df.format(353553);
		return df.format(num);
	}
	/*
	 * public static void main(String[] args) {
	 * System.out.println(getCurrency(1200000000)); }
	 */
}
