import jsched.Actor;

class PingPong extends Actor<String> {
	public PingPong friend;
	private final String name;
	private int i = 0;
	private final long time;

	public PingPong(String name, long time) {
		this.name = name;
		this.time = time;
	}

	@Override
	public void init() {

	}

	@Override
	public void handle(String n) {
		if ((System.currentTimeMillis() / 100) % time < 1) {
			i++;
			friend.send(name + "-" + i);
		}
		if (n != null) {
			System.out.println(name + "_" + i + " <- " + n + " ");
		}
	}

}
