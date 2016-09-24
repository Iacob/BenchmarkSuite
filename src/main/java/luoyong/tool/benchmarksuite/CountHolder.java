package luoyong.tool.benchmarksuite;

public class CountHolder {

	private static long cnt = 0;
	
	public static synchronized void inc() {
		cnt++;
	}
	
	public static long getCount() {
		return cnt;
	}
	
	public static void reset() {
		cnt = 0;
	}
}
