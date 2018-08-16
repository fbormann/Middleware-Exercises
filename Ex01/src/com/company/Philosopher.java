package com.company;



public class Philosopher {
    private int leftFork;
    private int rightFork;
    private PhilosopherAction action = PhilosopherAction.THINKING; //if he is not thinking he is eating
    private int tablePosition;

    public enum PhilosopherAction {
        THINKING,
        EATING
    }

    public Philosopher(int tablePosition) {
        this.leftFork = 1;
        this.rightFork = 0;
        this.tablePosition = tablePosition;
    }

    

    public boolean tryToEat(int[] forks) {
        if (forks[tablePosition] == 0 && forks[tablePosition+1] == 0) {
            thinking = false;
            return true;
        } else {
            return false;
        }
    }
}
