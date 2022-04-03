package nl.hsleiden.iprwc.DAO.Repository;

import nl.hsleiden.iprwc.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
