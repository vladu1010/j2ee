package net.homecredit.comp.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

@NamedQueries({ //
        @NamedQuery(name = Role.QUERY_BY_DEPT, //
                query = "select rol from Role rol where rol.code in ( :codes)"),
        @NamedQuery(name = Role.QUERY_BY_CODE, //
                query = "select rol from Role rol where rol.department in ( :department)"),
        @NamedQuery(name = Role.QUERY_ALL, //
                query = "select rol from Role rol ")
})
@Entity
@Cacheable
@Table(name = "BOSS_ROLE")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 5111088765176145122L;

    public static final String QUERY_BY_DEPT = "Role.findDepartment";
    public static final String QUERY_BY_CODE = "Role.findCode";
    public static final String QUERY_ALL = "Role.findAll";

    @Id
    @GeneratedValue
    private int id;

    @Column (name = "CODE")
    private String code;

    @Column(name = "DEPARTMENT_ID")
    private String department;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
