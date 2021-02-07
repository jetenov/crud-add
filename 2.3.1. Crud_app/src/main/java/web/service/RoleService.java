package web.service;

import web.model.Role;

import java.util.Set;

public interface RoleService {
    Role getByName(String s);
    Set<Role> roleSet();
}
