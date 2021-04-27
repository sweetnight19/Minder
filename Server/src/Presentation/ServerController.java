package Presentation;

import Business.Model.StatisticsManagement;

public class ServerController extends Thread{
    private ServerView serverView;
    private StatisticsManagement statisticsManagement;


    public ServerController(ServerView serverView, StatisticsManagement statisticsManagement) {
        this.serverView = serverView;
        this.statisticsManagement = statisticsManagement;
    }

    @Override
    public void run() {
        this.serverView.updateTable(this.statisticsManagement.getData(),
                this.statisticsManagement.getHeaders());
    }
}
