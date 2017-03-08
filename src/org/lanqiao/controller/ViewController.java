package org.lanqiao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.Information;
import org.lanqiao.bean.RegisterUser;
import org.lanqiao.bean.UserView;
import org.lanqiao.service.InformationService;
import org.lanqiao.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	ViewService viewService;

	public ViewService getViewService() {
		return viewService;
	}

	@Autowired
	public void setViewService(ViewService viewService) {
		this.viewService = viewService;
	}

	InformationService informationService;

	public InformationService getInformationService() {
		return informationService;
	}

	@Autowired
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	@RequestMapping("addView")
	public String addView(HttpServletRequest request, HttpSession session) { // 添加评论
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int iid = Integer.parseInt(request.getParameter("IId"));
		String content = request.getParameter("VContent");
		int ruid = registerUser.getRUId();
		Date date = new Date();
		UserView userView = new UserView(ruid, iid, content, date);
		viewService.addView(userView);
		return "redirect:selectView.do?IId=" + iid;
	}

	@RequestMapping("selectView")
	public String selectView(HttpServletRequest request, HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		String iid = request.getParameter("IId");
		// 获取session中的查询关键字
		String Iid = (String) session.getAttribute("Iid");
		if (Iid != null) {
			if (!Iid.equals(iid)) {
				request.getSession().removeAttribute("Iid");
				session.setAttribute("Iid", iid);
				Iid = iid;
			}
			iid = Iid;
		} else {
			session.setAttribute("Iid", iid);
		}
		int IId = Integer.parseInt(iid);
		Information info = new Information(IId);
		Information information = informationService.selectOneInfoById(info);
		request.setAttribute("info", information);
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = viewService.selectViewById(IId).size();
		List<UserView> ilist = new ArrayList<UserView>();
		// 获取全部发布信息
		List<UserView> list = viewService.selectViewById(IId);
		// 准备分页，一页五条记录
		// 当总记录条数小于5条时，直接显示无需分页
		if (list.size() < 5) {
			request.setAttribute("vlist", list);
		} else {
			// 当总记录条数大于5条时，根据当前页拿出应显示的数据显示
			// 考虑记录数与页数的关系，防止下标越界
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("vlist", ilist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("vlist", ilist);
			}
		}
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		return "ViewIndex";
	}

	// 删除评论
	@RequestMapping("deleteView")
	public String deleteView(HttpServletRequest request, HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int iid = Integer.parseInt(request.getParameter("IId"));
		int vid = Integer.parseInt(request.getParameter("VId"));
		viewService.deleteView(vid);
		return "redirect:selectView.do?IId=" + iid;
	}
}
