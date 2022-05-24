package dto;

import com.ziletech.ecommerce.entity.Product;
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
public class ProductDTO {
    private Long id;
    private String name;
    private String code;
    private String shortDesc;
    private String longDesc;
    private String unitType;
    private Double unitPrice;
    private Double mrp;
    private Boolean isEnable;
    private SubCategoryDTO subCategory;
    List<ProductDetailDTO> productDetails;

    public void copyFromEntity(Product product) {
        setId(product.getId());
        setName(product.getName());
        setCode(product.getCode());
        setShortDesc(product.getShortDesc());
        setLongDesc(product.getLongDesc());
        setUnitType(product.getUnitType());
        setUnitPrice(product.getUnitPrice());
        setMrp(product.getMrp());
        setIsEnable(product.getIsEnable());
    }

    public void copyToEntity(Product product) {
        if (getId() != null) {
            product.setId(getId());
        }
        product.setName(getName());
        product.setCode(getCode());
        product.setShortDesc(getShortDesc());
        product.setLongDesc(getLongDesc());
        product.setUnitType(getUnitType());
        product.setUnitPrice(getUnitPrice());
        product.setMrp(getMrp());
        product.setIsEnable(getIsEnable());
    }


}
