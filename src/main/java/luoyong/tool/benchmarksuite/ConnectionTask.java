package luoyong.tool.benchmarksuite;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ConnectionTask implements Runnable {

	private static final Logger logger = Logger.getLogger(ConnectionTask.class.getCanonicalName());
	
	public void run() {
		for(int i=0; i<Constants.CONNECT_COUNT; i++) {
			connectionTask();
		}
	}
	
	public void connectionTask() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(Constants.URL);
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
			CountHolder.inc();
		}catch(Throwable t) {
			logger.log(Level.SEVERE, "", t);
		}finally {
		}
	}
}
