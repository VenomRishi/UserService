package com.bridgelabz.fundoo.user.service;

import org.springframework.cache.annotation.Cacheable;

import com.bridgelabz.fundoo.user.entity.User;

public class ImplRedisService implements IRedisService {

	@Cacheable(value = "user", key = "#id")
	@Override
	public User cacheable(int id) {
		return null;
	}

	@Cacheable(value = "user", key = "#id")
	@Override
	public User cacheput(int id, User user) {
		return user;
	}

}
