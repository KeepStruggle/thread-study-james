package unSafeThreadDemo;

/**
 * @version 1.8
 * @ClassName TestTortoiseAndRabbitRace
 * @Description 模拟龟兔赛跑
 * @Author James
 * @date 2021/1/31 15:04
 */
public class TestTortoiseAndRabbitRace implements Runnable{

    private String winner;

    public void run() {
        for(int i=0; i<=100; i++){
            //模拟兔子在跑了4步后，休息10毫秒
            if(Thread.currentThread().getName().equals("兔子") && i==5){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //判断比赛是否结束
            boolean flag = gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }


    }

    //判断比赛是否结束
    private boolean gameOver(int steps){
        if(winner != null){
            return true;
        }else {
            if(steps >= 100){
                winner = Thread.currentThread().getName();
                System.out.println(winner + "赢得了比赛");
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TestTortoiseAndRabbitRace testTortoiseAndRabbitRace = new TestTortoiseAndRabbitRace();

        new Thread(testTortoiseAndRabbitRace, "兔子").start();
        new Thread(testTortoiseAndRabbitRace, "乌龟").start();

    }

}
