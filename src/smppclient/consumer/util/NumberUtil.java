package smppclient.consumer.util;

public class NumberUtil {
	public static String toLocal(String number) {
		if(number.startsWith("+") && number.length() < 13)
			return number;
		
		if(number.startsWith("0092") && number.length() < 14)
			return number;
		
		if(number.length() < 12)
			return number;
		
		number = number.replace("+", "");
		
		if(number.startsWith("0092")) {
			number = "0" + number.substring(4);
		} else if(number.startsWith("92")) {
			number = "0" + number.substring(2);
		}
		
		return number;
	}
}
