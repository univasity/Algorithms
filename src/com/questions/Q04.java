package com.questions;

/**
 * 
 * 数组中有n个数据，要将它们顺序循环向后移动k位，
 * 即前面的元素向后移动k位，后面的元素则循环向前
 * 移k位，例如:
 * 0、1、2、3、4
 * 
 * 循环移动3位后为:
 * 2、3、4、0、1
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
	 * 实现思路：
	 * 新建一个储存空间，利用求余的方式实现循环。
	 * 
	 * 算法时间复杂度为O(n),，空间复杂度为O(2n)。
	 * 
	 * @param array
	 * @param k
	 */
	public void move(int[] array, int k){
		int n = array.length;
		int loop = k-1;
		int temp = 0; // 一个辅助空间
		
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
	 * 实现思路：
	 * 每次移动一位，用一个变量记录第一位，以后的每位往前
	 * 移。然后将记录的第一位放到最后。重复操作，直到移动
	 * 的次数为k。
	 * 
	 * 算法时间复杂度为O(n*n)，空间复杂度为O(1)。
	 * 
	 * @param array
	 * @param k
	 */
	public void move1(int[] array, int k){
		int n = array.length; 
		int[] result = new int[n];
		
		for (int i = 0; i < n; i++) {
			result[(k + i) % n] = array[i]; // 求余解决循环后移  
		}
	}
	
	/**
	 * 实现思路：
	 * 这个问题其实就是将一个有n+m个元素的数组的前n个
	 * 元素和后m个元素交换位置。《编程珠玑》里面有一个
	 * 很妙的算法，是这样的，先将前n个元素位置反转，再
	 * 将后m个元素的位置反转，最后整个数组倒转，这样就
	 * 完成了。
	 * 
	 * 例如：
	 * 有数组1，2，3，4，5，6，7，现在要将5，6，7移到数
	 * 组最前面，即得到5，6，7，1，2，3，4。
	 * 
	 * 可以这样做：
	 * 第一步，反转前4个元素的位置：4，3，2，1，5，6，7
	 * 第二步，反转后3个元素的位置：4，3，2，1，7，6，5
	 * 第三步，反转整个数组      ：5，6，7，1，2，3，4  
	 * 
	 * 很明显，时间复杂度O(n)，空间复杂度O(1)。
	 * 
	 * @param array
	 * @param k
	 */
	public static void move2(int[] array, int k){
	      
	      int n = array.length;
	      
	      int temp = 0; // 一个辅助空间
	      
	      // 将前半部分逆序, O(k/2) 
	      for(int i=0; i<k/2; i++){
	            temp = array[i];
	            array[i] = array[k-i-1];
	            array[k-i-1] = temp;
	      }
	      
	      // 将后半部分逆序, O((n-k)/2)
	      for(int i=0; i<(n-k)/2; i++){
	            temp = array[i+k];
	            array[i+k] = array[n-i-1];
	            array[n-i-1] = temp;
	      }
	      
	      // 将整个数组逆序, O(n/2)
	      for(int i=0; i<n/2; i++){
	            temp = array[i];
	            array[i] = array[n-i-1];
	            array[n-i-1] = temp;
	      }
	      
	      // O(k/2) + O((n-k)/2) + O(n/2) = O(k/2+(n-k)/2+n/2) = O(n)
	      
	}
	
}
