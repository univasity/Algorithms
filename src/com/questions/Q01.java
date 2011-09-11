package com.questions;


/**
 * �㷨�����⣺
 * �ù�˾�������1����Ҫ����10���������ꡣ
 * ��Ŀ���£���1��2��2��3��4��5���������֣���javaдһ
 * ��main��������ӡ�����в�ͬ�����У��磺512234��412345��
 * ��Ҫ��"4"�����ڵ���λ��"3"��"5"���������� 
 * 
 * @author univasity
 *
 */
public class Q01 {

	static final String[] IncludeNums = {
		"1", "2", "3", "4", "5"
	};
	
	static boolean isInvalidNumber(String num){
		
		// �Ƿ�������е�����
		for(int i=IncludeNums.length-1; i>=0; i--){
			if(num.indexOf(IncludeNums[i])==-1){
				return false;
			}
		}
		
		// �Ƿ��������2
		if(num.lastIndexOf("2")==num.indexOf("2")){
			return false;
		}
		
		// 4�����ڵ���λ
		if(num.charAt(2)=='4'){
			return false;
		}
			
		// 3��5��������
		if(num.indexOf("53")!=-1 || num.indexOf("35")!=-1){
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args){
		
		int count = 0;
		
		for(int i=122345; i<=543221; i++){
			if(isInvalidNumber(String.valueOf(i))){
				System.out.println(i);
				count++;
			}
		}
		
		System.out.println("��:"+count);
		
	}
	
}
