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

	private Continuation loop() {
		final Msg message;
		System.out.println("" + this + " loop");
		message = mailBox.getMessage();
		System.out.println("get msg = " + message);
		if (message != null) {
			actor.handle(message);
		}
		return new Continuation() {
			@Override
			public Continuation call() {
				return loop();
			}
		};
	}

	public void start() {
		actor.init();
		start(new Continuation() {
			@Override
			public Continuation call() {
				return loop();
			}
		});
	}
}
