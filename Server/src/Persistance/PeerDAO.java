package Persistance;

import java.util.ArrayList;

import Business.Entity.Peer;

public interface PeerDAO {
    /**
     * Method to read all peers.
     *
     * @return A list of peers.
     */
    ArrayList<Peer> getAllPeers();

    /**
     * Method to add a like on Peer form the model by its id1 to id2.
     *
     * @param id1 The id of user 1 who make the like.
     * @param id2 The id of user 2 who receive the like.
     */
    void addLike(int id1, int id2);

    /**
     * Method to remove a Peer form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to delete the peer.
     * @param id2 The id of user 2 to delete the peer.
     */
    void deletePeer(int id1, int id2);

    /**
     * Method to change a Peer form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to change the status peer.
     * @param id2 The id of user 2 to change the status peer.
     */
    // void tooglePeer(int id1, int id2);

}
