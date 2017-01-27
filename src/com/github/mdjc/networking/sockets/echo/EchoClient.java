package com.github.mdjc.networking.sockets.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.mdjc.networking.common.Utils;

/**
 * This class connects to a server socket to read and write messages. One line
 * at a time is written from the standard input and sent to the server. The
 * server responds sending the received message back. The servers' response is
 * printed to the standard output.
 * 
 * This server accepts multiple connections and stars one thread to handle each one.
 * 
 * @author Mirna
 *
 */
public class EchoClient {
	private static final String SERVER_IP = "192.168.1.5";
	private static final int SERVER_PORT = 45673;
	

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
			
			String inputLine = null;
			do {
				System.out.print("Write some message: ");
				inputLine = stdInReader.readLine();
				socketWriter.println(inputLine);
				socketWriter.flush();
				System.out.println("echo from server: " + socketReader.readLine());	
			} while (inputLine != null);

		} finally {
			Utils.closeQuietly(stdInReader, socketReader, socketWriter, socket);
		}
	}
}
