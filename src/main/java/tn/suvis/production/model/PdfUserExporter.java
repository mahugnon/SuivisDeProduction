package tn.suvis.production.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import tn.suvis.production.entities.Users;

public class PdfUserExporter extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"user_list.pdf\"");
		@SuppressWarnings("unchecked")
		List<Users> list=(List<Users>) model.get("userList");

		Table table=new Table(5);
		table.addCell("#");
		table.addCell("Nom et Prénom");
		table.addCell("Sexe");
		table.addCell("Adresse");
		table.addCell("Téléphone");
		for(Users user:list) {
			table.addCell(String.valueOf(user.getIdUser()));
			table.addCell(String.valueOf(user.getFirstName()));
			table.addCell(String.valueOf(user.getSexe()));
			table.addCell(String.valueOf(user.getAdresse()));
			table.addCell(String.valueOf(user.getTelephone()));
		}
		document.add(table);
	}

}
