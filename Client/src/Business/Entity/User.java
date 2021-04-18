package Business.Entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String nickname;
    private int age;
    private String type;
    private String email;
    private String password;
    private String pathImage;
    private String description;
    private String programmingLanguage;

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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
