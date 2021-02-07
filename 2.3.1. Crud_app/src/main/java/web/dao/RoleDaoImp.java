package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getByName(String s) {
        return (Role)entityManager.createQuery("Select u from Role u where u.name = :s").setParameter("s", s).getResultList().get(0);
    }

    @Override
    public Set<Role> roleSet() {
        Set<Role> roles = new HashSet<>();
        List<Role> list = entityManager.createQuery("SELECT u from Role u").getResultList();
        roles.addAll(list);
        return roles;
    }
}
