package test;

import java.util.*;

public class test41_50 {

	public static void main(String[] args) {
		test41_50 ts = new test41_50();
		int[] prere = { 1, 1 };
		String a = "letstry";
		System.out.println(ts.fractionToDecimal(Integer.MIN_VALUE,-1));
		// System.out.println(a.substring(0,1));

	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public String fractionToDecimal(int numerator, int denominator) {
		String part1 = "";
		if(numerator==Integer.MIN_VALUE){
			if(denominator==1)return Integer.toString(numerator);
			if(denominator==-1)return Integer.toString(numerator).substring(1);			
		}
		double nume = numerator;
		double deno = denominator;
		if ((nume < 0 && deno > 0)
				|| (nume > 0 && deno < 0)) {
			nume = Math.abs(nume);
			deno = Math.abs(deno);
			part1+="-";
		}
		part1+=Integer.toString((int) (nume / deno));
		nume = nume % deno;
		if (nume == 0)
			return part1;

		String part2 = Integer.toString((int) (nume * 10 / deno));

		HashMap<Double, Integer> hs = new HashMap<Double, Integer>();
		int cur = 0;
		hs.put(nume, cur);

		while (true) {
			nume = (int) ((double)nume * 10 % (double)deno);
			if (nume == 0) {
				return part1 + "." + part2;
			}
			part2 += Integer.toString((int) (nume * 10 / deno));
			cur++;
			if (hs.containsKey(nume)) {
				int temp = hs.get(nume);
				return part1 + "." + part2.substring(0, temp) + "("
						+ part2.substring(temp, cur) + ")";
			}
			hs.put(nume, cur);
		}
	}

	/*
	 * public String fractionToDecimal(int numerator, int denominator) { String
	 * part1 = Integer.toString(numerator / denominator); numerator = numerator
	 * % denominator; if (numerator == 0) return part1; String part2 =
	 * Integer.toString(numerator * 10 / denominator); String part3 = "";
	 * boolean notfinish = true; while (notfinish) { numerator = numerator * 10
	 * % denominator; if (numerator == 0) { return part1 + "." + part2; } part2
	 * += Integer.toString(numerator * 10 / denominator); for (int i =
	 * part2.length() - 2; i >= (part2.length() - 1) / 2; i--) {
	 * if(part2.charAt(part2.length() - 1)=='0')continue; notfinish = false; int
	 * j; for (j = 0; j < part2.length() - i -1; j++) { if
	 * (part2.charAt(part2.length() - 1 - j) != part2.charAt(i - j)) { notfinish
	 * = true; break; } } if (!notfinish) { part3 = part2.substring(i - j + 1, i
	 * + 1); part2 = part2.substring(0, i - j + 1); break; } ;
	 * 
	 * } }
	 * 
	 * return part1 + "." + part2 + "(" + part3 + ")"; }
	 */

	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> result = new LinkedList<Integer>();
		boolean isNum = true;
		List<Integer> temp1 = new LinkedList<Integer>();
		List<Integer> temp2 = new LinkedList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			if ((int) input.charAt(i) < 48) {
				isNum = false;
				temp1 = diffWaysToCompute(input.substring(0, i));
				temp2 = diffWaysToCompute(input
						.substring(i + 1, input.length()));
				for (int j = 0; j < temp1.size(); j++) {
					for (int k = 0; k < temp2.size(); k++) {
						result.add(calculater(input.charAt(i), temp1.get(j),
								temp2.get(k)));
					}
				}
			}
		}
		if (isNum) {
			result.add(Integer.parseInt(input));
			return result;
		}

		return result;
	}

	public int calculater(char input, int a, int b) {
		int res = 0;
		switch (input) {
		case '+':
			res = a + b;
			break;
		case '*':
			res = a * b;
			break;
		case '-':
			res = a - b;
			break;
		default:
			System.out.println("wrong signal");
			break;
		}
		return res;
	}

	public int minSubArrayLen(int s, int[] nums) {
		int start = -1;
		int temp = 0;
		int length = 0;
		int cur = 0;
		for (int i = 0; i < nums.length; i++) {
			temp += nums[i];
			cur++;
			while (temp >= s) {
				if (length == 0) {
					length = cur;
				}
				if (cur < length) {
					length = cur;
					if (length == 1)
						return 1;
				}
				start++;
				temp -= nums[start];
				cur--;
			}
		}
		return length;
	}

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] temp = new int[26];
		int cur = 0;
		for (int i = 0; i < s.length(); i++) {
			cur = (int) s.charAt(i) - 97;
			temp[cur]++;
		}
		for (int i = 0; i < s.length(); i++) {
			cur = (int) t.charAt(i) - 97;
			temp[cur]--;
		}
		for (int i = 0; i < 26; i++) {
			if (temp[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public int[] productExceptSelf(int[] nums) {
		if (nums.length == 1)
			return nums;
		int[] result = new int[nums.length];
		int temp = 1;
		result[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			temp *= nums[i - 1];
			result[i] = temp;
		}
		temp = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			temp *= nums[i + 1];
			result[i] *= temp;
		}

		return result;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == p || root == q || root == null)
			return root;
		TreeNode left = lowestCommonAncestor(root.left, p, q), right = lowestCommonAncestor(
				root.right, p, q);
		if (left == null)
			return right;
		if (right == null)
			return left;
		return root;
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] emptyResult = {};
		int[] result = new int[numCourses];
		int cur = 0;
		Queue<Integer> nodeQ = new LinkedList<Integer>();
		;
		int[] nodeIncom = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			nodeIncom[prerequisites[i][0]]++;
		}

		for (int i = 0; i < numCourses; i++) {
			if (nodeIncom[i] == 0) {
				nodeIncom[i]--;
				nodeQ.add(i);
				result[cur] = i;
				cur++;
			}
		}

		while (!nodeQ.isEmpty()) {
			int tempNode = nodeQ.poll();
			for (int i = 0; i < prerequisites.length; i++) {
				if (prerequisites[i][1] == tempNode) {
					nodeIncom[prerequisites[i][0]]--;
					if (nodeIncom[prerequisites[i][0]] == 0) {
						nodeIncom[prerequisites[i][0]]--;
						nodeQ.add(prerequisites[i][0]);
						result[cur] = prerequisites[i][0];
						cur++;
					}
					prerequisites[i][1] = -1;
				}
			}
		}

		for (int i = 0; i < prerequisites.length; i++) {
			if (prerequisites[i][1] != -1) {
				return emptyResult;
			}
		}

		return result;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Queue<Integer> nodeQ = new LinkedList<Integer>();
		;
		int[] nodeIncom = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			nodeIncom[prerequisites[i][1]]++;
		}

		for (int i = 0; i < numCourses; i++) {
			if (nodeIncom[i] == 0) {
				nodeIncom[i]--;
				nodeQ.add(i);
			}
		}

		while (!nodeQ.isEmpty()) {
			int tempNode = nodeQ.poll();
			for (int i = 0; i < prerequisites.length; i++) {
				if (prerequisites[i][0] == tempNode) {
					nodeIncom[prerequisites[i][1]]--;
					if (nodeIncom[prerequisites[i][1]] == 0) {
						nodeIncom[prerequisites[i][1]]--;
						nodeQ.add(prerequisites[i][1]);
					}
					prerequisites[i][0] = -1;
				}
			}
		}

		for (int i = 0; i < prerequisites.length; i++) {
			if (prerequisites[i][0] != -1) {
				return false;
			}
		}

		return true;
	}
}
