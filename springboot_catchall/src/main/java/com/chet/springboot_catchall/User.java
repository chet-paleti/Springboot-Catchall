package com.chet.springboot_catchall;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	private String name ;
	private String email ;
	//private String city;
	private HashMap<String, String> addr = new HashMap<String, String>();
	
	public HashMap<String, String> getAddr() {
		return addr;
	}

	public void setAddr(HashMap<String, String> addr) {
		this.addr = addr;
	}

	@JsonProperty("address")
	private void unpackNested(Map<String,Object> address) {
       // this.city = (String)address.get("city");
        this.addr.put("street", (String)address.get("street"));
        this.addr.put("suite", (String)address.get("suite"));
        this.addr.put("city", (String)address.get("city"));
       
    }

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
