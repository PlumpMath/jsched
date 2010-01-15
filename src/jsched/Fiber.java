package jsched;

public abstract class Fiber {
	public Continuation continuation;

	public void start() {
		continuation = do_run();
	}

	public boolean run() {
		continuation = continuation.call();
		return continuation != null;
	}

	protected abstract Continuation do_run();

	public void stop() {
		continuation = null;
	}

}
