package com.demos.minimax;

import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

/**
 * 限制了搜索深度的Minimax算法示例
 * 
 * Max - 'x'
 * Min - 'o'
 */
public class MiniMaxDemo {
	
	static final char x = 'x';
	static final char o = 'o';
	static final char empty = '\0';
	
	static final int INFINITY = 100;
	static final int WIN = +INFINITY;
	static final int LOSE = -INFINITY;
	static final int DOUBLE_LINK = INFINITY/2;
	static final int DRAW = 0;
	static final int INPROGRESS = 1;
	
	static final int[][] WIN_STATUS = {
		{0, 1, 2},
		{3, 4, 5},
		{6, 7, 8},
		{0, 3, 6},
		{1, 4, 7},
		{2, 5, 8},
		{0, 4, 8},
		{2, 4, 6}
	};
	
	// 开局时，每个位置的估值
	static final int[] INITIAL_POS_VALUE = {
		3, 2, 3,
		2, 4, 2,
		3, 2, 3
	};
	
	// test
	private int searchDeep;
	private int callTimes;
	
	public static void main(String[] args){
		
		MiniMaxDemo demo = new MiniMaxDemo();
		char[] board = new char[9];
		
		int gameState = INPROGRESS;
		while(!demo.isGameOver(board)){
			
			int nextPos = demo.getNextMove(board);
			System.out.println("nextPos:"+nextPos);
			board[nextPos] = x;
			printBoard(board);
			
			if(demo.isGameOver(board)){
				break;
			}
			
			Scanner input = new Scanner(System.in);
			int playerChoice = input.nextInt();
			while(playerChoice<0 || playerChoice>=9 || board[playerChoice]!=empty){
				playerChoice = input.nextInt();
			}
			board[playerChoice] = o;
			printBoard(board);
			
		}
		gameState = demo.gameState(board);
		switch(gameState){
			case WIN:
				System.out.println("You Lose!");
				break;
			
			case LOSE:
				System.out.println("You Win!");
				break;
			
			case DRAW:
				System.out.println("Draw!");
				break;
		}
		
	}
	
	/**
	 * 打印棋盘
	 */
	private static void printBoard(char[] board){
		int index = 0;
		for(char chess : board){
			index++;
			if(chess==empty){
				System.out.print("  ,");
			}else{
				System.out.print(" "+chess+',');
			}
			if(index%3==0){
				System.out.println();
			}
		}
		System.out.println("\n---------------------\n");
	}
	
	/**
	 * 获取'x'的下一步走法
	 */
	public int getNextMove(char[] board){
		int nextPos = minimax(board, 6);
		System.out.println("searchDeep:"+(6-searchDeep)+", callTimes:"+callTimes);
		return nextPos;
	}
	
	/**
	 * 判断游戏是否结束了，胜利、失败或和局
	 */
	public boolean isGameOver(char[] board){
		int gameState = gameState(board);
		return (gameState==WIN || gameState==LOSE || gameState==DRAW);
	}
	
	/**
	 * 以'x'的角度来考虑的极小极大算法
	 */
	public int minimax(char[] board, int depth){
		int[] bestMoves = new int[9];
		int index = 0;
		
		// test
		searchDeep = depth;
		callTimes = 0;
		
		int bestValue = -INFINITY;
		for(int pos=0; pos<9; pos++){
			
			if(board[pos]==empty){
				board[pos] = x;
				
				int value = min(board, depth);
				//System.out.println(pos+":"+value);
				if(value>bestValue){
					bestValue = value;
					index = 0;
					bestMoves[index] = pos;
				}else
				if(value==bestValue){
					index++;
					bestMoves[index] = pos;
				}
				
				board[pos] = empty;
			}
			
		}
		
		System.out.println("index:"+index+" bestValue:"+bestValue);
		if(index>1){
			index = (new Random(System.currentTimeMillis()).nextInt()>>>1)%index;
		}
		return bestMoves[index];
		
	}
	
	/**
	 * 估值函数，提供一个启发式的值，决定了游戏AI的高低
	 */
	public int gameState(char[] board){
		int result = INPROGRESS;
		boolean isFull = true;
		int sum = 0;
		int index = 0;
		// is game over?
		for(int pos=0; pos<9; pos++){
			char chess = board[pos];
			if(empty==chess){
				isFull = false;
			}else{
				sum += chess;
				index = pos;
			}
		}
		
		// 如果是初始状态，则使用开局库
		boolean isInitial = (sum==x||sum==o);
		if(isInitial){
			return (sum==x?1:-1)*INITIAL_POS_VALUE[index];
		}
		
		// is Max win/lose?
		for(int[] status : WIN_STATUS){
			char chess = board[status[0]];
			if(chess==empty){
				break;
			}
			int i = 1;
			for(; i<status.length; i++){
				if(board[status[i]]!=chess){
					break;
				}
			}
			if(i==status.length){
				result = chess==x ? WIN : LOSE;
				break;
			}
		}
		
		if(result!=WIN & result!=LOSE){
			
			if(isFull){
				// is draw
				result = DRAW;
			}else{
				// check double link
				// finds[0]->'x', finds[1]->'o'
				int[] finds = new int[2];
				for(int[] status : WIN_STATUS){
					char chess = empty;
					boolean hasEmpty = false;
					int count = 0;
					for(int i=0; i<status.length; i++){
						if(board[status[i]]==empty){
							hasEmpty = true;
						}else{
							if(chess==empty){
								chess = board[status[i]];
							}
							if(board[status[i]]==chess){
								count++;
							}
						}
					}
					if(hasEmpty && count>1){
						if(chess==x){
							finds[0]++;
						}else{
							finds[1]++;
						}
					}
				}
				
				// check two in one line
				if(finds[1]>0){
					result = -DOUBLE_LINK;
				}else
				if(finds[0]>0){
					result = DOUBLE_LINK;
				}
				
			}
			
		}
		
		return result;
		
	}
	
	/**
	 * 对于'x'，估值越大对其越有利
	 */
	public int max(char[] board, int depth){
		
		int evalValue = gameState(board);
		
		boolean isGameOver = (evalValue==WIN || evalValue==LOSE || evalValue==DRAW);
		if(depth==0 || isGameOver){
			// test
			searchDeep = Math.min(searchDeep, depth);
			return evalValue;
		}
		
		// test
		callTimes++;
		
		int bestValue = -INFINITY;
		for(int pos=0; pos<9; pos++){
			
			if(board[pos]==empty){
				// try
				board[pos] = x;
				
				// maximixing
				bestValue = Math.max(bestValue, min(board, depth-1));
				
				// reset
				board[pos] = empty;
			}
			
		}
		
		return evalValue;
		
	}
	
	/**
	 * 对于'o'，估值越小对其越有利
	 */
	public int min(char[] board, int depth){
		
		int evalValue = gameState(board);
		
		boolean isGameOver = (evalValue==WIN || evalValue==LOSE || evalValue==DRAW);
		if(depth==0 || isGameOver){
			// test
			searchDeep = Math.min(searchDeep, depth);
			return evalValue;
		}
		
		// test
		callTimes++;
		
		int bestValue = +INFINITY;
		for(int pos=0; pos<9; pos++){
			
			if(board[pos]==empty){
				// try
				board[pos] = o;
				
				// minimixing
				bestValue = Math.min(bestValue, max(board, depth-1));
				
				// reset
				board[pos] = empty;
			}
			
		}
		
		return evalValue;
		
	}
	
}