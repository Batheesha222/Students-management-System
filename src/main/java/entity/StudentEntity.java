package entity;

import javax.persistence.*;

//entity clz eke tyenne db yna ewa witri
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// auto increment id
    private int id;
    private String name;
    private String address;
    private String contact;


    public StudentEntity() {
    }

    public StudentEntity(int id, String name, String address, String contact ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}

