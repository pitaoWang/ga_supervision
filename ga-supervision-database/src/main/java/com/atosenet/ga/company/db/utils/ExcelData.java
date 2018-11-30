/**
 * 
 */
package com.atosenet.ga.company.db.utils;

/**
 * @author lvhao
 *
 */
public interface ExcelData {
	/**
	 * 
	 * TODO(保存各列中文名称) 
	 *
	 * @Title: getFieldTitles 
	 * @return  
	 * @throws
	 *
	 */
	public String[] getFieldTitles();
	
	/**
	 * 
	 * TODO(保存各列名) 
	 *
	 * @Title: getFieldNames 
	 * @return  
	 * @throws
	 *
	 */
	public String[] getFieldNames();
}
