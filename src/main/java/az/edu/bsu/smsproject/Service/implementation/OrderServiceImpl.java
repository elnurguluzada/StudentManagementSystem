package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.OrderService;
import az.edu.bsu.smsproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public int add(String path) {
        return orderRepository.add(path);
    }

    @Override
    public File getOrderById(long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public int getNumberOfAllOrders() {
        return orderRepository.getNumberOfAllOrders();
    }

    @Override
    public List<File> getFilteredOrdersList() {
        return orderRepository.getFilteredOrdersList();
    }

    @Override
    public int getNumberOfFilteredOrders() {
        return orderRepository.getNumberOfFilteredOrders();
    }
}
