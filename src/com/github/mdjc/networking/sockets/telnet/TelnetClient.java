package com.github.mdjc.networking.sockets.telnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.github.mdjc.networking.common.Utils;

public class TelnetClient {
	private static final String HOST_NAME = "google.com";
	private static final int SERVER_PORT = 80;
	
	public static void main(String[] args) throws Exception {
		Socket socket = null;
		PrintWriter socketWriter = null;
		BufferedReader socketReader = null;
		
		try {
			socket = new Socket(InetAddress.getByName(HOST_NAME), SERVER_PORT);
			socketWriter = new PrintWriter(socket.getOutputStream());
			socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			socketWriter.println("GET http://www.google.com/ HTTP/1.1");
			socketWriter.println();
			socketWriter.flush();
			
			String line;
			while((line = socketReader.readLine()) != null) {
				System.out.println(line);				
			}

		} finally {
			Utils.closeQuietly(socketReader, socketWriter, socket);
		}
	}
	
}
