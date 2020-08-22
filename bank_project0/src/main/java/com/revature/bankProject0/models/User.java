package com.revature.bankProject0.models;

import java.util.Objects;

/**
 * This is a skeleton class to define user objects in the system
 */
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String email;
    private Role role;

    /**
     * No args Constructor
     */
    public User() {
        super();
    }

    /**
     * Create a basic user with first and last names, username, and password
     * @param firstName
     * @param lastName
     * @param userName
     * @param passWord
     */
    public User(String firstName, String lastName, String userName, String passWord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
    }
    public User(String firstName, String lastName, String userName, String passWord, String email) {
        this(firstName,lastName,userName,passWord);
        this.email = email;
    }
    /**
     * Create a basic user with id, first and last names, username, and password
     * @param id
     * @param firstName
     * @param lastName
     * @param userName
     * @param passWord
     */
    public User(Integer id, String firstName, String lastName, String userName, String passWord, String email) {
        this(firstName, lastName, userName, passWord, email);
        this.id = id;
    }

    /**
     * Create user with all fields
     * @param id
     * @param firstName
     * @param lastName
     * @param userName
     * @param passWord
     * @param role
     */
    public User(Integer id, String firstName, String lastName, String userName, String passWord,String email, Role role) {
        this(id,firstName, lastName, userName, passWord,email);
        this.role = role;
    }

    /**
     *Copy constructor (used for conveniently copying the fields of one instance into another instance)
     * @param copy
     */
    public User(User copy){
        this(copy.id, copy.firstName, copy.lastName, copy.userName, copy.passWord, copy.email, copy.role);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getPassWord(), user.getPassWord()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getUserName(), getPassWord(), getEmail(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
