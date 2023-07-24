package com.shoponline.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	List<String> list(String folder);
	byte[] read(String folder,String name);
	List<String> upload(String folder, MultipartFile[] files);
	void delete(String folder, String name);
}
