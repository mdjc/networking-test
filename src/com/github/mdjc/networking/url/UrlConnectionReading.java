package com.github.mdjc.networking.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.github.mdjc.networking.common.Utils;

public class UrlConnectionReading {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.google.com/");
		BufferedReader reader = null;
		URLConnection connection = null;
		
		try {
			connection = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			Utils.closeQuietly(reader);
		}
		
	}

}
