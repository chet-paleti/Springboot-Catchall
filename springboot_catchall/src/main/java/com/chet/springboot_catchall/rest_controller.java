package com.chet.springboot_catchall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rest_controller {
	
	@Autowired
	private PlayerRepository player_repo ;
	
	@GetMapping("/rest-players")
	public List<Player> get_players () {
		return player_repo.findAll() ;
	}

}
