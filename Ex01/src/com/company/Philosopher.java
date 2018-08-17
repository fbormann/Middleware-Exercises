package com.company;

import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {
    private int tablePosition;
    private int neighborPosition;
    private Lock[] forks;

    public Philosopher(int tablePosition, Lock[] forks) {
      this.tablePosition = tablePosition;
      this.neighborPosition = (tablePosition + 1) % forks.length;
      this.forks = forks;
    }

    private void stopEating() {
      this.forks[tablePosition].unlock();
      this.forks[neighborPosition].unlock();
    }

    private void goEat() {
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        System.out.println(e);
      }
    }

    public void run() {
      while(true) {
        this.forks[tablePosition].lock();
        System.out.println("Philosopher " + tablePosition + ", fork" + tablePosition);
        this.forks[neighborPosition].lock();
        System.out.println("Philosopher " + tablePosition + ", fork" + neighborPosition);
        goEat();
        stopEating();
      }
    }

}
