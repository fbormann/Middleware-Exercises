package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
  private int tablePosition;
  private int neighborPosition;
  private ReentrantLock[] forks;

  public Philosopher(int tablePosition, ReentrantLock[] forks) {
    this.tablePosition = tablePosition;
    this.neighborPosition = (tablePosition + 1) % forks.length;
    this.forks = forks;
  }

  private void stopEating() {
    this.forks[tablePosition].unlock();
    this.forks[neighborPosition].unlock();
    System.out.println("The philosopher in position " +
    this.tablePosition + " is thinking");
  }

  private void goEat() {
    try {
      System.out.println("The philosopher in position " +
      this.tablePosition + " is EATING");
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
