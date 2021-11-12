package com.lzumetal.springboot.netty.wheeltimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-11-11
 */
public class WheelTimerTest {

    static class MyTimerTask implements TimerTask {

        boolean flag;

        public MyTimerTask(boolean flag) {
            this.flag = flag;
        }

        public void run(Timeout timeout) throws Exception {
            System.out.println("处理超时的订单");
            this.flag = false;
        }
    }

    public static void main(String[] argv) {
        MyTimerTask timerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);
        int i = 1;
        while (timerTask.flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + "秒过去了");
            i++;
        }
    }

}
