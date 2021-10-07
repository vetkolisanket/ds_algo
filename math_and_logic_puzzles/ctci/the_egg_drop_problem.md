The Egg Drop Problem: There is a building of 100 floors. If an egg drops from the Nth floor or above, it will break. If it's dropped from any floor below, it will not break.You're given two eggs. Find N, while minimizing the number of drops for the worst case.

We may observe that, regardless of how we drop Egg 1, Egg 2 must do a linear search (from lowest to highest) between the "breaking floor" and the next highest non-breaking floor. For example, if Egg 1 is dropped from floors 5 and 10 without breaking, but it breaks when it's dropped from floor 15, then Egg 2 must be dropped, in the worst case, from floors 11, 12, 13, and 14.
The Approach
As a first try, suppose we drop an egg from the 10th floor, then the 20th, ...
• If Egg 1 breaks on the first drop (floor 10), then we have at most 10 drops total.
If Egg 1 breaks on the last drop (floor 100), then we have at most 19 drops total (floors 10,20, ...,90, 100, then 91 through 99).
That's pretty good, but all we've considered is the absolute worst case.We should do some "load balancing" to make those two cases more even.
Our goal is to create a system for dropping Egg 1 such that the number of drops is as consistent as possible, whether Egg 1 breaks on the first drop or the last drop.
1. A perfectly load-balanced system would be one in which Drops (Egg 1) + Drops (Egg 2) is always the same, regardless of where Egg 1 breaks.
2. Forthattobethecase,sinceeachdropofEgg1takesonemorestep,Egg2isallowedonefewerstep.
3. We must, therefore, reduce the number of steps potentially required by Egg 2 by one drop each time. For example, if Egg 1 is dropped on floor 20 and then floor 30, Egg 2 is potentially required to take 9 steps. When we drop Egg 1 again, we must reduce potential Egg 2 steps to only 8.That is, we must drop Egg 1at floor 39.
4. Therefore, Egg 1 must start at floor X, then go up by X-1 floors, then X- 2, ..., until it gets to 100. 5. Solve for X.

X+(X - l)+(X - 2)+...+1=100 
X(X+1)/2 =100
X ~ 13.65

X clearly needs to be an integer. Should we round X up or down?
• If we round X up to 14, then we would go up by 14, then 13, then 12, and so on. The last increment would be 4, and it would happen on floor 99. If Egg 1 broke on any of the prior floors, we know we've balanced the eggs such that the number of drops of Egg 1 and Egg 2 always sum to the same thing: 14. If Egg 1 hasn't broken by floor 99, then we just need one more drop to determine if it will break at floor 100. Either way, the number of drops is no more than 14.
• If we round X down to 13, then we would go up by 13, then 12, then 11, and so on. The last increment will be 1 and it will happen at floor 91 . This is after 13 drops. Floors 92 through 100 have not been covered yet. We can't cover those floors in just one drop (which would be necessary to merely tie the"round up"case).
Therefore, we should round X up to 14. That is, we go to floor 14, then 27, then 39, .... This takes 14 steps in
the worse case.
As in many other maximizing / minimizing problems, the key in this problem is "worst case balancing:' The following code simulates this approach.

int breakingPoint = ... , int countDrops = 0;

boolean drop(int floor) { 
	countDrops++;
	return floor >= breakingPoint;
}

int findBreakingPoint(int floors) { 
	int interval = 14;
	int previousFloor = 0;
	int eggl = interval;
	/* Drop eggl at decreasing intervals . */ 
	while (!drop(eggl) && egg1 <= floors) {
		interval -= 1; 
		previousFloor = egg1; 
		egg1 += interval;
	}
	/* Drop egg2 at 1 unit increments. */
	int egg2 = previousFloor + 1;
	while (egg2 < eggl && egg2 <= floors && !drop(egg2)) {
		egg2 += 1; 
	}
	/* If it didn't break, return -1. */
	return egg2 > floors ? -1 : egg2; 
}

If we want to generalize this code for more building sizes, then we can solve for x in: X(X+1)/2 =number of floors
This will involve the quadratic formula .
