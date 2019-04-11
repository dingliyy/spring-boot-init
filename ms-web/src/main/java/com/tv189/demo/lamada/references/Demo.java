/**
 *	
 * @author dingli02  
 * @date 2019/04/11 15:29
 */
package com.tv189.demo.lamada.references;

/**
 * 
 * @author dingli02
 * @date 2019/04/11 15:29
 */
public class Demo {

	public static String strReverse(String str) {
		StringBuilder result = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			result.append(str.charAt(i));
		}
		return result.toString();
	}

	public static String stringOp(Func sf, String s) {
		return sf.func(s);
	}

	public static void main(String[] args) {
		String inStr = "lambda add power to Java";
		// MyStringOps::strReverse 相当于实现了接口方法func()
		// 并在接口方法func()中作了MyStringOps.strReverse()操作
		String outStr = stringOp(Demo::strReverse, inStr);
		System.out.println("Original string: " + inStr);
		System.out.println("String reserved: " + outStr);
	}

}
