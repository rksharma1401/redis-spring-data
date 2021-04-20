package com.learn.redis.rest;
 

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.redis.entity.AppInfo;
import com.learn.redis.repo.AppInfoRepo;

@RestController
public class AppInfoController {

	@Autowired
	private AppInfoRepo repo;
	
	@GetMapping("/appinfo/{id}")
	public Optional<AppInfo>  get(@PathVariable("id") Integer id) {
		return repo.findById(id);
	}
	
	@PutMapping("/appinfo")
	public AppInfo  save(@RequestBody AppInfo appInfo) {
		return repo.save(appInfo);
	}

}
