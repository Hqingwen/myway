package com.hqw.solution;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Solution {

	/**
	 * 字符串反转
	 * 
	 * @param input
	 * @return
	 */
	public static String reverse(String paramString) {
		StringBuilder localStringBuilder = new StringBuilder();
		char[] arrayOfChar = paramString.toCharArray();
		for (int i = arrayOfChar.length - 1; i >= 0; i--) {
			localStringBuilder.append(arrayOfChar[i]);
		}
		return localStringBuilder.toString();
	}

	/**
	 * 字符串是否回文
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static boolean isRotation(String paramString1, String paramString2) {
		return (paramString1.length() == paramString2.length())
				&& (new StringBuilder().append(paramString1)
						.append(paramString1).toString().indexOf(paramString2) != -1);
	}

	/**
	 * 检查字符串每个字符是否唯一
	 * 
	 * @param paramString
	 * @return
	 */
	public static boolean isUnique(String paramString) {
		if (paramString.isEmpty()) {
			return false;
		}
		HashSet<Character> localHashSet = new HashSet<Character>();
		char[] arrayOfChar = paramString.toCharArray();
		for (int i = 0; i < arrayOfChar.length; i++) {
			if (localHashSet.contains(Character.valueOf(arrayOfChar[i]))) {
				return false;
			}
			localHashSet.add(Character.valueOf(arrayOfChar[i]));
		}
		return true;
	}

	/**
	 * 移除字符串中所有重复字符
	 * 
	 * @param paramString
	 * @return
	 */
	public static String removeDuplicates(String paramString) {
		LinkedHashSet<Character> localLinkedHashSet = new LinkedHashSet<Character>();
		char[] arrayOfChar = paramString.toCharArray();
		for (char c : arrayOfChar) {
			localLinkedHashSet.add(Character.valueOf(c));
		}
		StringBuilder localStringBuilder = new StringBuilder();
		for (Character localCharacter : localLinkedHashSet) {
			localStringBuilder.append(localCharacter);
		}
		return localStringBuilder.toString();
	}
	
	/**
	 * 按空格反转字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public static String reverseWordOrder(String paramString) {
		char[] arrayOfChar1 = paramString.toCharArray();
		int i = 0;
		for (int j = 0; j < arrayOfChar1.length; j++) {
			if (arrayOfChar1[j] == ' ') {
				reverseCharater(arrayOfChar1, i, j);
				i = j + 1;
			}
		}
		reverseCharater(arrayOfChar1, i, arrayOfChar1.length);
		StringBuilder localStringBuilder = new StringBuilder();
		for (char c : arrayOfChar1)
			localStringBuilder.append(c);
		return localStringBuilder.toString();
	}

	/**
	 * 反转字符数组
	 * @param paramArrayOfChar
	 * @param paramInt1
	 * @param paramInt2
	 */
	private static void reverseCharater(char[] paramArrayOfChar, int paramInt1, int paramInt2) {
		for (int i = 0; i < (paramInt2 - paramInt1) / 2; i++) {
			char temp = paramArrayOfChar[paramInt1 + i];
			paramArrayOfChar[paramInt1 + i] = paramArrayOfChar[paramInt2 - i
					- 1];
			paramArrayOfChar[(paramInt2 - i - 1)] = temp;
		}
	}

	/**
	 * 返回字符串的int值
	 * @param paramString
	 * @return
	 */
	public static int stringTointeger(String paramString) {
		int i = 0;
		int j = 0;
		int k = 0;
		if (paramString.charAt(j) == '-')
			k = 1;
		for (; j < paramString.length(); j++) {
			i *= 10;
			i += paramString.charAt(j) - '0';
		}
		if (k != 0)
			i *= -1;
		return i;
	}

	/**
	 * 返回最长公共字段长度
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static int longestCommonSubstring(String paramString1,String paramString2) {
		int i = paramString1.length();
		int j = paramString2.length();

		if ((i == 0) || (j == 0)) {
			return 0;
		}
		int k = 0;
		int[][] arrayOfInt = new int[i][j];

		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				if (paramString1.charAt(m) == paramString2.charAt(n)) {
					if ((m == 0) || (n == 0))
						arrayOfInt[m][n] = 1;
					else
						arrayOfInt[m][n] = (arrayOfInt[(m - 1)][(n - 1)] + 1);
					if (arrayOfInt[m][n] > k)
						k = arrayOfInt[m][n];
				}
			}
		}
		return k;
	}
	
	/**
	 * 返回2的x次方
	 * 
	 * @param paramInt
	 * @return
	 */
	public static int twoToThe(int paramInt) {
		return 1 << paramInt;
	}

	/**
	 * 返回x是否为2的幂数
	 * @param x
	 * @return
	 */
	public static boolean isPowerOfTwo(int x){
		return (x & (x - 1)) == 0;
	}
	
	/**
	 * 交换两个整型值
	 * @param a
	 * @param b
	 */
	public static void swapInPlace(int a, int b) {
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
	}
	
	/**
	 * 交换字节值得前4个值和后4个值
	 * @param x
	 * @return
	 */
	public static byte swapBits(byte x) {
		return (byte) (((x & 0xf0) >> 4) | ((x & 0xf0) << 4));
	}
	
	/**
	 * 给定一个整数数组，其中每个数字恰好出现两次，除了一个整数
	 * 恰好出现一次，找到唯一的整数。
	 * @param  input [description]
	 * @return       [description]
	 */
	public static int findTheLonger(int[] input) {
		int value = 0;
		for(int i = 0; i < input.length; i++){
			value = value ^ input[i];
		}
		return value;
	}
	
	/**
	 * 判断两整数是否正负型相同
	 * @param  x [description]
	 * @param  y [description]
	 * @return   [description]
	 */
	public static boolean isSameSign(int x, int y){
		return ((x ^ y) < 0);
	}
	
	/**
	 * 删除双向链表中值为target的节点
	 * @param head   [description]
	 * @param target [description]
	 */
	public static void removeTargetNode(Node head, int target){
		Node current = head;
		while(current != null){
			if(current.value == target){
				if(current.next != null)
					current.next.previous = current.previous;
				if(current.previous != null)
					current.previous.next = current.next;
				break;
			}
			current = current.next;
		}
	}
	/**
	 * 定义双向链表
	 */
	private class Node{
		public Node next;
		public Node previous;
		public int value;
	}
	
	/**
	 * 链表相加
	 * @param  first  [description]
	 * @param  second [description]
	 * @return        [description]
	 */
	public static SinglyNode addLinkedListNumber(SinglyNode first, SinglyNode second) {
		int carry = 0;
		SinglyNode result = null;
		SinglyNode iter = null;
		while(first != null || second != null){
			int firstValue = first == null ? 0 : first.value;
			int secondValue = second == null ? 0 : second.value;
			int sum = (firstValue + secondValue + carry) % 10;
			carry = (firstValue + secondValue + carry) / 10;

			SinglyNode node = new SinglyNode();
			node.value = sum;
			node.next = null;
			if(result == null){
				iter = node;
				result = node;
			}
			else {
				iter.next = node;
				iter = node;
			}

			first = (first == null) ? null : first.next;
			second = (second == null) ? null : second.next;
		}

		if(carry != 0){
			SinglyNode node = new SinglyNode();
			node.value = carry;
			node.next = null;
			iter.next = node;
			iter = node;
		}
		return result;
	}


	private static class SinglyNode{
		public SinglyNode next;
		public int value;
	}


	public static int foo(int x){
		return x&-x;
	}


	public static int fn(int n){
		if(n==1)
			return 1;
		else if(n==2)
			return 2;
		else if(n==3)
			return 4;
		else 
			return fn(n-1)+fn(n-2)+fn(n-3);
	}
























	
}
