package com.chet.springboot_catchall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class main_controller {
	
	@Autowired
	ServletContext context;
	
	@Value("${pics_folder}") 
	private String pics_loc ;
	
	@RequestMapping("/")
	public String landing () {
		return "landing" ;
	}
	
	@GetMapping("pic")
	public void test(HttpServletRequest req, HttpServletResponse resp) {
	
		String path = req.getContextPath();
		
			
	
		File file;
		String fname = "user-pic" ;
		try {
			//file = new ClassPathResource("static/" + fname + ".jpg").getFile();
			//file = new File("C:\\Users\\chsurya\\Desktop\\Tmp\\Spring\\springboot_catchall\\src\\main\\resources\\static\\" + fname + ".jpg") ;
			file = new File(pics_loc + "\\" + fname + ".jpg") ;
			if(file.exists()) {
				Files.copy(file.toPath(), resp.getOutputStream());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	@PostMapping("save_pic")
	public void save_pic (@RequestParam("file") MultipartFile file,HttpServletResponse resp) {
		
		String fname = "user-pic" ;
		//String fpath = "C:\\Users\\chsurya\\Desktop\\Tmp\\Spring\\springboot_catchall\\src\\main\\resources\\static\\" + fname + ".jpg" ;
		String fpath = pics_loc + "\\" + fname + ".jpg" ;
			
		
		try {
			InputStream fileInputStream = file.getInputStream();
			File tmpfile = new File(fpath) ;
			
			Files.copy(fileInputStream, tmpfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			resp.sendRedirect("/");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	

}
