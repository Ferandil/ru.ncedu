package pinPong;

import java.util.concurrent.Semaphore;

public class PingBody extends Thread{
    Semaphore sem;
    Semaphore sem2;
    public PingBody(Semaphore sem, Semaphore sem2){
        this.sem = sem;
        this.sem2 = sem2;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++){
                sem.acquire();
                while(true){
                    if( !sem2.tryAcquire()){
                        sem.release();
                        break;
                    }
                }

                System.out.println("Ping!");
                sem.release();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
