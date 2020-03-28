package controller;

import view.SimulatorFrame;
import model.Task;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    public ArrayList<String> list = new ArrayList<String>(100);
    private SimulatorFrame frame;
    //private DisplayFrame df;
    private AtomicInteger waitingPeriod;
    public Task[] ts;
    private int queueID;
    private int taskNr = 0;
    private int nr = 0;
    private int totalWaitingTime = 0;
    private int totalServiceTime = 0;
    private Task aux = new Task (0,0,0);


    public Server(int queueID, SimulatorFrame frame){
        tasks = new ArrayBlockingQueue<Task>(100);
        waitingPeriod = new AtomicInteger();
        //inn = 0;
        this.queueID = queueID;
        this.frame = frame;
        //initialize queue and waitingPeriod
    }

    public int getTotalWaitingTime(){
        return this.totalWaitingTime;
    }

    public int getTotalServiceTime() {
        return this.totalServiceTime;
    }

    public int getTaskNr() {
        return this.taskNr;
    }

    public int getNr() {
        return this.nr;
    }

    public Task[] getTasks(){
        Task[] ts = new Task[list.size()];
        ts = tasks.toArray(ts);
        return ts;
    }

    public int getQueueID() {
        return queueID;
    }

    public AtomicInteger getWaitingPeriod(){
        return this.waitingPeriod;
    }

    public void addTask(Task newTask){
        //add task to queue
        //increment the waitingPeriod
        tasks.add(newTask);
        taskNr++;
        nr++;
        if(!this.tasks.isEmpty()){
            if(aux.getFinishTime() > newTask.getArrivalTime()){
            newTask.setWaitingTime(aux.getFinishTime()-newTask.getArrivalTime());}
        }
        //ts[in++] = newTask;
        getWaitingPeriod().addAndGet(newTask.getProcessingTime());
        totalWaitingTime+=newTask.getWaitingTime();
        totalServiceTime+=newTask.getProcessingTime();
        //totalServiceTime+=newTask.getProcessingTime();
        //newTask.setFinishTime(getWaitingPeriod().intValue());
        //System.out.println(newTask.toString() + " enters queue " + this.getQueueID() + " at time " + newTask.getArrivalTime());
        //list.add("Client " + newTask.toString() + " enters queue " + this.getQueueID() + " at time " + newTask.getArrivalTime());
        frame.addText(frame.area1, newTask.toString() + " enters queue " + this.getQueueID() + " at time " + newTask.getArrivalTime());
        frame.updateQueue(frame.getTextField(this.getQueueID()),'a', newTask.getCustNr());
        aux = newTask;
    }

    public void removeTask(Task oldTask){
        //oldTask.setFinishTime(oldTask.getProcessingTime() + waitingPeriod.intValue());
        tasks.remove();
        taskNr--;
        //totalWaitingTime+=oldTask.getWaitingTime();
        //totalServiceTime+=oldTask.getProcessingTime();
        getWaitingPeriod().addAndGet(-(oldTask.getProcessingTime()));
        //System.out.println(oldTask.toString() + " exits queue " + this.getQueueID() + " at time " + oldTask.getFinishTime());
        //list.add("Client " + oldTask.toString() + " exits queue " + this.getQueueID() + " at time " + oldTask.getFinishTime());
        frame.addText(frame.area1, oldTask.toString() + " exits queue " + this.getQueueID() + " at time " + oldTask.getFinishTime());
        frame.updateQueue(frame.getTextField(this.getQueueID()),'r', oldTask.getCustNr());
    }

    public void run() {
        //ts = new Task[50];
        //System.out.println("Queue has size : " + getTasks2().size());
        while (true) {
            //take next task from queue
            // stop the thread for a time equal with the task's processing time
            //decrement the waitingPeriod
            Task t = new Task(0, 0,0);
            if (tasks.size() != 0) {
                t = tasks.element();
                try {
                    Thread.sleep(t.getProcessingTime() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.removeTask(t);
                //frame.displayData(getTasks());

            }
        }
    }


}
