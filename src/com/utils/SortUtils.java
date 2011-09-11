package com.utils;


public class SortUtils {

	/**
	 * 冒泡排序
	 * @param array
	 */
	public static void BubbleSort(int[] array){
		
		int size = array.length;
		for(int i=0; i<size-1; i++){
			boolean hasSwap = false;
			for(int j=i+1; j<size; j++){
				if(array[j]<array[i]){
					// swap
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
					hasSwap = true;
				}
			}
			if(!hasSwap){
				break;
			}
		}
		
	}
	
	/**
	 * 插入排序
	 * @param array
	 */
	public static void InsertionSort(int[] array){
		
		int size = array.length;
		for(int i=1; i<size; i++){
			
			int key = array[i];
			
			int j = i-1;
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
	
	/**
	 * 选择排序
	 * @param array
	 */
	public static void SelectSort(int[] array){
		
		int size = array.length;
		for(int i=0; i<size-1; i++){
			int minIndex = i;
			for(int j=i+1; j<size; j++){
				if(array[j]<array[i]){
					minIndex = j;
				}
			}
			if(minIndex!=i){
				int temp = array[minIndex];
				array[minIndex] = array[i];
				array[i] = temp;
			}
		}
		
	}
	
	/**
	 * 希尔排序 
	 * @param array
	 */
	public static void ShellSort(int[] array){
		
		int count = 0;
		
		int size = array.length;
		
		for(int increment=size/2; increment>0; increment/=2){
			
			for(int i=increment; i<size; i++){
				
				int key = array[i];
				
				int j=i;
				for(; j>=increment; j-=increment){
					count++;
					if(key<array[j-increment]){
						array[j] = array[j-increment];
					}else{
						break;
					}
					
				}
				
				array[j] = key;
				
			}
			
		}
		
		System.out.println("count:"+count);
		
	}
	
	static int count = 0;
	
	/**
	 * 快速排序
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void QuickSort(int[] array, int low, int high){
		
		if(low<high){
			int n = Partition(array, low, high);
			QuickSort(array, low, n);
			QuickSort(array, n+1, high);
		}
		
	}
	
	private static int Partition(int[] array, int low, int high){
		
		int pivot = array[low];
		int temp = 0;
		
		while(low<high){
			
			while(low<high && array[high]>=pivot){
				high--;
			}
			temp = array[high];
			array[high] = array[low];
			array[low] = temp;
			count++;
			
			while(low<high && array[low]<=pivot){
				low++;
			}
			temp = array[high];
			array[high] = array[low];
			array[low] = temp;
			count++;
			
		}
		
		return low;
		
	}
	
	/**
	 * 归并排序
	 * @param array
	 */
	public static void MergeSort(int[] array){
	      int size = array.length;
	      int step=2; // 设定一个步长
	      for(; step<=size; step*=2){ // 步长每次以2的指数级增长
	            
	            for(int i=0; i<size; i+=step){
	                  
	                  if(i+step<=size){
	                        MergeSort(array, i, i+step/2-1, i+step-1);
	                  }
	                  
	            }
	      }
	      // 对越出的部分(非2的次幂)进行处理
	      int lost = size%(step/2);
	      if(lost!=0){
	            MergeSort(array, 0, size-lost-1, size-1);
	      }
	}

	/**
	 * 对有序的子序列进行整合
	 * @param array
	 * @param start
	 * @param mid
	 * @param end
	 */
	private static void MergeSort(int[] array, int start, int mid, int end){
	      
	      int i=start;
	      int j=mid+1;
	      int index = 0;
	      
	      // 分配一个新的空间作为临时储存
	      int[] temp = new int[end-start+1];
	      // 对有序列进行排序
	      while(i<=mid && j<=end){
	            temp[index++] = array[i]<array[j]?array[i++]:array[j++];
	      }
	      while(i<=mid){
	            temp[index++] = array[i++];
	      }
	      while(j<=end){
	            temp[index++] = array[j++];
	      }
	      
	      // 数据转移
	      index = 0;
	      for(int k=start; k<=end; k++){
	            array[k] = temp[index++];
	      }
	      
	}
	
}
