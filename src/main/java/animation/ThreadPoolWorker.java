package animation;
// uses class ObjectFIFO from chapter 18

public class ThreadPoolWorker extends Object {
	private static int nextWorkerID = 0;

	private ObjectFIFO idleWorkers;
	private int workerID;
	private ObjectFIFO handoffBox;

	private Thread internalThread;
	private volatile boolean noStopRequested;

	public ThreadPoolWorker(ObjectFIFO idleWorkers) {
		this.idleWorkers = idleWorkers;

		workerID = getNextWorkerID();
		handoffBox = new ObjectFIFO(1); // only one slot

		// just before returning, the thread should be created and started.
		noStopRequested = true;

		Runnable r = new Runnable() {
				public void run() {
					try {
						runWork();
					} catch ( Exception x ) {
						// in case ANY exception slips through
						x.printStackTrace(); 
					}
				}
			};

		internalThread = new Thread(r);
		internalThread.start();
	}

	public static synchronized int getNextWorkerID() { 
		// notice: synchronized at the class level to ensure uniqueness
		int id = nextWorkerID;
		nextWorkerID++;
		return id;
	}

	public void process(Runnable target) throws InterruptedException {
		handoffBox.add(target);
	}

	private void runWork() {
		while ( noStopRequested ) {
			try {
//				System.out.println("workerID=" + workerID + 
//						", ready for work");
				// Worker is ready work. This will never block since the
				// idleWorker FIFO queue has enough capacity for all of
				// the workers.
				idleWorkers.add(this);

				// wait here until the server puts a request into the box
				Runnable r = (Runnable) handoffBox.remove();

//				System.out.println("workerID=" + workerID + 
//						", starting execution of new Runnable: " + r);
				runIt(r); // catches all exceptions
			} catch ( InterruptedException x ) {
				Thread.currentThread().interrupt(); // re-assert
			}
		}
	}

	private void runIt(Runnable r) {
		try {
			r.run();
		} catch ( Exception runex ) {
			// catch any and all exceptions 
			System.err.println("Uncaught exception fell through from run()");
			runex.printStackTrace();
		} finally {
			// Clear the interrupted flag (in case it comes back set)
			// so that if the loop goes again, the 
			// handoffBox.remove() does not mistakenly throw
			// an InterruptedException.
			Thread.interrupted();
		}
	}

	public void stopRequest() {
		System.out.println("workerID=" + workerID + 
				", stopRequest() received.");
		noStopRequested = false;
		internalThread.interrupt();
	}

	public boolean isAlive() {
		return internalThread.isAlive();
	}
}
