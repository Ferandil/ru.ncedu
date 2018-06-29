package pinPong;

import java.util.concurrent.Semaphore;

public class PongBody extends Thread{
    Semaphore sem;
    Semaphore sem2;
    public PongBody(Semaphore sem, Semaphore sem2){
        this.sem = sem;
        this.sem2 = sem2;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++){
                sem.acquire();
                System.out.println("Pong!");
                sem2.release();
                sem.release();

            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
