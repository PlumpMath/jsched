import java.util.ArrayDeque;
import java.util.Queue;

public class MailBox<T> {
	private final Queue<T> queue = new ArrayDeque<T>();

	public void addMessage(T msg) {
		queue.add(msg);
	}

	public T tryGetNextMessage() {
		try {
			return queue.remove();
		} catch (Exception e) {
			return null;
		}
	}
}
