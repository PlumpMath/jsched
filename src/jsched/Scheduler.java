package jsched;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private List<Fiber> fibers;

	public Scheduler() {
		fibers = new ArrayList<Fiber>();
	}

	public void schedule() {
		while (fibers.size() > 0) {

			List<Fiber> fibs = new ArrayList<Fiber>();

			for (Fiber f : fibers) {
				// System.out.println("*** schedule " + f);
				if (f.run()) {
					fibs.add(f);
				}
			}

			fibers = fibs;
		}
	}

	public void spawn(Continuation... jobs) {
		for (Continuation job : jobs) {
			Fiber f = new Fiber();
			f.start(job);
			fibers.add(f);
		}
	}

	public <T> void spawnActor(Actor<T>... actors) {
		for (Actor<T> a : actors) {
			ActorLoop<T> l = new ActorLoop<T>(a);
			l.start();
			fibers.add(l);
		}
	}
}
