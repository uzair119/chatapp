package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.User;
import com.example.websocketdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path="/signup",method = RequestMethod.POST)
    @ResponseBody
    public User createUser(@RequestBody User user, HttpServletResponse httpServletResponse) throws IOException {
        if(userService.existsByUsername(user.getUsername()))
            httpServletResponse.sendError(403);
        else
            return userService.saveUser(user);
        return null;
    }

    @RequestMapping(path="/update",method = RequestMethod.PUT)
    @ResponseBody
    public User updateUser(@RequestBody User user, HttpServletResponse httpServletResponse) throws IOException {
        User ret = userService.saveUser(user);
        if(ret == null)
            httpServletResponse.sendError(403);
        return ret;
    }

    @RequestMapping(path="/{username}",method = RequestMethod.GET)
    @ResponseBody
    public User findUser(@PathVariable String username) {
        return userService.findUser(username);
    }

}
