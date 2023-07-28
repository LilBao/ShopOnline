package com.shoponline.Rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoponline.Service.FileService;

@RestController
public class FileRest {
	@Autowired
	FileService fileService;
	
	@GetMapping("/api/file/{folder}")
	public ResponseEntity<List<String>> list(@PathVariable("folder") String folder){
		return ResponseEntity.ok(fileService.list(folder));
	}
	
	@GetMapping("/api/file/{folder}/{file}")
	public byte[] read(@PathVariable("folder") String folder,@PathVariable("file") String file){
		return fileService.read(folder,file);
	}
	
	@DeleteMapping("api/file/{folder}/{file}")
	public void delete(@PathVariable("folder") String folder,@PathVariable("file") String file){
		fileService.delete(folder,file);
	}
	
	@PostMapping("/api/file/{folder}")
	public String[] upload(@PathVariable("folder") String folder,@PathParam("files") MultipartFile[] files){
		return fileService.upload(folder,files);
	}
	
}
