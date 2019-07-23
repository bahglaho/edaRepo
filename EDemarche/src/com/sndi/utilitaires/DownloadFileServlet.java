 package com.sndi.utilitaires;
 
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
 
 
 
 @Component
 public class DownloadFileServlet
 {
   private static final long serialVersionUID = 1L;
   Logger log = Logger.getLogger(DownloadFileServlet.class);
	static  String workingDir = "";
	 @PostConstruct
		public void postConstru() throws IOException {	
		 File dir = new File("..");
		 workingDir = dir.getCanonicalPath();	
		}
   
   public void doGet(String filePath, HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     File downloadFile = new File(filePath);
     FileInputStream inStream = new FileInputStream(downloadFile);
     
     ServletContext context = request.getServletContext();
     
 
     String mimeType = context.getMimeType(filePath);
     if (mimeType == null)
     {
       mimeType = "application/octet-stream";
     }
     System.out.println("MIME type: " + mimeType);
     
 
     response.setContentType(mimeType);
     response.setContentLength((int)downloadFile.length());
     
 
    String headerKey = "Content-Disposition";
     String headerValue = String.format("attachment; filename=\"%s\"", new Object[] { downloadFile.getName() });
     response.setHeader(headerKey, headerValue);
     
     try
     {
    	  //OutputStream out = new FileOutputStream(new File(destination + fileName));
       OutputStream outStream = response.getOutputStream();
       
      byte[] buffer = new byte[1024];
      int bytesRead = -1;
       
       while ((bytesRead = inStream.read(buffer)) != -1) {
         outStream.write(buffer, 0, bytesRead);
       }
       
				inStream.close();
                outStream.flush();
                outStream.close();
      
     }
     catch (IllegalStateException localIllegalStateException) {}
   
   }
   public void downloadFile(String fichierComplet, String nomFichier) throws IOException
   {
	   HttpServletRequest request = (HttpServletRequest)
		       FacesContext.getCurrentInstance().getExternalContext().getRequest();
      File file = new File(fichierComplet);
      if(file.exists() && file.isFile()) {
    	  InputStream fis = new FileInputStream(file);
          
          
    	     
          
          
          ServletContext context = request.getServletContext();
          
          
          String mimeType = context.getMimeType(fichierComplet);
          if (mimeType == null)
          {
            mimeType = "application/octet-stream";
          }
          System.out.println("MIME type: " + mimeType);
          
          HttpServletResponse response =
             (HttpServletResponse) FacesContext.getCurrentInstance()
            .getExternalContext().getResponse();

         response.setContentType("application/octet-stream");
         response.setHeader("Content-Disposition", "attachment;filename="+nomFichier);
         response.setContentType(mimeType);
         response.setContentLength((int)file.length());
        // OutputStream outStream = 
         
         byte[] buffer = new byte[1024];
         int bytesRead = -1;
          
          while ((bytesRead = fis.read(buffer)) != -1) {
        	  response.getOutputStream().write(buffer, 0, bytesRead);
          }
          
          			fis.close();
          			response.getOutputStream().flush();
          			response.getOutputStream().close();
         FacesContext.getCurrentInstance().responseComplete(); 
    	}else{
    		
    		FacesMessage msg = new FacesMessage("Le Document n'est pas disponible!");
    	       FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
     
   }
   public void downloadFileDB(String nomFichier, InputStream stream) throws IOException
   {
	   HttpServletRequest request = (HttpServletRequest)
			   FacesContext.getCurrentInstance().getExternalContext().getRequest();
	   File file = File.createTempFile(nomFichier, ".pdf");
		   
		   ServletContext context = request.getServletContext();
		   
		   
		   String mimeType = context.getMimeType(nomFichier);
		   if (mimeType == null)
		   {
			   mimeType = "application/octet-stream";
		   }
		   System.out.println("MIME type: " + mimeType);
		   
		   HttpServletResponse response =
				   (HttpServletResponse) FacesContext.getCurrentInstance()
				   .getExternalContext().getResponse();
		   
		   response.setContentType("application/octet-stream");
		   response.setHeader("Content-Disposition", "attachment;filename="+nomFichier);
		   response.setContentType(mimeType);
		   response.setContentLength((int)file.length());
		   // OutputStream outStream = 
		   
		   byte[] buffer = new byte[1024];
		   int bytesRead = -1;
		   
		   while ((bytesRead = stream.read(buffer)) != -1) {
			   response.getOutputStream().write(buffer, 0, bytesRead);
		   }
		   
		   stream.close();
		   response.getOutputStream().flush();
		   response.getOutputStream().close();
		   FacesContext.getCurrentInstance().responseComplete(); 
	   
	   
   }
   
 
   
   public void copieFileToTrueFolder(String fichierComplet, String nomFichier) throws IOException
   {
	  
      File file = new File(fichierComplet);
      

	
      if(file.exists() && file.isFile()) {
    	  InputStream in = new FileInputStream(file);
          
          
    	     
          File dirTrue = new File(workingDir+GRFProperties.PARAM_UPLOAD_DESTINATION_TRUE_DOC);
  			dirTrue.mkdirs();	
  		
  	OutputStream out = new FileOutputStream(new File(workingDir+GRFProperties.PARAM_UPLOAD_DESTINATION_TRUE_DOC + nomFichier));
	
	int read = 0;
	
	byte[] bytes = new byte[1024];
	
	while ((read = in.read(bytes)) != -1) {
	
	out.write(bytes, 0, read);
	
	}  
	
	in.close();
	
	out.flush();
	
	out.close();
    
    	}else{
    		
    		FacesMessage msg = new FacesMessage("Le Document n'est pas disponible!");
    	       FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
     
   }
   public boolean deleteFileOnFolder(String fichierComplet, String nomFichier) {
	   boolean rep = false;
	   try {
		File file = new File(fichierComplet);
		   
		   
		   
		   if(file.exists() && file.isFile()) {
			   file.delete();
			   rep = true;
			 
		   }else{
			   rep = false;
			   FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Le Document n'est pas disponible!","");
			   FacesContext.getCurrentInstance().addMessage(null, msg);
		   }
	} catch (Exception e) {
		
	}
	   return rep;
   }
   
   
   public String printOM(String codeExpImp) throws IOException {
	 return  null;// printDocument("C:\\SIGMissions\\", "OrdreMission", "codeExpedition", codeExpImp, "OM.rptdesign");  
	  }
   
  

   public void printAllPart() throws IOException {
	   printDocuments("C:\\SIGMissions\\Etats\\", "Participants", "EtatParticipants.rptdesign");  
	  }
 
   public void printDocuments(String destination, String TypeDoc, String fileRptdesign) throws IOException {
	    try {


	       
	      

	      // downloadFile(fichierComplet,nomfichier);

	    }
	    catch (Exception e)
	    {
	       this.log.error(e);
	    }
	  }
 }
