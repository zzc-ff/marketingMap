package com.example.stringbootbo.controller;


import com.example.stringbootbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/hello")
    public String  hello() {
//                JSONObject jsonArray = JSONObject.fromObject(result);
//
//
//                 // 根据key值取值
//               String username = jsonArray.getString("buildid");
//        Integer id=1;
//        User user = userService.selectById(id);
        System.out.print("111");
        return "user";
    }

}
