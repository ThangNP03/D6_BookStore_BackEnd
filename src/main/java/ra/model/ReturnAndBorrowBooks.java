package ra.model;

import lombok.*;
import ra.model.user.Users;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnAndBorrowBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Books bookId;
    @ManyToOne
    private Users userId;
    private Date borrowAt;
    private Date returnAt;
    private String status;
    private String reason;
    private int quantity;
    private double price;
}
