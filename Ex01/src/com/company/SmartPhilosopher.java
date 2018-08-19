package com.company;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class SmartPhilosopher extends Thread {
    private PhilosopherAction action = PhilosopherAction.THINKING; //if he is not thinking he is eating
    private int tablePosition;
    private int neighborPosition;
    private int[] forks;
    private ArrayList<ReentrantLock> locks;

    public enum PhilosopherAction {
        THINKING,
        EATING
    }

    public SmartPhilosopher(int tablePosition, int[] forks, ArrayList<ReentrantLock> locks) {
        this.tablePosition = tablePosition;
        this.neighborPosition = (tablePosition + 1) % forks.length;
        this.forks = forks;
        this.locks = locks;
    }

    private synchronized void stopEating() {
        this.forks[tablePosition] = 0;
        this.forks[neighborPosition] = 0;
        this.locks.get(tablePosition).unlock();
        this.locks.get(neighborPosition).unlock();
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
            System.out.println("The philosopher in position " +
                    this.tablePosition + " is thinking");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void run() {
        while (true) {
            if (this.action.equals(PhilosopherAction.THINKING)) {
                if (locks.get(tablePosition).tryLock()) {
                    forks[tablePosition] = 1;

                    if (locks.get(neighborPosition).tryLock()) {
                        forks[neighborPosition] = 1;
                        eat();
                    } else {
                        locks.get(tablePosition).unlock();

                    }
                }
            }
        }
    }
}
