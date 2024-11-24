package model;

public class UserModel {
    private int avatarId;
    private String firstName;
    private String lastName;
    private long age;
    private String country;
    private String city;

    public UserModel(int avatarId, String firstName, String lastName, long age, String country, String city) {
        this.avatarId = avatarId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}