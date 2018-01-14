package net.homecredit.comp.dao;

import lombok.extern.slf4j.Slf4j;
import net.homecredit.comp.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class BossRoleDaoImpl extends AbstractDao<Role> implements IBossRoleDaoImpl{

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public List<Role> findDept(Collection<String> codes) {
        return em
                .createNamedQuery(Role.QUERY_BY_DEPT, Role.class)
                .setParameter("codes", codes).getResultList();
    }

    @Override
    public List<Role> findCodes(String department) {
        return em
                .createNamedQuery(Role.QUERY_BY_CODE, Role.class)
                .setParameter("department", department).getResultList();
    }

    @Override
    public List<Role> findAll() {
        return em
                .createNamedQuery(Role.QUERY_ALL, Role.class)
                .getResultList();
    }
}
