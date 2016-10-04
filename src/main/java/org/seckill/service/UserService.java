package org.seckill.service;

import java.util.List;

import org.seckill.entity.User;

public interface UserService {
	public User getUserById(Long userId);
	
	public List<User> getUserAll();
	
	public int insert(User user);
	
	public int insertSelective(User user);
	
	public int updateByPrimaryKeySelective(User user);
	
	public int updateByPrimaryKey(User user);
	
	public int deleteByPrimaryKey(Long id);
}
