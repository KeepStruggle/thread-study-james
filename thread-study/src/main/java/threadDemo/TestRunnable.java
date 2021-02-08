package threadDemo;

/**
 * @version 1.8
 * @ClassName TestRunnable
 * @Description 创建线程方式二：实现runnable接口，重写run方法，执行线程需要丢入runnable接口实现类，调用start方法
 * @Author James
 * @date 2021/1/31 14:27
 */
public class TestRunnable implements Runnable {

    public void run() {
        //run方法线程体
        for (int i=0; i<10; i++){
            System.out.println("我在看代码————" + i);
        }
    }

    public static void main(String[] args) {
        TestRunnable testRunnable = new TestRunnable();
        //创建线程对象，通过线程对象来开启我们的线程
//        Thread thread = new Thread(testRunnable);
//        thread.start();

        new Thread(testRunnable).start();



        for (int i=0; i<1000; i++){
            System.out.println("我在学习多线程----" + i);
        }
    }
}

