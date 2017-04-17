package cn.no7player.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.no7player.model.User;
import cn.no7player.service.UserService;

import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Created by zl on 2015/8/27.
 */
@RestController
@RequestMapping(value="/users")
public class SwaggerController {


    /*
     *  http://localhost:8080/swagger/index.html
     */

	@Autowired
    private UserService userService;
	
    /**
     *
     * @return
     */
    @ApiOperation(value="Get all users",notes="requires noting")
    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(){
        List<User> list=new ArrayList<User>();

//        User user=new User();
//        user.setName("hello");
//        list.add(user);
//
//        User user2=new User();
//        user.setName("world");
//        list.add(user2);
        User user = userService.getUserInfo();
        list.add(user);
        return list;
    }

    @ApiOperation(value="Get user with id",notes="requires the id of user")
    @RequestMapping(value="/{name}",method=RequestMethod.GET)
    public User getUserById(@PathVariable String name){
    	User user = userService.getUserInfoByUser(name);
//        User user=new User();
//        user.setName("hello world");
        return user;
    }
}
