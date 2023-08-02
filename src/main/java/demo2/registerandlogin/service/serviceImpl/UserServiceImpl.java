package demo2.registerandlogin.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import demo2.registerandlogin.dto.UserDTO;
import demo2.registerandlogin.model.User;
import demo2.registerandlogin.repository.UserRepository;
import demo2.registerandlogin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getFullname());
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        if (userRepository != null) {
            return userRepository.findByUsername(username);
        }
        return null;
    }

}
