package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String password;
    private List<AddressDTO> addresses;
    private List<RoleDTO> roles;


}
