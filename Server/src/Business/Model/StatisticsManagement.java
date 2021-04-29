package Business.Model;

import Business.Entity.User;
import Persistance.PeerDAO;
import Persistance.UserDAO;

import java.util.ArrayList;

public class StatisticsManagement {
    private UserDAO userDAO;
    private PeerDAO peerDAO;

    public StatisticsManagement(UserDAO userDAO, PeerDAO peerDAO){
        this.userDAO = userDAO;
        this.peerDAO = peerDAO;
    }

    public String[] getHeaders(){
        String[] headers = {"Position", "Name", "Matches"};
        return headers;
    }

    public String[][] getData(){
        ArrayList<User> users = this.userDAO.top5();

        String [][] data = new String[5][3];
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        data[i][j] = String.valueOf(i+1);
                        break;
                    case 1:
                        data[i][j] = users.get(i).getFirstName();
                        break;
                    case 2:
                        ArrayList<User> peers = this.peerDAO.getUserPeers(users.get(i));
                        data[i][j] = String.valueOf(peers.size());
                        break;
                }
            }
        }
        return data;
    }
}