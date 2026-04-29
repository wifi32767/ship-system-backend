package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.system.model.UserRoleVO;
import com.wifi32767.domain.user.adapter.repository.UserRepository;
import com.wifi32767.domain.user.model.UserVO;
import com.wifi32767.infra.dao.UserDao;
import com.wifi32767.infra.dao.po.User;
import com.wifi32767.infra.dao.po.UserRole;
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
        return userDao.insertUser(userVO2User(user));
    }

    @Override
    public int login(String username, String password) throws Exception {
        User user = userDao.queryUserByUsername(username);
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
    public UserVO getUserInfo(String username) {
        User user = userDao.queryUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }
        return user2UserVO(user);
    }

    @Override
    public List<UserVO> getAllUsersInfo(int pageNum, int pageSize) {
        List<User> users = userDao.queryAllUsers((pageNum - 1) * pageSize, pageSize);
        return users.stream().map(user -> user2UserVO(user)).toList();
    }

    @Override
    public void deleteUser(String username) {
        userDao.deleteUserByUsername(username);
    }

    @Override
    public void updateUserInfo(UserVO user) {
        userDao.updateUserInfo(userVO2User(user));
    }

    @Override
    public List<UserRoleVO> getAllUsersRole() {
        return userDao.queryAllUsersRole().stream().map(
                userRole -> UserRoleVO.builder()
                        .roleId(userRole.getRoleId())
                        .roleName(userRole.getRoleName())
                        .modules(getModulesByRoleId(userRole.getRoleId()))
                        .build()
        ).toList();
    }

    @Override
    public void addUserRole(UserRoleVO userRole) {
        // TODO: 写在一个事务
        UserRole role = UserRole.builder()
                .roleName(userRole.getRoleName())
                .build();
        userDao.insertUserRole(role);
        int roleId = role.getRoleId();
        userRole.getModules().forEach(
                module -> userDao.insertPermission(roleId, module)
        );
    }

    @Override
    public void removeUserRole(int userRoleId) {
        userDao.deleteUserRole(userRoleId);
    }

    @Override
    public void addPermission(int userRoleId, int permissionId) {
        userDao.insertPermission(userRoleId, permissionId);
    }

    @Override
    public void addPermissionBatch(int userRoleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            userDao.insertPermission(userRoleId, permissionId);
        }
    }

    @Override
    public void removePermission(int userRoleId, int permissionId) {
        userDao.deletePermission(userRoleId, permissionId);
    }

    @Override
    public void removePermissionBatch(int userRoleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            userDao.deletePermission(userRoleId, permissionId);
        }
    }

    @Override
    public boolean hasPermission(int userRoleId, int permissionId) {
        return userDao.hasPermission(userRoleId, permissionId);
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

    private User userVO2User(UserVO userVO) {
        return User.builder()
                .userId(userVO.getUserId())
                .userRole(userVO.getUserRole().getRoleId())
                .userName(userVO.getUserName())
                .nickName(userVO.getNickName())
                .email(userVO.getEmail())
                .sex(userVO.getSex())
                .avatarAddress(userVO.getAvatarAddress())
                .password(encode(userVO.getPassword()))
                .remark(userVO.getRemark())
                .phoneNumber(userVO.getPhoneNumber())
                .build();
    }

    private UserVO user2UserVO(User user) {
        List<Integer> permissions = userDao.queryPermissionsByRoleId(user.getUserRole());
        UserVO userVO = UserVO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .sex(user.getSex())
                .avatarAddress(user.getAvatarAddress())
                .remark(user.getRemark())
                .phoneNumber(user.getPhoneNumber())
                .build();
        // TODO: 这里可以设置一个缓存机制，保存角色权限
        userVO.setUserRole(UserRoleVO.builder()
                .roleId(user.getUserRole())
                .roleName(userDao.queryRoleNameByRoleId(user.getUserRole()))
                .modules(getModulesByRoleId(user.getUserRole()))
                .build());
        return userVO;
    }

    private List<Integer> getModulesByRoleId(int roleId) {
        return userDao.queryPermissionsByRoleId(roleId);
    }
}
