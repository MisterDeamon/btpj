package com.jack.security.webapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jack.security.pojo.SecurityUser;
import com.jack.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jack.security.service.SecurityUserService;

@Controller
public class SecurityLoginController {

	private static final String LOGIN = "login";

	private static int loginCount=0;


	private static final Logger logger = Logger.getLogger(SecurityLoginController.class);

	@Autowired
	private SecurityUserService userService;

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(){
		return LOGIN;
	}

	@RequestMapping(value = "/checkName",method=RequestMethod.GET,produces ="application/json;charset=UTF-8" )
	public @ResponseBody String checkName(@RequestParam String userName) throws UnsupportedEncodingException {
		String encode = StringUtils.getEncoding(userName);

		userName = new String(userName.getBytes(encode),"UTF-8");
		SecurityUser securityUser = userService.findByName(userName);
		Map<String,Object> map = new HashMap<String,Object>();
		if(null==securityUser) {
			map.put("success",false);
			map.put("msg","用户名不存在!");
			logger.info(userName+"try to login system failed");
		}else{
			map.put("success",true);
			map.put("headpicPath", securityUser.getHeadPicPath());
		}
		ObjectMapper mapper = new ObjectMapper();
		String result = "";

		try {
			result = mapper.writeValueAsString(map);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}


	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String fail(@RequestParam("userName") String userName,
					   Map<String, Object> map, HttpServletRequest request){

		Map<String,String> maps = parseException(request);
		map.put("msg", maps.get("msg"));
		map.put("mtype", maps.get("type"));
		map.put("userName", userName);
		SecurityUser securityUser = userService.findByName(userName);
		if(securityUser!=null){
			loginCount++;
			if(loginCount>3){
				userService.lockAccount(securityUser.getId());
				loginCount=0;
			}
		}

		return LOGIN;
	}

	private Map<String,String> parseException(HttpServletRequest request) {
		Map<String,String> map=new HashMap<String, String>();
		String error = (String) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String msg = "用户已登陆请点击主页进入！";
		String type="0";
		if (error != null) {
			if ("org.apache.shiro.authc.UnknownAccountException".equals(error)){
				msg = "未知帐号错误！";
				type="1";
			}else if ("org.apache.shiro.authc.IncorrectCredentialsException"
					.equals(error)){
				msg = "密码错误！";
				type="1";
			}else if ("com.ygsoft.security.shiro.IncorrectCaptchaException"
					.equals(error)){
				msg = "验证码错误！";
				type="1";
			}else if ("org.apache.shiro.authc.AuthenticationException"
					.equals(error)){
				msg = "认证失败！";
				type="1";
			}else if ("org.apache.shiro.authc.LockedAccountException"
					.equals(error)){
				msg = "账号被冻结,请联系管理员解锁！";
				type="1";
			}
		}
		map.put("msg","登录失败，" + msg );
		map.put("type", type);
		return map;
	}



}
