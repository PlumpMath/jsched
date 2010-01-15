import jsched.Scheduler;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		PingPong ping = new PingPong("ping", 12);
		PingPong pong = new PingPong("pong", 17);
		ping.friend = pong;
		pong.friend = ping;

		scheduler.spawnActor(ping, pong);
		ping.send("hello");
		scheduler.schedule();
	}

}
