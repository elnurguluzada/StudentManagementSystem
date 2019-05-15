package az.edu.bsu.smsproject.repository.implementation;

import az.edu.bsu.smsproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int add(String path) {
        String sql = "INSERT INTO rector_order(id, path) VALUES(nextval('rector_order_sequence'), ?)";

        return jdbcTemplate.update(sql, path);

    }

    @Override
    public File getOrderById(long id) {
        String sql = "SELECT path FROM rector_order WHERE id = ?";
        String path = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString("path")), id).get(0);

        return new File(path);
    }

    @Override
    public int getNumberOfAllOrders() {
        String sql = "SELECT count(*) FROM rector_order";
        return jdbcTemplate.query(sql, (resultSet, i)-> resultSet.getInt(1)).get(0);
    }

    @Override
    public List<File> getFilteredOrdersList() {
        String sql = "SELECT path FROM rector_order ORDER BY id";
        List<String> pathList = jdbcTemplate.query(sql,
                ((resultSet, i) -> resultSet.getString("path")));
        List<File> fileList = new ArrayList<>();
        for (String path: pathList){
            fileList.add( new File(path) );
        }

        return fileList;
    }

    @Override
    public int getNumberOfFilteredOrders() {
        return 0;
    }
}
