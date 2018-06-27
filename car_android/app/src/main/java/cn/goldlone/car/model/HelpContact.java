package cn.goldlone.car.model;

/**
 * @author : Created by CN on 2018/6/27 11:10
 */
public class HelpContact {

    private String name;
    private String phone;

    public HelpContact() {
    }

    public HelpContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        HelpContact helpContact = (HelpContact)obj;
        if(this.phone.equals(helpContact.phone))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "HelpContact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
