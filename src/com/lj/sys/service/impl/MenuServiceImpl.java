package com.lj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lj.sys.domain.Menu;
import com.lj.sys.mapper.MenuMapper;
import com.lj.sys.service.MenuService;
import com.lj.sys.utils.DataGridView;
import com.lj.sys.vo.MenuVo;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> queryAllMenuForList(MenuVo menuVo) {
		return menuMapper.queryAllMenu(menuVo);
	}

	
	//后期做完权限管理还需要改动
	@Override
	public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
		return menuMapper.queryAllMenu(menuVo);
	}


	@Override
	public DataGridView queryAllMenu(MenuVo menuVo) {
		Page<Object> page=PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
		List<Menu> data=menuMapper.queryAllMenu(menuVo);
		
		return new DataGridView(page.getTotal(),data);
		
	}

}
