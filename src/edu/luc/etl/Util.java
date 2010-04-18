package edu.luc.etl;

public class Util {

	//for debugging purposes
	//from: http://www.rgagnon.com/javadetails/java-0596.html
	public static String getHexString(byte[] b) {
		try{
			String result = "";
			for (int i=0; i < b.length; i++) {
				result +=
					Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
			}
			return result;
		} catch(Exception e) {
			return "Could not convert " + b + " to hex string.";
		}
	}

}
