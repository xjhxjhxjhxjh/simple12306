package com.xjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.client.BuyClient;
import com.xjh.client.OrderClient;
import com.xjh.entity.User;
import com.xjh.mapper.UserMapper;
import com.xjh.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Pattern;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 9:36
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private BuyClient buyClient;

    @Autowired
    private OrderClient orderClient;

    @Override
    public String register(User user, String code) throws ExecutionException, InterruptedException {
        User queryUser = baseMapper.selectOne(new QueryWrapper<User>().
                eq("uName", user.getUName()).
                or().eq("uEmail", user.getUEmail()).
                or().eq("uMobile", user.getUMobile()).
                or().eq("uIdCard", user.getUIdCard()));
        if (queryUser == null) {
            String salt = UUID.randomUUID().toString().substring(0, 6);
            user.setUSalt(salt);
            user.setUPassword(DigestUtils.md5Hex(user.getUPassword() + salt));
            return save(user) ? "注册成功" : "注册失败";
        }
        return "信息重复";
    }

    @Override
    public User findUserByPwd(String input, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (input.contains("@")) {
            wrapper.eq("uEmail", input);
        } else if (input.length() == 18) {
            wrapper.eq("uIdCard", input);
        } else if (input.length() == 11) {
            wrapper.eq("uMobile", input);
        } else {
            wrapper.eq("uName", input);
        }
        User user = baseMapper.selectOne(wrapper);
        return (user != null && DigestUtils.md5Hex(password + user.getUSalt()).
                equals(user.getUPassword())) ? user : null;
    }

    @Override
    @GlobalTransactional
    public Boolean buyTicket(Integer startId, Integer endId, String date, Long uId) {
        Boolean ifAbsent = redisTemplate.opsForValue().
                setIfAbsent(startId + endId + date + uId, "1");
        if (ifAbsent == null || !ifAbsent) {
            return false;
        }
        Boolean flag = false;
        if (buyClient.buyTicket(startId, endId, date)) {
            flag = orderClient.addOrder(uId, date, startId, endId);
        }else {
            redisTemplate.delete(startId + endId + date + uId);
        }
        return flag;
    }
}
