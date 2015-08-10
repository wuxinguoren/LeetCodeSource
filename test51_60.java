package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class test51_60 {
	public static void main(String args[]) {
		test51_60 ts = new test51_60();
		// int[][] a = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
		int[] a = { 1,3,2 };
		char[ ][ ] b = {{'1','1'}};
		
		Set<String> st = new HashSet<String>();
		st.add("ted");
		st.add("tex");
		st.add("red");
		st.add("tad");
		st.add("tax");
		st.add("den");
		st.add("rex");
		st.add("pee");
		// {"most","fist","lost","cost","fish"};
		// "lost", "cost", ["most","fist","lost","cost","fish"]
		System.out.print(ts.numIslands(b));
		//ts.nextPermutation(a);
	}

    public int numIslands(char[][] grid) {
    	int count =0;
    	int lengthx= grid.length;
    	if(lengthx==0)return 0;
    	int lengthy= grid[0].length;
    	for(int i=0;i<lengthx;i++){
    		for(int j=0;j<lengthy;j++){
    			if(grid[i][j]=='1'){
    				dps(grid,i,j);
    				count++;
    			}
    		}
    	}
    	return count;
    }
    
    public void dps(char[][] grid, int x, int y){
    		grid[x][y]='0';
    		if(x>0&&grid[x-1][y]=='1'){
    			dps(grid,x-1,y);
    		}
    		if(y>0&&grid[x][y-1]=='1'){
    			dps(grid,x,y-1);
    		}
    		if(x<grid.length-1&&grid[x+1][y]=='1'){
    			dps(grid,x+1,y);
    		}
    		if(y<grid[0].length-1&&grid[x][y+1]=='1'){
    			dps(grid,x,y+1);
    		}
    }
    
    
	
	public void nextPermutation(int[] nums) {
		int length = nums.length - 1;
		if (length == 0)
			return;
		for (int cur = length - 1; cur >= 0; cur--) {
			for (int i = length; i > cur; i--) {
				if (nums[cur] < nums[i]) {
					int temp = nums[cur];
					nums[cur] = nums[i];
					nums[i] = temp;
					Arrays.sort(nums, cur+1, length+1);
					return;
				}
			}
		}
		Arrays.sort(nums);
	}

	public int searchInsert(int[] nums, int target) {

		int start = 0;
		int end = nums.length - 1;

		while (start < end) {
			int temp = nums[(start + end) / 2];
			if (temp == target)
				return (start + end) / 2;
			if (temp < target) {
				start = (start + end) / 2 + 1;
			} else {
				end = (start + end) / 2 - 1;
			}
		}
		return nums[start] > target ? start : start + 1;
	}

	public int[] searchRange(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		int[] result = { -1, -1 };
		while (start < end) {
			int temp = nums[(start + end) / 2];
			if (temp == target) {
				while (nums[start] < target) {
					start++;
				}
				while (nums[end] > target) {
					end--;
				}
				result[0] = start;
				result[1] = end;
				return result;
			}
			if (temp < target) {
				start = (start + end) / 2 + 1;
			} else {
				end = (start + end) / 2 - 1;
			}
		}
		if (nums[start] == target) {
			result[0] = start;
			result[1] = end;
			return result;
		}
		return result;
	}

	private class Node {
		private Node childs[];
		private boolean isLeaf;

		public Node() {
			isLeaf = false;
			childs = new Node[26];
		}
	}

	private Node root;

	private boolean insertnew(Node root, String words) {
		words = words.toLowerCase();
		char[] chrs = words.toCharArray();

		for (int i = 0, length = chrs.length; i < length; i++) {
			int index = chrs[i] - 'a';
			if (root.childs[index] != null) {
				return true;
			} else {
				root.childs[index] = new Node();
			}

			if (i == length - 1) {
				root.childs[index].isLeaf = true;
			}
			root = root.childs[index];
		}
		return false;

	}

	/*
	 * 
	 * Set<String> set = new HashSet<String>();
	 * 
	 * List<String> out = new ArrayList<String>();
	 * 
	 * if (s.length() <= 10) return out;
	 * 
	 * int n = s.length();
	 * 
	 * for (int i = 0; i <= n - 10; ++i) { String cur = s.substring(i, i + 10);
	 * if (!set.add(cur)) { if (!out.contains(cur)) out.add(cur); continue; } }
	 * 
	 * return out; the simply result. which works better at low data capability.
	 */

	public List<String> findRepeatedDnaSequences(String s) {
		root = new Node();
		List<String> result = new ArrayList<String>();
		if (s.length() <= 10)
			return result;

		int n = s.length();

		for (int i = 0; i <= n - 10; ++i) {
			String cur = s.substring(i, i + 10);
			if (insertnew(root, cur)) {
				result.add(cur);
			}
		}
		return result;
	}

	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		List<List<String>> result = new LinkedList<List<String>>();
		boolean notfinish = true;
		List<String> temp = new LinkedList<String>();
		Set<String> set = new HashSet<String>();
		Queue<List<String>> queue = new LinkedList<List<String>>();
		temp.add(start);
		queue.offer(temp);
		int count = 1;
		set.add(start);

		while (count > 0 && notfinish) {
			dict.removeAll(set);
			set.clear();
			while (count > 0) {
				temp = new LinkedList<String>(queue.poll());
				char[] curr = temp.get(temp.size() - 1).toCharArray();
				for (int i = 0; i < curr.length; i++) {
					char tmp = curr[i];
					for (char c = 'a'; c <= 'z'; c++) {
						if (c == tmp)
							continue;
						curr[i] = c;
						String str = new String(curr);
						if (str.equals(end)) {
							notfinish = false;
							List<String> cur = new LinkedList<String>(temp);
							cur.add(str);
							result.add(cur);
						}
						if (dict.contains(str)) {
							List<String> cur = new LinkedList<String>(temp);
							cur.add(str);
							queue.offer(cur);
							set.add(str);
						}
					}
					curr[i] = tmp;
				}
				count--;
			}
			count = queue.size();
		}
		return result;
	}

	public int ladderLength(String start, String end, Set<String> dict) {
		Set<String> set = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		dict.add(end);
		int distance = 1;
		int count = 1;
		set.add(start);

		while (count > 0) {
			while (count > 0) {
				char[] curr = queue.poll().toCharArray();
				for (int i = 0; i < curr.length; i++) {
					char tmp = curr[i];
					for (char c = 'a'; c <= 'z'; c++) {
						if (c == tmp)
							continue;
						curr[i] = c;
						String str = new String(curr);
						if (str.equals(end))
							return distance + 1;
						if (dict.contains(str) && !set.contains(str)) {
							queue.offer(str);
							set.add(str);
						}
					}
					curr[i] = tmp;
				}
				count--;
			}
			distance++;
			count = queue.size();
		}
		return 0;
	}

	/*
	 * public int ladderLength(String beginWord, String endWord, Set<String>
	 * wordDict) { if(beginWord.equals(endWord))return 2;
	 * 
	 * Queue<String> al = new LinkedList<String>(); LinkedList<String> ll = new
	 * LinkedList<String>(wordDict); al.add(beginWord); al.add("0");
	 * ll.add(endWord); String cur; int result = 2; int length =
	 * beginWord.length();
	 * 
	 * while(!al.isEmpty()){ cur = al.poll(); if(al.isEmpty())return 0;
	 * if(cur.equals("0")){ al.add("0"); cur = al.poll(); result++; } for(int
	 * i=0;i<ll.size();i++){ int count =0; String temp = ll.get(i); for(int
	 * j=0;j<length;j++){ if(cur.charAt(j)!=temp.charAt(j)){ count++;
	 * if(count>1)break; } if(j==length-1){ if(temp.equals(endWord))return
	 * result; ll.remove(i); al.add(temp); } } }
	 * 
	 * }
	 * 
	 * return 0; }
	 */

	public boolean searchMatrix(int[][] matrix, int target) {
		int lengthi = matrix.length;
		int lengthj = matrix[0].length;
		for (int i = 0, j = 0; i < lengthi && j < lengthj; i++, j++) {
			if (matrix[i][j] >= target) {
				if (matrix[i][j] == target)
					return true;
				return searchMatrixUp(matrix, target, i, j)
						|| searchMatrixDown(matrix, target, i, j);
			} else {
				if (i == matrix.length - 1 && j < matrix[i].length - 1) {
					for (int k = j; k < matrix[i].length; k++) {
						if (matrix[i][k] >= target) {
							if (matrix[i][k] == target)
								return true;
							break;
						}
					}
				} else if (j == matrix[i].length - 1 && i < matrix.length - 1) {
					for (int k = i; k < matrix.length; k++) {
						if (matrix[k][j] >= target) {
							if (matrix[k][j] == target)
								return true;
							break;
						}
					}
				}
			}
		}

		return false;
	}

	public boolean searchMatrixUp(int[][] matrix, int target, int i, int j) {
		int lengthj = matrix[0].length;

		while (i >= 0 && j < lengthj) {
			if (matrix[i][j] == target)
				return true;
			if (matrix[i][j] > target) {
				i--;
			} else {
				j++;
			}
		}
		return false;
	}

	public boolean searchMatrixDown(int[][] matrix, int target, int i, int j) {
		int lengthi = matrix.length;
		while (j >= 0 && i < lengthi) {
			if (matrix[i][j] == target)
				return true;
			if (matrix[i][j] > target) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}

	// 快排的一半，及时停止
	public int findKthLargest(int[] nums, int k) {
		if (nums.length < 2 * k)
			return kthLargest(nums, k);
		return kthSmallest(nums, nums.length - k);
	}

	public int kthLargest(int[] nums, int k) {
		int result = 0;
		return result;
	}

	public int kthSmallest(int[] nums, int k) {
		int result = 0;
		return result;
	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> templist = new LinkedList<Integer>();
		combinationSum3(k, n, result, templist, 1);
		return result;
	}

	public void combinationSum3(int k, int n, List<List<Integer>> res,
			List<Integer> temp, int start) {

		if (!isSolvable(k, n, start))
			return;
		if (k == 1) {
			if (n >= start) {
				temp.add(n);
				res.add(new LinkedList<Integer>(temp));
				temp.remove(temp.size() - 1);
				return;
			}
			return;
		}

		for (int i = start; i <= 9; i++) {
			temp.add(i);
			combinationSum3(k - 1, n - i, res, temp, i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	private boolean isSolvable(int k, int n, int start) {
		int max = (19 - k) * k / 2;
		if (n > max)
			return false;
		int min = (2 * start - 1) * k / 2;
		if (n < min)
			return false;
		return true;
	}

	/*
	 * public List<List<Integer>> combinationSum3(int k, int n) {
	 * LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
	 * LinkedList<Integer> templist = new LinkedList<Integer>(); int max =
	 * (19-k)*k/2; if(n>max)return result; int temp = 0; for(int i =1;i<=k;i++){
	 * templist.add(i); temp += i; }
	 * 
	 * while (!templist.isEmpty()) { if (temp >= n) { if (temp == n &&
	 * templist.size()==k) { LinkedList<Integer> tl = new LinkedList<Integer>();
	 * for(int i=0;i<templist.size();i++){ tl.add(templist.get(i)); }
	 * result.add(tl); } if (!templist.isEmpty()) { temp -= templist.getLast();
	 * templist.removeLast(); } if (!templist.isEmpty()) { int i =
	 * templist.getLast(); temp -= i; templist.removeLast(); i++; temp += i;
	 * templist.add(i); } } else { int i=0; if (templist.getLast() ==
	 * 9-templist.size()) { temp -= templist.getLast(); templist.removeLast();
	 * int cur = k-templist.size(); i = templist.getLast(); while (i == 9-cur) {
	 * temp -= i; templist.removeLast();
	 * 
	 * if(!templist.isEmpty())return result; i = templist.getLast(); } }else{ i
	 * = templist.getLast(); } if(templist.size()==k){ temp -= i;
	 * templist.removeLast(); i++; temp += i; templist.add(i); }
	 * while(templist.size()!=k){ temp += templist.getLast() + 1;
	 * templist.add(templist.getLast() + 1); } } } return result; }
	 */

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> templist = new LinkedList<Integer>();
		int temp = 0;
		int cur = 0;
		while (temp < target) {
			if (cur == candidates.length)
				return result;
			templist.add(cur);
			temp += candidates[cur];
			cur++;
		}

		while (!templist.isEmpty()) {
			if (temp >= target) {
				if (temp == target) {
					LinkedList<Integer> tl = new LinkedList<Integer>();
					for (int i = 0; i < templist.size(); i++) {
						tl.add(candidates[templist.get(i)]);
					}
					result.add(tl);
				}
				if (!templist.isEmpty()) {
					temp -= candidates[templist.getLast()];
					templist.removeLast();
				}
				if (!templist.isEmpty()) {
					int i = templist.getLast();
					temp -= candidates[i];
					templist.removeLast();
					i++;
					if (i == candidates.length) {
						continue;
					}
					temp += candidates[i];
					templist.add(i);
				}
			} else {
				if (templist.getLast() == candidates.length - 1) {
					if (!templist.isEmpty()) {
						temp -= candidates[templist.getLast()];
						templist.removeLast();
					}
					if (!templist.isEmpty()) {
						int i = templist.getLast();
						temp -= candidates[i];
						templist.removeLast();
						if (i == candidates.length) {
							continue;
						}
						temp += candidates[i + 1];
						templist.add(i + 1);
					}
				} else {
					temp += candidates[templist.getLast() + 1];
					templist.add(templist.getLast() + 1);
				}
			}
		}

		for (int i = 0; i < result.size() - 1; i++) {
			for (int k = 1; k < result.size() - i; k++) {
				if (result.get(i).size() == result.get(i + k).size()) {
					for (int j = 0; j < result.get(i).size(); j++) {
						if (result.get(i).get(j) != result.get(i + k).get(j))
							break;
						if (j == result.get(i).size() - 1) {
							result.remove(i + k);
							k--;
							break;
						}
					}
				}
			}
		}

		return result;
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
		LinkedList<Integer> templist = new LinkedList<Integer>();
		int temp = 0;
		while (temp < target) {
			templist.add(0);
			temp += candidates[0];
		}
		while (!templist.isEmpty()) {
			if (temp >= target) {
				if (temp == target) {
					LinkedList<Integer> tl = new LinkedList<Integer>();
					for (int i = 0; i < templist.size(); i++) {
						tl.add(candidates[templist.get(i)]);
					}
					result.add(tl);
				}
				if (!templist.isEmpty()) {
					temp -= candidates[templist.getLast()];
					templist.removeLast();
				}
				if (!templist.isEmpty()) {
					int i = templist.getLast();
					temp -= candidates[i];
					templist.removeLast();
					while ((i == candidates.length - 1) && !templist.isEmpty()) {
						i = templist.getLast();
						temp -= candidates[i];
						templist.removeLast();
					}
					i++;
					if (i == candidates.length) {
						continue;
					}
					temp += candidates[i];
					templist.add(i);
				}

			} else {
				temp += candidates[templist.getLast()];
				templist.add(templist.getLast());
			}

		}

		return result;
	}
}
