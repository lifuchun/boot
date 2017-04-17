package cn.no7player.redis;

import java.util.List;

/**
 * 
* @ClassName: RedisServce 
* @Description: (redis工具类接口) 
* @author LeeFC
* @date 2017年3月15日 下午1:37:00 
*
 */
public interface IRedisServce {
	  
    public boolean set(String key, String value);  
      
    public String get(String key);  
      
    public boolean expire(String key,long expire);  
      
    public <T> boolean setList(String key ,List<T> list);  
      
    public <T> List<T> getList(String key,Class<T> clz);  
      
    public long lpush(String key,Object obj);  
      
    public long rpush(String key,Object obj);  
      
    public String lpop(String key);  
    
}
