package model;

import controller.Server;
import view.SimulatorFrame;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    //@Override
    private Task[][] tas;
    public static boolean generate = false;
    //public int[] c = new int[3];
    public SimulatorFrame frame;
    public static boolean isGenerate() {
        return generate;
    }

    /*public Task[][] getTasks() {
        return tas;
    }*/
    public void addTask(List<Server> servers, Task t){
        tas = new Task[10][100];
        int min = Integer.MAX_VALUE;
        int indx = -1;
        //System.out.println(servers.size());
        for(int i=0; i < servers.size(); i++){
            if(servers.get(i).getWaitingPeriod().intValue() < min){
                min = servers.get(i).getWaitingPeriod().intValue();
                indx = i;
            }
        }
            servers.get(indx).addTask(t);
            t.setFinishTime(t.getArrivalTime() + t.getWaitingTime()+t.getProcessingTime()) ;
            //c[indx] = t.getFinishTime();

    }
}
