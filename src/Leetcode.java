import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Leetcode {
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int left=0, right=n-1, top=0, bottom=n-1;
		while(n>1) {
			for(int i=0; i<n-1; i++) {
				int temp = matrix[top][left+i];
				matrix[top][left+i] = matrix[bottom-i][left];
				matrix[bottom-i][left] = matrix[bottom][right-i];
				matrix[bottom][right-i] = matrix[top+i][right];
				matrix[top+i][right] = temp;
			}
			left++;
			right--;
			top++;
			bottom--;
			n -= 2;
		}
	}
    
	public static void main(String[] args) {

		String res = getLongestCommonSubString("rasw", "asw");
		System.out.println(res);
	}

}
