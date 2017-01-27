package com.github.mdjc.networking.common;

import java.io.Closeable;

public class Utils {
	public static void closeQuietly(Closeable... resources) {
		for(Closeable resource: resources) {
			try {
				if (resource != null) resource.close();
			} catch(Exception ignore) {
			}
		}
	}
}
