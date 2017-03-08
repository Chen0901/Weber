package org.lanqiao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.CollectionInfo;
import org.lanqiao.bean.Information;
import org.lanqiao.bean.RegisterUser;
import org.lanqiao.service.CollectionInfoService;
import org.lanqiao.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class CollectionInfoController {
	CollectionInfoService collectionInfoService;

	public CollectionInfoService getCollectionInfoService() {
		return collectionInfoService;
	}

	@Autowired
	public void setCollectionInfoService(
			CollectionInfoService collectionInfoService) {
		this.collectionInfoService = collectionInfoService;
	}

	InformationService informationService;

	public InformationService getInformationService() {
		return informationService;
	}

	@Autowired
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	// 收藏
	@RequestMapping("addCollectionInformation")
	@ResponseBody
	public String addCollectionInformation(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int RUId = registerUser.getRUId();
		int IId = Integer.parseInt(request.getParameter("IId"));
		int flag = collectionInfoService.addCollectionInformation(RUId, IId);
		if (flag != 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 取消收藏
	@RequestMapping("deleteCollectionInformation")
	public String deleteCollectionInformation(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int IId = Integer.parseInt(request.getParameter("IId"));
		CollectionInfo collectionInfo = new CollectionInfo(registerUser.getRUId(),IId);
		collectionInfoService.deleteCollectionInformation(collectionInfo);
		return "redirect:selectInfoByCollect.do";
	}

	// 查询此信息是否已经被收藏
	@RequestMapping("selectCollectionInfoById")
	@ResponseBody
	public String selectCollectionInfoById(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int ruid = registerUser.getRUId();
		int IId = Integer.parseInt(request.getParameter("IId"));
		CollectionInfo collectionInfo = collectionInfoService
				.selectCollectionInfoById(new CollectionInfo(ruid, IId));
		if (collectionInfo == null) {
			String id = String.valueOf(IId);
			return id;
		} else {
			return "0";
		}
	}

	// 显示用户收藏列表
	@RequestMapping("selectInfoByCollect")
	public String selectInfoByCollect(HttpServletRequest request,
			HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = informationService.selectInfoByCollect(
				registerUser.getRUId()).size();
		// 获取全部用户
		List<Information> list = informationService
				.selectInfoByCollect(registerUser.getRUId());
		List<Information> ilist = new ArrayList<Information>();
		// 准备分页，一页五条记录
		// 当总记录条数小于5条时，直接显示无需分页
		if (list.size() < 5) {
			request.setAttribute("InfoList", list);
		} else {
			// 当总记录条数大于5条时，根据当前页拿出应显示的数据显示
			// 考虑记录数与页数的关系，防止下标越界
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("InfoList", ilist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					ilist.add(list.get(i));
				}
				request.setAttribute("InfoList", ilist);
			}
		}
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		return "CollectionIndex";
	}
}
