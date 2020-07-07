package cn.boot.st.redis.rlock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @program: mall
 * @author: maht
 * @create: 2020-07-07 18:33
 **/
public interface DistributedLocker {
    /**
     * 加锁
     *
     * @param lockKey
     * @return
     */
    RLock lock(String lockKey);

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    RLock lock(String lockKey, int timeout);

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    RLock lock(String lockKey, TimeUnit unit, int timeout);

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    /**
     * 释放锁
     * @param lockKey
     */
    void unlock(String lockKey);

    void unlock(RLock lock);

    void setRedissonClient(RedissonClient redissonClient);

}
