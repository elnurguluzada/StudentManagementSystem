package az.edu.bsu.smsproject.Service.implementation;

import az.edu.bsu.smsproject.Service.CommonService;
import az.edu.bsu.smsproject.property.FileStorageProperties;
import az.edu.bsu.smsproject.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Set;

@Service
public class CommonServiceImpl implements CommonService {

    private final CommonRepository commonRepository;

    @Autowired
    public CommonServiceImpl(CommonRepository commonRepository) {
        this.commonRepository = commonRepository;
    }

    @Override
    public Set<String> getFacultySet(int year) {
        return commonRepository.getFacultySet(year);
    }



    @Override
    public Set<String> getProfessionSet(int year, String faculty) {
        return commonRepository.getProfessionSet(year, faculty);
    }

    @Override
    public Set<String> getSectionSet(int year, String faculty, String profession) {
        return commonRepository.getSectionSet(year, faculty, profession);
    }

//    ------------------------------------------------------------------------------------------------------------------

    @Override
    public int addOrder(MultipartFile file) throws FileAlreadyExistsException {
        return commonRepository.addOrder(file);
    }

    @Override
    public Resource getOrderByName(String fileName) {
        return commonRepository.getOrderByName(fileName);
    }

    @Override
    public Resource getOrderById(long id) {
        return commonRepository.getOrderById(id);
    }

    @Override
    public int getNumberOfAllOrders() {
        return commonRepository.getNumberOfAllOrders();
    }

    @Override
    public List<Resource> getFilteredOrdersList(int beginRow, int endRow) {
        return commonRepository.getFilteredOrdersList(beginRow, endRow);
    }

    @Override
    public int getNumberOfFilteredOrders() {
        return commonRepository.getNumberOfFilteredOrders();
    }
}
