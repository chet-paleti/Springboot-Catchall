package com.chet.springboot_catchall;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("players")
public class Player {
	
	private String id;
	private String player_name ;
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public String getId() {
		return id;
	}
	

}
