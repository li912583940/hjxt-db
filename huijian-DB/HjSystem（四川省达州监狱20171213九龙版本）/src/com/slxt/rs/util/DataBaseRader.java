package com.slxt.rs.util;

import java.util.Timer;
import java.util.TimerTask;

public class DataBaseRader {
   Timer timer;//定义一个定时器
   int i=0;
   public DataBaseRader(int seconds) {
        timer = new Timer(); 
        timer.schedule(new MyTask(), seconds*1000);//定义好想要执行的任务和每次执行的时间间隔
   }

   class MyTask extends TimerTask {//定义自己的任务类,一定要继承TimerTask
       public void run() {
    	   	System.out.println(i++);
            //查询数据库,更新数据
    	   System.out.println("---");
        }
    }

   public static void main(String args[]) {
        System.out.println("About to schedule task.");


        new DataBaseRader(5);
       
   
      
        System.out.println("Task scheduled.");
    }
}
