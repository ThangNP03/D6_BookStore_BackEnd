package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.CartItem;

import java.util.List;

public interface ICartItem extends JpaRepository<CartItem, Long > {
    boolean clearCart(int cartId);
    List<CartItem> FindAllByCartId(int cartId);
    CartItem findCartItemById(int cartId, int booId);
}
