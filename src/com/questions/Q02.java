package com.questions;

import java.util.Random;



/**
 * ��Ѷ���⣺
 * 1�ڸ�����ȡǰ1��������
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
		
		// ��������
		array = new int[totalSize];
		for(int i=totalSize-1; i>=0; i--){
			array[i] = rnd.nextInt();
//			System.out.println(array[i]);
		}
		
		// ��������ȡ����ǰ1000����
		int[] result = getBiggerNum(array, 1000);
		
		runTime = System.currentTimeMillis()-runTime;
		System.out.println("time:"+runTime+"ms");
		
		for(int i=0; i<result.length; i++){
			System.out.print(result[i]+",");
		}
	}

	/**
	 * ��ȡ����������
	 * @param array
	 * @param n
	 * @return
	 */
	private static int[] getBiggerNum(int[] array, int n){
		
		// ������ڱ����С��ֱ�ӷ���
		if(array.length<=n){
			return array;
		}
		
		// ��ȡǰn����Ϊ�ȽϵĻ���
		int[] result = new int[n];
		for(int i=0; i<n; i++){
			result[i] = array[i];
		}
		
		// ��������ʹ���С��������
		InsertionSort(result);
		int min = 0;
		
		// ��n+1��ʼ������
		int size = array.length;
		for(int i=n; i<size; i++){
			
			// ������������е���С�������滻
			if(array[i]>result[min]){
				result[min] = array[i];
				// ��Ҫʱ������������
				if(result[min]>result[min+1]){
					InsertionSort(result);
				}
			}
			
		}
		
		return result;
	}
	
	/**
	 * ��������
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
