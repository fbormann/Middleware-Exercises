package com.company;



public class Philosopher extends Thread {
    private PhilosopherAction action = PhilosopherAction.THINKING; //if he is not thinking he is eating
    private int tablePosition;
    private int neighborPosition;
    private int[] forks;

    public enum PhilosopherAction {
        THINKING,
        EATING,
        TRYING_TO_EAT
    }

    public Philosopher(int tablePosition, int[] forks) {
        this.tablePosition = tablePosition;
        this.neighborPosition = (tablePosition + 1) % forks.length;
        this.forks = forks;
    }

    private void stopEating() {
      this.forks[tablePosition] = 0;
      this.forks[neighborPosition] = 0;
      this.action = PhilosopherAction.THINKING;
    }

    public void run() {
      while(true) {
          if(this.action.equals(PhilosopherAction.THINKING)){
            if (forks[tablePosition] == 0) {
              this.forks[tablePosition] = 1;
              this.action = PhilosopherAction.TRYING_TO_EAT;
            }
            if (forks[neighborPosition] == 0) {
              this.forks[neighborPosition] = 1;
              this.action = PhilosopherAction.TRYING_TO_EAT;
            }
          } else if (this.action.equals(PhilosopherAction.TRYING_TO_EAT)) {
            if (forks[tablePosition] == 0) {
              this.forks[tablePosition] = 1;
              this.action = PhilosopherAction.EATING;
            } else if (forks[neighborPosition] == 0) {
              this.forks[neighborPosition] = 1;
              this.action = PhilosopherAction.EATING;
            }
          }
          if (this.action.equals(PhilosopherAction.EATING) ||
            (forks[neighborPosition] == 1 && forks[tablePosition] == 1)) {
            this.action = PhilosopherAction.EATING;
            try {
              Thread.sleep(200);
              stopEating();
            } catch (InterruptedException e) {
              System.out.println(e);
            }
          }
        }
    }

}
