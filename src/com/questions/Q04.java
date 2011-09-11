package com.questions;

/**
 * 
 * ��������n�����ݣ�Ҫ������˳��ѭ������ƶ�kλ��
 * ��ǰ���Ԫ������ƶ�kλ�������Ԫ����ѭ����ǰ
 * ��kλ������:
 * 0��1��2��3��4
 * 
 * ѭ���ƶ�3λ��Ϊ:
 * 2��3��4��0��1
 * 
 * @author univasity
 * 
 */
public class Q04 {

	static int[] array = {
		0, 1, 2, 3, 4, 5, 6
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		move2(array, 3);
		printArray(array);
	}
	
	static void printArray(int[] array){
		for(int num : array){
			System.out.print(num+",");
		}
		System.out.println();
	}

	/**
	 * ʵ��˼·��
	 * �½�һ������ռ䣬��������ķ�ʽʵ��ѭ����
	 * 
	 * �㷨ʱ�临�Ӷ�ΪO(n),���ռ临�Ӷ�ΪO(2n)��
	 * 
	 * @param array
	 * @param k
	 */
	public void move(int[] array, int k){
		int n = array.length;
		int loop = k-1;
		int temp = 0; // һ�������ռ�
		
		for(int i=0; i<loop; i++){ // T(n) = 0~(n-1)
			
			temp = array[0];
			
			for(int j=0; j<n-1; j++){ // T(n) = n-1
				
				array[j] = array[j+1];
			
			}
			
			array[n-1] = temp;
			
		}
		
		// O((n-1)*(n-1)) = O(n*n)
		
	}
	
	/**
	 * ʵ��˼·��
	 * ÿ���ƶ�һλ����һ��������¼��һλ���Ժ��ÿλ��ǰ
	 * �ơ�Ȼ�󽫼�¼�ĵ�һλ�ŵ�����ظ�������ֱ���ƶ�
	 * �Ĵ���Ϊk��
	 * 
	 * �㷨ʱ�临�Ӷ�ΪO(n*n)���ռ临�Ӷ�ΪO(1)��
	 * 
	 * @param array
	 * @param k
	 */
	public void move1(int[] array, int k){
		int n = array.length; 
		int[] result = new int[n];
		
		for (int i = 0; i < n; i++) {
			result[(k + i) % n] = array[i]; // ������ѭ������  
		}
	}
	
	/**
	 * ʵ��˼·��
	 * ���������ʵ���ǽ�һ����n+m��Ԫ�ص������ǰn��
	 * Ԫ�غͺ�m��Ԫ�ؽ���λ�á���������ᡷ������һ��
	 * ������㷨���������ģ��Ƚ�ǰn��Ԫ��λ�÷�ת����
	 * ����m��Ԫ�ص�λ�÷�ת������������鵹ת��������
	 * ����ˡ�
	 * 
	 * ���磺
	 * ������1��2��3��4��5��6��7������Ҫ��5��6��7�Ƶ���
	 * ����ǰ�棬���õ�5��6��7��1��2��3��4��
	 * 
	 * ������������
	 * ��һ������תǰ4��Ԫ�ص�λ�ã�4��3��2��1��5��6��7
	 * �ڶ�������ת��3��Ԫ�ص�λ�ã�4��3��2��1��7��6��5
	 * ����������ת��������      ��5��6��7��1��2��3��4  
	 * 
	 * �����ԣ�ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(1)��
	 * 
	 * @param array
	 * @param k
	 */
	public static void move2(int[] array, int k){
	      
	      int n = array.length;
	      
	      int temp = 0; // һ�������ռ�
	      
	      // ��ǰ�벿������, O(k/2) 
	      for(int i=0; i<k/2; i++){
	            temp = array[i];
	            array[i] = array[k-i-1];
	            array[k-i-1] = temp;
	      }
	      
	      // ����벿������, O((n-k)/2)
	      for(int i=0; i<(n-k)/2; i++){
	            temp = array[i+k];
	            array[i+k] = array[n-i-1];
	            array[n-i-1] = temp;
	      }
	      
	      // ��������������, O(n/2)
	      for(int i=0; i<n/2; i++){
	            temp = array[i];
	            array[i] = array[n-i-1];
	            array[n-i-1] = temp;
	      }
	      
	      // O(k/2) + O((n-k)/2) + O(n/2) = O(k/2+(n-k)/2+n/2) = O(n)
	      
	}
	
}
