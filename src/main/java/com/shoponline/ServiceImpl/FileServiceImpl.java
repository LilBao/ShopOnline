package com.shoponline.ServiceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoponline.Service.FileService;

@Service
public class FileServiceImpl implements FileService{
	@Autowired
	ServletContext app;
	
	private Path getPath(String folder, String filename) {
		File dir = Paths.get(app.getRealPath("/files/"),folder).toFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return Paths.get(dir.getAbsolutePath(),filename);
	}
	
	@Override
	public List<String> list(String folder) {
		List<String> listFile =new ArrayList<>();
		File dir = Paths.get(app.getRealPath("/files/"),folder).toFile();
		if(dir.exists()) {
			File[] files = dir.listFiles();
			for(File file : files) {
				listFile.add(file.getName());
			}
		}
		return listFile;
	}

	@Override
	public byte[] read(String folder, String name) {
		Path path = this.getPath(folder, name);
		try {
			return Files.readAllBytes(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> upload(String folder, MultipartFile[] files) {
		List<String> filesname = new ArrayList<>();
		for (MultipartFile file : files) {
			String name = System.currentTimeMillis()+file.getOriginalFilename();
			String filename = Integer.toHexString(name.hashCode())+name.substring(name.lastIndexOf("."));
			Path path = this.getPath(folder, filename);
			try {
				file.transferTo(path);
				filesname.add(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return filesname;
	}

	@Override
	public void delete(String folder, String name) {
		Path path = this.getPath(folder, name);
		path.toFile().delete();
	}
	
}
