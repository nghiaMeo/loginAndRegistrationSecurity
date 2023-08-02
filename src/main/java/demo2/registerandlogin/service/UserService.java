package demo2.registerandlogin.service;

import demo2.registerandlogin.dto.UserDTO;
import demo2.registerandlogin.model.User;

public interface UserService {
    User saveUser(UserDTO userDTO);

    User findByUsername(String username);
}
