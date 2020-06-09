package com.peterstev.socklaundering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SockLaundry {

    public int getMaximumPairOfSocks(int noOfWashes, int[] cleanPile, int[] dirtyPile) {

        if (noOfWashes < 0 || noOfWashes > 50) return -1;
        if (cleanPile.length < 1 || cleanPile.length > 50
                || dirtyPile.length < 1 || dirtyPile.length > 50)
            return -1;

        final int[] sockPairCount = {0};
        final int[] washCounter = {0};
        List<Integer> cleanSockPile = new ArrayList<>();
        List<Integer> dirtySockPile = Arrays.stream(dirtyPile).boxed().collect(Collectors.toList());

        for (int sock : cleanPile) {
            if (cleanSockPile.contains(sock)) {
                //if this sock exists in the sockPile.
                //we have a clean pair, hence remove both the old sock and the new entry
                //thus the sockPile is always, either 1 or 0
                cleanSockPile.remove(Integer.valueOf(sock));
                sockPairCount[0]++;
                continue;
            }
            //whatever is left in the pile after sorting == the unpaired clean sock
            cleanSockPile.add(sock);
        }

        //attempt to merge clean single pairs to washed pairs
        for (int x = 0; x < cleanSockPile.size(); x++) {
            int sock = cleanSockPile.get(x);

            if (washCounter[0] < noOfWashes)
                if (dirtySockPile.contains(sock)) {
                    cleanSockPile.remove(Integer.valueOf(sock));
                    dirtySockPile.remove(Integer.valueOf(sock));
                    x--;
                    sockPairCount[0]++;
                    washCounter[0]++;
                }
        }

        Collections.sort(dirtySockPile);
        int dirtyMaxSize = dirtySockPile.size();
        for (int x = 0; x < dirtySockPile.size(); x++) {
            if (washCounter[0] + 1 < noOfWashes) {
                int sock = dirtySockPile.get(x);
                if (x < dirtyMaxSize - 1) {
                    if (sock == dirtySockPile.get(x + 1)) {
                        washCounter[0] = washCounter[0] + 2;
                        sockPairCount[0]++;
                        x++;
                    }
                }
            }
        }

        if (noOfWashes == 0) {
            // If Anna's machine isn't working, i.e noOfWashes is 0.
            // The maximum number of socks she can take on her trips is 1.
            return Math.max(sockPairCount[0], 0);
        }

        return sockPairCount[0];
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void print(Integer message) {
        System.out.println(message);
    }
}