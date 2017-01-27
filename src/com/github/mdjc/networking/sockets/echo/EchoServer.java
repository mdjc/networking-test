package com.github.mdjc.networking.sockets.echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.mdjc.networking.common.Utils;

public class EchoServer {
	private static final int PORT_NUMBER = 45673;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			System.out.println(String.format("Listening from %s in port %s",
					serverSocket.getInetAddress().getHostName(), serverSocket.getLocalPort()));
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new Thread(new ManageClientTask(clientSocket)).start();
			}

		} finally {
			Utils.closeQuietly(serverSocket);
		}
	}
}
