package com.zpt.demo.controller;

import com.zpt.demo.model.User;
import com.zpt.demo.service.UserService;
import com.zpt.demo.util.ResponseObjectResult;
import com.zpt.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("User")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("index")
    public String index() {
        return "login";
    }

    @RequestMapping("login")
    public ModelAndView login(User user, ModelAndView mv) {
        int i = userService.login(user);
        if (i == 0) {
            mv.setViewName("login");
        }else {
            mv.setViewName("index");
        }
        return mv;
    }

    //查
    @RequestMapping("getUsers")
    public ModelAndView getUsers(){
        List<User> list = userService.getUsers();
        ModelAndView mav = new ModelAndView("items");
        mav.addObject("list", list);
        mav.setViewName("list");
        return mav;
    }


   /* @RequestMapping("/getUsers")
    public ResponseObjectResult getUsers() {
        try {
            return userService.getUsers();
        }catch (Exception e){
            return new ResponseObjectResult(new ResponseStatus(10001,"操作失败",false));
        }
    }*/

    //增
    @RequestMapping("/addUser")
    public ResponseObjectResult addUser(@RequestBody User user) {
        try {
            return userService.addUser(user);
        } catch (Exception e) {
            return new ResponseObjectResult(new ResponseStatus(10001, "操作失败！", false));
        }
    }
}
