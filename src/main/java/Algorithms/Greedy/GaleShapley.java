package Algorithms.Greedy;

import java.util.*;

public class GaleShapley {

    static final int N = 3;

    public static void main(String[] args) {

        // Men's preferences
        int[][] menPref = {
            {0, 1, 2}, // M1
            {0, 1, 2}, // M2
            {0, 1, 2}  // M3
        };

        // Women's preferences (ranking matrix)
        int[][] womenRank = {
            {1, 2, 0}, // W1:
            {2, 0, 1}, // W2:
            {0, 1, 2}  // W3:
        };

        stableMatching(menPref, womenRank);
        //
    }

    public static void stableMatching(int[][] menPref, int[][] womenRank) {

        int[] womenPartner = new int[N];
        boolean[] menFree = new boolean[N];
        int[] nextProposal = new int[N];

        Arrays.fill(womenPartner, -1);
        Arrays.fill(menFree, true);

        int freeCount = N;
        int proposals = 0;

        while (freeCount > 0) {
            int m;
            for (m = 0; m < N; m++) {
                if (menFree[m]) break;
            }

            int w = menPref[m][nextProposal[m]];
            nextProposal[m]++;
            proposals++;

            if (womenPartner[w] == -1) {
                womenPartner[w] = m;
                menFree[m] = false;
                freeCount--;
            } else {
                int mPrime = womenPartner[w];

                if (prefersNewMan(womenRank, w, m, mPrime)) {
                    womenPartner[w] = m;
                    menFree[m] = false;
                    menFree[mPrime] = true;
                }
            }
        }

        // Output results
        System.out.println("Final Matching:");
        for (int w = 0; w < N; w++) {
            System.out.println("W" + (w + 1) + " - M" + (womenPartner[w] + 1));
        }

        System.out.println("Total proposals (iterations): " + proposals);
    }

    // Check if woman prefers m over m'
    public static boolean prefersNewMan(int[][] rank, int w, int m, int mPrime) {
        return rank[w][m] < rank[w][mPrime];
    }
}