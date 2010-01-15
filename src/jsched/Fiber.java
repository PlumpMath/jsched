package jsched;

public class Fiber {
	private Continuation continuation = null;

	public Fiber() {
	}

	public void start(Continuation init) {
		continuation = init;
	}

	public boolean run() {
		// System.out.println("--- run " + this);
		if (continuation != null) {
			continuation = continuation.call();
		}
		// System.out.println("--- next: " + continuation);
		return continuation != null;
	}

	public void stop() {
		continuation = null;
	}

}
