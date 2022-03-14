package nl.hsleiden.iprwc.DAO.Repository;

import nl.hsleiden.iprwc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
