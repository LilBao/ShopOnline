package com.shoponline.Utilize;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class MailHelpers {
	public String[] parseStringEmailToArray(String listEmail) {
		String[] arrEmail = null;
		if(listEmail.length()>0) {
			listEmail = removeSpace(listEmail);
			arrEmail = listEmail.split(",");
		}
		return arrEmail;
	}
	
	private String removeSpace(String listEmail) {
		return listEmail.replace(" ", "");
	}
	
	public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		//Tạo 1 thư mục tạm thời để chứa file, sau này có thể xóa thư mục tạm thời đó đi
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
		multipartFile.transferTo(convFile);
		return convFile;
	}
}
