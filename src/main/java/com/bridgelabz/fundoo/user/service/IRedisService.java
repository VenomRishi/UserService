package com.bridgelabz.fundoo.user.service;

public interface IRedisService {

	void cacheAble(int id);
	
	void cachePut(int id);
}
