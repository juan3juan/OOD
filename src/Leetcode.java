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

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

public class Leetcode {
	public boolean wordBreak(String s, List<String> wordDict) {
		return wordBreakHelper(s, wordDict, 0, new HashSet<Integer>());
	}

	public boolean wordBreakHelper(String s, List<String> wordDict, int d, Set<Integer> cache) {
		if (d == s.length())
			return true;
		if (cache.contains(d))
			return false;
		for (String word : wordDict) {
			if (s.startsWith(word, d)) {
				if (wordBreakHelper(s, wordDict, d + word.length(), cache))
					return true;
				cache.add(d + word.length()); // prove this d+word.length() has tried and fail, no need to try, save
												// time
			}
		}
		return false;
	}

	public static void main(String[] args) {

		int res = climbStairs(4);
		System.out.println(res);
	}

}
