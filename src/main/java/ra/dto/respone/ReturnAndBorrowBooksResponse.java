package ra.dto.respone;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnAndBorrowBooksResponse {
    private Long bookId;
    private int quantity;
//    private double price;
}
