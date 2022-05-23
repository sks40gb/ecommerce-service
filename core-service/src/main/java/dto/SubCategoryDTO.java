package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubCategoryDTO {
    private Long id;
    private String name;
    private Boolean isEnable;
    private CategoryDTO categoryDTO;
}
