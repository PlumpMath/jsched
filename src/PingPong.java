import jsched.Body;
import jsched.Continuation;
import jsched.Process;

class PingPong extends Process<String> {
	public Process<String> friend;
	private final String name;
	private int i = 0;

	public PingPong(String name) {
		this.name = name;
	}

	@Override
	public Continuation do_run() {
		i++;
		System.out.println(name + " -> " + i);
		friend.send(name + "_" + i);

		Body<String> body = new Body<String>() {
			@Override
			public Continuation exec(String n) {
				System.out.println(name + "_" + i + " <- " + n + " ");
				if (i > 20) {
					return null;
				}
				return do_run();
			}
		};

		return receive(body);
	}
}
