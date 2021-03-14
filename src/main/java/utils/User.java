package utils;

public class User {
    private String email, password, country, city, gender, name, surname;
    private int age;

    public User(String email,
                String password,
                String country, String city,
                String gender, String name,
                String surname, int age) {
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public User(String email,
                String password) {
        this.email = email;
        this.password = password;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
