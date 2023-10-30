package com.example.test.service.impl;

import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import com.example.test.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.selectByUsernameAndPasswordAndIsAdmin(user.getUsername(), user.getUserpassword(), user.getIsadmin());
    }


    @Override
    public Integer register(String username, String password) {
        User tmp = userMapper.selectByUsername(username);
        if(tmp != null) return 0;  //账号重复

        User user = new User();
        user.setUsername(username);
        user.setUserpassword(password);
        user.setIsadmin((byte)0);
        return userMapper.insertSelective(user);
    }

    @Override
    public void setPassword(Integer id, String password) {
        User user = new User();
        user.setUserid(id);
        user.setUserpassword(password);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer getCount() {
        return userMapper.selectCount();
    }

    @Override
    public List<User> queryUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int getSearchCount(Map<String, Object> params) {
        return userMapper.selectCountBySearch(params);
    }

    @Override
    public List<User> searchUsersByPage(Map<String, Object> params) {
        return userMapper.selectBySearch(params);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer deleteUser(User user) {
        if(user.getUserid() == 1) return 0;
        return userMapper.deleteByPrimaryKey(user.getUserid());
    }

    @Override
    public Integer deleteUsers(List<User> users) {
        int count = 0;
        for(User user : users) {
            count += deleteUser(user);
        }
        return count;
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Map<String, Object>> theDelete(String outputedGeneratedCode){
        List<Map<String, Object>> resultList =  new ArrayList<>();
        if(userMapper.theDelete(outputedGeneratedCode)>=1){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", 3);
            resultList.add(resultMap);
        }
        else {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", 4);
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> theUpdate(String outputedGeneratedCode){
        List<Map<String, Object>> resultList =  new ArrayList<>();
        if(userMapper.theUpdate(outputedGeneratedCode)>=1){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", 5);
            resultList.add(resultMap);
        }
        else {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", 6);
            resultList.add(resultMap);
        }
        return resultList;
    }


    @Override
    public List<Map<String, Object>> theSelect(String outputedGeneratedCode) {
        boolean isCountQuery = outputedGeneratedCode.toLowerCase().contains("count(*)");
        if (isCountQuery) {
            List<Map<String, Object>> resultList = new ArrayList<>();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("count", userMapper.theCount(outputedGeneratedCode));
            resultList.add(resultMap);
            return resultList;
        } else {
            return userMapper.theSelect(outputedGeneratedCode);
        }
    }

    List<User> userList = new ArrayList<>();
    @Override
    public List<Map<String, Object>> theInsert(String outputedGeneratedCode){

        List<Map<String, Object>> resultList =  new ArrayList<>();
        if(userMapper.theInsert(outputedGeneratedCode)>=1){
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", 1);
            resultList.add(resultMap);
        }
        else {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", 2);
            resultList.add(resultMap);
        }
        return resultList;

    }


    @Override
    public List<String> getSheetTableHeaders() {
        return userMapper.queryForList();
    }
}
