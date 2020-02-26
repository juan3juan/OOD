package designPattern;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


enum Rank{
    OPERATOR, SUPERVISOR, DIRECTOR
}

enum CallState{
    READY, IN_PROGRESS, COMPLETE    
}

abstract class Employee{
    protected int employee_id;
    protected String name;
    protected Rank rank;
    protected Call call;
    protected CallCenter callCenter;
    
    public Employee(int eid, String name, Rank rank, Call call, CallCenter cc) {
        this.employee_id = eid;
        this.rank = rank;
        this.name = name;
        this.call = call;
        this.callCenter = cc;
    }
    
    public void takeCall(Call c) {
        this.call = c;
        call.setEmployee(this);
        call.setCallState(CallState.IN_PROGRESS);
        System.out.print(name+" is processing "+this.call.getId());
    }
    
    public void completeCall() {
        call.setCallState(CallState.COMPLETE);
    }
    
    public abstract void escalateCall();
    
//    public Call escalateCall_1() {
//        Call tc = this.call;
//        this.call = null;
//        //self.call_center.notify_call_escalated(call)
//        return tc;
//    }
//}
}

class Operator extends Employee{

    public Operator(int eid, String name, Rank rank, Call call, CallCenter cc) {
        super(eid, name, Rank.OPERATOR, call, cc);
    }
    
    public void escalateCall() {
        this.call.setRank(Rank.SUPERVISOR);
        this.call.setCallState(CallState.READY);
        this.callCenter.callsQueue.add(call);
        this.call = null;
    }
}

class Supervisor extends Employee{

    public Supervisor(int eid, String name, Rank rank, Call call, CallCenter cc) {
        super(eid, name, Rank.SUPERVISOR, call, cc);
    }
    
    public void escalateCall() {
        this.call.setRank(Rank.DIRECTOR);
        this.call.setCallState(CallState.READY);
        this.callCenter.callsQueue.add(call);
        this.call = null;
    }
}

class Director extends Employee{

    public Director(int eid, String name, Rank rank, Call call, CallCenter cc) {
        super(eid, name, Rank.DIRECTOR, call, cc);
    }
    
    public void escalateCall() {
        
    }
}

class Call{
    private CallState callState;
    private Rank rank;
    private Employee employee;
    private int id;
    
    public Call(CallState cs, Rank rank, Employee em, int id) {
        this.callState = cs;
        this.rank = rank;
        this.employee = em;
        this.id = id;
    }

    public CallState getCallState() {
        return callState;
    }

    public void setCallState(CallState callState) {
        this.callState = callState;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

public class CallCenter {
    List<Employee> operators = new ArrayList<>();
    List<Employee> supervisors = new ArrayList<>();
    List<Employee> directors = new ArrayList<>();
    Queue<Call> callsQueue;

    public CallCenter(List<Employee> operators, List<Employee> supervisors, List<Employee> directors) {
        this.operators = operators;
        this.supervisors = supervisors;
        this.directors = directors;
        callsQueue = new LinkedList<>();
    }

    public Employee dispatchCall_1(Call c, List<Employee> es) {
        for (Employee e : es) {
            if (e.call == null) {
                e.takeCall(c);
                return e;
            }
        }
        return null;
    }

    public void dispatchCall(Call call) {
        Employee e = null;
        if (call.getRank() == Rank.OPERATOR)
            e = dispatchCall_1(call, operators);
        else if (call.getRank() == Rank.SUPERVISOR)
            e = dispatchCall_1(call, supervisors);
        else if (call.getRank() == Rank.DIRECTOR)
            e = dispatchCall_1(call, directors);
        if (e == null)
            callsQueue.add(call);
    }
    
    public void checkQueue() {
        while(!callsQueue.isEmpty()) {
            Call call = callsQueue.poll();
            dispatchCall(call);
        }
    }

}

