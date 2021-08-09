
package com.nicolasmoncarz.requestcache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnection {

	private static final String USER_AGENT = "Mozilla/5.0";

	public static String sendGET(String url) {
		try {
			HttpURLConnection connection = createConnection(url);
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return readResponse(connection);
			}
		} catch (IOException exception) {
			System.out.println("IOException: " + exception.getMessage());
		}
		return "GET request not worked";
	}

	private static HttpURLConnection createConnection(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		return con;
	}

	public static String readResponse(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(
			new InputStreamReader(connection.getInputStream())
		);
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
