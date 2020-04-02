package com.lj.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lj.sys.constast.SysConstast;
import com.lj.sys.domain.Menu;
import com.lj.sys.domain.User;
import com.lj.sys.service.MenuService;
import com.lj.sys.utils.DataGridView;
import com.lj.sys.utils.TreeNode;
import com.lj.sys.utils.TreeNodeBuilder;
import com.lj.sys.utils.WebUtils;
import com.lj.sys.vo.MenuVo;

/**
 * 菜单管理控制器
 * 
 * @author LJH
 *
 */
@RestController
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("loadIndexLeftMenuJson")
	public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
		//得到当前登陆的用户对象
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		List<Menu> list=null;
		menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
		if(user.getType()==SysConstast.USER_TYPE_SUPER) {
			list=this.menuService.queryAllMenuForList(menuVo);
		}else {
			list=this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
		}
		List<TreeNode> nodes= new ArrayList<>();
		//把list里面的数据放到nodes
		for (Menu menu : list) {
			Integer id=menu.getId();
			Integer pid=menu.getPid();
			String title=menu.getTitle();
			String icon=menu.getIcon();
			String href=menu.getHref();
			Boolean spread=menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
			String target=menu.getTarget();
			nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
		}
		return TreeNodeBuilder.builder(nodes, 1);
	}
	
	
	/**
	 * 加载菜单管理左边的菜单树
	 */
	@RequestMapping("loadMenuManagerLeftTreeJson")
	public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
		menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
		List<Menu> list=this.menuService.queryAllMenuForList(menuVo);
		List<TreeNode> nodes= new ArrayList<>();
		//把list里面的数据放到nodes
		for (Menu menu : list) {
			Integer id=menu.getId();
			Integer pid=menu.getPid();
			String title=menu.getTitle();
			String icon=menu.getIcon();
			String href=menu.getHref();
			Boolean spread=menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
			String target=menu.getTarget();
			nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
		}
		return new DataGridView(nodes);
	}
	
	/**
	 * 加载菜单列表返回DataGridView
	 */
	@RequestMapping("loadAllMenu")
	public DataGridView loadAllMenu(MenuVo menuVo) {
		return this.menuService.queryAllMenu(menuVo);
	}
	
	
}
