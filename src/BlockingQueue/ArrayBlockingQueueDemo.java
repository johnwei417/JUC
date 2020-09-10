package BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * honglinwei created on 3/23/20 inside the package - PACKAGE_NAME
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Interviewer r1 = new Interviewer(queue);
        Consumer r2 = new Consumer(queue);
        new Thread(r1).start();
        new Thread(r2).start();
     }
}

class Interviewer implements Runnable{
    BlockingQueue<String> queue;

    public Interviewer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("10 candidates already come");
        for(int i = 0; i < 10; i ++){
            String candidate = "Candidate "+ i;

            try {
                queue.put(candidate);
                System.out.println(candidate+" is all set");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable{
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;
        try {
            while(!(msg= queue.take()).equals("stop")){
                System.out.println(msg+" arrived ");
            }
            System.out.println("all candidates are all set");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}