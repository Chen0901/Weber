package org.lanqiao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.GoodAndBad;
import org.lanqiao.bean.RegisterUser;
import org.lanqiao.service.GoodAndBadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodAndBadController {
	GoodAndBadService goodAndBadService;

	public GoodAndBadService getGoodAndBadService() {
		return goodAndBadService;
	}

	@Autowired
	public void setGoodAndBadService(GoodAndBadService goodAndBadService) {
		this.goodAndBadService = goodAndBadService;
	}

	// 查看用户是否点过此条信息的赞或踩
	@RequestMapping("selectGBInfoById")
	@ResponseBody
	public String selectGBInfoById(HttpServletRequest request,
			HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int iid = Integer.parseInt(request.getParameter("IId"));
		GoodAndBad goodAndBad = goodAndBadService
				.selectGBInfoById(new GoodAndBad(registerUser.getRUId(), iid));
		if (goodAndBad != null) {
			return "0";
		}else{
			return request.getParameter("IId");
		}
	}
}
