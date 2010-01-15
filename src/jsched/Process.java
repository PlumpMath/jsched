package jsched;

public abstract class Process<T> extends Fiber {
	private final MailBox<T> mailBox;

	protected Process() {
		mailBox = new MailBox<T>();
	}

	public void send(T msg) {
		mailBox.addMessage(msg);
	}

	protected Continuation receive(final Body<T> body) {
		final T message;
		message = mailBox.getMessage();
		if (message != null) {
			return new Continuation() {
				public Continuation call() {
					return body.exec(message);
				}
			};
		} else {
			return new Continuation() {
				public Continuation call() {
					return receive(body);
				}
			};
		}
	}
}
