package test;

import java.util.*;

public class test31_40 {

	public static void main(String args[]) {
		//String a = "1";
		//String b = "  1.1";
		char[][] x = { { '0', '0', '1', '0' }, { '1', '1', '1', '0' },
				{ '1', '1', '1', '0' }, { '1', '1', '1', '0' },
				{ '1', '1', '0', '0' } };
		test31_40 instance = new test31_40();
		int[] pp = { -1, -1 };
		// System.out.print(a.substring(4));
		//System.out.print(instance.containsNearbyAlmostDuplicate(pp, 1, -1));
/*		ListNode A = instance.new ListNode(1);
		ListNode B = instance.new ListNode(1);
		ListNode C = instance.new ListNode(2);
		ListNode D = instance.new ListNode(1);
		A.next=B;B.next=C;
		instance.isPalindrome(A);*/
		WordDictionary wordDictionary = instance.new WordDictionary();
		wordDictionary.addWord("and");
		wordDictionary.addWord("at");
		wordDictionary.addWord("an");
		wordDictionary.addWord("add");
		wordDictionary.addWord("bat");
//		System.out.print(wordDictionary.search("a"));
		System.out.print(wordDictionary.search(".at"));
//		System.out.print(wordDictionary.search("an."));	
	}
	
	/////////////////////////////
	public class WordDictionary {

		private class Node{
			private ArrayList<Integer> childslist;
			private Node childs[];////此处用数组实现，当然也可以map或list实现以节省空间
			private boolean isLeaf;///是否为单词节点
			public Node(){
				isLeaf=false;
				childs=new Node[26]; 
				childslist = new ArrayList<Integer>();
			}
		}	

	    
	    private Node root;///树根   
		public WordDictionary(){
			///初始化trie 树
			root=new Node();
		}

	    // Adds a word into the data structure.
	    public void addWord(String words) {
			addWord(this.root, words);        
	    }
	    
	    private void addWord(Node root,String words){
			char[] chrs=words.toCharArray();		
			for(int i=0,length=chrs.length; i<length; i++){
				int index=chrs[i]-'a';
				if(root.childs[index]==null){
					root.childs[index]=new Node();
					root.childslist.add(index);
				}	
				if(i==length-1){
					root.childs[index].isLeaf=true;
				}
				root=root.childs[index];
			}
		}
	    
	    // Returns if the word is in the data structure. A word could
	    // contain the dot character '.' to represent any one letter.
	    public boolean search(String word) {
			return search(this.root, word);        
	    }
	    
	    private boolean search(Node root,String word){
	        if(word.length()==0)return root.isLeaf;
			char[] chs=word.toLowerCase().toCharArray();
			for(int i=0; i<chs.length;i++){

				if(chs[i]=='.'){
					boolean result = false;
					if(root.childslist.size()==0){
						return false;
					}
					for(int j=0;j<root.childslist.size();j++){
						int k=root.childslist.get(j);
						if(root.childs[k]!=null){
							result = search(root.childs[k],word.substring(i+1));
							if(result){
								return true;
							}
						}
					}
					return false;
				}
				int index=chs[i]-'a';
				if(root.childs[index]==null){
					return false;
				}			
				root=root.childs[index];
			}
			return root.isLeaf;
		}
	}
	/////////////////////////////

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	
	//计算到n为止的数有多少1.
/*    public int countDigitOne(int n) {
        int result = 0;
        int temp = n;
        int count = 0;
        while(temp!=0){
        	temp/=10;
        	count++;
        }
        
    }*/
    
    public String majorityElement1(String s) {
    	char cur = 0; int k =0;
    	for(int i=0;i<s.length();i++){
    		if(k==0){
    			cur=s.charAt(i);
    			if(i==s.length()-1){
    				break;
    			}
    			k++;
    			i++;
    		}
    		if(s.charAt(i)==cur){
    			k++;
    		}else{
    			k--;
    		}
    	}
    	k=0;
    	for(int i=0;i<s.length();i++){
    		if(cur==s.charAt(i))
    			k++;
    	}
    	if(k>=(s.length()/2))return Character.toString(cur);
    	
    	return null;
    }
    
    
    public int countFullBefore(int n){
    	
    	if(n==1)return 1;
    	
    	int temp = n;
    	int result = 1;
    	while(temp>1){
    		result*=10;
    		temp--;
    	}
    	return result + 10*countFullBefore(n-1);
    }
	
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
    	if(root==p||root==q)return root;
    	
    	Queue<TreeNode> node = new LinkedList<TreeNode>();
    	Queue<String> route = new LinkedList<String>();
    	
    	boolean first = false;
    	boolean second = false;
    	String result1 = "";
    	String result2 = "";
    	
    	node.add(root);
    	route.add("");
    	while(!(first&second)&&!node.isEmpty()){
    		TreeNode temp = node.poll();
    		String tempRoute = route.poll();
    		if(temp.val==p.val){
    			first = true;
    			result1 = tempRoute;
    		}
    		if(temp.val==q.val){
    			second = true;
    			result2 = tempRoute;
    		}
    		if(temp.left!=null){
    			node.add(temp.left);
    			route.add(tempRoute+"1");
    		}
    		if(temp.right!=null){
    			node.add(temp.right);
    			route.add(tempRoute+"2");
    		}
    	}
    	
    	for(int i =0;i<Math.min(result1.length(), result2.length());i++){
    		char temp1 = result1.charAt(i);
    		char temp2 = result2.charAt(i);
    		if(temp1!= temp2){
    			return root;
    		}else{
    			if(temp1=='1'){
    				root = root.left;
    			}else{
    				root = root.right;
    			}
    		}
    	}
    	
    	return root;  
    }
	
    public boolean isPalindrome(ListNode head) {
    	if(head==null||head.next==null)return true;
    	ListNode mid = head;
    	ListNode end = head;
    	boolean isSkip = false;
    	while(end.next!=null&&end.next.next!=null){
    		mid = mid.next;
    		end = end.next.next;
    		if(end.next==null){
    			isSkip = true;
    		}
    	}

    	ListNode palin2 = mid.next;
    	mid.next = null;
    	ListNode temp = head.next;
    	ListNode pre = null;
    	
    	while(head!=null){
    		temp = head.next;
    		head.next=pre;
    		pre = head;
    		head = temp;
    	}
    	ListNode palin1 = pre;
    	
    	if(isSkip){
    		palin1= palin1.next;
    	}
    	while(palin1!=null){
    		if(palin1.val!=palin2.val)return false;
    		palin1 = palin1.next;
    		palin2 = palin2.next;
    	}
    	
    	
    	return true;  
    }
	
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     
    	if(p==q)
    		return q;
    	if(root==p||root==q){
    		return root;
    	}
    	if(root.val<p.val&&root.val<q.val){
    		return lowestCommonAncestor(root.right,p,q);
    	}
    	if(root.val>p.val&&root.val>q.val){
    		return lowestCommonAncestor(root.left,p,q);
    	}
    	
    	return root;
    }

	// Implement Queue using Stacks

	Stack<Integer> mainStack = new Stack<Integer>();
	Stack<Integer> tempStack = new Stack<Integer>();

	// Push element x to the back of queue.
	public void push(int x) {
		while (!mainStack.isEmpty()) {
			tempStack.push(mainStack.pop());
		}
		mainStack.push(x);
		while (!tempStack.isEmpty()) {
			mainStack.push(tempStack.pop());
		}
	}

	// Removes the element from in front of queue.
	public void pop() {
		mainStack.pop();
	}

	// Get the front element.
	public int peek() {
		return mainStack.peek();
	}

	// Return whether the queue is empty.
	public boolean empty() {
		return mainStack.isEmpty();
	}

	// Intersection of Two Linked Lists
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		ListNode EndA = headA;
		ListNode EndB = headB;
		int depA = 1;
		int depB = 1;
		while (EndA.next != null) {
			EndA = EndA.next;
			depA++;
		}
		while (EndB.next != null) {
			EndB = EndB.next;
			depB++;
		}
		if (EndA != EndB)
			return null;
		while (depA > depB) {
			depA--;
			headA = headA.next;
		}
		while (depA < depB) {
			depB--;
			headB = headB.next;
		}
		while (headA != headB) {
			headA = headA.next;
			headB = headB.next;
		}

		return null;
	}

	// find t differ in k differ place
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		TreeSet<Integer> treeset = new TreeSet<>();
		for (int i = 0; i < nums.length; ++i) {
			Integer floor = nums[i] - t;
			Integer ceiling = nums[i] + t + 1;
			if ((long) nums[i] - (long) (t) < -2147483647)
				floor = Integer.MIN_VALUE;
			if ((long) nums[i] + (long) (t) + 1 > 2147483646)
				ceiling = Integer.MAX_VALUE;
			if (t >= 0 && treeset.subSet(floor, ceiling).size() != 0)
				return true;
			treeset.add(nums[i]);
			if (i >= k)
				treeset.remove(nums[i - k]);
		}
		return false;
	}

	// Given a 2D binary matrix filled with 0's and 1's, find the largest square
	// containing all 1's and return its area.
	public int maximalSquare(char[][] matrix) {
		int result = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == '1') {
					int temp = expendSquare(matrix, i, j);
					if (temp > result) {
						result = temp;
					}
				}
			}
		}
		return result;
	}

	public int expendSquare(char[][] matrix, int i, int j) {
		int k = 1;
		while (i + k < matrix.length && j + k < matrix[i].length) {
			for (int m = 0; m <= k; m++) {
				if (matrix[i + k][j + m] != '1' || matrix[i + m][j + k] != '1') {
					return k * k;
				}
			}
			k++;
		}
		return k * k;
	}

	// determine a number is power of two.
	public boolean isPowerOfTwo(int n) {
		boolean end = false;
		if (n < 0 || n == 1) {
			return false;
		}
		while (n != 0) {
			if ((n & 1) == 1) {
				if (end)
					return false;
				end = true;
			}
			n >>= 1;
		}

		return true;

	}

	// calculate with + - * /
	public int calculate1(String s) {
		int result = 0;
		Deque<Integer> number = new LinkedList<Integer>();
		Deque<Character> sign = new LinkedList<Character>();
		int num = 0;
		int pre = 0;
		boolean ismul = false;
		boolean isdivide = false;

		Queue<Character> str = new LinkedList<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				str.add(s.charAt(i));
			}
		}

		while (!str.isEmpty()) {
			char cur = str.poll();

			if (!Character.isDigit(cur) || str.isEmpty()) {
				if (str.isEmpty()) {
					num = num * 10 + cur - '0';
				}
				if (ismul) {
					pre = number.pollLast();
					ismul = false;
					num = pre * num;
				}
				if (isdivide) {
					pre = number.pollLast();
					isdivide = false;
					num = pre / num;
				}
				number.add(num);
				if (str.isEmpty()) {
					break;
				}
				num = 0;
				if (cur == '*') {
					ismul = true;
				} else if (cur == '/') {
					isdivide = true;
				} else {
					sign.add(cur);
				}
			} else {
				num = num * 10 + cur - '0';
			}
		}

		result = number.pollFirst();
		while (!sign.isEmpty()) {
			char temp = sign.pollFirst();
			if (temp == '+') {
				result += number.pollFirst();
			} else {
				result -= number.pollFirst();
			}
		}

		return result;
	}

	// Implement a basic calculator to evaluate a simple expression string.
	// use stack to handle sign.
	public static int calculate(String s) {
		Deque<Integer> stack = new LinkedList<>();
		int rs = 0;
		int sign = 1;
		stack.push(1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ')
				continue;
			else if (s.charAt(i) == '(') {
				stack.push(stack.peekFirst() * sign);
				sign = 1;
			} else if (s.charAt(i) == ')')
				stack.pop();
			else if (s.charAt(i) == '+')
				sign = 1;
			else if (s.charAt(i) == '-')
				sign = -1;
			else {
				int temp = s.charAt(i) - '0';
				while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1)))
					temp = temp * 10 + s.charAt(++i) - '0';
				rs += sign * stack.peekFirst() * temp;
			}
		}
		return rs;
	}

	// recursion solution
	/*
	 * public static int calculate(String s) { int result = 0; int num = 0;
	 * boolean isPlus = true; char cur = ' '; for (int i = 0; i < s.length();
	 * i++) { cur = s.charAt(i); if (cur != ' ') { if (cur >= '0' && cur <= '9')
	 * { num = num * 10 + ((int) cur - 48); } else { if (isPlus) { result +=
	 * num; } else { result -= num; } num = 0; if(cur=='+') { isPlus = true; }
	 * if (cur == '-') { isPlus = false; } if (cur == '(') { int part = 1; for
	 * (int j = i + 1; j < s.length(); j++) { if (s.charAt(j) == ')') { part--;
	 * } else if (s.charAt(j) == '(') { part++; } if (part == 0) { num =
	 * calculate(s.substring(i + 1, j)); i = j; break; }
	 * 
	 * } } } } }
	 * 
	 * if (isPlus) { result += num; } else { result -= num; }
	 * 
	 * return result; }
	 */

	// Given an integer array of size n, find all elements that appear more than
	// n/3 times.
	public List<Integer> majorityElement(int[] nums) {
		// Moore voting
		List<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0)
			return result;
		int N = nums.length;
		int cand1 = nums[0], counter1 = 1;
		int i = 1;
		while (i < N && cand1 == nums[i]) {
			counter1++;
			i++;
		}
		int temp = N * 2;
		if (i > temp / 3) {
			result.add(cand1);
			return result;
		}
		int cand2 = nums[i++], counter2 = 1;
		while (i < N) {
			if (nums[i] == cand1) {
				cand1 = nums[i];
				counter1++;
			} else if (nums[i] == cand2) {
				cand2 = nums[i];
				counter2++;
			} else {
				if (counter1 == 0) {
					cand1 = nums[i];
					counter1++;
				} else if (counter2 == 0) {
					cand2 = nums[i];
					counter2++;
				} else {
					counter1--;
					counter2--;
				}
			}
			i++;
		}
		// check cand1 & cand2
		int c1 = 0, c2 = 0;
		for (int j = 0; j < N; j++) {
			if (cand1 == nums[j])
				c1++;
			else if (cand2 == nums[j])
				c2++;
		}
		if (c1 > N / 3)
			result.add(cand1);
		if (c2 > N / 3)
			result.add(cand2);
		return result;
	}

	// 比较版本号。
	public int compareVersion(String version1, String version2) {
		while (version1.length() != 0 || version2.length() != 0) {
			String temp1 = null, temp2 = null;
			if (version1.length() == 0) {
				temp1 = "0";
			}
			for (int i = 0; i < version1.length(); i++) {
				if (version1.charAt(i) == '.') {
					temp1 = version1.substring(0, i);
					version1 = version1.substring(i + 1);
					break;
				}
				if (i == version1.length() - 1) {
					temp1 = version1;
					version1 = "";
					break;
				}
			}

			if (version2.length() == 0) {
				temp2 = "0";
			}
			for (int i = 0; i < version2.length(); i++) {
				if (version2.charAt(i) == '.') {
					temp2 = version2.substring(0, i);
					version2 = version2.substring(i + 1);
					break;
				}
				if (i == version2.length() - 1) {
					temp2 = version2;
					version2 = "";
					break;
				}
			}
			if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
				return 1;
			}

			if (Integer.parseInt(temp1) < Integer.parseInt(temp2)) {
				return -1;
			}
		}

		return 0;
	}
}
