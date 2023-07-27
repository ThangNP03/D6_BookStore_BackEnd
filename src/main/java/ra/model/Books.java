package ra.model;
import lombok.*;


import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String bookName;
    private String description;
    private float loanPrice;
    private float depositFee;
    private String author;
    private String nxb;
    private int numberOfPage;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;
    private String translator;
    private int likes;
    private int quantity;
    private String image;
}
