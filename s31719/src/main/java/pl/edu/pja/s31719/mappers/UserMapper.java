package pl.edu.pja.s31719.mappers;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719.dto.requests.UserRequestDTO;
import pl.edu.pja.s31719.dto.responses.UserResponseDTO;
import pl.edu.pja.s31719.model.User;

@Service
public class UserMapper {

    public User mapFromDTO(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());
        return user;
    }

    public UserResponseDTO mapToDTO(User user){
        UserResponseDTO u = new UserResponseDTO();
        u.setId(user.getId());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        return u;
    }
}
