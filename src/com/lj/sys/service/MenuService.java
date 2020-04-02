package com.lj.sys.service;

import java.util.List;

import com.lj.sys.domain.Menu;
import com.lj.sys.utils.DataGridView;
import com.lj.sys.vo.MenuVo;

/*
 * 菜单管理的服务接口
 */
public interface MenuService {
	
	//查询所有菜单，返回List
	public List<Menu> queryAllMenuForList(MenuVo menuVo);
		
	//根据用户id查询用户的可用菜单
	public List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

	//查询所有菜单，分页
	public DataGridView queryAllMenu(MenuVo menuVo);
	
}
