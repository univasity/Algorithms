package com.questions;


/**
 * 算法程序题：
 * 该公司笔试题就1个，要求在10分钟内作完。
 * 题目如下：用1、2、2、3、4、5这六个数字，用java写一
 * 个main函数，打印出所有不同的排列，如：512234、412345等
 * ，要求："4"不能在第三位，"3"与"5"不能相连。 
 * 
 * @author univasity
 *
 */
public class Q01 {

	static final String[] IncludeNums = {
		"1", "2", "3", "4", "5"
	};
	
	static boolean isInvalidNumber(String num){
		
		// 是否包含所有的数字
		for(int i=IncludeNums.length-1; i>=0; i--){
			if(num.indexOf(IncludeNums[i])==-1){
				return false;
			}
		}
		
		// 是否包含两个2
		if(num.lastIndexOf("2")==num.indexOf("2")){
			return false;
		}
		
		// 4不能在第三位
		if(num.charAt(2)=='4'){
			return false;
		}
			
		// 3和5不能相连
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
		
		System.out.println("共:"+count);
		
	}
	
}
