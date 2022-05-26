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
        setId(category.getId());
        setName(category.getName());
        setIsEnable(category.getIsEnable());
    }
    public void copyToEntity(BaseCategory category){
        if(this.getId()!=null){
            category.setId(getId());
        }
        category.setName(getName());
        category.setIsEnable(getIsEnable());

    }

    @Override
    public String toString() {
        return "BaseCategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isEnable=" + isEnable +
                '}';
    }
}
