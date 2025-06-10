package pl.edu.pja.s31719.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.s31719.dto.requests.UserRequestDTO;
import pl.edu.pja.s31719.dto.responses.UserResponseDTO;
import pl.edu.pja.s31719.services.UserService;
import pl.edu.pja.s31719.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .header("reason", e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Long userId) {
        try {
            UserResponseDTO userResponseDTO = userService.getById(userId);
            return ResponseEntity.ok(userResponseDTO);
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .header("reason", e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    @GetMapping("username/{userUsername}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("userUsername") String userUsername) {
        try {
            UserResponseDTO userResponseDTO = userService.getByUsername(userUsername);
            return ResponseEntity.ok(userResponseDTO);
        } catch (NotFoundException e) {
            return ResponseEntity
                    .notFound()
                    .header("reason", e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        try {
            Long id = userService.addUser(userRequestDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
