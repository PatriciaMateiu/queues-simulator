package model;

import controller.Server;
import view.SimulatorFrame;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List <Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private SimulatorFrame frame;


    public Scheduler(int maxNoServers, int maxTasksPerServer, SimulatorFrame frame){
        //for maxNoServers
        //-create server object
        //-create thread with the object
        servers = new ArrayList<Server>();
        this.frame=frame;
        for(int i = 0; i < maxNoServers; i++){
            servers.add(new Server(i + 1,frame));
            //Thread tr = new Thread(servers.get(i));
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        //apply strategy pattern to instantiate the strategy with the concrete
        //strategy corresponding to policy

        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
            //System.out.println("Queue strategy!!");
        }

        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
            //System.out.println("Time strategy!!");
        }


    }

        public Strategy getStrategy(){
            return  strategy;
        }
        public void dispatchTask(Task t){
            strategy.addTask(servers, t);
        }



        public List<Server> getServers(){
            return servers;
        }
}
