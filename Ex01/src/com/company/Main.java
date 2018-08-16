package com.company;

import javafx.scene.paint.PhongMaterial;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Philosopher> philophers = new ArrayList<Philosopher>();
        ArrayList<Integer> requesters = new ArrayList<>();
        int[] forks = new int[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = 0; //assign that all forks are down.
        }

        for (int i = 0; i < 5; i++) {
            philophers.add(new Philosopher(i, forks));
        }

        for (int i = 0; i < philophers.size(); i++) {
            philophers.get(i).start();
        }

        int x = 0;
        while(true) {
            x += 1;
        }
    }
}
