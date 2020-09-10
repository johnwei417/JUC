package waitAndNotify;

import java.util.Date;
import java.util.LinkedList;


/**
 * honglinwei created on 3/23/20 inside the package - PACKAGE_NAME
 */
public class ProducerAndConsumerModel {
    public static void main(String[] args) {
        Storage storage = new Storage();

        new Thread(new Producer(storage)).start();
        new Thread(new Consumer(storage)).start();
    }

   static  class Producer implements Runnable{
        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.put();
            }
        }
    }
    static class Consumer implements Runnable{
        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.take();
            }
        }
    }
}


class Storage{
    LinkedList<Date> queue;
    int maxSize;

    public Storage() {
        maxSize = 10;
        queue = new LinkedList<>();
    }

    public synchronized void put(){
        if(queue.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(new Date());
        System.out.println("waitAndNotify.Storage has "+queue.size()+" items now");
        notify();
    }

    public synchronized void take(){
        if(queue.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get "+queue.poll()+", there is "+queue.size()+" remaining");
        notify();
    }
}
