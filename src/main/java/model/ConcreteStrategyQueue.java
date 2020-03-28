package model;

import controller.Server;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    private Task[][] tasks;
    public static boolean generate = false;

    public Task[][] getTasks() {
        return tasks;
    }

    public void addTask(List<Server> servers, Task t){
        //TODO Auto-generated method stub
        int min = Integer.MAX_VALUE;
        int indx = -1;
        for(int i=0; i < servers.size(); i++){
            if(servers.get(i).getTasks().length < min){
                min = servers.get(i).getTasks().length;
                indx = i;
            }
            servers.get(indx).addTask(t);
            //tasks[indx][min] = t;
        }
    }
}
