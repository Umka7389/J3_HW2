public class ABC {
    static volatile char letter = 'A';
    static Object monitor = new Object();
    static int count = 5;

    public static void main(String[] args) {
        Thread threadPrintA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++){
                    synchronized (monitor){
                        while (letter != 'A') {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(letter);
                        letter = 'B';
                        monitor.notifyAll();
                    }
                }
            }
        });
        Thread threadPrintB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++){
                    synchronized (monitor){
                        while (letter != 'B') {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(letter);
                        letter = 'C';
                        monitor.notifyAll();
                    }
                }
            }
        });
        Thread threadPrintC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++){
                    synchronized (monitor){
                        while (letter != 'C') {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(letter);
                        letter = 'A';
                        monitor.notifyAll();
                    }
                }
            }
        });

        threadPrintA.start();
        threadPrintB.start();
        threadPrintC.start();

    }
}
