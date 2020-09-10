package Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * honglinwei created on 3/24/20 inside the package - PACKAGE_NAME
 */
public class ConditionDemo {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    void method1(){
        lock.lock();
        try{
            System.out.println("condition is not fullfill, start waiting");
            try {
                condition.await();
                System.out.println("condition is fullfill, resume task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lock.unlock();
        }
    }

    void method2(){
        lock.lock();
        try{
            System.out.println("tasks are ready, wake up other thread");
            condition.signalAll();
        }finally {
            lock.unlock();
        }
        }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo = new ConditionDemo();
        new Thread(()->{
            conditionDemo.method1();
        }).start();

        Thread.sleep(1000);
        new Thread(()->{
            conditionDemo.method2();
        }).start();
    }
}
