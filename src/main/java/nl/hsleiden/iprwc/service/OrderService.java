package nl.hsleiden.iprwc.service;

import lombok.RequiredArgsConstructor;
import nl.hsleiden.iprwc.DAO.OrderDAO;
import nl.hsleiden.iprwc.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public Iterable<Order> getAllOrders() {
        return this.orderDAO.getAllOrders();
    }


    public Order create(Order order) {
        return this.orderDAO.create(order);
    }


    public void update(Order order) {
        this.orderDAO.update(order);
    }
}
