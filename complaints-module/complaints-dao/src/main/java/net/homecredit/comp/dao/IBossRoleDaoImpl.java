package net.homecredit.comp.dao;

import net.homecredit.comp.domain.Role;

import java.util.Collection;
import java.util.List;

public interface IBossRoleDaoImpl {

    List<Role> findDept(Collection<String> codes);

    List<Role> findCodes(String department);

    List<Role> findAll();
}
