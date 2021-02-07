package web.dao;

import web.model.Role;

import java.util.Set;

public interface RoleDao {
    Role getByName(String s);
    Set<Role> roleSet();
}
