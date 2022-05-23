package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO{
    private Integer id;
    private String name;
    private String code;
    private String shortDesc;
    private String longDesc;
    private String unitType;
    private Double unitPrice;
    private Double mrp;
    private Boolean isEnable;
    List<ProductDetailDTO> productDetails;
}
