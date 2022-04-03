package nl.hsleiden.iprwc.DAO;

import nl.hsleiden.iprwc.DAO.Repository.OrderProductRepository;
import nl.hsleiden.iprwc.model.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProductDAO {
    @Autowired
    private OrderProductRepository orderProductRepository;

    public void OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
