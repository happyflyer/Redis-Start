package org.example.redis_start;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lifei
 */
public class JedisDemo1 {
    /**
     * 单实例连接
     */
    public void demo1() {
        // 1、设置ip和port
        Jedis jedis = new Jedis("192.168.9.16", 63790);
        // 2、保存数据
        jedis.set("name", "mooc");
        // 3、获取数据
        String value = jedis.get("name");
        System.out.println(value);
        // 4、释放资源
        jedis.close();
    }

    /**
     * 连接池
     */
    public void demo2() {
        // 1、获取连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置对打连接数
        config.setMaxTotal(30);
        // 设置最大空闲连接数
        config.setMinIdle(10);
        // 2、获取连接池
        JedisPool pool = new JedisPool(config, "192.168.9.16", 63790);
        // 3、获取核心对象
        Jedis jedis = null;
        try {
            // 通过连接池获得连接
            jedis = pool.getResource();
            // 设置数据
            jedis.set("name", "zhangsan");
            // 获取数据
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (pool != null) {
                pool.close();
            }
        }
    }
}
