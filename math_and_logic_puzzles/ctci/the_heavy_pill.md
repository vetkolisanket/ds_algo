The Heavy Pill: You have 20 bottles of pills. 19 bottles have 1.0 gram pills, but one has pills of weight 1.1 grams. Given a scale that provides an exact measurement, how would you find the heavy bottle? You can only use the scale once

Sometimes, tricky constraints can be a clue. This is the case with the constraint that we can only use the scale once.
Because we can only use the scale once, we know something interesting: we must weigh multiple pills at the same time. In fact, we know we must weigh pills from at least 19 bottles at the same time. Other- wise, if we skipped two or more bottles entirely, how could we distinguish between those missed bottles? Remember that we only have one chance to use the scale.
So how can we weigh pills from more than one bottle and discover which bottle has the heavy pills? Let's suppose there were just two bottles, one of which had heavier pills. If we took one pill from each bottle, we would get a weight of 2.1 grams, but we wouldn't know which bottle contributed the extra 0.1 grams. We know we must treat the bottles differently somehow.
If we took one pill from Bottle #1 and two pills from Bottle #2, what would the scale show? It depends. If Bottle #1 were the heavy bottle, we would get 3.1 grams. If Bottle #2 were the heavy bottle, we would get 3.2 grams. And that is the trick to this problem.
We know the "expected" weight of a bunch of pills. The difference between the expected weight and the actual weight will indicate which bottle contributed the heavier pills, provided we select a different number of pills from each bottle.
We can generalize this to the full solution: take one pill from Bottle #1, two pills from Bottle #2, three pills from Bottle #3, and so on. Weigh this mix of pills. If all pills were one gram each, the scale would read 210 grams(1 + 2 + '" + 213 =213 * 21 / 2 =2113).Any"overage"mustcomefromtheextra0.1 gram pills.
This formula will tell you the bottle number:
weight - 21e grams
e.1 grams
So, if the set of pills weighed 211.3 grams, then Bottle #13 would have the heavy pills..
