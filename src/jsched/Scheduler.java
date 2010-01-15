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
				// System.out.println("-- run " + p);
				Continuation run = f.continuation.run();
				// System.out.println("-- got " + run);
				if (run != null) {
					f.continuation = run;
					fibs.add(f);
				}
			}
			fibers = fibs;
		}
	}

	public void spawn(Fiber... procs) {
		for (Fiber process : procs) {
			// System.out.println("-- schedule " + process);
			fibers.add(process);
			process.start(this);
		}
	}
}
