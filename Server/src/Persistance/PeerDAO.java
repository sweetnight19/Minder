package Persistance;

import java.util.ArrayList;

import Business.Entity.Peer;
import Business.Entity.User;

/**
 * The interface Peer dao.
 */
public interface PeerDAO {
    /**
     * Method to read all peers.
     *
     * @return A list of peers.
     */
    ArrayList<Peer> getAllPeers();

    /**
     * Method to read all peers from user.
     *
     * @param user The user who want their peers.
     * @return A list of peers.
     */
    ArrayList<User> getUserPeers(User user);

    /**
     * Method to add a like on Peer form the model by Peer.
     *
     * @param peer The object Peer to add like.
     * @return the boolean
     */
    boolean addLike(Peer peer);

    /**
     * Method to remove a Peer form the model by id1 & id2.
     *
     * @param peer The object Peer to delete like.
     * @return the boolean
     */
    boolean deletePeer(Peer peer);

    /*
     * Method to change a Peer form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to change the status peer.
     * 
     * @param id2 The id of user 2 to change the status peer.
     * 
     * void tooglePeer(int id1, int id2);
     */

}
