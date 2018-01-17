package ro.stoicaVlad.Dreamcar.domain;

import javax.persistence.*;

@NamedQueries({ //
        @NamedQuery(name = Offer.QUERY_BY_ITEM, //
                query = "select o from Offer o where o.item in ( :item) and o.activeFlag like 1"),
        @NamedQuery(name = Offer.DELETE, //
                query = "delete from Offer o where o.item in ( :item)"),
        @NamedQuery(name = Offer.QUERY_BY_FLAG, //
                query = "select o from Offer o where o.activeFlag like 1")
})
@Entity
@Table(name="offers")
public class Offer {

    public static final String QUERY_BY_ITEM = "Offer.findItem";
    public static final String DELETE = "Offer.deleteItem";
    public static final String QUERY_BY_FLAG = "Offer.findByFlag";

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "company_name", nullable = false, length = 45)
    private String name;
    @Column(name = "item", nullable = false, length = 60)
    private String item;
    @Column(name = "price")
    private int price;
    @Column(name = "cantity")
    private int cantity;
    @Column (name = "active_flag")
    private Integer activeFlag;

    public Offer () {}

    public Offer (String company, String item, int price, int cantity, int activeFlag) {
        this.name = company;
        this.item = item;
        this.price = price;
        this.cantity = cantity;
        this.activeFlag = activeFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCantity() { return cantity; }

    public void setCantity(int cantity) {
        this.cantity = cantity;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }
}
