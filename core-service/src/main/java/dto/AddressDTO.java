package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String apartment;
    private String street;
    private String city;
    private String state;
    private Long pinCode;
    private UserDTO user;
}
