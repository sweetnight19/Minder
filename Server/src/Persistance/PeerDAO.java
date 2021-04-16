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
     * Method that creates a new Peer with a given ids.
     *
     * @param id1 The id of user 1 to create the peer.
     * @param id2 The id of user 2 to create the peer.
     */
    void addPeer(int id1, int id2);

    /**
     * Method to remove a Peer form the model by id1 & id2.
     *
     * @param id1 The id of user 1 to delete the peer.
     * @param id2 The id of user 2 to delete the peer.
     */
    void deleteUser(int id1, int id2);
}
