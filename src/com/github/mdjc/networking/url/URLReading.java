package com.github.mdjc.networking.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.github.mdjc.networking.common.Utils;

/**
 * Rather than getting an input stream directly from the URL, this program
 * explicitly retrieves a URLConnection object and gets an input stream from the
 * connection. The connection is opened implicitly by calling getInputStream.
 * 
 * However, reading from a URLConnection instead of reading directly from a URL
 * might be more useful. This is because you can use the URLConnection object
 * for other tasks (like writing to the URL) at the same time.
 * 
 *
 */
public class URLReading {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.google.com/");
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			Utils.closeQuietly(reader);
		}
	}
}
