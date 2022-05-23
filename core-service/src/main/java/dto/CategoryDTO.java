package dto;

import com.ziletech.ecommerce.entity.Category;
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
public class CategoryDTO extends BaseCategoryDTO {

    private List<SubCategoryDTO> subCategoryList;


    public void copyFromEntity(Category category){
        this.setId(category.getId());
        this.setName(category.getName());
        this.setIsEnable(category.getIsEnable());
    }
    public void copyToEntity(Category category){
        if(this.getId()!=null){
            category.setId(this.getId());
        }
        category.setName(this.getName());
        category.setIsEnable(this.getIsEnable());

    }

}
