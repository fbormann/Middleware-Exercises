package com.company;



public class Philosopher extends Thread {
    private PhilosopherAction action = PhilosopherAction.THINKING; //if he is not thinking he is eating
    private int tablePosition;
    private int[] forks;

    public enum PhilosopherAction {
        THINKING,
        EATING
    }

    public Philosopher(int tablePosition, int[] forks) {
        this.tablePosition = tablePosition;
        this.forks = forks;
    }

    public void run() {
        while(true) {
            if (forks[tablePosition] == 0 && forks[(tablePosition + 1) % forks.length] == 0) {
                this.forks[tablePosition] = 1;
                this.forks[(tablePosition + 1) % forks.length] = 1;
                this.action = PhilosopherAction.EATING;
                try {
                    Thread.sleep(200);
                    this.forks[tablePosition] = 0;
                    this.forks[(tablePosition + 1) % forks.length] = 0;
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            } else {

                this.action = PhilosopherAction.THINKING;
            }
        }
    }

}
