/**
 * 
 */
package com.atosenet.ga.company.mgt.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.atosenet.ga.company.db.utils.ExcelData;

/**
 * @author lvhao
 *
 */
public abstract class ExcelExport<T extends ExcelData> {
	private Class<T> clazz;
	private String[] fieldTitles;
	private String[] fieldNames;

	public ExcelExport() {

		Class cla = getClass();
		Type type = cla.getGenericSuperclass();
		Type[] params = ((ParameterizedType) type).getActualTypeArguments();
		clazz = (Class) params[0];

	}

	public void export(String name, List<T> datas, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		T t = (T) clazz.newInstance();
		String[] titles = t.getFieldTitles();
		String[] names = t.getFieldNames();

		// 生成excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("sheet1");
		XSSFRow rowHead = sheet.createRow(0);

		// 标题行
		for (int i = 0; i < titles.length; i++) {
			XSSFCell cell = rowHead.createCell(i);
			cell.setCellValue(titles[i]);
		}
		// 数据
		XSSFRow row = null;
		for (int i = 0; i < datas.size(); i++) {
			T obj = datas.get(i);
			row = sheet.createRow(i + 1);
			for (int j = 0; j < names.length; j++) {
				XSSFCell cell = row.createCell(j);
				Field field = clazz.getDeclaredField(names[j]);
				field.setAccessible(true);
				Object val = field.get(obj);
				if(val == null){
					cell.setCellValue("");
				}else{
					cell.setCellValue(val.toString());	
				}
			}

		}

		// 下载导出
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			String fileName = name + ".xlsx";
			// 获取浏览器类型
			String agent = request.getHeader("user-agent");
			// 解决中文名乱码问题
			fileName = FileUtils.encodeDownloadFilename(fileName, agent);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
