package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemDTO {
    private Long id;
    private int quantity;
    private double price;
    private OrderDTO order;
    private ProductDTO product;
}
