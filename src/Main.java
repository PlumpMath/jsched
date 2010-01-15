import jsched.Scheduler;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		PingPong ping = new PingPong("ping");
		PingPong pong = new PingPong("pong");
		ping.friend = pong;
		pong.friend = ping;

		scheduler.spawn(ping, pong);
		scheduler.schedule();
	}

}
