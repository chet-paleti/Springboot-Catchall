package com.chet.springboot_catchall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;



import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class main_controller {
	
	
	
	@Value("${pics_folder}") 
	private String pics_loc ;
	
	@Autowired
	private PlayerRepository player_repo ;
	
	@RequestMapping("/")
	public String landing () {
		return "landing" ;
	}
	
	@GetMapping("pic")
	public void test(HttpServletRequest req, HttpServletResponse resp) {
	
		//String path = req.getContextPath();
		
			
	
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
	
	@GetMapping("/players")
	public String players(Model mymodel) {
		
		List<Player> players = new ArrayList<Player>() ;
		players = player_repo.findAll();
		//System.out.println(players.get(0).getPlayer_name());
		mymodel.addAttribute("playerlist", players) ;
		return "players" ;
		
	}
	
	
	@GetMapping("/players-1")
	public String players_1(Model mymodel) {
		
		/*
		Players resp = new RestTemplate().getForObject
				("http://localhost:8081/rest-players", 
						Players.class);
						*/
		ResponseEntity<Player[]> resp = new RestTemplate().getForEntity("http://localhost:8081/rest-players", Player[].class) ;
		
		//List<Player> player_list = resp.getPlayer_list() ;
		Player[] player_list = resp.getBody() ;
		mymodel.addAttribute("playerlist", player_list) ;
		return "players" ;
		
	}
	
	@GetMapping("/players-2")
	public String players_2(Model mymodel) {
		
		/*
		Players resp = new RestTemplate().getForObject
				("http://localhost:8081/rest-players", 
						Players.class);
						*/
		String resp = new RestTemplate().getForObject("http://localhost:8081/rest-players", String.class) ;
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//List<Player> player_list = resp.getPlayer_list() ;
		Star[] player_list;
		try {
			player_list = mapper.readValue(resp, Star[].class);
			mymodel.addAttribute("playerlist", player_list) ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "players-2" ;
		
	}
	
	@GetMapping("/users")
	public String users(Model mymodel) {
		
	
		String resp = new RestTemplate().getForObject("https://jsonplaceholder.typicode.com/users", String.class) ;
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//List<Player> player_list = resp.getPlayer_list() ;
		User[] users;
		try {
			users = mapper.readValue(resp, User[].class);
			mymodel.addAttribute("users", users) ;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "users" ;
		
	}
	

}
