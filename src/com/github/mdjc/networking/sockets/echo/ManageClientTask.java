package com.github.mdjc.networking.sockets.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.github.mdjc.networking.common.Utils;

public class ManageClientTask implements Runnable {
	private Socket clientSocket;
	
	public ManageClientTask(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		PrintWriter writer = null;
		BufferedReader reader = null;
		
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new PrintWriter(clientSocket.getOutputStream(), true);
			
			while ((line = reader.readLine()) != null) {
				System.out.println(String.format("Client %s sends %s", clientSocket.toString(), line));
				writer.println(line);
				writer.flush();
			}
		} catch (Exception e) {
			System.out.println(String.format("Exception from Client %s. Message=%s", clientSocket.toString(), e.getMessage()));
		} finally {
			Utils.closeQuietly(writer, reader, clientSocket);
		}
	}

}
