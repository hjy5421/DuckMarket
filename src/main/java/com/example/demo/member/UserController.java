package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /*
    JSON을 이용한 사용자 등록
     */
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /*
    JSON을 이용한 사용자 수정
     */
    @PostMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
        User origin = userRepository.findById(id);
        origin = user;
        return userRepository.save(origin);
    }

    /*
    PK값을 통한 사용자 조회
     */
    @GetMapping("/user/{id}")
    public User readUser(@PathVariable long id) {
        return userRepository.findById(id);
    }

    /*
    Username, Password를 이용한 로그인
    - 성공시 사용자 정보 반환
    - 실패시 NULL
     */
    @PostMapping("/user/login")
    public User loginUser(@RequestBody User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
