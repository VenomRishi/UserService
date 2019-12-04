package com.bridgelabz.fundoo.user.service;

import org.springframework.cache.annotation.Cacheable;

public class ImplRedisService implements IRedisService {

	@Cacheable(value = "user", key="#id")
	@Override
	public void cacheAble(int id) {
		// TODO Auto-generated method stub
		
	}

	@Cacheable(value = "user", key="#id")
	@Override
	public void cachePut(int id) {
		// TODO Auto-generated method stub
	}

}
