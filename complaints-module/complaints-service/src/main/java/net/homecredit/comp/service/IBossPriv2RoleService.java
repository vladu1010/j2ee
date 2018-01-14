package net.homecredit.comp.service;

import net.homecredit.comp.domain.Priv2Role;

import java.util.Collection;
import java.util.List;

public interface IBossPriv2RoleService {

    List<Priv2Role> getPrivileges(Collection<String> roles);
}
