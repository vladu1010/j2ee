package net.homecredit.comp.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamedQueries({ //
        @NamedQuery(name = Privilege.QUERY_BY_DESCRIPTION, //
                query = "select prv from Privilege prv where prv.code like ( :code)")//
})
@Entity
@Cacheable
@Table(name = "BOSS_PRIVILEGE")
public class Privilege extends AbstractIdEntity {

    private static final long serialVersionUID = 5111088765176895302L;

    public static final String QUERY_BY_DESCRIPTION = "Privilege.findDescription";

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
