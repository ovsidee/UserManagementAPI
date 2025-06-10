package pl.edu.pja.s31719.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.pja.s31719.dto.requests.UserRequestDTO;
import pl.edu.pja.s31719.dto.responses.UserResponseDTO;
import pl.edu.pja.s31719.exceptions.NotFoundException;
import pl.edu.pja.s31719.mappers.UserMapper;
import pl.edu.pja.s31719.model.User;
import pl.edu.pja.s31719.repositories.UserRepositorySpringData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;


@Service
public class UserService {
    public UserRepositorySpringData userRepository;
    public UserMapper userMapper;

    public UserService(UserRepositorySpringData userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> findAll() {
        List<User> usersFromDatabase = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .toList();

        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();

        for (User user : usersFromDatabase) {
            userResponseDTOS.add(userMapper.mapToDTO(user));
        }

        return userResponseDTOS;
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
        return userMapper.mapToDTO(user);
    }

    public UserResponseDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username: " + username + " not found"));
        return userMapper.mapToDTO(user);
    }

    @Transactional
    public Long addUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.mapFromDTO(userRequestDTO);
        User saved = userRepository.save(user);
        return saved.getId();
    }

}