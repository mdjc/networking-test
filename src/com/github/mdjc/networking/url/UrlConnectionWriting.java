package com.github.mdjc.networking.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.github.mdjc.networking.common.Utils;

public class UrlConnectionWriting {
	public static void main(String[] args) throws Exception {		
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		
		try {
			URL url = new URL("https://httpbin.org/post");			
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);						
			out = new OutputStreamWriter(connection.getOutputStream());
			out.write("name=" + "mirna");
		    out.close();
		    
		    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		    	System.out.println(line);
		    }
		} finally {
			Utils.closeQuietly(reader, out);
		}
	}
}
