package net.homecredit.comp.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamedQueries({ //
        @NamedQuery(name = Priv2Role.QUERY_BY_PRIV, //
                query = "select p2r from Priv2Role p2r where p2r.roleCode in ( :roles)")//
})
@Entity
@Cacheable
@Table(name = "BOSS_PRIV2ROLE")
public class Priv2Role extends AbstractIdEntity {

    private static final long serialVersionUID = 5111088765176145302L;

    public static final String QUERY_BY_PRIV = "Priv2Role.findPrivileges";

    @Column(name = "PRIV_CODE")
    private String privCode;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    public String getPrivCode() {
        return privCode;
    }

    public void setPrivCode(String privCode) {
        this.privCode = privCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
