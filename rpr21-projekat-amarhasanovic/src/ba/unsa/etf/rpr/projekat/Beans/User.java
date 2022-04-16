package ba.unsa.etf.rpr.projekat.Beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private int id;
    private SimpleStringProperty firstName, lastName, username, email, password, address, picture;
    private SimpleIntegerProperty phoneNumber;

    public User() {
        this.picture = new SimpleStringProperty("/pictures/avatar-default.png");
    }
    public User(int id, String firstName, String lastName, String username, String email, String password) {
        this.id = id;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty();
        this.picture = new SimpleStringProperty("/pictures/avatar-default.png");
        this.phoneNumber = new SimpleIntegerProperty();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPicture() {
        return picture.get();
    }

    public SimpleStringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
    }

    public int getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleIntegerProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}
