package jsched;

public class ActorLoop<Msg> extends Fiber {
	private final MailBox<Msg> mailBox;
	private final Actor<Msg> actor;

	public ActorLoop(Actor<Msg> actor) {
		mailBox = new MailBox<Msg>();
		if (actor == null) {
			throw new IllegalArgumentException("process can't be null");
		}
		this.actor = actor;
		actor.loop = this;
	}

	public void send(Msg msg) {
		mailBox.addMessage(msg);
	}

	Continuation my_loop = new Continuation() {
		@Override
		public Continuation call() {
			return loop();
		}
	};

	private Continuation loop() {
		final Msg message;
		// System.out.println("" + this + " loop");
		message = mailBox.getMessage();
		if (message != null) {
			// System.out.println("get msg = " + message);
		}

		actor.handle(message);

		return my_loop;
	}

	private Continuation receive(long timeout, Continuation next,
			Continuation after) {
		return next;
	}

	public void start() {
		actor.init();
		start(my_loop);
	}
}
