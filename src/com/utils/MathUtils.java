package com.utils;

public class MathUtils {

	/**
	 * �����Լ��
	 * - Euclidean Algorithm (շת�����)
	 * - ���Լ��=��������С��������������Լ��
	 * - ����ֻ���ǷǸ�����
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
