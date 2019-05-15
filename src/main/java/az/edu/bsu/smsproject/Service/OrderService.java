package az.edu.bsu.smsproject.Service;

import java.io.File;
import java.util.List;

public interface OrderService {
    public int add(String path);
    public File getOrderById(long id);
    public int getNumberOfAllOrders();
    public List<File> getFilteredOrdersList();
    public int getNumberOfFilteredOrders();
}
