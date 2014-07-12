package smppclient.consumer.provgw.util;

public class RequestBuilder {
	
	public static String REQUEST_FUNCTION_SUB = "subscribeService";
	public static String REQUEST_FUNCTION_UNSUB = "unsubscribeService";
	public static String REQUEST_FUNCTION_STATUS = "querySubscriber";
	public static String REQUEST_FUNCTION_ADD_SKYPE_ID = "addSkypeID";
	public static String REQUEST_FUNCTION_REMOVE_SKYPE_ID = "removeSkypeID";
	public static String REQUEST_FUNCTION_QUERY_BUDDY_LIST = "querybuddylist";
	public static String REQUEST_FUNCTION_HELP = "help";
	
	public static String build(String function, String channel, String... nameValuePairs) {
		StringBuilder sb = new StringBuilder("<methodCall>");
		sb.append("<function>" + function + "</function>");
		sb.append("<channel>" + channel + "</channel>");
		
		if(nameValuePairs.length > 0 && nameValuePairs.length % 2 == 0) {
			for(int i=0; i<nameValuePairs.length/2; i++) {
				sb.append("<");
				sb.append(nameValuePairs[i*2]);
				sb.append(">");
				
				sb.append(nameValuePairs[i*2 + 1]);
				
				sb.append("</");
				sb.append(nameValuePairs[i*2]);
				sb.append(">");
			}
		}
		
		sb.append("</methodCall>");
		
		return sb.toString();
	}
}
