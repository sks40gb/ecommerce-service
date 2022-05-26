package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubCategoryDTO extends BaseCategoryDTO{

    private CategoryDTO category;
    private List<ProductDTO> products;



}
