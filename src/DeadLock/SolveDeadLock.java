package DeadLock;

/**
 * honglinwei created on 3/30/20 inside the package - DeadLock
 */
public class SolveDeadLock implements Runnable{

    int flag = 1;
    static Object lock = new Object();
    static Account a = new Account(500);
    static Account b = new Account(500);
    public static void main(String[] args) throws InterruptedException {
        SolveDeadLock r = new SolveDeadLock();
        SolveDeadLock r2 = new SolveDeadLock();
        r.flag = 1;
        r2.flag = 0;

        Thread t3 = new Thread(()->{
            System.out.println("hello");
        });
        t3.start();
       Thread t1 =  new Thread(r);
       Thread t2 = new Thread(r2);
       t1.start();
       t2.start();

        System.out.println("a balance: "+a.balance);
        System.out.println("b balance: "+b.balance);

    }

    @Override
    public void run() {

        if(flag == 1){
           transferMoney(a,b,200);
        }
        if(flag == 0){
            transferMoney(b,a,200);
        }
    }

    public static void transferMoney(Account from, Account to, int amount) {
        if(from.hashCode() > to.hashCode()) {
            synchronized (from) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    if (from.balance - amount < 0) {
                        System.out.println("balance is not enough, transfer failed");
                    }
                    to.balance += amount;
                    from.balance -= amount;
                }
            }
        }else if(from.hashCode() < to.hashCode()){
            synchronized (to) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (from) {
                    if (from.balance - amount < 0) {
                        System.out.println("balance is not enough, transfer failed");
                    }
                    to.balance += amount;
                    from.balance -= amount;
                }
            }
        }else{
            synchronized (lock){
                synchronized (to) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (from) {
                        if (from.balance - amount < 0) {
                            System.out.println("balance is not enough, transfer failed");
                        }
                        to.balance += amount;
                        from.balance -= amount;
                    }
                }
            }
        }

    }

    static class Account{
        int balance;
        public Account(int balance){
            this.balance = balance;
        }
    }
}
