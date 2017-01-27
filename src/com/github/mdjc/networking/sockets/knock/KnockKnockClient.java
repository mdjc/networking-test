package com.github.mdjc.networking.sockets.knock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.mdjc.networking.common.Utils;

public class KnockKnockClient {
	private static final String THE_END = "the end.";
	private static final String SERVER_IP = "192.168.1.5";
	private static final int SERVER_PORT = 45674;
	

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = null;
		PrintWriter socketWriter = null;
		BufferedReader socketReader = null;
		BufferedReader stdInReader = null;

		try {
			socket = new Socket(InetAddress.getByName(SERVER_IP), SERVER_PORT);
			socketWriter = new PrintWriter(socket.getOutputStream());
			socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			stdInReader = new BufferedReader(new InputStreamReader(System.in));
			
			
			String serverInput, userInput;
			while((serverInput = socketReader.readLine()) != null) {
				System.out.println("Server says: " + serverInput);
				
				if (serverInput.equals(THE_END)) {
					break;
				}
				
				userInput = stdInReader.readLine();
				if (userInput != null) {
					socketWriter.println(userInput);
					socketWriter.flush();
				}
			}
		} finally {
			Utils.closeQuietly(stdInReader, socketReader, socketWriter, socket);
		}
	}
}
