package nl.hsleiden.iprwc.DAO;

import nl.hsleiden.iprwc.DAO.Repository.OrderRepository;
import nl.hsleiden.iprwc.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderDAO {
    @Autowired
    private OrderRepository orderRepository;


    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }


    public Order create(Order order) {
        order.setDateOrderCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }


    public void update(Order order) {
        this.orderRepository.save(order);
    }
}
