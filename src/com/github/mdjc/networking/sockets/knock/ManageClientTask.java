package com.github.mdjc.networking.sockets.knock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.github.mdjc.networking.common.Utils;

public class ManageClientTask implements Runnable {
	private Socket clientSocket;

	public ManageClientTask(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new PrintWriter(clientSocket.getOutputStream(), true);
			String inputLine, outputLine;
			KnockKnockProtocol kkp = new KnockKnockProtocol();
			outputLine = kkp.processInput(null);
			writer.println(outputLine);

			while ((inputLine = reader.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				writer.println(outputLine);
				writer.flush();

				if (outputLine.equals(KnockKnockProtocol.THE_END)) {					
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(String.format("Exception from Client %s. Message=%s", clientSocket, e.getMessage()));
		} finally {
			Utils.closeQuietly(writer, reader, clientSocket);
		}
	}
}
