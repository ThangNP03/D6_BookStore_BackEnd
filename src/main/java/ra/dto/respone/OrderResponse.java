package ra.dto.respone;

import com.sun.tools.javac.util.List;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Set<Long> listId;
    private String status;
}
