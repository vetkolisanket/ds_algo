/*
Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in Linked L is t data structure.
*/
//A cleaner soln
abstract class Animal {
	private int order;
	protected String name;
	public Animal(String n) { name = n;  }
	public void setOrder(int ord) { order = ord;  }
	public int getOrder() { return order;  }

	public boolean isOlderThan(Animal a) {
		return this.order < a.getOrder();
	}
}

public class Dog extends Animal {
	public Dog(String n) { super(n);  }
}

public class Car extends Animal {
	public Cat(String n) { super(n);  }
}

class AnimalQueue {
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0;

	public void enqueue(Animal a) {
		a.setOrder(order);
		order++;

		if (a instanceof Dog) dogs.addLast((Dog) a);
		else if (a instanceof Cat) dogs.addLast((Cat) a);
	}

	public Animal dequeueAny() {
		if (dogs.size() == 0) return dequeueCats();
		else if (cats.size == 0) return dequeueDogs();

		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if (dog.isOlderThan(cat)) dequeueDogs();
		else dequeueCats();
	}

	public Dog dequeueDogs() { return dogs.poll();  }

	public Cat dequeueCats() { return cats.poll();  }
}

//My Soln
class AnimalShelter {

	LinkedList<String> list;

	public AnimalShelter() {
		list = new LinkedList<String>();
	}

	public void enqueue(String animal) {
		list.add();
	}

	public String dequeueAny() throws EmptyQueueException {
		if (list.isEmpty()) throw new EmptyQueueException();
		return list.remove();
	}

	public String dequeueDog() throws EmptyQueueException, AnimalNotFoundException {
		if (list.isEmpty()) throw new EmptyQueueException();
		int index = list.indexOf("DOG");
		if (index == -1) throw AnimalNotFoundException("DOG");
		String dog = list.get(index);
		list.remove(dog);
		return dog;
	}

	public String dequeueCat() throws EmptyQueueException, AnimalNotFoundException {
		if (list.isEmpty()) throw new EmptyQueueException();
		int index = list.indexOf("CAT");
		if (index == -1) throw AnimalNotFoundException("CAT");
		String cat = list.get(index);
		list.remove(cat);
		return cat; 
}

}
