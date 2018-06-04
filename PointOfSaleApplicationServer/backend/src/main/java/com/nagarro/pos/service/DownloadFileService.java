package com.nagarro.pos.service;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.pos.dao.impl.EmployeeCashDrawerRepositoryImplment;
import com.nagarro.pos.dao.impl.OrderRepositoryImplement;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.EmployeeCashDrawer;
import com.nagarro.pos.model.Orders;

/**
 * it will handle all the query related to file making and downloading file
 * which is coming from controller
 * 
 * @author nishantgarg
 *
 */
@Service
public class DownloadFileService {

	@Autowired
	private EmployeeCashDrawerRepositoryImplment employeeCashDrawerRepositoryImplment;

	@Autowired
	private OrderRepositoryImplement orderRepositoryImplement;

	/**
	 * create file for cash on server and return path
	 * 
	 * @param token
	 * @param employee
	 * @return {@link String}
	 */
	public String makeFileOnServerForCashDrawer(String token, Employee employee) {
		token = token.substring(1, token.length() - 1);
		FileWriter fileWriter = null;
		Workbook workbook = null;
		FileOutputStream fileOut = null;
		try

		{

			List<EmployeeCashDrawer> employeeCashDrawers = employeeCashDrawerRepositoryImplment
					.getEmployeeCashDrawer(employee.getId());

			workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xlsx` file

			/*
			 * CreationHelper helps us create instances of various things like DataFormat,
			 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("EmployeeCashDrawer");

			// Create a Font for styling header cells

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();

			// Create a Row
			Row headerRow = sheet.createRow(0);

			// Create cells
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("Start time");
			cell.setCellStyle(headerCellStyle);

			cell = headerRow.createCell(1);
			cell.setCellValue("End time");
			cell.setCellStyle(headerCellStyle);

			cell = headerRow.createCell(2);
			cell.setCellValue("Start Cash");
			cell.setCellStyle(headerCellStyle);

			cell = headerRow.createCell(3);
			cell.setCellValue("End Cash");
			cell.setCellStyle(headerCellStyle);

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			// Create Other rows and cells with employees data
			int rowNum = 1;
			for (EmployeeCashDrawer emp : employeeCashDrawers) {
				Row row = sheet.createRow(rowNum++);

				Cell startDate = row.createCell(0);
				startDate.setCellValue(emp.getStartTime());
				startDate.setCellStyle(dateCellStyle);

				Cell endDate = row.createCell(1);
				endDate.setCellValue(emp.getEndTime());
				endDate.setCellStyle(dateCellStyle);

				row.createCell(2).setCellValue(emp.getStartCash());

				row.createCell(3).setCellValue(emp.getEndCash());

			}

			// Resize all columns to fit the content size
			for (int i = 0; i < 4; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			String s = employee.toString();
			fileOut = new FileOutputStream(s + "cash" + ".xlsx");
			workbook.write(fileOut);
			String path = s + "cash" + ".xlsx";
			System.err.println(path);
			return path;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	/**
	 * Create file For order on server and return path
	 * 
	 * @param token
	 * @param employee
	 * @return {@link String}
	 */
	public String makeFileOnServerForOrder(String token, Employee employee) {
		token = token.substring(1, token.length() - 1);
		FileWriter fileWriter = null;
		Workbook workbook = null;
		FileOutputStream fileOut = null;
		try

		{

			List<Orders> orders = orderRepositoryImplement.findAllOrders(employee.getId());

			workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xlsx` file

			/*
			 * CreationHelper helps us create instances of various things like DataFormat,
			 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("EmployeeCashDrawer");

			// Create a Font for styling header cells

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();

			// Create a Row
			Row headerRow = sheet.createRow(0);

			// Create cells
			Cell cell = headerRow.createCell(0);
			cell.setCellValue("Order Id");
			cell.setCellStyle(headerCellStyle);

			cell = headerRow.createCell(1);
			cell.setCellValue("Order Date");
			cell.setCellStyle(headerCellStyle);

			cell = headerRow.createCell(2);
			cell.setCellValue("Order Status");
			cell.setCellStyle(headerCellStyle);

			cell = headerRow.createCell(3);
			cell.setCellValue("Customer name");
			cell.setCellStyle(headerCellStyle);

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			// Create Other rows and cells with employees data
			int rowNum = 1;
			for (Orders o : orders) {
				Row row = sheet.createRow(rowNum++);

				Cell startDate = row.createCell(0);
				startDate.setCellValue(o.getId());

				Cell endDate = row.createCell(1);
				endDate.setCellValue(o.getDate());
				endDate.setCellStyle(dateCellStyle);

				row.createCell(2).setCellValue(o.getStatus());

				row.createCell(3).setCellValue(o.getCustomer().getName());

			}

			// Resize all columns to fit the content size
			for (int i = 0; i < 4; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			String s = employee.toString();
			fileOut = new FileOutputStream(s + "order" + ".xlsx");
			workbook.write(fileOut);
			String path = s + "order" + ".xlsx";
			System.err.println(path);
			return path;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
}
