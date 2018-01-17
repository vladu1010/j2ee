package ro.stoicaVlad.Dreamcar.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({ //
        @NamedQuery(name = Auction.QUERY_BY_NAME, //
                query = "select o from Auction o where o.name in ( :name)"),
        @NamedQuery(name = Auction.QUERY_BY_ALL, //
                query = "select act from Auction act")
})
public class Auction {

    public static final String QUERY_BY_NAME = "Auction.findName";
    public static final String QUERY_BY_ALL = "Auction.findAll";

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cantity")
    private int cantity;
    @Column(name = "minimPrice")
    private int minimPrice;
    @Column(name = "publishDate")
    private String publishDate;
    @Column(name = "expirationTime")
    private int expirationTime;
    @Column(name = "expirationDate")
    private String expirationDate;

    public Auction() {

    }

    public Auction(String name, int cantity, int minimPrice, int expirationTime) {
        this.name = name;
        this.cantity = cantity;
        this.minimPrice = minimPrice;
        this.expirationTime = expirationTime;
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

    public int getMinimPrice() {
        return minimPrice;
    }

    public void setMinimPrice(int minimPrice) {
        this.minimPrice = minimPrice;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }


    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
