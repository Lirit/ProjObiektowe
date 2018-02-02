package main;

public class Person {
    int id;
    String name;
    String surname;
    int telephone;
    String mail;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }
}
