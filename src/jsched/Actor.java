package jsched;

public abstract class Actor<Msg> {

	public ActorLoop<Msg> loop;

	public void send(Msg msg) {
		if (loop != null) {
			loop.send(msg);
		}
	}

	public abstract void init();

	public abstract void handle(Msg message);

}
