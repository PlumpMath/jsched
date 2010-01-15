

import jsched.Continuation;
import jsched.Func;
import jsched.Process;

class PingPong extends Process<String> {
	public Process<String> friend;
	private final String name;

	public PingPong(String name) {
		this.name = name;
	}

	@Override
	public Continuation run() {
		friend.send(name);
		return receive(new Func<String, Continuation>() {

			@Override
			public Continuation exec(String n) {
				System.out.println(name + " received message from " + n);
				return run();
			}
		});
	}
}
