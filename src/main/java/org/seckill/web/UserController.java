package org.seckill.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.seckill.entity.User;
import org.seckill.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/showUser")
    public String showUser(HttpServletRequest request,Model model){  
		//Long userId = Long.valueOf(request.getParameter("id"));
        //User user = this.userService.getUserById(userId);
        //model.addAttribute("user", user);
        return "showUser";
    }
	
	@RequestMapping(value = "/showUserAll",
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map showUserAll(){
		List<User> userList = this.userService.getUserAll();
		Map result = new HashMap();
		result.put("total", String.valueOf(userList.size()));
		result.put("rows", userList);
		return result;
	}
	
	@RequestMapping(value = "/goAddUser")
    public String goAddUser(HttpServletRequest request,Model model){  
        return "addUser";
    }
	
	@RequestMapping(value = "/addUser",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public User addUser(HttpServletRequest request){
		String userName = request.getParameter("userName");
		String telphone = request.getParameter("telphone");
		String email = request.getParameter("email");
		User user = new User();
		user.setUserName(userName);
		user.setTelphone(telphone);
		user.setEmail(email);
		int id = userService.insertSelective(user);
		if(id > 0){
			return userService.getUserById(user.getId());
		}else{
			return null;
		}
	}
	
	@RequestMapping(value = "/updateUser",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public User updateUser(HttpServletRequest request){
		Long userId = Long.valueOf(request.getParameter("id"));
		String telphone = request.getParameter("telphone");
		String email = request.getParameter("email");
		User user = new User();
		user.setId(userId);
		user.setTelphone(telphone);
		user.setEmail(email);
		int updateCount = userService.updateByPrimaryKeySelective(user);
		if(updateCount > 0){
			return userService.getUserById(userId);
		}else{
			return null;
		}
	}
	
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public String deleteUser(HttpServletRequest request){
		Long userId = Long.valueOf(request.getParameter("id"));
		int deleteCount = userService.deleteByPrimaryKey(userId);
		if(deleteCount > 0){
			return String.valueOf(true);
		}else{
			return String.valueOf(false);
		}
	}
}
