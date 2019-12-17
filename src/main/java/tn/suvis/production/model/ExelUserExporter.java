package tn.suvis.production.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;


import tn.suvis.production.entities.Users;

public class ExelUserExporter extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			response.setHeader("Content-Disposition", "attachment; filename=\"user_list.xls\"");
			@SuppressWarnings("unchecked")
			List<Users> list=(List<Users>) model.get("userList");
			
			Sheet sheet=workbook.createSheet("User List");
			//header row
			Row header=sheet.createRow(0);
			header.createCell(0).setCellValue("#");
			header.createCell(1).setCellValue("Nom et Prénom");
			header.createCell(2).setCellValue("Sexe");
			header.createCell(3).setCellValue("Adresse");
			header.createCell(4).setCellValue("Téléphone");
			int rowNum=1;
			for(Users user:list) {
				Row row=sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(user.getIdUser());
				row.createCell(1).setCellValue(user.getFirstName()+" "+user.getLastName());
				row.createCell(2).setCellValue(user.getSexe());
				row.createCell(3).setCellValue(user.getAdresse());
				row.createCell(4).setCellValue(user.getTelephone());
			}
	}

}
