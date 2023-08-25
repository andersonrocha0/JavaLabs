import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var runnablePaTaPaRa = new MyRunnable("1PaTaPaRa");
        var runnableJuBaLuBa = new MyRunnable("2JuBaLuBa");
        var paTaPaRa = new Thread(runnableJuBaLuBa);
        var juBaLuBa = new Thread(runnablePaTaPaRa);

        var threadsToTurnOff = new HashMap<String, MyRunnable>();
        threadsToTurnOff.put("PaTaPaRa", runnablePaTaPaRa);
        threadsToTurnOff.put("JuBaLuBa", runnableJuBaLuBa);

        new Thread(new MyServerRunnable(5555, threadsToTurnOff)).start();
        paTaPaRa.start();
        juBaLuBa.start();

        try {
            paTaPaRa.join();
            juBaLuBa.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main thread, time to death");
    }

}
