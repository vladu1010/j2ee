package ro.stoicaVlad.Dreamcar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auction {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String company;
    private int cantity;
    private int price;

    public Auction () {

    }

    public Auction (String name, int cantity, String company, int price) {
        this.name = name;
        this.cantity = cantity;
        this.company = company;
        this.price = price;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantity() {
        return cantity;
    }

    public void setCantity(int cantity) {
        this.cantity = cantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
