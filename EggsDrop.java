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
    private long critFloor;
    private long numberOfFloors;

    public EggsDrop(long n, long T) {
        this.critFloor = T;
        this.numberOfFloors = n;
    }

    public boolean isEggBreaked(long floor) {
        return floor >= critFloor;//egg breaks
    }

    public long criticalFloor1() {
        long i = 1;
        while (i <= numberOfFloors) {
            if (isEggBreaked(i)) break; //egg breaks
            else i++;
        }
        return i;
    }

    public long criticalFloor2() {

        long lo = 1;
        long hi = numberOfFloors;
        long mid = lo;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (isEggBreaked(mid)) {//egg breaks
                hi = mid; // so as not to exclude the critical floor (not mid-1)
            }
            else if (!isEggBreaked(mid)) {
                lo = mid + 1;//egg does not break
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        EggsDrop eggdr = new EggsDrop(1000000000, 96314894);
        StdOut.println("version 1: " + eggdr.criticalFloor1());
        StdOut.println("version 2: " + eggdr.criticalFloor2());
    }
}
