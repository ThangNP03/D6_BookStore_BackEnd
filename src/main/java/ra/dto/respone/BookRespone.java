package ra.dto.respone;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookRespone {
    private String bookName;
    private String description;
    private float loanPrice;
    private float depositFee;
    private String author;
    private String nxb;
    private Integer numberOfPage;
    private String translator;
    private Integer quantity;
    private String image;
    private boolean status;
}
