package net.homecredit.comp.dao;

import lombok.extern.slf4j.Slf4j;
import net.homecredit.comp.domain.Priv2Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class BossPriv2RoleDaoImpl extends AbstractDao<Priv2Role> implements IBossPriv2RoleDao {

    @Override
    protected Class<Priv2Role> getEntityClass() {
        return Priv2Role.class;
    }

    @Override
    public List<Priv2Role> findPrivileges(Collection<String> roles) {
        return em
                .createNamedQuery(Priv2Role.QUERY_BY_PRIV, Priv2Role.class)
                .setParameter("roles", roles).getResultList();
    }

}
