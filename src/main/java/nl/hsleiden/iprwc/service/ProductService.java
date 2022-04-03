package nl.hsleiden.iprwc.service;

import lombok.RequiredArgsConstructor;
import nl.hsleiden.iprwc.DAO.ProductDAO;
import nl.hsleiden.iprwc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public Iterable<Product> getAllProducts(){
        return this.productDAO.getAllProducts();
    }

    public Product getProduct(Long id){
        return this.productDAO.getProduct(id);
    }

    public Product save(Product product){
        return this.productDAO.save(product);
    }
}
