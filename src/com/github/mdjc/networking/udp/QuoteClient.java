package com.github.mdjc.networking.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.github.mdjc.networking.common.Utils;

public class QuoteClient {
	private static final String SERVER_IP = "192.168.1.5";
	private static final int SERVER_PORT = 45678;

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = null;
		BufferedReader stdIn = null;
		
		try {
			socket = new DatagramSocket();
			byte[] buffer = new byte[256];

			stdIn = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while(true) {
				System.out.print("Get a quote of the moment? Y / N: ");
				line = stdIn.readLine();
				
				if (line.equalsIgnoreCase("N")) break;
				
				DatagramPacket packet = new DatagramPacket(buffer, 
						buffer.length, 
						InetAddress.getByName(SERVER_IP),
						SERVER_PORT);
				socket.send(packet);				
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String received = new String(packet.getData(), 0, packet.getLength());
				System.out.println(String.format("\"%s\"", received));
			}
			
			System.out.println("Bye");
			
		} finally {
			Utils.closeQuietly(stdIn, socket);
		}
	}
}
