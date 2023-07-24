package ra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.user.Users;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String cartName;
    private int  cartQuantity;
    private float total;
    private float price;
    private float priceFee;
    private String image;
    @OneToOne
    private Users userId;
    @ManyToOne
    private Books bookId;
    private boolean status;
}
