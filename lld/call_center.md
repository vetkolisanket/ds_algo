
<b>Call Center</b>: Imagine you have a call center with three levels of employees: respondent, manager, and director. An incoming telephone call must be first allocated to a respondent who is free. If the respondent can't handle the call, he or she must escalate the call to a manager. If the manager is not free or not able to handle it, then the call should be escalated to a director. Design the classes and data structures for this problem. Implement a method dispatchCall() which assigns a call to the first available employee.

<b>Solution</b>: All three ranks of employees have different work to be done, so those specific functions are profile specific. We should keep these things within their respective class.

There are a few things which are common to them, like address, name, job title, and age. These things can be kept in one class and can be extended or inherited by others.

Finally,there should be one CallHandler class which would route the calls to the correct person.

Note that on any object-oriented design question, there are many ways to design the objects. Discuss the trade-offs of different solutions with your interviewer. You should usually design for long-term code flex- ibility and maintenance.

We'll go through each of the classes below in detail.

Employee is a super class for the Director, Manager and Respondent classes. It is implemented as an abstract class since there should be no reason to instantiate an employee directly

```
abstract class Employee {
  private Call currentCall = null;
  protected Rank rank;
  
  public Employee(CallHandler handler) {
    //TODO();
  }
  
  /*Start the conversation*/
  public void receiveCall(Call call) {
    currentCall = call;
  }
  
  /*the issue is resolved, finish the call*/
  public void callCompleted() {
    currentCall = null;
  }
  
  /*The issue has not been resolved. Escalate the call, and assign a new call to the employee*/
  public void escalateAndReassign() {
    //TODO();
  }
  
  /*Assign a new call to the employee, if the employee is free. */
  public boolean assignNewCall() {
    if (isFree()) {
      //TODO();
    }
  }
  
  /*Returns whether or not the employee is free*/
  public boolean isFree() {
    return currentCall == null;
  }
  
  public Rank getRank() { return rank; }
}
```

The Respondent, Manager and Director classes are now just simple extensions of the Employee class.

```
class Director extends Employee {
  public Director() {
    rank = Rank.Director;
  }
}

class Manager extends Employee {
  public Manager() {
    rank = Rank.Manager;
  }
}

class Respondent extends Employee {
  public Respondent() {
    rank = Rank.Responder;
  }
}
```
Call represents a call from a user. Acall has a minimum rank and is assigned to the first employee who can handle it.

```
public class Call {
  /*Minimum rank of employee who can handle this*/
  private Rank rank;
  
  /*Person who is calling*/
  private Caller caller;
  
  /*Employee who is handling call*/
  private Employee handler;
  
  public Call(Caller c) {
    rank = Rank.Responder;
    caller = c;
  }
  
  /*Set employee who is handling this call*/
  public void setHandler(Employee e) {
    handler = e;
  }
  
  public void reply(String message) {
    //TODO();
  }
  
  public Rank getRank() { return rank; }
  
  public void setRank(Rank r) { rank = r; }
  
  public void incrementRank() {
    //TODO();
  }
  
  public void disconnect() {
    //TODO();
  }
}
```


CallHandler represents the body of the program, and all calls are funneled first through it.

```
public class CallHandler {
  /*3 levels of employees: respondents, managers, directors. */
  private final int LEVELS = 3;
  
  /*Initialize 10 respondents, 4 managers and 2 directors*/
  private final int NUM_RESPONDENTS = 10;
  private final int NUM_MANAGERS = 4;
  private final int NUM_DIRECTORS = 2;
  
  /* List of employees, by level.
  * employeeLevels[0] = respondents
  * employeeLevels[1] = managers
  * employeeLevels[2] = directors
  */
  List<List<Employee>> employeeLevels;
  
  /*queues for each call's rank*/
  List<List<Call>> callQueues;
  
  public CallHandler() {
    //TODO();
  }
  
  /*Gets the first employee who can handle this call.*/
  public Employee getHandlerForCall(Call call) {
    //TODO();
  }
  
  /*Routes the call to an available employee, or saves in a queue if no employee is available*/
  public void dispatchCall(Call call) {
    /*Try to route the call to an employee with minimal rank.*/
    Employee emp = getHandlerForCall(call);
    if (emp != null) {
      emp.receiveCall(call);
      call.setHandler(emp);
    } else {
      /*Place the call in the corresponding call queue according to its rank.*/
      call.reply("Please wait for free employee to reply.")
      callQueue.get(call.getRank().getValue()).add(call);
    }
  }
  
  /*An employee got free. Look for a waiting call that employee can serve. Return true if we assigned a call, false otherwise. */
  public boolean assignCall(Employee emp) {
    //TODO();
  }
}
```
