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
				if (f.run()) {
					fibs.add(f);
				}
			}

			fibers = fibs;
		}
	}

	public void spawn(Fiber... procs) {
		for (Fiber f : procs) {
			fibers.add(f);
			f.start();
		}
	}
}
