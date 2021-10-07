100 Lockers: There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers. Next, he closes every second locker. Then, on his third pass, he toggles every third locker (closes it if it is open or opens it if it is closed). This process continues for 100 passes, such that on each pass i, the man toggles every ith locker. After his 100th pass in the hallway, in which he toggles only locker #100, how many lockers are open?

We can tackle this problem by thinking through what it means for a door to be toggled.This will help us deduce which doors at the very end will be left opened.
Question: For which rounds is a door toggled (open or closed)?
A door n is toggled once for each factor of n, including itself and 1. That is, door 15 is toggled on rounds 1, 3,5, and 15.
Question: When would a door be left open?
A door is left open if the number of factors (which we will call x) is odd.You can think about this by pairing factors off as an open and a close. If there's one remaining, the door will be open.
Question: When would x be odd?
The value x is odd if n is a perfect square. Here's why: pair n's factors by their complements. For example, if n is 36, the factors are (1, 36), (2, 18), (3, 12), (4, 9), (6,6). Note that (6, 6) only contributes one factor, thus giving n an odd number offactors.
Question: How many perfect squares are there?
There are 10 perfect squares. You could count them (1 , 4,9, 16, 25, 36,49,64, 81, 100)' or you could simply realize that you can take the numbers 1 through 10 and square them:
1*1, 2*2, 3*3, ... , HI*10 Therefore, there are 10 lockers open at the end of this process.
