package net.homecredit.comp.service;

import net.homecredit.comp.domain.Role;

import java.util.Collection;
import java.util.List;

public interface IBossRoleService {

    List<Role> getDept(Collection<String> codes);

    List<Role> getCodes(String dept);

    List<Role> getAll();
}
