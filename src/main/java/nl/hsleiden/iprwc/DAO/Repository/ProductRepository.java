package nl.hsleiden.iprwc.DAO.Repository;

import nl.hsleiden.iprwc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
