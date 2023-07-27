package ra.model;

import lombok.*;
import ra.model.user.Users;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "likesAndDisLike")
public class LikesAndDisLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users users;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Books books;
    private boolean statusLike;

}
