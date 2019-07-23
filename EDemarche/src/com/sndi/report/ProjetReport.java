package com.sndi.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.GrayColor;
import com.sndi.service.Iservice;
import com.sndi.utilitaires.CheckSysTem;
import com.sndi.utilitaires.DownloadFileServlet;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Controller
public class ProjetReport {
	@Autowired
	Iservice iservice;
	
	@Autowired
	ConnectionUtils connectionUtils;
	 @Autowired
	  DownloadFileServlet downloadFileServlet;
	 
	static final String path = "\\standalone\\deployments\\SIGMiCAP.war\\report\\";
	static final String pathdirLinux = "standalone/deployments/SIGMiCAP.war/report/";
	static final String pathimagedir = "\\standalone\\deployments\\SIGMiCAP.war\\report\\images\\";
	static final String pathimagedirLinux = "/report/images/";
	static  String workingDir = "";
	
	 public static final Font FONT = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.GRAYWHITE);
	   
	 @PostConstruct
		public void postConstru() throws IOException {	
		 File dir = new File("..");
		 workingDir = dir.getCanonicalPath();	
		}

	
	 public InputStream genReportDocx(Map<String, Object> params, String jrxmlName){
			
			try{
				Connection conn = connectionUtils.getConnection();
				
				String jrxmlFile = FacesContext.getCurrentInstance().getExternalContext()
						.getRealPath("/report/" + jrxmlName + ".jrxml");
				InputStream input = new FileInputStream(new File(jrxmlFile));
				JasperReport jasperReport = JasperCompileManager.compileReport(input);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
				
				File docxRun = File.createTempFile("output.", ".docx");
				//JasperExportManager.exportReportToPdfStream(jasperPrint,  new FileOutputStream(docxRun));

				   JRDocxExporter exporter = new JRDocxExporter();
				    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));   
				    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(docxRun)));
				    exporter.exportReport();
				 InputStream inputStream = new FileInputStream(docxRun);
				
				return inputStream;
		
			}catch(Exception e){
				System.out.println(e);
				return null;
			}
			
		}

	
	
		public InputStream runCCMTemplateReport(String codeExpImp) {
			
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("codeImputation", codeExpImp);
				return genReportDocx(params, "ccmTemplate");
	
	}
		
		
	
	public void showReportDoc(String codeExpImp, String reportName, String jrxmlName) {
		try {

			 String pathdir ="";
			 
			 if (CheckSysTem.isWindows()) {
				 pathdir = workingDir+path;
				 
		            System.out.println("This is Windows");
		        } else {
		        	pathdir = FacesContext.getCurrentInstance().getExternalContext()
		 					.getRealPath("/report/");
		        	pathdir +="/";
		        
		        }
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("codeImputation", codeExpImp);
			param.put("SUBREPORT_DIR", pathdir);
			
			//JRDataSource jrDatasource = new JRBeanCollectionDataSource(findAll());
			Connection conn = connectionUtils.getConnection();
			
			
			String jrxmlFile = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/report/"+jrxmlName+".jrxml");
			InputStream input = new FileInputStream(new File(jrxmlFile));
			JasperReport jasperReport = JasperCompileManager.compileReport(input);	
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);

			   HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
			   httpServletResponse.addHeader("Content-disposition", "attachment; filename="+reportName+"-"+codeExpImp+".doc");  

			   FacesContext.getCurrentInstance().responseComplete();
			   ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();  

			   JRDocxExporter exporter = new JRDocxExporter();
			    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));      
	
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));
			    SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
			   // SimpleDocxExporterConfiguration configuration = new SimpleDocxExporterConfiguration();
			    config.setFlexibleRowHeight(true);
			    config.setFramesAsNestedTables(false);
			    
			    exporter.setConfiguration(config);
			    exporter.exportReport();
			    
			 //  JasperExportManager.expor(jasperPrint, servletOutputStream);  
			   System.out.println("Terminé doc");
			   servletOutputStream.flush();
			   servletOutputStream.close(); 
			   FacesContext.getCurrentInstance().responseComplete();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		catch (NamingException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
	
	
		
	}
	
	
	//Print Document Opérateur
	public void showOperateurPDF(String opeMatricule){
		
		
		 String pathdir ="";
		
		 if (CheckSysTem.isWindows()) {
			
				pathdir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/images/");
				pathdir += "/";
			 
			 System.out.println("This is Windows");
		 } else {
			 pathdir = FacesContext.getCurrentInstance().getExternalContext()
	 					.getRealPath("/report/images/");
	        	pathdir +="/";
	        	System.out.println("Chemain "+pathdir);	
	        
		 }
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("opeMatricule", opeMatricule);
		param.put("report_image", pathdir);
		System.out.println("path=  "+pathdir);
	try{
		Connection conn = connectionUtils.getConnection();
		
		
		String jrxmlFile = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/report/operateur.jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport jasperReport = JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
		
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		httpServletResponse.addHeader("contentType", "application/pdf");
		 httpServletResponse.addHeader("Content-disposition", "attachment; filename=Operateur-"+opeMatricule+".pdf");  
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		 servletOutputStream.flush();
		 servletOutputStream.close(); 
		FacesContext.getCurrentInstance().responseComplete();
		
		
	}catch(Exception e){
	System.out.println(e);	
	}
		
	}
	
	//Print Document Opérateurs
	public void showOperateursPDF(){
		
		
		 String pathdir ="";
		
		 if (CheckSysTem.isWindows()) {
			 pathdir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/images/");
				pathdir += "/";
			 
			 System.out.println("This is Windows");
		 } else {
			 pathdir = FacesContext.getCurrentInstance().getExternalContext()
	 					.getRealPath("/report/images/");
	        	pathdir +="/";
	        	System.out.println("Chemain "+pathdir);	
			 
		 }
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("report_image", pathdir);
		System.out.println("path=  "+pathdir);
	try{
		Connection conn = connectionUtils.getConnection();
		
		
		String jrxmlFile = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/report/operateurs.jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport jasperReport = JasperCompileManager.compileReport(input);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
		
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		httpServletResponse.addHeader("contentType", "application/pdf");
		 httpServletResponse.addHeader("Content-disposition", "attachment; filename=Operateurs.pdf");  
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		 servletOutputStream.flush();
		 servletOutputStream.close(); 
		FacesContext.getCurrentInstance().responseComplete();
		
		
	}catch(Exception e){
	System.out.println(e);	
	}
		
	}
	

}