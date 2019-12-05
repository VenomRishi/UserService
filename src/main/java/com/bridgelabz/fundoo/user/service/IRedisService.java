package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.entity.User;

public interface IRedisService {

	User cacheable(int id);
	
	User cacheput(int id, User user);
}
