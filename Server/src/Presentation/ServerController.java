package Presentation;

import Business.Model.StatisticsManagement;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Server controller.
 */
public class ServerController extends Thread{
    private final ServerView serverView;
    private final StatisticsManagement statisticsManagement;


    /**
     * Instantiates a new Server controller.
     *
     * @param serverView           the server view
     * @param statisticsManagement the statistics management
     */
    public ServerController(ServerView serverView, StatisticsManagement statisticsManagement) {
        this.serverView = serverView;
        this.statisticsManagement = statisticsManagement;
    }

    @Override
    public void run() {
        Timer t = new Timer( );
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    serverView.updateTable(statisticsManagement.getData(),
                            statisticsManagement.getHeaders());
                });
            }
        }, 200,30000);
    }
}
