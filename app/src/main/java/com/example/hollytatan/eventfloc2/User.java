package com.example.hollytatan.eventfloc2;

import org.jasypt.util.password.*;

/**
 * Created by hollytatan on 28/04/15.
 */
    public class User {

    private int userID;
    private String userEmail;
    private String password;
    private String encryptedPassword;

    //User type 0 = admin , 1 = student, 2 = society
    private int userType;

    public User(String userEmail, String password, int userType) {
        this.userEmail = userEmail;
        this.password = password;
        this.userType = userType;

    }

    public User() {

    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return encryptedPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String encryptPassword() {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        encryptedPassword = passwordEncryptor.encryptPassword(password);
        return encryptedPassword;
    }

    public boolean checkPassword(String userEmail, String submittedPassword) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = DatabaseQueries.getUser(userEmail).getPassword();
        return passwordEncryptor.checkPassword(submittedPassword, encryptedPassword);
    }

    public void storeUserPassword(String username, String hashedPassword) {

    }
}
