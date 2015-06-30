/*package test;

import java.io.*;
import java.util.*;
import java.math.*;

public class test {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	@SuppressWarnings("null")
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int temp = 1;
		test ts = new test();
		test.ListNode result = ts.new ListNode(0);
		test.ListNode tempNode = ts.new ListNode(0);

		int a = 0;
		int b = 0;
		while (l1 != null) {
			a = a + temp * l1.val;
			temp *= 10;
			l1 = l1.next;
		}
		temp = 1;
		while (l2 != null) {
			b = b + temp * l2.val;
			temp *= 10;
			l2 = l2.next;
		}

		temp = a + b;
		int len = Integer.toString(temp).length();
		boolean isfirst = true;
		for (int i = 0; i < len; i++) {

			test.ListNode curNode = ts.new ListNode(temp % 10);
			temp = temp / 10;
			if (isfirst) {
				result = curNode;
				tempNode = result;
				isfirst = false;
			} else {
				tempNode.next = curNode;
				tempNode = tempNode.next;
			}

		}

		return result;
	}

	public static int titleToNumber(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result += (s.charAt(i) - 64) * Math.pow(26, s.length() - i - 1);
		}

		return result;
	}

	public static int majorityElement(int[] num) {

		if (num.length < 3) {
			return num[0];
		}

		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 1; i < num.length; i++) {
			if (al.isEmpty()) {
				al.add(num[i]);
			} else {
				if (al.get(0) == num[i]) {
					al.add(num[0]);
				} else {
					al.remove(0);
				}
			}
		}

		return al.get(0);
	}

	public static void rotate(int[] nums, int k) {
		int[] temp = new int[nums.length - k];
		for (int i = 0; i < nums.length - k; i++) {
			temp[i] = nums[i];
		}
		for (int i = 0; i < k; i++) {
			nums[i] = nums[i + nums.length - k];
		}
		for (int i = 0; i < nums.length - k; i++) {
			nums[i + k] = temp[i];
		}
		int i = 0;
	}

	public static String convertToTitle(int n) {
		String result = "";
		while (n > 0) {
			char temp = (char) ((n - 1) % 26 + 65);
			result = temp + result;

			n = (n - 1) / 26;
		}
		return result;
	}

	public int compareVersion(String version1, String version2) {
		int result = 0;

		float version1Float = Float.parseFloat(version1);
		float version2Float = Float.parseFloat(version2);
		if (version1Float > version2Float) {
			return 1;
		}
		if (version1Float < version2Float) {
			return -1;
		}

		return result;
	}

	public static void main(String[] args) {

		Q q = new Q();
		new Producer(q);
		new Consumer1(q);
		new Consumer2(q);
		System.out.println("Press Control-C to stop.");
		// System.out.print( convertToTitle(27));
		// System.out.print( (char)65);
	}

}

class Q {
	int n;
	int valueSet = 0;

	synchronized int get() {
		//System.out.println("1  Got1: " + n);
		if (valueSet == 1) 
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
			System.out.println("1  Got1: " + n);
			valueSet=1;
			notify();
			return n;

	}

	synchronized int get2() {

		//System.out.println("2  Got2: " + n);
		if (valueSet == 3) 
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
			System.out.println("2  Got2: " + n);
			valueSet=3;
			notify();
			return n;

	}

	synchronized void put(int n) {
		//System.out.println("Put: "+valueSet);
		if (valueSet % 2 == 0)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		this.n = n;
		valueSet++;
		if (valueSet > 4) {
			valueSet = 1;
		}
		System.out.println("Put: " + n);
		notify();
		
	}

}

class Producer implements Runnable {
	Q q;

	Producer(Q q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int i = 0;
		while (i < 3) {
			q.put(i++);
		}
	}
}

class Consumer1 implements Runnable {
	Q q;

	Consumer1(Q q) {
		this.q = q;
		new Thread(this, "Consumer1").start();
	}

	public void run() {
		while (true) {
			q.get();
		}
	}
}

class Consumer2 implements Runnable {
	Q q;

	Consumer2(Q q) {
		this.q = q;
		new Thread(this, "Consumer2").start();
	}

	public void run() {
		while (true) {
			q.get();
		}
	}
}
*/