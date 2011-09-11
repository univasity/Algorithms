package com.utils;

public class MathUtils {

	/**
	 * 求最大公约数
	 * - Euclidean Algorithm (辗转相除法)
	 * - 最大公约数=两数中最小数和两数差的最大公约数
	 * - 参数只能是非负整数
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int calculateGCD(int num1, int num2){
		if(num1<0 || num2<0){
			throw new IllegalArgumentException("the argument can't be negative!");
		}
		if(num1==0){
			return num2;
		}
		if(num2==0){
			return num1;
		}
		
		return calculateGCD(Math.min(num1, num2), Math.abs(num1-num2));
	}
	
}
