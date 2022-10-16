package model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class User {
    private Integer id;
    private String name;
    private String family;
    private Integer age;
    private String nationalCode;
    private LocalDate birthday;

    private DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");


    public User(String name, String family, String nationalId, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalCode = nationalId;
        this.birthday = birthday;
        this.age = LocalDate.now().getYear()-birthday.getYear();
    }







//getters


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }


    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", family='" + family +
                ", age=" + age +
                ", nationalId=" + nationalCode +
                ", birthday=" + birthday+
                "}\n";
    }
}
