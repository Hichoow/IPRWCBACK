package nl.hsleiden.iprwc.DAO.Repository;

import nl.hsleiden.iprwc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
