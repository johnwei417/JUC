package Condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class ConditionProducerAndConsumerModel {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionProducerAndConsumerModel conditionProducerAndConsumerModel = new ConditionProducerAndConsumerModel();
        Producer producer = conditionProducerAndConsumerModel.new Producer();
        Consumer consumer = conditionProducerAndConsumerModel.new Consumer();
        producer.start();
        consumer.start();
    }
    class Consumer extends Thread{
        @Override
        public void run(){
            consume();
        }
        private void consume(){
            while(true){
                lock.lock();
                try{
                    while(queue.size() == 0){
                        System.out.println("queue is empty, waiting data ");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    notFull.signalAll();
                    System.out.println("remove one item from queue, there are "+queue.size()+" remaining");
                }finally {
                    lock.unlock();
                }
            }
        }

    }

    class Producer extends Thread{
        @Override
        public void run(){
            produce();
        }
        private void produce(){
            while(true){
                lock.lock();
                try{
                    while(queue.size() == queueSize){
                        System.out.println("queue is full, waiting.. ");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("insert one item to queue, there are "+(queueSize- queue.size())+" remaining");
                }finally {
                    lock.unlock();
                }
            }
        }

    }
}
