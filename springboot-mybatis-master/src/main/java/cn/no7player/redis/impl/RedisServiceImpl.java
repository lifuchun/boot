package cn.no7player.redis.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import cn.no7player.redis.IRedisServce;
import cn.no7player.util.JsonUtil;

/**
 * 
 * @ClassName: RedisServiceImpl
 * @Description: (Redis工具实现类)
 * @author LeeFC
 * @date 2017年3月15日 下午1:40:43
 *
 */
@Service
public class RedisServiceImpl implements IRedisServce {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;

	/**
	 * redis 存入数据
	 */
	@Override
	public boolean set(String key, String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate
						.getStringSerializer();
				connection.set(serializer.serialize(key),
						serializer.serialize(value));
				return true;
			}

		});
		return result;
	}

	/**
	 * redis获取数据
	 */
	@Override
	public String get(String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate
						.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
		return result;
	}

	/**
	 * redis过期时间设置
	 */
	@Override
	public boolean expire(String key, long expire) {
		return redisTemplate.expire(key, 60, TimeUnit.SECONDS);
	}

	@Override
	public <T> boolean setList(String key, List<T> list) {
		String value = JsonUtil.toJson(list);
		return set(key, value);
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clz) {
		String json = get(key);
		if (json != null) {
			List<T> list = JsonUtil.toList(json, clz);
			return list;
		}
		return null;
	}

	@Override
	public long lpush(String key, Object obj) {
		final String value = JsonUtil.toJson(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate
						.getStringSerializer();
				long count = connection.lPush(serializer.serialize(key),
						serializer.serialize(value));
				return count;
			}
		});
		return result;
	}

	@Override
	public long rpush(String key, Object obj) {
		final String value = JsonUtil.toJson(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate
						.getStringSerializer();
				long count = connection.rPush(serializer.serialize(key),
						serializer.serialize(value));
				return count;
			}
		});
		return result;
	}

	@Override
	public String lpop(String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate
						.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));

				return serializer.deserialize(value);
			}
		});
		return result;
	}

}
