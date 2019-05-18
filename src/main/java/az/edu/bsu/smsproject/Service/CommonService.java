package az.edu.bsu.smsproject.Service;

import az.edu.bsu.smsproject.property.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Set;

public interface CommonService {

    public Set<String> getFacultySet(int year);
    public Set<String> getProfessionSet(int year, String faculty);
    public Set<String> getSectionSet(int year, String faculty, String profession);

    public int addOrder(MultipartFile file) throws FileAlreadyExistsException;
    public Resource getOrderByName(String fileName);
    public Resource getOrderById(long id);
    public int getNumberOfAllOrders();
    public List<Resource> getFilteredOrdersList(int beginRow, int endRow);
    public int getNumberOfFilteredOrders();
}
