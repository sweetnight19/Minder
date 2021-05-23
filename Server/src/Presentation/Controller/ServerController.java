package Presentation.Controller;

import Business.Model.StatisticsManagement;
import Presentation.View.ServerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Server controller.
 */
public class ServerController extends Thread implements ActionListener {
    private final ServerView serverView;
    private final StatisticsManagement statisticsManagement;
    private int panelGraph = 0;


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

    /**
     * Thread that runs every 30 seconds to update the top5 table, in case there are changes into the
     * database.
     */
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ServerView.BUTTON_MONTH:
                System.out.println("Hola Mes");
                this.serverView.monthGraphic(panelGraph);
                panelGraph = 2;
                break;
            case ServerView.BUTTON_WEEK:
                System.out.println("Hola Setmana");
                this.serverView.weekGraphic(panelGraph);
                panelGraph = 1;
                break;
            case ServerView.BUTTON_DAY:
                System.out.println("Hola Dia");
                this.serverView.dayGraphic(panelGraph);
                panelGraph = 0;
                break;
        }
    }

}

