package org.lanqiao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.RegisterUser;
import org.lanqiao.service.FollowService;
import org.lanqiao.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class FollowController {
	FollowService followService;
	RegisterUserService registerUserService;

	public FollowService getFollowService() {
		return followService;
	}

	@Autowired
	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}

	public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}

	@Autowired
	public void setRegisterUserService(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	// 关注
	@RequestMapping("addFollowUser")
	@ResponseBody
	public String addFollowUser(HttpServletRequest request, HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if(registerUser==null){
			return "LoginRegisterUser";
		}
		int RUId = registerUser.getRUId();
		int EUId = Integer.parseInt(request.getParameter("EUId"));
		int flag = followService.addFollowUser(RUId, EUId);
		String home = request.getParameter("home");
		if (home != null && !home.equals("")) {
			return "redirect:selectMyFans.do";
		}
		if (flag != 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 取关
	@RequestMapping("deleteFollowUser")
	@ResponseBody
	public String deleteFollowUser(HttpServletRequest request, HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if(registerUser==null){
			return "LoginRegisterUser";
		}
		int EUId = Integer.parseInt(request.getParameter("EUId"));
		int flag = followService.deleteFollowUser(EUId);
		String home = request.getParameter("home");
		if (home != null && !home.equals("")) {
			return "redirect:selectFollowUser.do";
		}
		if (flag != 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 获取我关注的人
	@RequestMapping("selectFollowUser")
	public String selectFollowUser(HttpServletRequest request,
			HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if(registerUser==null){
			return "LoginRegisterUser";
		}
		int ruid = registerUser.getRUId();
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = registerUserService.selectAllFollow(ruid).size();
		// 获取全部用户
		List<RegisterUser> list = registerUserService.selectAllFollow(ruid);
		List<RegisterUser> ilist = new ArrayList<RegisterUser>();
		// 准备分页，一页五条记录
		// 当总记录条数小于5条时，直接显示无需分页
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			// 当总记录条数大于5条时，根据当前页拿出应显示的数据显示
			// 考虑记录数与页数的关系，防止下标越界
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("List", ilist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("List", ilist);
			}
		}
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("listPage", 0);
		return "FollowUserList";
	}

	// 获取关注我的人
	@RequestMapping("selectMyFans")
	public String selectMyFans(HttpServletRequest request, HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if(registerUser==null){
			return "LoginRegisterUser";
		}
		int ruid = registerUser.getRUId();
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = registerUserService.selectMyFans(ruid).size();
		// 获取全部用户
		List<RegisterUser> list = registerUserService.selectMyFans(ruid);
		List<RegisterUser> ilist = new ArrayList<RegisterUser>();
		// 准备分页，一页五条记录
		// 当总记录条数小于5条时，直接显示无需分页
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			// 当总记录条数大于5条时，根据当前页拿出应显示的数据显示
			// 考虑记录数与页数的关系，防止下标越界
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("List", ilist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("List", ilist);
			}
		}
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("listPage", 1);
		return "FollowUserList";
	}
}
