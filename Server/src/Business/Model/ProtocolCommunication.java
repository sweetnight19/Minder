package Business.Model;

public class ProtocolCommunication {
  // Protocols for requesting connection or disconnection with the server
  public static final String CONNECTION = "CONNECTION";
  public static final String DISCONNECTION = "DISCONNECTION";

  /*
   * Protocols for the server to communicate to the client if the process has been
   * approved or there has been some error
   */
  public static final String OK = "OK";
  public static final String KO = "KO";

  // Server protocol to warn the client all the object in array have been sent
  public static final String LIST_FINISHED = "FINISH";

  // Protocols for requesting or updating information about the users
  public static final String LOGIN_USER = "LOGIN_USER";
  public static final String CREATE_USER = "CREATE_USER";
  public static final String READ_USER = "READ_USER";
  public static final String UPDATE_USER = "UPDATE_USER";
  public static final String DELETE_USER = "DELETE_USER";

  // Protocols for requesting or updating information about the likes between
  // users
  public static final String CREATE_PEER = "CREATE_PEER";
  public static final String READ_PEERS = "READ_PEER";
  public static final String UPDATE_PEER = "UPDATE_PEER";
  public static final String DELETE_PEER = "DELETE_PEER";

  // Protocols for requesting or updating the conversations between users
  public static final String LIST_CHAT = "LIST_CHAT";
  public static final String CREATE_CHAT_MESSAGE = "CREATE_MESSAGE";
  public static final String READ_CHAT = "READ_CHAT";
  public static final String UPDATE_CHAT = "UPDATE_CHAT";
  public static final String DELETE_CHAT = "DELETE_CHAT";

  // Here to add more protocols in the future
}
