/* *****************************************************************************
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs.
 * An egg breaks if it is dropped from floor T or higher and does not break otherwise.
 * Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:
 * Version 0: 1 egg, ≤T tosses.
 * Version 1: ∼1lgn eggs and ∼1Lg(n) tosses.
 * Version 2: ∼lgT eggs and ∼2Lg(T) tosses.
 * Version 3: 2 eggs and ∼2sqrt(n)tosses.
 * Version 4: 2 eggs and ≤ C sqrt(T) tosses for some fixed constant c.
 * Hints:
 * Version 0: sequential search.
 * Version 1: binary search.
 * Version 2: find an interval containing T of size ≤ 2T, then do binary search.
 * Version 3: find an interval of size sqrt(n), then do sequential search.
 *                                            Note: can be improved to sqrt(2n) tosses.
 * Version 4: 1+2+3+…+t∼12t2. Aim for c = 2sqrt(2)
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class EggsDrop {
    private int critFloor;
    private int numberOfFloors;

    public EggsDrop(int n, int T) {
        this.critFloor = T;
        this.numberOfFloors = n;
    }

    public boolean isEggBreaked(int floor) {
        return floor >= critFloor;//egg breaks
    }

    public int criticalFloor1() {
        int i = 1;
        while (i <= numberOfFloors) {
            if (isEggBreaked(i)) break; //egg breaks
            else i++;
        }
        return i;
    }

    public int criticalFloor2() {
            /*
        int lo = 1;
        int hi = numberOfFloors;
        int mid = lo;
        while (lo <= hi) {
            mid = 1 + (hi - lo) / 2;
            if (isEggBreaked(mid)) {//egg breaks
                if (lo == hi) break;
                hi = mid;
            }
            else if (!isEggBreaked(mid)) lo = mid + 1;//egg does not break
        }
        return mid;

             */
        int high = n;
        int low = 1;
        int mid = low;
        while (high >= low) {
            mid = (high + low) >>> 1;
            if (!test(mid, t)) {
                if (high == low) break;
                high = mid;
            }
            else if (test(mid, t)) {
                low = mid + 1;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        EggsDrop eggdr = new EggsDrop(15, 5);
        StdOut.println("version 1: " + eggdr.criticalFloor1());
        StdOut.println("version 2: " + eggdr.criticalFloor2());
    }
}
