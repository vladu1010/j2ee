package net.homecredit.comp.service;

import lombok.extern.slf4j.Slf4j;
import net.homecredit.comp.dao.IBossRoleDaoImpl;
import net.homecredit.comp.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BossRoleServiceImpl implements IBossRoleService{

    @Autowired
    private IBossRoleDaoImpl iBossRoleDao;

    @Override
    public List<Role> getDept(Collection<String> codes) {
        return iBossRoleDao.findDept(codes);
    }

    @Override
    public List<Role> getCodes(String dept) {
        return iBossRoleDao.findCodes(dept);
    }

    @Override
    public List<Role> getAll() {
        return iBossRoleDao.findAll();
    }
}
