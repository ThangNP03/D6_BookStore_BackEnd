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
    private Long numberOfPage;
    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;
    private String translator;
    private Long likes ;
    private Long disLike ;
    private Long quantity;
}
