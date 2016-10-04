package org.seckill.service.impl;

import java.util.List;

import org.seckill.dao.UserDao;
import org.seckill.entity.User;
import org.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UserDao userDao;  
	
    public User getUserById(Long userId) {  
        return this.userDao.selectByPrimaryKey(userId); 
    }

	public List<User> getUserAll() {
		return this.userDao.selectAll();
	}

	public int insert(User user) {
		return userDao.insert(user);
	}

	public int insertSelective(User user) {
		return userDao.insertSelective(user);
	}

	public int updateByPrimaryKeySelective(User user) {
		return userDao.updateByPrimaryKeySelective(user);
	}

	public int updateByPrimaryKey(User user) {
		return userDao.updateByPrimaryKey(user);
	}

	public int deleteByPrimaryKey(Long id) {
		return userDao.deleteByPrimaryKey(id);
	}
	
}
