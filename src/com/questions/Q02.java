package com.questions;

import java.util.Random;



/**
 * 腾讯试题：
 * 1亿个数据取前1万大的整数
 * 
 * @author univasity
 * 
 */
public class Q02 {

	static final int totalSize = 1000000;
	static int[] array;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long runTime = System.currentTimeMillis();
		Random rnd = new Random(System.currentTimeMillis());
		
		// 填充随机数
		array = new int[totalSize];
		for(int i=totalSize-1; i>=0; i--){
			array[i] = rnd.nextInt();
//			System.out.println(array[i]);
		}
		
		// 从数组中取最大的前1000个数
		int[] result = getBiggerNum(array, 1000);
		
		runTime = System.currentTimeMillis()-runTime;
		System.out.println("time:"+runTime+"ms");
		
		for(int i=0; i<result.length; i++){
			System.out.print(result[i]+",");
		}
	}

	/**
	 * 获取任意个最大数
	 * @param array
	 * @param n
	 * @return
	 */
	private static int[] getBiggerNum(int[] array, int n){
		
		// 如果大于本身大小，直接返回
		if(array.length<=n){
			return array;
		}
		
		// 获取前n个作为比较的基数
		int[] result = new int[n];
		for(int i=0; i<n; i++){
			result[i] = array[i];
		}
		
		// 进行排序，使其从小到大排列
		InsertionSort(result);
		int min = 0;
		
		// 从n+1开始遍历，
		int size = array.length;
		for(int i=n; i<size; i++){
			
			// 如果比有序列中的最小数大，则替换
			if(array[i]>result[min]){
				result[min] = array[i];
				// 必要时进行重新排序
				if(result[min]>result[min+1]){
					InsertionSort(result);
				}
			}
			
		}
		
		return result;
	}
	
	/**
	 * 插入排序
	 * @param array
	 */
	private static void InsertionSort(int[] array){
		
		int size = array.length;
		for(int i=1; i<size; i++){
			
			int key = array[i];
			
			int j=i-1;
			for(; j>=0; j--){
				
				if(key<array[j]){
					array[j+1] = array[j];
				}else{
					break;
				}
				
			}
			array[j+1] = key;
			
		}
		
	}
	
}
