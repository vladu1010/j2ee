package net.homecredit.comp.service;

import lombok.extern.slf4j.Slf4j;
import net.homecredit.comp.dao.IBossPriv2RoleDao;
import net.homecredit.comp.domain.Priv2Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BossPriv2RoleServiceImpl implements IBossPriv2RoleService {

    @Autowired
    private IBossPriv2RoleDao iBossPriv2RoleDao;

    @Override
    public List<Priv2Role> getPrivileges(Collection<String> roles) {
        return iBossPriv2RoleDao.findPrivileges(roles);
    }

}

