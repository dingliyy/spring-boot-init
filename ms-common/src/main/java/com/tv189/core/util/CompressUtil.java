package com.tv189.core.util;

import java.io.IOException;

import org.xerial.snappy.Snappy;

public class CompressUtil {
	public static byte[] compress(String str) {
		try {
			return Snappy.compress(str.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decompress(byte[] bytes) {
		try {
			return new String(Snappy.uncompress(bytes));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
