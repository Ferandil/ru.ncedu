package pinPong;

import java.util.concurrent.Semaphore;

public class PingPong{
    public static void main(String args[]){
        Semaphore mainSem = new Semaphore(1, true);
        Semaphore mainSem2 = new Semaphore(1, true);
        Thread pingThread = new Thread(new PingBody(mainSem, mainSem2));
        Thread pongThread = new Thread(new PongBody(mainSem, mainSem2));
        try {
            mainSem.acquire();
            pingThread.start();
            pongThread.start();
            mainSem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
