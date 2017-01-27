package com.github.mdjc.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

import com.github.mdjc.networking.common.Utils;

public class QuoteServer {
	private static final int PORT_NUMBER = 45678;
	private static final String[] quotes = {
			"Education is the most powerful weapon which you can use to change the world.",
			"It always seems impossible until it's done.", "education is not filling a bucket but lighting a fire.",
			"I am the master of my fate. I am the captain of my soul." };
	private static final Random random = new Random();

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = null; 

		try {
			socket = new DatagramSocket(PORT_NUMBER);
			System.out.println(String.format("Listening from %s in port %s",
					socket.getLocalAddress().getHostAddress(), socket.getLocalPort()));
			
			while (true) {
				byte[] buffer = new byte[256];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);

				String quote = getRandomQuote();
				buffer = quote.getBytes();
				packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
				socket.send(packet);				
			}
		} finally {
			Utils.closeQuietly(socket);
		}
	}

	private static String getRandomQuote() {
		int index = random.nextInt(quotes.length);
		return quotes[index];
	}
}
