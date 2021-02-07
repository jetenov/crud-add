package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.RoleDaoImp;
import web.model.Role;

import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleDao roleDao;

    public RoleServiceImp() {
        this.roleDao = new RoleDaoImp();
    }

    @Transactional
    @Override
    public Role getByName(String s) {
        return roleDao.getByName(s);
    }

    @Transactional
    @Override
    public Set<Role> roleSet() {
        return roleDao.roleSet();
    }
}
