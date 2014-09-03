package smppclient.consumer.directrequest.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class DirectRequestServer {
	private Logger log = Logger.getLogger(getClass().getName());
	private int mPort = 9001;
	private int mMaxClients = 5;
	private ServerSocket mListenSocket;
	ExecutorService mRequestPool;

	public DirectRequestServer() {
		readConfig();
	}

	public void readConfig() {
		this.log.info("Initializing DirectRequestServer ...");

		try {
			
			ResourceBundle myResources = ResourceBundle.getBundle("client");
			this.mPort = Integer.parseInt(myResources.getString("requestserver.port"));
			this.mRequestPool = Executors.newFixedThreadPool(this.mMaxClients);

		} catch (Exception ex) {
			log.error("Unable to initialize: " + ex.getMessage(), ex);
			System.exit(-1);
		}

		this.log.info("DirectRequestServer initialized");
	}

	public void startServer() {
		try {
			this.mListenSocket = new ServerSocket(this.mPort);
			log.info("DirectRequestServer started at port: " + this.mPort);
			while (true) {
				Socket clientSocket = this.mListenSocket.accept();
				DirectRequestHandler req = new DirectRequestHandler(clientSocket);
				this.mRequestPool.execute(req);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			log.info("Shutting down DirectRequestServer...");
		}
	}
}
