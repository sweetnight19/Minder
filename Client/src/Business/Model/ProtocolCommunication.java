package Business.Model;

/**
 * The type Protocol communication.
 */
public class ProtocolCommunication {
    /**
     * The constant CONNECTION.
     */
// Protocols for requesting connection or disconnection with the server
    public static final String CONNECTION = "CONNECTION";
    /**
     * The constant DISCONNECTION.
     */
    public static final String DISCONNECTION = "DISCONNECTION";

    /**
     * The constant OK.
     */
    /*
     * Protocols for the server to communicate to the client if the process has been
     * approved or there has been some error
     */
    public static final String OK = "OK";
    /**
     * The constant KO.
     */
    public static final String KO = "KO";

    /**
     * The constant LOGIN_USER.
     */
//Protocols for requesting or updating information about the users
    public static final String LOGIN_USER = "LOGIN_USER";
    /**
     * The constant CHECK_LOGIN.
     */
    public static final String CHECK_LOGIN = "CHECK_LOGIN";
    /**
     * The constant CREATE_USER.
     */
    public static final String CREATE_USER = "CREATE_USER";
    /**
     * The constant READ_USER.
     */
    public static final String READ_USER = "READ_USER";
    /**
     * The constant UPDATE_USER.
     */
    public static final String UPDATE_USER = "UPDATE_USER";
    /**
     * The constant DELETE_USER.
     */
    public static final String DELETE_USER = "DELETE_USER";

    /**
     * The constant CREATE_PEER.
     */
//Protocols for requesting or updating information about the likes between users
    public static final String CREATE_PEER = "CREATE_PEER";
    /**
     * The constant READ_PEERS.
     */
    public static final String READ_PEERS = "READ_PEER";
    /**
     * The constant DELETE_PEER.
     */
    public static final String DELETE_PEER = "DELETE_PEER";

    /**
     * The constant LIST_CHAT.
     */
//Protocols for requesting or updating the conversations between users
    public static final String LIST_CHAT = "LIST_CHAT";
    /**
     * The constant CREATE_CHAT_MESSAGE.
     */
    public static final String CREATE_CHAT_MESSAGE = "CREATE_MESSAGE";
    /**
     * The constant READ_CHAT.
     */
    public static final String READ_CHAT = "READ_CHAT";
    /**
     * The constant DELETE_CHAT.
     */
    public static final String DELETE_CHAT = "DELETE_CHAT";

    /**
     * The constant READ_IMAGE.
     */
// Here to add more protocols in the future
    public static final String READ_IMAGE = "READ_IMAGE";
    /**
     * The constant SEND_IMAGE.
     */
    public static final String SEND_IMAGE = "SEND_IMAGE";
    /**
     * The constant STATUS_0.
     */
    public static final String STATUS_0 = "STATUS_0";
    /**
     * The constant STATUS_1.
     */
    public static final String STATUS_1 = "STATUS_1";
    /**
     * The constant COUNT.
     */
    public static final String COUNT = "COUNT";
}
