package ra.dto.respone;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnAndBorrowBooksResponse {

    private Long bookId;
    private int quantity;
}
