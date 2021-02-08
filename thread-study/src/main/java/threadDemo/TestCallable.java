package threadDemo;

import java.util.concurrent.*;

/**
 * @version 1.8
 * @ClassName TestCallable
 * @Description 线程创建方式三：实现callable接口
 *              callable的好处：1、可以定义返回值；2、可以抛出异常；
 * @Author James
 * @date 2021/1/31 15:31
 */
public class TestCallable implements Callable<Boolean> {

    //网络图片地址
    private String url;
    //保存的文件名
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=310092035,1791877477&fm=26&gp=0.jpg", "1.jpg");
        TestCallable t2 = new TestCallable("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2385730798,3482830449&fm=26&gp=0.jpg", "2.jpg");
        TestCallable t3 = new TestCallable("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3384913163,1256852657&fm=26&gp=0.jpg", "3.jpg");

        //1. 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //2. 提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        //3. 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        //4. 关闭服务
        ser.shutdownNow();


    }
}


