package com.company;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MainWithoutDeadLock {
  public static void main(String[] args) {
    int AMOUNT_PHILOSOPHERS = 5;
    SmartPhilosopher[] philophers = new SmartPhilosopher[AMOUNT_PHILOSOPHERS];
    ReentrantLock[] forks = new ReentrantLock[AMOUNT_PHILOSOPHERS];

    for (int i = 0; i < AMOUNT_PHILOSOPHERS; i++) {
      forks[i] = new ReentrantLock();
    }

    for (int i = 0; i < AMOUNT_PHILOSOPHERS; i++) {
      philophers[i] = new SmartPhilosopher(i, forks);
    }

    for (int i = 0; i < AMOUNT_PHILOSOPHERS; i++) {
      philophers[i].start();
    }
  }
}
