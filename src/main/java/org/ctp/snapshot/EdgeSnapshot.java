package org.ctp.snapshot;

import org.ctp.CTPGraph.EdgeStatus;
import org.jgrapht.graph.Edge;

import java.util.Random;

public class EdgeSnapshot extends Edge {

    private EdgeStatus currentState = EdgeStatus.UNKNOWN;

//    public EdgeSnapshot(Boolean isBlocked, Edge e) {
//        this.isBlocked = isBlocked;
//        this.setSource(e.getSource());
//        this.setDest(e.getDest());
//    }

    public EdgeStatus getCurrentState() {
        return currentState;
    }

    private boolean conductIsBlocked() {

        Double roundedProb = this.getBlockingProb();
        Integer numberOfBlocked = (int) Math.round(roundedProb * 10);

        Boolean[] maybeBlocked = new Boolean[10];
        for (int i = 0; i < numberOfBlocked; i++) {
            maybeBlocked[i] = true;
        }
        for (int i = numberOfBlocked; i < 10; i++) {
            maybeBlocked[i] = false;
        }

        int resultingIndex = getRandomNumberUsingInts(0, 10);

        return maybeBlocked[resultingIndex];
    }

    private int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }


}
