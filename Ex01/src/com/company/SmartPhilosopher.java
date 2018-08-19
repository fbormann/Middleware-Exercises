package com.company;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class SmartPhilosopher extends Thread {
  private PhilosopherAction action = PhilosopherAction.THINKING; //if he is not thinking he is eating
  private int tablePosition;
  private int neighborPosition;
  private ReentrantLock[] forks;

  public enum PhilosopherAction {
    THINKING,
    EATING
  }

  public SmartPhilosopher(int tablePosition, ReentrantLock[] forks) {
    this.tablePosition = tablePosition;
    this.neighborPosition = (tablePosition + 1) % forks.length;
    this.forks = forks;
  }

  private synchronized void stopEating() {
    this.forks[tablePosition].unlock();
    this.forks[neighborPosition].unlock();
    this.action = PhilosopherAction.THINKING;
    System.out.println("The philosopher in position " +
            this.tablePosition + " is thinking");
  }

  private void eat() {
    this.action = PhilosopherAction.EATING;
    try {
      System.out.println("The philosopher in position " +
              this.tablePosition + " is EATING");
      Thread.sleep(2000);
      stopEating();
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  public void run() {
      while (true) {
        if (this.action.equals(PhilosopherAction.THINKING)) {
          if (forks[tablePosition].tryLock()) {
            if (forks[neighborPosition].tryLock()) {
                eat();
            } else {
              forks[tablePosition].unlock();
            }
          }
        }
      }
  }
}
