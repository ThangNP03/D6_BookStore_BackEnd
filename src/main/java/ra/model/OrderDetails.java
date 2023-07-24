package ra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.user.Users;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private float total;
    @ManyToOne
    private Books booksId;
    private  int quantity;
    private String bookName ;
    @OneToOne
    private Users userId;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;
    private String author;
    private String nxb;
    private String translator;
    private String description;
    private Long numberOfPage;
}
