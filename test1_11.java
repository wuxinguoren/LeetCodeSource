package test;

import java.util.*;

public class test1_11 {

    
    
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 2, 3, 4, 5, 6, 7 };
		System.out.print(isHappy(9));
	}

	//隔一格比较大小，动态规划法，得到每一格的最大值
    public static int rob(int[] nums) {
        if(nums.length==0)
        {
        	return 0;
        }
    	if(nums.length<2){
        	return nums[0];
    	}
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0], nums[1]);
        for(int i =2;i<nums.length;i++){
        	dp[i] =  Math.max(nums[i]+dp[i-2], dp[i-1]);
        }
    	return dp[nums.length-1];
    }
	// 求质数prime..给定数字n以内的质数个数。
	public static int countPrimes(int n) {
		if (n < 2) {
			return 0;
		}

		boolean notPrime[] = new boolean[n];
		notPrime[0] = notPrime[1] = true;
		for (int i = 2; i * i < n; i++) {
			if (!notPrime[i]) {
				int c = i * i;
				while (c < n) {
					notPrime[c] = true;
					c += i;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (!notPrime[i])
				result++;
		}
		return result;
	}

	//happy number.. 取各个数位的乘方和，最终等于1
		public static boolean isHappy(int n) {
			if (n == 1||n==7) {
				return true;
			}
			if(n<10){
				return false;
			}

			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			while (n != 1) {
				int temp = 0;
				while (n > 0) {
					temp += (n % 10) * (n % 10);
					n /= 10;
				}
				n = temp;
				if (temp < 100 && temp > 10) {
					if (hm.containsKey(temp / 10)) {
						if (hm.get(temp / 10) == temp % 10) {
							return false;
						}
					} else {
						hm.put(temp / 10, temp % 10);
						hm.put(temp % 10, temp / 10);
					}
				}
			}

			return true;
		}

		
	// 是不是相同格式
	public static boolean isIsomorphic(String s, String t) {
		HashMap<Character, Character> hms = new HashMap<Character, Character>();
		HashMap<Character, Character> hmt = new HashMap<Character, Character>();
		char temps, tempt;
		for (int i = 0; i < s.length(); i++) {
			temps = s.charAt(i);
			tempt = t.charAt(i);

			if (hms.containsKey(temps)) {
				if (hms.get(temps) != tempt) {
					return false;
				}
			} else {
				hms.put(temps, tempt);
			}

			if (hmt.containsKey(tempt)) {
				if (hmt.get(tempt) != temps) {
					return false;
				}
			} else {
				hmt.put(tempt, temps);
			}
		}
		return true;
	}

	/**
	 * Definition for singly-linked list. public class ListNode { int val;
	 * ListNode next; ListNode(int x) { val = x; } }
	 */
	public ListNode reverseList(ListNode head) {
		if (head != null)
			return reverseListwork(head, null);
		else
			return head;
	}

	public ListNode reverseListwork(ListNode head, ListNode pre) {
		ListNode next = head.next;
		head.next = pre;
		if (next != null) {
			return reverseListwork(next, head);
		}

		return head;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	// Given an array of integers,
	// find if the array contains any duplicates.
	public static boolean containsDuplicate(int[] nums) {
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (hs.contains(nums[i])) {
				return true;
			} else {
				hs.add(nums[i]);
			}
		}
		return false;
	}

	// with k distance
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (hs.contains(nums[i])) {
				return true;
			} else {
				hs.add(nums[i]);
				if (i - k >= 0) {
					hs.remove(nums[i - k]);
				}
			}
		}
		return false;
	}

	// computeArea
	public static int computeArea(int A, int B, int C, int D, int E, int F,
			int G, int H) {
		int result = 0;
		result = (C - A) * (D - B) + (G - E) * (H - F);

		if (A > G || C < E || B > H || D < F) {
			return result;
		} else {
			result -= (getSmall(C, G) - getLarge(A, E))
					* (getSmall(D, H) - getLarge(B, F));
		}

		return result;
	}

	public static int getLarge(int i, int j) {
		if (i > j) {
			return i;
		}
		return j;
	}

	public static int getSmall(int i, int j) {
		if (i < j) {
			return i;
		}
		return j;
	}

	// summary Ranges
	public static List<String> summaryRanges(int[] nums) {
		List<String> resultList = new LinkedList<String>();
		if (nums.length == 0) {
			return resultList;
		}
		int start, end;
		end = start = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (end + 1 != nums[i]) {
				String temp;
				if (start != end) {
					temp = Integer.toString(start) + "->"
							+ Integer.toString(end);
				} else {
					temp = Integer.toString(start);
				}
				resultList.add(temp);
				start = nums[i];
			}
			end = nums[i];
		}

		String temp;
		if (start != end) {
			temp = Integer.toString(start) + "->" + Integer.toString(end);
		} else {
			temp = Integer.toString(start);
		}
		resultList.add(temp);

		return resultList;
	}

	// romanToInt
	public static int romanToInt(String s) {
		int result = 0;
		int pre, cur;

		cur = result = charToInt(s.charAt(0));

		for (int i = 1; i < s.length(); i++) {
			pre = cur;
			cur = charToInt(s.charAt(i));

			if (cur <= pre) {
				result += cur;
			} else {
				result = result - pre * 2 + cur;
			}
		}

		return result;
	}

	public static int charToInt(char c) {
		int data = 0;

		switch (c) {
		case 'I':
			data = 1;
			break;
		case 'V':
			data = 5;
			break;
		case 'X':
			data = 10;
			break;
		case 'L':
			data = 50;
			break;
		case 'C':
			data = 100;
			break;
		case 'D':
			data = 500;
			break;
		case 'M':
			data = 1000;
			break;
		default:
			break;
		}

		if (data == 0) {
			System.out.print("invaild input.. please check it!");
		}
		return data;
	}
}
