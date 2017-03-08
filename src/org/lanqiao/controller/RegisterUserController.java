package org.lanqiao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.Manager;
import org.lanqiao.bean.RegisterUser;
import org.lanqiao.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class RegisterUserController {
	RegisterUserService registerUserService;

	public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}

	@Autowired
	public void setRegisterUserService(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	// 获取全部用户
	@RequestMapping("selectAllRegisterUser")
	public String selectAllRegisterUser(HttpServletRequest request,
			HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 设置页面数，0表示为管理用户页
		request.setAttribute("indexNum", 0);
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = registerUserService.selectAllRegisterUser().size();
		// 获取全部用户
		List<RegisterUser> list = registerUserService.selectAllRegisterUser();
		List<RegisterUser> rlist = new ArrayList<RegisterUser>();
		// 准备分页，一页五条记录
		// 当总记录条数小于5条时，直接显示无需分页
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			// 当总记录条数大于5条时，根据当前页拿出应显示的数据显示
			// 考虑记录数与页数的关系，防止下标越界
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					rlist.add(list.get(i));
				}
				request.setAttribute("List", rlist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					rlist.add(list.get(i));
				}
				request.setAttribute("List", rlist);
			}
		}
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("msg", request.getParameter("msg"));
		return "ManagerIndex";
	}

	// 删除指定用户
	@RequestMapping("deleteRegisterUser")
	public String deleteRegisterUser(HttpServletRequest request,
			HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		int RUId = Integer.parseInt(request.getParameter("RUId"));
		RegisterUser registerUser = new RegisterUser(RUId);
		int flag = registerUserService.deleteRegisterUser(registerUser);
		if (flag != 0) {
			return "redirect:selectAllRegisterUser.do?msg=Delete Successed";
		} else {
			return "redirect:selectAllRegisterUser.do?msg=Delete Failed";
		}
	}

	// 管理员查询指定用户
	@RequestMapping("searchRegisterUser")
	public String searchRegisterUser(HttpServletRequest request,
			HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 获取session中的查询关键字
		String session_search = (String) session.getAttribute("word");
		// 获取传来的查询关键字
		String searchword = request.getParameter("searchword");
		// 正则表达式匹配正整数，判断查询关键字是否为数字
		String regex = "^[0-9]*[1-9][0-9]*$";
		int num = 0;
		if (session_search != null) {
			if (searchword != null && !searchword.equals("")
					&& !session_search.equals(searchword)) {
				session.setAttribute("word", searchword);
				session_search = searchword;
			}
			searchword = session_search;
		} else {
			session.setAttribute("word", searchword);
		}
		// 分页设置
		int totalsize = 0;
		int totalpage = 1;
		int currentPage = 1;
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		// 根据关键字查询
		List<RegisterUser> list = new ArrayList<RegisterUser>();
		List<RegisterUser> rlist = new ArrayList<RegisterUser>();
		RegisterUser registerUser = new RegisterUser();
		if (searchword != null && !searchword.equals("")) {
			if (searchword.matches(regex)) {
				num = Integer.parseInt(searchword);
				registerUser.setRUId(num);
			} else {
				registerUser.setRUName(searchword);
			}
			list = registerUserService.searchRegisterUser(registerUser);
		}
		// 准备分页，一页5条
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					rlist.add(list.get(i));
				}
				request.setAttribute("List", rlist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					rlist.add(list.get(i));
				}
				request.setAttribute("List", rlist);
			}
		}
		totalsize = list.size();
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("indexNum", 0);
		return "ManagerSearchResult";
	}

	// 用户信息修改方法
	@RequestMapping("updatePersonalInformation")
	public String updatePersonalInformation(HttpServletRequest request,
			HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		// 获取用户名和密码
		int rusex = Integer.parseInt(request.getParameter("user_sex"));
		String ruintroduction = request.getParameter("user_introduce");
		registerUser.setRUSex(rusex);
		registerUser.setRUIntroduction(ruintroduction);
		int flag = registerUserService.updatePersonalInformation(registerUser);
		if (flag != 0) {
			request.getSession().removeAttribute("registerUser");
			session.setAttribute("registerUser", registerUser);
			request.setAttribute("msg", "修改成功");
		} else {
			request.setAttribute("msg", "修改失败");
		}
		return "UpdatePersonalInformation";
	}

	@RequestMapping("updatePassword")
	public String updatePassword(HttpServletRequest request, HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		// 获取旧密码
		String old_pwd = request.getParameter("old_pwd");
		// 获取新密码
		String new_pwd = request.getParameter("new_pwd").trim();
		// 判断旧密码是否一样，否则不匹配
		if (registerUser.getRUPassWord().equals(old_pwd)) {
			// 判定新密码是否为空，不为空直接修改
			if (new_pwd != null && !new_pwd.equals("")) {
				if (new_pwd.length() < 6) {
					request.setAttribute("pwd_error", "密码长度不小于6位");
				} else {
					registerUser.setRUPassWord(new_pwd);
					int flag = registerUserService.updatePassword(registerUser);
					if (flag != 0) {
						request.getSession().removeAttribute("registerUser");
						session.setAttribute("registerUser", registerUser);
						request.setAttribute("pwd_error", "修改成功");
					} else {
						request.setAttribute("pwd_error", "修改失败");
					}
				}
			} else {
				request.setAttribute("pwd_error", "新密码不得为空");
			}
		} else {
			request.setAttribute("pwd_error", "原密码输入错误");
		}
		return "RegisterPasswordIndex";
	}

	// 添加注册用户
	@RequestMapping("addRegisterUser")
	@ResponseBody
	public String addRegisterUser(HttpServletRequest request) {
		String RUName = request.getParameter("RUName");
		String RUPassWord = request.getParameter("RUPassWord");
		RegisterUser registerUser = new RegisterUser();
		registerUser.setRUName(RUName);
		registerUser.setRUPassWord(RUPassWord);
		int flag = registerUserService.addRegisterUser(registerUser);
		if (flag != 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 注册：根据用户名提示该用户是否存在
	@RequestMapping("selectRegisterUserByRUName")
	@ResponseBody
	public String selectRegisterUserByRUName(RegisterUser registerUser,
			HttpServletResponse response) {
		int RUId = registerUserService.selectRegisterUserByRUName(registerUser);
		if (RUId == 0) {
			// 用户不存在
			return "1";
		} else {
			// 用户存在
			return "0";
		}
	}

	// 用户登录
	@RequestMapping("loginRegisterUser")
	public String loginRegisterUser(HttpServletRequest request,
			HttpSession session) {
		// 获取用户名和密码
		String rname = request.getParameter("rName");
		String rpassword = request.getParameter("rPassword");
		RegisterUser registerUser = new RegisterUser();
		RegisterUser loginRegister = new RegisterUser(rname, rpassword);
		// 获取Session中的用户登录信息
		RegisterUser sessionRegisterUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (sessionRegisterUser != null) {
			if (rname != null && !sessionRegisterUser.getRUName().equals(rname)) {
				sessionRegisterUser = registerUserService
						.loginRegisterUser(loginRegister);
			}
			registerUser = sessionRegisterUser;
		} else { // 没有session时
			registerUser = registerUserService.loginRegisterUser(loginRegister);
		}

		if (registerUser != null) { // 可以登录时
			session.setAttribute("registerUser", registerUser);
			return "UserIndex";
		} else { // 不可以登录时
			return "LoginRegisterUser";
		}
	}

	// 退出登录的方法
	@RequestMapping("registerLoginOut")
	public String registerLoginOut(HttpServletRequest request) {
		// 移除Session中的用户登录信息
		request.getSession().removeAttribute("registerUser");
		return "LoginRegisterUser";
	}
}
