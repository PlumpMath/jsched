public abstract class Fiber {
	public Continuation continuation;

	public void start(Scheduler scheduler) {
		continuation = run();
	}

	public abstract Continuation run();

}
