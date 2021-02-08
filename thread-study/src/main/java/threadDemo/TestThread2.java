package threadDemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @version 1.8
 * @ClassName TestThread2
 * @Description 练习Thread, 实现多线程同步下载图片
 * @Author James
 * @date 2021/1/30 15:57
 */
public class TestThread2 extends Thread{

    //网络图片地址
    private String url;
    //保存的文件名
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为：" + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=310092035,1791877477&fm=26&gp=0.jpg", "1.jpg");
        TestThread2 t2 = new TestThread2("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2385730798,3482830449&fm=26&gp=0.jpg", "2.jpg");
        TestThread2 t3 = new TestThread2("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3384913163,1256852657&fm=26&gp=0.jpg", "3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }

}


/**
 * 下载器
 */
class  WebDownloader{
    /**
     * 下载方法
     */
    public void downloader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }

}