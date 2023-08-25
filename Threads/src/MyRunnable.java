public class MyRunnable implements Runnable {

    private final String name;

    private boolean isRunning = true;

    public void setRunning(boolean running) {
        isRunning = running;
    }

    MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("I'm a thread with name: " + name);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Time to leave running thread: " + name);

    }
}
