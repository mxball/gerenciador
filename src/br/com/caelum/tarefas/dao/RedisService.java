package br.com.caelum.tarefas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisService 
{
	@Autowired
    private RedisTemplate redisTemplate;
}