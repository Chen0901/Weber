package org.lanqiao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.Kind;
import org.lanqiao.bean.Manager;
import org.lanqiao.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class KindController {
	KindService kindService;

	public KindService getKindService() {
		return kindService;
	}

	@Autowired
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}

	// 获取所有类别信息
	@RequestMapping("selectAllKind")
	public String selectAllKind(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 设置页面数，1表示为管理类别页
		request.setAttribute("indexNum", 1);
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = kindService.selectAllKind().size();
		// 获取全部用户
		List<Kind> list = kindService.selectAllKind();
		List<Kind> klist = new ArrayList<Kind>();
		// 准备分页，一页五条记录
		// 当总记录条数小于5条时，直接显示无需分页
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			// 当总记录条数大于5条时，根据当前页拿出应显示的数据显示
			// 考虑记录数与页数的关系，防止下标越界
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					klist.add(list.get(i));
				}
				request.setAttribute("List", klist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					klist.add(list.get(i));
				}
				request.setAttribute("List", klist);
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

	// 增加类别
	@RequestMapping("addKind")
	public String addKind(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		String kname = request.getParameter("kname");
		if (kname != null && !kname.equals("")) {
			Kind kind = new Kind(kname);
			int flag = kindService.addKind(kind);
			if (flag != 0) {
				request.setAttribute("msg", "添加成功");
			} else {
				request.setAttribute("msg", "添加失败");
			}
		} else {
			request.setAttribute("msg", "存在输入为空");
		}
		return "AddKind";
	}

	// 删除指定类别
	@RequestMapping("deleteKind")
	public String deleteKind(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		int kid = Integer.parseInt(request.getParameter("KId"));
		Kind kind = new Kind(kid);
		int flag = kindService.deleteKind(kind);
		if (flag != 0) {
			return "redirect:selectAllKind.do?msg=Delete Successed";
		} else {
			return "redirect:selectAllKind.do?msg=Delete Failed";
		}
	}

	// 修改指定类别
	@SuppressWarnings("null")
	@RequestMapping("updateKind")
	public String updateKind(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		int kid = Integer.parseInt(request.getParameter("kid"));
		String kname = request.getParameter("kname");
		if (kname == null && kname.equals("")) {
			request.setAttribute("msg", "存在输入为空");
			return "UpdateKind";
		}
		Kind kind = new Kind(kid, kname);
		int flag = kindService.updateKind(kind);
		if (flag != 0) {
			request.setAttribute("msg", "修改成功");
		} else {
			request.setAttribute("msg", "修改失败");
		}
		return "UpdateKind";
	}

	// 查询指定类别
	@RequestMapping("searchKind")
	public String searchKind(HttpServletRequest request, HttpSession session) {
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
		List<Kind> list = new ArrayList<Kind>();
		List<Kind> klist = new ArrayList<Kind>();
		Kind kind = new Kind();
		if (searchword != null && !searchword.equals("")) {
			if (searchword.matches(regex)) {
				num = Integer.parseInt(searchword);
				kind.setKId(num);
			} else {
				kind.setKName(searchword);
			}
			list = kindService.searchKind(kind);
		}
		// 准备分页，一页5条
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					klist.add(list.get(i));
				}
				request.setAttribute("List", klist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					klist.add(list.get(i));
				}
				request.setAttribute("List", klist);
			}
		}
		totalsize = list.size();
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("indexNum", 1);
		return "ManagerSearchResult";
	}
}
