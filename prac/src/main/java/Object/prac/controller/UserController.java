package Object.prac.controller;

import Object.prac.entity.User;
import Object.prac.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/test")
    public void postUser(@RequestBody User user) {
        log.info(String.valueOf(user));
        userRepository.save(user);
    }

    @GetMapping("/")
    public List<User> GetUsers(){
        List<User> all = userRepository.findAll();
        return all;
//        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User GetUser(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public User PostUser(@RequestBody User user){
        log.info(String.valueOf(user));
        return userRepository.save(user);
    }
    @PutMapping("/")
    public User PutUser(@RequestBody User user){
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    @DeleteMapping("/{id}")
    public Long DeleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return id;
    }

}
