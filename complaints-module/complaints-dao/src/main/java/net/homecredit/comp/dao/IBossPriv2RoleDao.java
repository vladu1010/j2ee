package net.homecredit.comp.dao;

import net.homecredit.comp.domain.Priv2Role;

import java.util.Collection;
import java.util.List;

public interface IBossPriv2RoleDao extends IAbstractDao<Priv2Role> {

    List<Priv2Role> findPrivileges(Collection<String> roles);

}
