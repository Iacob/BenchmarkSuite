package luoyong.tool.benchmarksuite;

import java.util.LinkedList;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getCanonicalName());

	public static void main(String args[]) {
		long begin_time = System.currentTimeMillis();
		try {
			LinkedList<Future> taskList = new LinkedList<Future>();
			ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(Constants.THREADS);
			for (int i=0; i<Constants.THREADS; i++) {
				ConnectionTask task = new ConnectionTask();
				taskList.push(pool.submit(task));
			}
			for (Future f : taskList) {
				f.get();
			}
		}catch(Throwable t) {
			logger.log(Level.SEVERE, "", t);
		}finally {
			try {
				long duration = System.currentTimeMillis() - begin_time;
				System.out.println(String.format("time duration is: %d", duration));
				System.out.println(String.format("count is: %d", CountHolder.getCount()));
				System.out.println(String.format("requests per minute is: %f", Double.valueOf(CountHolder.getCount()).doubleValue() / (Double.valueOf(duration).doubleValue() / Double.valueOf(60000).doubleValue())));
			}catch(Throwable t) {
				logger.log(Level.SEVERE, "", t);
			}
		}
	}
}
