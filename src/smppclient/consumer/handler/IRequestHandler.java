package smppclient.consumer.handler;

public interface IRequestHandler extends Runnable {
	public enum RequestType { SUB, UNSUB, OTHER };
}
