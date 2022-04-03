package nl.hsleiden.iprwc.controller;

import nl.hsleiden.iprwc.DAO.OrderDAO;
import nl.hsleiden.iprwc.DAO.OrderProductDAO;
import nl.hsleiden.iprwc.DAO.ProductDAO;
import nl.hsleiden.iprwc.dto.OrderProductDTO;
import nl.hsleiden.iprwc.model.Order;
import nl.hsleiden.iprwc.model.OrderProduct;
import nl.hsleiden.iprwc.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private ProductService productService;
    private OrderDAO orderService;
    private OrderProductDAO orderProductDAO;

    public OrderController(ProductService productService, OrderDAO orderDAO, OrderProductDAO orderProductDAO) {
        this.productService = productService;
        this.orderService = orderDAO;
        this.orderProductDAO = orderProductDAO;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }


    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDTO> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus("PAID");
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDTO dto : formDtos) {
            orderProducts.add(orderProductDAO.create(new OrderProduct(order, productService.getProduct(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDTO> orderProducts) {

        List<OrderProductDTO> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new Error("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDTO> productOrders;

        public List<OrderProductDTO> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDTO> productOrders) {
            this.productOrders = productOrders;
        }
    }
}
