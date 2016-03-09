/**
 * 
 */
package com.vion.core.security;

import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年5月13日 下午7:37:17
 */
public class FragmentSQL {
	
	private Join join;
	
	private PlainSelect select;

	public FragmentSQL(Join join) {
		super();
		this.join = join;
	}
	
	public FragmentSQL(PlainSelect select) {
		super();
		this.select = select;
	}
	
	
	public boolean isJoin(){
		return join != null;
	}
	
	
	public String getRootFrom(){
		String rootTable = null;
		if(select != null && select.getFromItem() instanceof Table){
			Table table = (Table) select.getFromItem();
			rootTable = table.getName();
		} else if (join.getRightItem() instanceof Table) {
			Table table = (Table) join.getRightItem();
			rootTable = table.getName();
		}
		return rootTable;
	}
	
	
	public List<String> getAllEntitys(){
		List<String> retEntitys = new ArrayList<String>();
		
		if (select != null) {
			if(select.getFromItem() instanceof Table){
				Table table = (Table) select.getFromItem();
				retEntitys.add(table.getName());
			}
			
			@SuppressWarnings("unchecked")
			List<Join>  joins = select.getJoins();
			if (joins != null) {
				for (Join join : joins) {
					FromItem rightItem = join.getRightItem();
					if(rightItem instanceof Table){
						Table table = (Table) rightItem;
						retEntitys.add(table.getName());
					}
				}
			}
			return retEntitys;
		}else {
			if(join.getRightItem() instanceof Table){
				Table table = (Table) join.getRightItem();
				retEntitys.add(table.getName());
				return retEntitys;
			}
		}
		return retEntitys;
	}
	
}
