package org.lanqiao.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lanqiao.bean.Follow;
import org.lanqiao.bean.GoodAndBad;
import org.lanqiao.bean.Information;
import org.lanqiao.bean.Kind;
import org.lanqiao.bean.Manager;
import org.lanqiao.bean.RegisterUser;
import org.lanqiao.service.FollowService;
import org.lanqiao.service.GoodAndBadService;
import org.lanqiao.service.InformationService;
import org.lanqiao.service.KindService;
import org.lanqiao.service.RegisterUserService;
import org.lanqiao.util.ImgCompress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class InformationController {
	InformationService informationService;

	public InformationService getInformationService() {
		return informationService;
	}

	// 自动填充
	@Autowired
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	private KindService kindService;

	KindService getKindService() {
		return kindService;
	}

	@Autowired
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}

	RegisterUserService registerUserService;

	public RegisterUserService getRegisterUserService() {
		return registerUserService;
	}

	@Autowired
	public void setRegisterUserService(RegisterUserService registerUserService) {
		this.registerUserService = registerUserService;
	}

	FollowService followService;

	public FollowService getFollowService() {
		return followService;
	}

	@Autowired
	public void setFollowService(FollowService followService) {
		this.followService = followService;
	}

	GoodAndBadService goodAndBadService;

	public GoodAndBadService getGoodAndBadService() {
		return goodAndBadService;
	}

	@Autowired
	public void setGoodAndBadService(GoodAndBadService goodAndBadService) {
		this.goodAndBadService = goodAndBadService;
	}

	// 发布信息
	@SuppressWarnings("unchecked")
	@RequestMapping("addpublishInformation")
	public String addpublishInformation(HttpServletRequest request,
			HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		String IContent = "";
		String kid = "";
		String impic = "";
		String savePath = request.getServletContext().getRealPath("upload");
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		DiskFileItemFactory dfi = new DiskFileItemFactory();
		// 设置文件大小超过20*1024*1024就写到disk上
		dfi.setSizeThreshold(1024 * 1024 * 5);
		// 设置存储的仓库
		dfi.setRepository(new File(savePath));
		// 实例化一个servletFileUpload对象
		ServletFileUpload sfu = new ServletFileUpload(dfi);
		// 解决上传文件乱码问题
		sfu.setHeaderEncoding("utf-8");
		// 设置文件最大字节数
		sfu.setSizeMax(1024 * 1024 * 5);
		// 定义规定上传文件的类型
		String[] arr = { ".jpg", ".png", ".bmp" };
		// 将类型放到List中
		List<String> fileStandType = Arrays.asList(arr);
		// 对请求进行解析,有几个输入项就会有几个FileItem对象
		List<FileItem> items;
		try {
			items = sfu.parseRequest(request);
			for (FileItem item : items) {
				// 判断输入元素的类型，
				if (item.isFormField()) {
					// 普通数据项
					if ("IContent".equals(item.getFieldName())) {
						IContent = item.getString("UTF-8").trim();
					}
					if ("KId".equals(item.getFieldName())) {
						kid = item.getString();
					}
				} else {
					// 是上传文件输入项
					// 获取上传文件名称
					String fileName = item.getName();
					// 判断fileName是否为空即是否真的选择了上传文件,不为空继续
					if (!fileName.trim().equals("")) {
						// 对文件名进行处理得到文件名
						fileName = fileName.substring(fileName
								.lastIndexOf("\\") + 1);
						// 得到文件后缀判断文件类型
						String fileType = fileName.substring(fileName
								.lastIndexOf("."));
						// 判断是否是制定的文件类型
						if (!fileStandType.contains(fileType)) {
							// 如果不是制定类型的文件跳转页面
							return "redirect:selectInfoForRegister.do?msg=Filetype Not Match";
						}
						long size = item.getSize();
						if (size > (5 * 1024 * 1024)) {
							// 如果不是制定类型的文件跳转页面
							return "redirect:selectInfoForRegister.do?msg=File Size Must Be Less than 5M";
						}
						// 存大图
						FileOutputStream fos;
						StringBuffer buffer = new StringBuffer();
						// 存小图
						StringBuffer buffer2 = new StringBuffer();
						String name = "" + System.currentTimeMillis();
						String path = savePath
								+ "\\"
								+ name
								+ fileName.subSequence(fileName.indexOf("."),
										fileName.length());
						impic = name + ".png";
						fos = new FileOutputStream(path);
						if (item.isInMemory()) {
							fos.write(item.get());
							fos.close();
						} else {
							InputStream is = item.getInputStream();
							byte[] bytes = new byte[1024];
							int len;
							while ((len = is.read(bytes)) > 1) {
								fos.write(bytes, 0, len);
							}
							is.close();
							fos.close();
						}
						buffer.append(savePath).append("\\B").append(impic);
						buffer2.append(savePath).append("\\S").append(impic);
						ImgCompress imgCompress = new ImgCompress(path);
						if (imgCompress.getWidth() <= 300) {
							if (imgCompress.getHeight() > 500) {
								imgCompress.resizeFix(imgCompress.getWidth(),
										500, buffer.toString());
								imgCompress.resizeFix(imgCompress.getWidth(),
										300, buffer2.toString());
							} else {
								imgCompress.resizeFix(imgCompress.getWidth(),
										imgCompress.getHeight(),
										buffer.toString());
								imgCompress.resizeFix(imgCompress.getWidth(),
										imgCompress.getHeight(),
										buffer2.toString());
							}
						} else if (imgCompress.getWidth() > 300
								&& imgCompress.getWidth() <= 750) {
							if (imgCompress.getHeight() > 500) {
								imgCompress.resizeFix(imgCompress.getWidth(),
										500, buffer.toString());
								imgCompress.resizeFix(imgCompress.getWidth(),
										300, buffer2.toString());
							} else {
								imgCompress.resizeFix(imgCompress.getWidth(),
										imgCompress.getHeight(),
										buffer.toString());
								imgCompress.resizeFix(imgCompress.getWidth(),
										imgCompress.getHeight() / 2,
										buffer2.toString());
							}
						} else {
							if (imgCompress.getHeight() > 500) {
								imgCompress.resizeFix(imgCompress.getWidth(),
										400, buffer.toString());
								imgCompress.resizeFix(imgCompress.getWidth(),
										300, buffer2.toString());
							} else {
								imgCompress.resizeFix(
										imgCompress.getHeight() / 2,
										imgCompress.getHeight() / 2,
										buffer.toString());
								imgCompress.resizeFix(
										imgCompress.getHeight() / 4,
										imgCompress.getHeight() / 4,
										buffer2.toString());
							}
						}
						File file2 = new File(path);
						file2.delete();// 删除临时原图
						item.delete();// 在关闭流之后，删除临时缓存文件
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int RUId = registerUser.getRUId();
		Date date = new Date();
		if (IContent != null && !IContent.equals("")) {
			Information information = new Information();
			information.setIContent(IContent);
			information.setRUId(RUId);
			if (kid != null && !kid.equals("")) {
				information.setKId(Integer.parseInt(kid));
			}
			information.setIPicpath(impic);
			information.setITime(date);
			int flag = informationService.addpublishInformation(information);
			if (flag != 0) {
				return "redirect:selectInfoForRegister.do?msg=publish successed";
			} else {
				return "redirect:selectInfoForRegister.do?msg=publish failed";
			}
		} else {
			return "redirect:selectInfoForRegister.do?msg=content is null";
		}
	}

	// 删除指定信息
	@RequestMapping("deleteInfo")
	public String deleteInfo(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 根据ID进行删除，获取ID
		int IID = Integer.parseInt(request.getParameter("IId"));
		Information information = new Information(IID);
		int flag = informationService.deleteInfo(information);
		// 定义了一个flag，获取影响行数，判断它不为零则删除成功，否则失败
		if (flag != 0) {
			return "redirect:selectAllInfo.do?msg=Delete Successed";
		} else {
			return "redirect:selectAllInfo.do?msg=Delete Failed";
		}
	}

	// 根据ID搜索信息
	@RequestMapping("searchInfo")
	public String searchInfo(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 获取session中的查询关键字
		String session_search = (String) session.getAttribute("word");
		// 获取传来的查询关键字
		String searchInfo = request.getParameter("searchword");
		// 正则表达式匹配正整数，判断查询关键字是否为数字
		String regex = "^[0-9]*[1-9][0-9]*$";
		int num = 0;
		if (session_search != null) {
			if (searchInfo != null && !searchInfo.equals("")
					&& !session_search.equals(searchInfo)) {
				session.setAttribute("word", searchInfo);
				session_search = searchInfo;
			}
			searchInfo = session_search;
		} else {
			session.setAttribute("word", searchInfo);
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
		List<Information> list = new ArrayList<Information>();
		List<Information> Ilist = new ArrayList<Information>();
		Information Info = new Information();
		if (searchInfo != null && !searchInfo.equals("")) {
			if (searchInfo.matches(regex)) {
				num = Integer.parseInt(searchInfo);
				Info.setIId(num);
			} else {
				Info.setIContent(searchInfo);
			}
			list = informationService.searchInfo(Info);
		}
		// 准备分页，一页5条
		if (list.size() < 5) {
			request.setAttribute("List", list);
		} else {
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					Ilist.add(list.get(i));
				}
				request.setAttribute("List", Ilist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					Ilist.add(list.get(i));
				}
				request.setAttribute("List", Ilist);
			}
		}
		totalsize = list.size();
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("indexNum", 2);
		return "ManagerSearchResult";
	}

	// 获取全部发布信息
	@RequestMapping("selectAllInfo")
	public String selectAllInfo(HttpServletRequest request, HttpSession session) {
		Manager manager = (Manager) session.getAttribute("manager");
		if (manager == null) {
			return "ManagerLogin";
		}
		// 设置页面数，1表示为管理类别页
		request.setAttribute("indexNum", 2);
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = informationService.selectAllInfo().size();
		// 获取全部用户
		List<Information> list = informationService.selectAllInfo();
		List<Information> ilist = new ArrayList<Information>();
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
		request.setAttribute("msg", request.getParameter("msg"));
		return "ManagerIndex";
	}

	// 删除发布的信息
	@RequestMapping("deleteInformation")
	public String deleteInformation(HttpServletRequest request,
			HttpSession session) {
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		String path = request.getServletContext().getRealPath("upload");
		String path1 = path + "\\B" + request.getParameter("path");
		String path2 = path + "\\S" + request.getParameter("path");
		File file = new File(path1);
		File file2 = new File(path2);
		if (file.exists()) {
			file.delete();
		}
		if (file2.exists()) {
			file2.delete();
		}
		int iid = Integer.parseInt(request.getParameter("IId"));
		Information information = new Information(iid);
		informationService.deleteInfo(information);
		return "redirect:selectInfoForSelf.do";
	}

	// 热门广场信息展示
	@RequestMapping("selectInfoForUser")
	public String selectInfoForUser(HttpServletRequest request) {
		// 0表示这是普通信息
		int pagingMode = 0;
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = informationService.selectInfoForUser().size();
		List<Information> ilist = new ArrayList<Information>();
		// 获取全部发布信息
		List<Information> list = informationService.selectInfoForUser();
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
		request.setAttribute("pagingMode", pagingMode);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		return "DisplayInformation";
	}

	// 热门广场分类展示
	@RequestMapping("selectKindForUser")
	public String selectKindForUser(HttpServletRequest request,
			HttpSession session) {
		// 查询所有标签
		List<Kind> kind = new ArrayList<Kind>();
		kind = kindService.selectAllKind();
		request.setAttribute("KindList", kind);
		return "KindPage";
	}

	// 根据分类搜索信息
	@RequestMapping("selectInfoByKind")
	public String selectInfoByKind(HttpServletRequest request) {
		// 1表示这是根据分类搜索信息
		int pagingMode = 1;
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		Information information = new Information(Integer.parseInt(request
				.getParameter("KId")));
		totalsize = informationService.selectInfoByKind(information).size();
		List<Information> ilist = new ArrayList<Information>();
		// 获取全部发布信息
		List<Information> list = informationService
				.selectInfoByKind(information);
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
		request.setAttribute("pagingMode", pagingMode);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		return "DisplayInformation";
	}

	// 为用户查询发布信息
	@RequestMapping("searchInfoForUser")
	public String searchInfoForUser(HttpServletRequest request,
			HttpSession session) {
		// 2表示这是根据用户搜索查询到的信息
		int pagingMode = 2;
		// 获取session中的查询关键字
		String session_search = (String) session.getAttribute("keyword");
		// 获取传来的查询关键字
		String searchInfo = request.getParameter("word");
		if (session_search != null) {
			if (searchInfo != null && !searchInfo.equals("")
					&& !session_search.equals(searchInfo)) {
				session.setAttribute("keyword", searchInfo);
				session_search = searchInfo;
			}
			searchInfo = session_search;
		} else {
			session.setAttribute("keyword", searchInfo);
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
		List<Information> list = new ArrayList<Information>();
		List<Information> Ilist = new ArrayList<Information>();
		Information info = new Information();
		if (searchInfo != null && !searchInfo.equals("")) {
			info.setRUName(searchInfo.trim());
			info.setIContent(searchInfo.trim());
			list = informationService.searchInfoForUser(info);
		}
		// 准备分页，一页5条
		if (list.size() < 5) {
			request.setAttribute("InfoList", list);
		} else {
			if (currentPage * 5 > list.size()) {
				for (int i = (currentPage - 1) * 5; i < list.size(); i++) {
					Ilist.add(list.get(i));
				}
				request.setAttribute("InfoList", Ilist);
			} else {
				for (int i = (currentPage - 1) * 5; i < currentPage * 5; i++) {
					Ilist.add(list.get(i));
				}
				request.setAttribute("InfoList", Ilist);
			}
		}
		totalsize = list.size();
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("pagingMode", pagingMode);
		return "DisplayInformation";
	}

	// 用户首页信息展示
	@RequestMapping("selectInfoForRegister")
	public String selectInfoForRegister(HttpServletRequest request,
			HttpSession session) {
		// 获取Session中的用户登录信息
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		// 3表示这是用户相关发布信息
		int pagingMode = 3;
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		Information information = new Information(registerUser.getRUId());
		totalsize = informationService.selectInfoForRegister(information)
				.size();
		List<Information> ilist = new ArrayList<Information>();
		// 获取全部发布信息
		List<Information> list = informationService
				.selectInfoForRegister(information);
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
		// 查询所有标签
		List<Kind> kind = new ArrayList<Kind>();
		kind = kindService.selectAllKind();
		totalpage = (totalsize % 5 == 0) ? (totalsize / 5)
				: (totalsize / 5 + 1);
		request.setAttribute("kind", kind);
		request.setAttribute("pagingMode", pagingMode);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalsize", totalsize);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("msg", request.getParameter("msg"));
		return "DisplayInformation";
	}

	// 点赞
	@RequestMapping("addGood")
	@ResponseBody
	public String addGood(HttpServletRequest request, HttpSession session) {
		// 获取Session中的用户登录信息
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int iid = Integer.parseInt(request.getParameter("IId"));
		Information information = new Information(iid);
		int flag = informationService.addGood(information);
		if (flag != 0) {
			int flag2 = goodAndBadService.addGBInfo(new GoodAndBad(registerUser
					.getRUId(), iid));
			if (flag2 != 0) {
				return request.getParameter("IId");
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	// 踩
	@RequestMapping("addBad")
	@ResponseBody
	public String addBad(HttpServletRequest request, HttpSession session) {
		// 获取Session中的用户登录信息
		RegisterUser registerUser = (RegisterUser) session
				.getAttribute("registerUser");
		if (registerUser == null) {
			return "LoginRegisterUser";
		}
		int iid = Integer.parseInt(request.getParameter("IId"));
		Information information = new Information(iid);
		int flag = informationService.addBad(information);
		if (flag != 0) {
			int flag2 = goodAndBadService.addGBInfo(new GoodAndBad(registerUser
					.getRUId(), iid));
			if (flag2 != 0) {
				return request.getParameter("IId");
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	// 通过用户Id获取信息
	@RequestMapping("selectInfoById")
	public String selectInfoById(HttpServletRequest request, HttpSession session) {
		String ruid = request.getParameter("RUId");
		// 获取session中的查询关键字
		String Rid = (String) session.getAttribute("ruid");
		if (Rid != null) {
			if (ruid != null && !ruid.equals("") && !Rid.equals(ruid)) {
				session.setAttribute("ruid", ruid);
				Rid = ruid;
			}
			ruid = Rid;
		} else {
			session.setAttribute("ruid", ruid);
		}
		int RUid = Integer.parseInt(ruid);
		Information information = new Information(RUid);
		RegisterUser registerUser = registerUserService
				.selectOneRegisterUser(RUid);
		// 获取Session中的用户登录信息
		RegisterUser user = (RegisterUser) session.getAttribute("registerUser");
		if (user == null) {
			request.setAttribute("msg", "点击关注");
		} else {
			Follow follow = followService.selectFollowById(new Follow(user
					.getRUId(), RUid));
			if (follow != null) {
				request.setAttribute("msg", "取消关注");
			} else {
				request.setAttribute("msg", "点击关注");
			}
		}
		// 设置分页
		int totalsize = 0; // 记录总条数
		int totalpage = 1; // 总页数
		int currentPage = 1; // 当前页数
		String page_now = request.getParameter("currentPage");
		if (page_now != null) {
			currentPage = Integer.parseInt(page_now);
		}
		totalsize = informationService.selectInfoById(information).size();
		List<Information> ilist = new ArrayList<Information>();
		// 获取全部发布信息
		List<Information> list = informationService.selectInfoById(information);
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
		request.setAttribute("user", registerUser);
		return "PersonalIndex";
	}

	// 查询登录用户个人发布信息
	@RequestMapping("selectInfoForSelf")
	public String selectInfoForSelf(HttpServletRequest request,
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
		Information information = new Information(registerUser.getRUId());
		totalsize = informationService.selectInfoForSelf(information).size();
		List<Information> ilist = new ArrayList<Information>();
		// 获取全部发布信息
		List<Information> list = informationService
				.selectInfoForSelf(information);
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
		return "RegisterInformation";
	}

}
