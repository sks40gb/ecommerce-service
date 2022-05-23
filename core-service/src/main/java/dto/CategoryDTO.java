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
public class CategoryDTO{
    private Long id;
    private String name;
    private Boolean isEnable;
    private List<SubCategoryDTO> subCategoryList;
}
