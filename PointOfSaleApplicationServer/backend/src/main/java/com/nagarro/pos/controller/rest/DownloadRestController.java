package com.nagarro.pos.controller.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.pos.constant.ConfigurationConstant;
import com.nagarro.pos.model.Employee;
import com.nagarro.pos.model.EmployeeCashDrawer;
import com.nagarro.pos.model.ResponseMessage;
import com.nagarro.pos.service.DownloadFileService;
import com.nagarro.pos.service.VerificationService;

/**
 * It will invoke when '/download' has been call and it will query related to
 * download
 * 
 * @author nishantgarg
 *
 */
@CrossOrigin(origins = ConfigurationConstant.CORS)
@RestController
@RequestMapping("/download")
public class DownloadRestController {

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private DownloadFileService downloadFileService;

	/**
	 * It will create the file and save the header to the response
	 * 
	 * @param httpServletRequest
	 * @param response
	 * @param fileName
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@RequestMapping("/file")
	public void downloadPDFResource(HttpServletRequest httpServletRequest, HttpServletResponse response,
			@RequestParam("fileName") String fileName) throws IOException, InterruptedException {
		String token = httpServletRequest.getHeader("token");
		System.out.println("token" + token);
		// if(token == null || verificationService.verifyingEmployee(token)==null) {
		// System.out.println("in error");
		// return ;
		// }
		// EmployeeCashDrawer employeeCashDrawer =
		// verificationService.verifyingEmployee(token);
		//// System.out.println(fileName);
		// String path =
		// downloadFileService.makeFileOnServer(token,employeeCashDrawer.getEmployees());
		// System.out.println(path);
		String path = new File("").getAbsolutePath() + "\\" + fileName;
		System.out.println(path);
		File file = new File(path);
		// fileName = token.substring(1,11) + ".xlsx";
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed inline in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * attachment, that is downloaded and saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
			// response.setHeader("Content-Disposition", String.format("inline; filename=\""
			// + file.getName() + "\""));

			// Here we have mentioned it to show as attachment
			response.setHeader("Content-Disposition", String.format("attachment;filename=\"" + file.getName() + "\""));
			// response.setHeader(arg0, arg1);
			response.setContentLength((int) file.length());
			
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

			// return file.getPath();

		} else {
			System.err.println("error h bhai");
		}
		// return null;
	}

	@RequestMapping("/getFileName")
	public ResponseMessage getFileName(HttpServletRequest httpServletRequest, HttpServletResponse response, @RequestParam String fileType) {

		// verifying the user
		String token = httpServletRequest.getHeader("token");
		if (token == null || verificationService.verifyingEmployee(token) == null) {
			return null;
		}
		// after verification product details returned
		EmployeeCashDrawer employeeCashDrawer = verificationService.verifyingEmployee(token);
		ResponseMessage res = new ResponseMessage();
		res.setStatus(200);
		String path = null;
		System.out.println("pathh" + fileType);
		//create file depending on type of file needed 
		if("orders".equals(fileType))
			path = downloadFileService.makeFileOnServerForOrder(token,employeeCashDrawer.getEmployees());
		else path = downloadFileService.makeFileOnServerForCashDrawer(token,employeeCashDrawer.getEmployees());
		if(path ==null )return null;
		
		res.setSuccessMessage(path);
		return res;
	}

}
