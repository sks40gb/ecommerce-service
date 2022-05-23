package dto;

import com.ziletech.ecommerce.entity.BaseCategory;
import com.ziletech.ecommerce.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseCategoryDTO {
    private Long id;
    private String name;
    private Boolean isEnable;

    public void copyFromEntity(BaseCategory category){
        this.setId(category.getId());
        this.setName(category.getName());
        this.setIsEnable(category.getIsEnable());
    }
    public void copyToEntity(BaseCategory category){
        if(this.getId()!=null){
            category.setId(this.getId());
        }
        category.setName(this.getName());
        category.setIsEnable(this.getIsEnable());

    }
}
