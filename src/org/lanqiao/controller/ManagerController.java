package org.lanqiao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.Manager;
import org.lanqiao.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class ManagerController {
	ManagerService managerService;

	public ManagerService getManagerService() {
		return managerService;
	}

	@Autowired
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	// 管理员登录方法
	@RequestMapping("managerLogin")
	public String managerLogin(HttpServletRequest request, HttpSession session) {
		// 获取用户名和密码
		String name = request.getParameter("managerName");
		String pwd = request.getParameter("managerPassword");
		Manager manager = new Manager();
		Manager loginManager = new Manager(name, pwd);
		// 获取Session中的管理员登录信息
		Manager sessionManager = (Manager) session.getAttribute("manager");
		if (sessionManager != null) {
			manager = sessionManager;
		} else {
			manager = managerService.managerLogin(loginManager);
		}
		if (manager != null) {
			session.setAttribute("manager", manager);
			// 获取页面值indexNum，决定管理用户还是类别
			int indexNum = 0;
			String s_num = request.getParameter("indexNum");
			if (s_num != null && !s_num.equals("")) {
				indexNum = Integer.parseInt(s_num);
			}
			if (indexNum == 0) {
				return "redirect:selectAllRegisterUser.do";
			} else if (indexNum == 1) {
				return "redirect:selectAllKind.do";
			} else {
				return "redirect:selectAllInfo.do";
			}
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			return "ManagerLogin";
		}
	}

	// 修改管理员登录密码
	@RequestMapping("updateManagerPwd")
	public String updateManagerPwd(HttpServletRequest request,
			HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 获取旧密码
		String MPassword = request.getParameter("old_pwd");
		// 获取新密码
		String MPassword1 = request.getParameter("new_pwd");
		// 判断旧密码是否一样，否则不匹配
		if (MPassword.equals(manager.getMPassword())) {
			// 判定新密码是否为空，不为空直接修改
			if (MPassword1 != null && !MPassword1.equals("")) {
				manager.setMPassword(MPassword1);
				int flag = managerService.updateManagerPwd(manager);
				if (flag != 0) {
					return "redirect:managerLoginOut.do";
				} else {
					System.out.println("修改失败");
					request.setAttribute("msg", "修改失败");
				}
			} else {
				request.setAttribute("msg", "新密码为空");
			}
		} else {
			request.setAttribute("msg", "原密码输入错误");
		}
		return "ManagerPassword";
	}

	// 管理员的查询操作方法
	@RequestMapping("managerSearch")
	public String managerSearch(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		int indexNum = Integer.parseInt(request.getParameter("indexNum"));
		String searchword = request.getParameter("searchword");
		if (indexNum == 0) {
			return "redirect:searchRegisterUser.do?searchword=" + searchword;
		} else if (indexNum == 1) {
			return "redirect:searchKind.do?searchword=" + searchword;
		} else {
			return "redirect:searchInfo.do?searchword=" + searchword;
		}
	}

	// 退出登录的方法
	@RequestMapping("managerLoginOut")
	public String managerLoginOut(HttpServletRequest request) {
		// 移除Session中的管理员登录信息
		request.getSession().removeAttribute("manager");
		return "ManagerLogin";
	}
}
