package nl.hsleiden.iprwc.DAO;

import nl.hsleiden.iprwc.DAO.Repository.ProductRepository;
import nl.hsleiden.iprwc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProduct(Long id){
        return this.productRepository.findById(id)
                .orElseThrow(() -> new Error("Product Not Found"));
    }

    public Product save(Product product){
        return this.productRepository.save(product);
    }
}
