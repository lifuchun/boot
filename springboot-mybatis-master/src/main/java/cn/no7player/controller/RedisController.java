package cn.no7player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.no7player.redis.IRedisServce;
import cn.no7player.util.JsonUtil;

@Controller
public class RedisController {
	
	
	@Autowired
	private IRedisServce redisServce;
	
    @RequestMapping(value="/redis/set",method = RequestMethod.POST)  
    public String redisSet(@RequestBody String value,Model model){ 
    	JSONObject json = JSONObject.parseObject(value);
        boolean isOk = redisServce.set("test", json.getString("test")); 
        System.out.println(json.getString("test"));
        String back = null;
        if (true == isOk){
        	back = "写入成功";
        }
        model.addAttribute("name",back);
        return "hello";  
    }  
      
    @ResponseBody
    @RequestMapping(value="/redis/get/{name}",method= RequestMethod.GET)  
    public String redisGet(@PathVariable("name") String name){  
        String value = redisServce.get("name");  
        return JsonUtil.toJson(value); 
    }  
}
