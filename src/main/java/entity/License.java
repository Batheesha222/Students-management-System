package entity;

import javax.persistence.*;

@Entity
public class License {
    @Id
    @Column(length = 8)
    private String number;
    @Column(length = 4)
    private String bloodGroup;

    @OneToOne(optional = false)
    @JoinColumn(name = "nic_number")
    private NationalIdentityCard nic;


    public License() {
    }

    public License(String number, String bloodGroup, NationalIdentityCard nic) {
        this.number = number;
        this.bloodGroup = bloodGroup;
        this.nic = nic;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public NationalIdentityCard getNic() {
        return nic;
    }

    public void setNic(NationalIdentityCard nic) {
        this.nic = nic;
    }
}
