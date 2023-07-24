package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.OrderDetails;

public interface IOrderDetails extends JpaRepository<OrderDetails, Long > {
    Long createNewOrder(OrderDetails o);

}
