package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.RoleRepository;
import web.dao.UserRepository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public User getById(Long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    public List<User> UserList() {
        return userRepository.findAll();
    }

    public User add(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role: user.getRoles()){
            roles.add(roleRepository.getByName(role.getName()));
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User update(User user, Long id) {
        User userToBeUpdated = getById(id);
        Set<Role> roles = new HashSet<>();
        for (Role role: user.getRoles()){
            roles.add(roleRepository.getByName(role.getName()));
        }
        userToBeUpdated.setRoles(roles);
        return userRepository.save(userToBeUpdated);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Role getRoleByName(String s) {
        return roleRepository.getByName(s);
    }

    public Set<Role> roleSet() {
        Set<Role> roles = new HashSet<>();
        List<Role> list = roleRepository.findAll();
        roles.addAll(list);
        return roles;
    }

}
