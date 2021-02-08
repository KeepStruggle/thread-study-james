package unSafeThreadDemo;

/**
 * @version 1.8
 * @ClassName TestWithdrawMoney
 * @Description TODO
 * @Author James
 * @date 2021/2/7 17:59
 */
public class TestWithdrawMoney implements Runnable{

    private Account account;    //账户
    private int drawingMoney;   //取了多少钱
    private int nowMoney;       //现在手里有多少钱

    public TestWithdrawMoney(Account account, int drawingMoney, int nowMoney) {
        this.account = account;
        this.drawingMoney = drawingMoney;
        this.nowMoney = nowMoney;
    }

    public void run() {
        //锁的对象是变化的量，就是增删改的量
        synchronized (account) {
            if ((account.getMoney() - drawingMoney) < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡里余额 = 余额 - 你取得钱
            account.money = account.money - drawingMoney;
            //你手里的钱
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name + "余额为：" + account.money);
            System.out.println(Thread.currentThread().getName() + "手里的钱：" + nowMoney);
        }

    }

    public static void main(String[] args) {
        Account account = new Account("存款", 100);
        TestWithdrawMoney testWithdrawMoney = new TestWithdrawMoney(account,50, 0);
        TestWithdrawMoney testWithdrawMoney1 = new TestWithdrawMoney(account, 100, 0);

        new Thread(testWithdrawMoney, "张三").start();
        new Thread(testWithdrawMoney1, "王五").start();
//        System.out.println(testWithdrawMoney1.account.getMoney());
//        testWithdrawMoney.account.setMoney(50);
//        System.out.println(testWithdrawMoney1.account.getMoney());

    }
}

class Account{
    String name;    //账户名
    volatile int money;  //余额

    public Account() {
    }

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

