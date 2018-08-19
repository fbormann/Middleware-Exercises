package com.company;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.ArrayList;

public class MainWithDeadLock {

  public static void main(String[] args) {
    int AMOUNT_PHILOSOPHERS = 5;
    Philosopher[] philophers = new Philosopher[AMOUNT_PHILOSOPHERS];
    Lock[] forks = new ReentrantLock[AMOUNT_PHILOSOPHERS];

    for (int i = 0; i < AMOUNT_PHILOSOPHERS; i++) {
      forks[i] = new ReentrantLock();
    }

    for (int i = 0; i < AMOUNT_PHILOSOPHERS; i++) {
      philophers[i] = new Philosopher(i, forks);
    }

    for (int i = 0; i < AMOUNT_PHILOSOPHERS; i++) {
      philophers[i].start();
    }
      
  }
}
