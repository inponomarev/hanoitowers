package uk.ac.nulondon;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TowersOfHanoi {
    Deque<Integer> a = new ArrayDeque<>();
    Deque<Integer> b = new ArrayDeque<>();
    Deque<Integer> c = new ArrayDeque<>();

    private final int discsNumber;

    private int step = 1;

    TowersOfHanoi(int discsNumber) {
        for (int i = discsNumber; i > 0; i--) {
            a.push(i);
        }
        this.discsNumber = discsNumber;
    }

    void solve() {
        move(a, c, b, a.size());
    }

    void move(Deque<Integer> from, Deque<Integer> to, Deque<Integer> aux, int depth) {
        if (depth == 1) {
            to.push(from.pop());
            print();
        } else if (depth == 2) {
            aux.push(from.pop());
            print();
            to.push(from.pop());
            print();
            to.push(aux.pop());
            print();
        } else {
            //Freeing up the biggest disc
            move(from, aux, to, depth - 1);
            //Moving the biggest disc to the target
            to.push(from.pop());
            print();
            //Moving the rest of the tower from aux to target
            move(aux, to, from, depth - 1);
        }
    }

    public void print() {

        List<Integer> A = Stream.concat(Collections.nCopies(discsNumber - a.size(), 0).stream(),
                a.stream()).toList();
        List<Integer> B = Stream.concat(Collections.nCopies(discsNumber - b.size(), 0).stream(),
                b.stream()).toList();
        List<Integer> C = Stream.concat(Collections.nCopies(discsNumber - c.size(), 0).stream(),
                c.stream()).toList();
        for (int i = 0; i < discsNumber; i++) {
            System.out.println(
                    disc(A.get(i), discsNumber) + " "
                            + disc(B.get(i), discsNumber) + " "
                            + disc(C.get(i), discsNumber));
        }
        System.out.println("=".repeat((discsNumber + 1) * 6) + step++);
    }

    String disc(int value, int maxWidth) {
        return " ".repeat(maxWidth - value) + "-".repeat(value) + "|" + "-".repeat(value) + " ".repeat(maxWidth - value);
    }


    public static void main(String[] args) {
        TowersOfHanoi h = new TowersOfHanoi(6);

        h.print();
        h.solve();
    }
}
