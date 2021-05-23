package Business.Entity;

import java.io.Serializable;

/**
 * The type User.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private final int id;
    private final String firstName;
    private final String nickname;
    private final int age;
    private final String type;
    private final String email;
    private final String password;
    private String pathImage;
    private String description;
    private String programmingLanguage;
    private int status;

    /**
     * Instantiates a new User.
     *
     * @param id                  the id
     * @param firstName           the first name
     * @param nickname            the nickname
     * @param age                 the age
     * @param type                the type
     * @param email               the email
     * @param password            the password
     * @param pathImage           the path image
     * @param description         the description
     * @param programmingLanguage the programming language
     */
    public User(int id, String firstName, String nickname, int age, String type, String email, String password,
                String pathImage, String description, String programmingLanguage) {
        this.id = id;
        this.firstName = firstName;
        this.nickname = nickname;
        this.age = age;
        this.type = type;
        this.email = email;
        this.password = password;
        this.pathImage = pathImage;
        this.description = description;
        this.programmingLanguage = programmingLanguage;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets path image.
     *
     * @return the path image
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * Sets path image.
     *
     * @param pathImage the path image
     */
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets programming language.
     *
     * @return the programming language
     */
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    /**
     * Sets programming language.
     *
     * @param programmingLanguage the programming language
     */
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }
}
