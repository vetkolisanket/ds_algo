The Apocalypse: In the new post-apocalyptic world, the world queen is desperately concerned about the birth rate. Therefore, she decrees that all families should ensure that they have one girl or else they face massive fines. If all families abide by this policy-that is, they have continue to have children until they have one girl, at which point they immediately stop-what will the gender ratio of the new generation be? (Assume that the odds of someone having a boy or a girl on any given pregnancy is equal.) Solve this out logically and then write a computer simulation of it.

If each family abides by this policy, then each family will have a sequence of zero or more boys followed by a single girl. That is, if"G" indicates a girl and "B" indicates a boy, the sequence of children will look like one of: G; BG; BBG; BBBG; BBBBG; and so on.
We can solve this problem multiple ways.
Mathematically
We can work out the probability for each gender sequence.
P(G) = 1/2.That is, 50% offamilies will have a girl first. The others will go on to have more children. P(BG) = 1/4. Of those who have a second child (which is 50%), 50% of them will have a girl the next time. P(BBG) = 1/8. Of those who have a third child (which is 25%), 50% of them will have a girl the next time.
And so on.
We know that every family has exactly one girl. How many boys does each family have, on average? To compute this, we can look at the expected value of the number of boys.The expected value of the number of boys is the probability of each sequence multiplied by the number of boys in that sequence.

Or in other words, this is the sum of i to infinity of i divided by 21. "0 i
L..i=o 2'T
You probably won't know this off the top of your head, but we can try to estimate it. Let's try converting the
above values to a common denominator of 128(2^6). 

This looks like it's going to inch closer to 128/128 (which is of course 1). This "looks like" intuition is valuable, but it's not exactly a mathematical concept. It'sa clue though and we can turn to logic here. Should it be 1?

Logically
Ifthe earlier sum is 1, this would mean that the gender ratio is even.Families contribute exactly one girl and
on average one boy.The birth policy is therefore ineffective. Does this make sense?
At first glance, this seems wrong. The policy is designed to favor girls as it ensures that all families have a girl.

On the other hand, the families that keep having children contribute (potentially) multiple boys to the
population.This could offset the impact of the "one girl" policy.
One way to th ink about this is to imagine that we put all t he gender sequence of each family into one giant
string. So if family 1 has BG, family 2 has BBG, and family 3 has G, we would write BGBBGG.
In fact, we don't really care about the groupings of families because we're concerned about the population
as a whole. As soon as a child is born, we can just append its gender (B or G) to the string.
What are the odds of the next character being a G? Well, if the odds of having a boy and girl is the same, t h e n t h e o d d s o f t h e n e x t c h a r a c t e r b e i n g a G i s 5 0 % . T h e r e f o r e , r o u g h l y h a l f o f t h e s t r i n g s h o u l d b e Gs a n d half should be Bs, giving an even gender ratio.
This actually makes a lot of sense. Biology hasn't been changed. Half of newborn babies are girls and half are boys. Abiding by some rule about when to stop having children doesn't change this fact.
Therefore, the gender ratio is 50% girls and 50% boys.

Simulation
We'll write this in a simple way that directly corresponds to the problem.

double runNFamilies(int n) {
	int boys = 0;
	int girls = 0;
	for (int i = 0; i < n; i++) {
		int[] genders = runOneFamily();
		girls += genders[0];
		boys += genders[1];
	}
	return girls/(double) (boys + girls);
}

int[] runOneFamily() {
	Random random = new Random();
	int boys = 0;
	int girls = 0;
	while (girls == 0) {
		if (random.nextBoolean()) {
			girls++;
		} else {
			boys++;
		}	
	}	
	int[] genders = {girls, boys};
	return genders;
}

Sure enough, if you run this on large values of n, you should get something very close to 0.5.
