package controller;

import model.Scheduler;
import model.SelectionPolicy;
import view.SimulatorFrame;
import model.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.lang.*;

public class SimulationManager implements Runnable {
    //data read from UI
    private Thread t;
    private SimulatorFrame frame;
    private int timeLimit ;
    private int maxProcessingTime ;
    private int minProcessingTime ;
    private int maxArrivingTime ;
    private int minArrivingingTime ;
    private int numberOfServers ;
    private int numberOfClients = 0;
    private int sum=0;
    private int sum1=0;
    private int sum2=0;
    private int total=0;
    private int peakTime;
    private int minsum=0;
    private int id = 1;

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    public void setMaxProcessingTime(int maxProcessingTime){
        this.maxProcessingTime = maxProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public void setMaxArrivingTime(int maxArrivingTime){
        this.maxArrivingTime = maxArrivingTime;
    }

    public void setMinArrivingingTime(int minArrivingingTime) {
        this.minArrivingingTime = minArrivingingTime;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public int getMaxArrivingTime() {
        return maxArrivingTime;
    }

    public int getMinArrivingTime() {
        return minArrivingingTime;
    }


    public int getNumberOfServers() {
        return numberOfServers;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    //entity responsible with queue management and client distribution
    private Scheduler scheduler;
    public static boolean pressed =  false;
    public static boolean first =  false;
    //frame for displaying simulation
    //pool of tasks (client shopping in the store)
    private List<Task> generatedTasks = new ArrayList<Task>() ;
    private int sw = 0;

    class SimulationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                    pressed = true;
                    setTimeLimit(frame.getInterval());//maximum processing time - read from UI
                    System.out.println("Time limit : " + getTimeLimit());
                    setMaxProcessingTime(frame.getMaxService());
                    System.out.println("Max processing time : " + getMaxProcessingTime());
                    setMinProcessingTime(frame.getMinService());
                    System.out.println("Min processing time : " + getMinProcessingTime());
                    setMaxArrivingTime(frame.getMaxArrival());
                    System.out.println("Max arriving time : " + getMaxArrivingTime());
                    setMinArrivingingTime(frame.getMinArrival());
                    System.out.println("Min arriving time : " + getMinArrivingTime());
                    setNumberOfServers(frame.getNrQueues());
                    System.out.println("There are " + getNumberOfServers() + " queues");
                    if(getMinArrivingTime() <= 0){
                        frame.showError("Min arriving time must be at least 1!");
                        return;
                    }

                    if(getMaxArrivingTime() <= 0){
                        frame.showError("Max arriving time must be at least 1!");
                        return;
                    }
                    if(getMinArrivingTime() > getMaxArrivingTime()){
                        frame.showError("Min arriving time must be smaller than max processing time!!");
                        return;
                    }
                    if (getTimeLimit() <= 0) {
                        frame.showError("Time limit must be a positive number!");
                        return;
                    }
                    if (getNumberOfServers() <= 0) {
                        frame.showError("There must be at least 1 queue!");
                        return;
                    }
                    if (getMinProcessingTime() <= 0) {
                        frame.showError("Min processing time must be at least 1!");
                        return;
                    }
                    if (getMaxProcessingTime() <= 0) {
                        frame.showError("Max processing time must be at least 1!");
                        return;
                    }
                    if (getMinProcessingTime() > getMaxProcessingTime()) {
                        frame.showError("Min processing time must be smaller than max processing time!!");
                        return;
                    }
                    sw = 1;
                    //generateNRandomTasks();
                    frame.displayFrame();
            /*frame.setVisible(false);
            frame.dispose();
            JFrame f = new JFrame("Queue simulator");
            JPanel pan = new JPanel();
            f.setSize(800, 500);
            pan.setPreferredSize(new Dimension(800, 500));
            for(int i = 1; i <= numberOfServers; i++){
                JLabel l = new JLabel("Queue" + i);
                //l.setLocation(i*100, 200);;
                pan.add(l);
            }
            //pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));
            f.setContentPane(pan);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
            //Strategy st = scheduler.getStrategy();
            //frame.displayData(tasks);
        }
    }


    public SimulationManager(){
        frame = new SimulatorFrame();
        frame.addListener(new SimulationListener());
        while(pressed == false){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //frame.displayData();
        scheduler = new Scheduler(getNumberOfServers(), 100,frame);
        for(int i = 0; i < getNumberOfServers(); i++){
            t = new Thread(scheduler.getServers().get(i));
            t.start();
        }
        //generateNRandomTasks();
        //  => initialize frame to display simulation
        //generate numberOfClients clients using generateNRandomTasks()
        //and store them to generatedTasks

    }


   /* private void generateNRandomTasks() {
        //generate N random tasks :
        // -random processing time
        generatedTasks = new ArrayList<Task>();
        if(minProcessingTime > 0 &&  maxProcessingTime > 0 && timeLimit > 0) {
            int a = 0;
            while (a < getTimeLimit()) {
                Random rand = new Random();
                int ra = rand.nextInt((maxProcessingTime - minProcessingTime) + 1) + minProcessingTime;
                int arr = rand.nextInt((maxArrivingTime - minArrivingingTime) + 1) + minArrivingingTime;
                a+=arr;
                generatedTasks.add(new Task(arr, a, ra));
                numberOfClients++;
                //System.out.println("Task with arrival " + arr + " and processing " + ra + " is added to generatedTasks");
            }
            Collections.sort(generatedTasks, Task.arrTime);
            for (int i = 0; i < generatedTasks.size(); i++) {
                generatedTasks.get(i).setCustNr(i+1);
            }
        }
        //minProcessingTime < processingTime < maxProcessingTime
        // - random arrivalTime
        //sort list with respect to arrivalTime
    }*/

    //@Override
    public void run() {
        while (pressed == false) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            scheduler.changeStrategy(selectionPolicy);
            int currentTime = 0;
            while (currentTime < getTimeLimit()) {

                /*for(int j = 0; j < numberOfServers; j++){
                    total += scheduler.getServers().get(j).getTaskNr();
                }*/
               /*for (int i = 0; i < generatedTasks.size(); i++) {
                    Task tsk = generatedTasks.get(i);
                    if (tsk.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(tsk);
                        generatedTasks.remove(tsk);
                        i--;
                    }
                    if(total>minsum){
                        System.out.println("TOTAL1 " + total);
                        minsum = total;
                        peakTime = currentTime;
                    }
                    total = 0;

                }*/
                int a = 0;
                while (a < this.getTimeLimit()) {

                    Random rand = new Random();
                    int ra = rand.nextInt((maxProcessingTime - minProcessingTime) + 1) + minProcessingTime;
                    int arr = rand.nextInt((maxArrivingTime - minArrivingingTime) + 1) + minArrivingingTime;
                    a += arr;
                    //Task tsk = new Task(arr, a, ra);
                    //generatedTasks.add(tsk);
                    //System.out.println("Task generated with arrival " + a + " and processing time " + ra);
                    if (a == currentTime) {
                        Task tsk = new Task(arr, a, ra);
                        generatedTasks.add(tsk);
                        tsk.setCustNr(id);
                        scheduler.dispatchTask(tsk);
                        id++;
                        generatedTasks.remove(tsk);
                    }

                    for(int i = 0; i < getNumberOfServers(); i++){
                       total+=scheduler.getServers().get(i).getTasks().length;
                    }
                    if (total > minsum) {
                        //System.out.println("TOTAL1 " + total);
                        minsum = total;
                        peakTime = currentTime;
                    }
                    total = 0;
                }

                currentTime++;
                //System.out.println("current time : " + currentTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (currentTime == timeLimit) {
                frame.addText(frame.area1, "\nSimulation ended!");
                JOptionPane.showMessageDialog(null,"Simulation ended!",null,JOptionPane.INFORMATION_MESSAGE);
                total = 0;
                for(int i = 0; i < getNumberOfServers(); i++){
                    sum1 += scheduler.getServers().get(i).getTotalWaitingTime();
                    sum2 += scheduler.getServers().get(i).getTotalServiceTime();
                    total += scheduler.getServers().get(i).getNr();
                    //System.out.println("TOTAL2 " + total);
                    }
                //System.out.println("TOTAL2 " + total);
                //System.out.println("sum1 " + sum1);
                //System.out.println("sum2 " + sum2);

                int avg1 = sum1/total;
                int avg2 = sum2/total;
                frame.t4.setText(String.valueOf(avg1));
                frame.t5.setText(String.valueOf(avg2));
                frame.t6.setText(String.valueOf(peakTime));
            try{
                Thread.sleep(30000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return;}
                //wait an interval of 1 second
                //first = true;

    }
    public static void main(String[] args){
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
    }
}
