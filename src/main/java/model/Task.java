package model;
import java.util.Comparator;

public class Task {
    private int arrivalTime;
    private int processingTime;
    private int arrivesAfter;
    private int nr;
    private int finishTime= 0;
    private int waitingTime = 0;
    private boolean added = false;

    public Task(int arrivesAfter, int arrivalTime, int processingTime){
        this.arrivesAfter = arrivesAfter;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }
    public int getArrivalTime(){
        return this.arrivalTime;
    }

    public int getArrivesAfter() {
        return arrivesAfter;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime(){
        return this.waitingTime;
    }

    public int getProcessingTime(){
        return this.processingTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }


    public void setCustNr(int nr){
        this.nr = nr;
    }
    public int getCustNr(){
        return this.nr;
    }

    public static Comparator<Task> arrTime = new Comparator<Task>() {

        public int compare(Task t1, Task t2) {

            int arrival1 = t1.getArrivalTime();
            int arrival2 = t2.getArrivalTime();

            return arrival1-arrival2;

        }
    };

    public int compareTo(Task compare) {
        int compArr=((Task)compare).getArrivalTime();
        return this.arrivalTime-compArr;
    }

    public String toString(){
        return "Client " + nr + " : " + arrivalTime + " , " + processingTime;
    }

}
