package az.edu.bsu.smsproject.repository;

import java.io.File;
import java.util.List;

public interface OrderRepository {
    public int add(String path);
    public File getOrderById(long id);
    public int getNumberOfAllOrders();
    public List<File> getFilteredOrdersList();
    public int getNumberOfFilteredOrders();
}
