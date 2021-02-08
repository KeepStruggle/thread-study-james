package unSafeThreadDemo;

/**
 * @version 1.8
 * @ClassName TestBuyTicket
 * @Description 多个线程同时操作同一个对象
 *              买火车票的例子
 * @Author James
 * @date 2021/1/31 14:45
 */
//发现问题：多个线程同时操作同一个资源的情况下，线程不安全，数据絮乱
public class TestBuyTicket implements Runnable{

    //票数
    private int ticketNums = 10;

    public void run() {
        while(true){
            synchronized (this) {
                if (ticketNums <= 0) {
                    break;
                }
                //模拟延时，扩大问题的发生性
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "抢到了第" + ticketNums-- + "票");
            }

        }
    }

    public static void main(String[] args) {
        TestBuyTicket testBuyTicket = new TestBuyTicket();

        new Thread(testBuyTicket, "小明").start();
        new Thread(testBuyTicket, "老师").start();
        new Thread(testBuyTicket, "黄牛党").start();

    }
}

