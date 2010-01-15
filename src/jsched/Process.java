package jsched;
public abstract class Process<T> extends Fiber {
	private final MailBox<T> mailBox;

	protected Process() {
		mailBox = new MailBox<T>();
	}

	public void send(T msg) {
		mailBox.addMessage(msg);
	}

	protected Continuation receive(final Func<T, Continuation> continuation) {
		final T message;
		message = mailBox.tryGetNextMessage();
		if (message != null) {
			return new Continuation() {
				public Continuation run() {
					return continuation.exec(message);
				}
			};
		} else {
			return new Continuation() {
				public Continuation run() {
					return receive(continuation);
				}
			};
		}
	}
}
