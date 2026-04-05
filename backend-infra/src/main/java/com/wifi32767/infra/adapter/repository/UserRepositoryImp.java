package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.user.adapter.repository.UserRepository;
import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;
import com.wifi32767.infra.dao.UserDao;
import com.wifi32767.infra.dao.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImp implements UserRepository {
    /**
     * BCrypt 强度系数，默认10，范围4-31
     * 值越大计算越慢，安全性越高
     */
    private static final int STRENGTH = 10;
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder(STRENGTH);
    @Resource
    private UserDao userDao;

    @Override
    public int register(UserVO user) throws Exception {
        User userPO = new User();
        userPO.setUserRole(user.getUserRole());
        userPO.setUserName(user.getUserName());
        userPO.setPassword(encode(user.getPassword()));
        userPO.setNickName(user.getNickName());
        userPO.setEmail(user.getEmail());
        userPO.setSex(user.getSex());
        userPO.setAvatarAddress(user.getAvatarAddress());
        userPO.setRemark(user.getRemark());
        userPO.setPhoneNumber(user.getPhoneNumber());
        return userDao.insert(userPO);
    }

    @Override
    public int login(String username, String password) throws Exception {
        User user = userDao.queryByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        if (matches(password, user.getPassword())) {
            return user.getUserId();
        } else {
            throw new IllegalArgumentException("Invalid password for user: " + username);
        }
    }

    @Override
    public void delete(String username) {
        userDao.deleteByUsername(username);
    }

    @Override
    public UserVO getUserInfo(String username) {
        User user = userDao.queryByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setUserName(user.getUserName());
        userVO.setNickName(user.getNickName());
        userVO.setEmail(user.getEmail());
        userVO.setSex(user.getSex());
        userVO.setAvatarAddress(user.getAvatarAddress());
        userVO.setRemark(user.getRemark());
        userVO.setPhoneNumber(user.getPhoneNumber());
        return userVO;
    }

    @Override
    public SimpleUserVO getSimpleUserInfo(String username) {
        User user = userDao.queryByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        SimpleUserVO userVO = new SimpleUserVO();
        userVO.setUserId(user.getUserId());
        userVO.setUserRole(user.getUserRole());
        userVO.setUserName(user.getUserName());
        userVO.setNickName(user.getNickName());
        return userVO;
    }

    @Override
    public List<SimpleUserVO> getAllUsersInfo() {
        List<User> users = userDao.queryAll();
        return users.stream().map(user -> {
            SimpleUserVO userVO = new SimpleUserVO();
            userVO.setUserId(user.getUserId());
            userVO.setUserRole(user.getUserRole());
            userVO.setUserName(user.getUserName());
            userVO.setNickName(user.getNickName());
            return userVO;
        }).toList();
    }

    private String encode(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return ENCODER.encode(password);
    }

    private boolean matches(String rawPassword, String encodedPassword) {
        if (StringUtils.isEmpty(rawPassword) && StringUtils.isEmpty(encodedPassword)) {
            return true;
        } else if (StringUtils.isEmpty(encodedPassword) || StringUtils.isEmpty(encodedPassword)) {
            return false;
        }
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
