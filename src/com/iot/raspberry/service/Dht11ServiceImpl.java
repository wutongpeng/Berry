package com.iot.raspberry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.exceptions.DaoFinderException;
import com.iot.raspberry.dao.Dht11DAO;
import com.iot.usermgmt.service.UserServiceImpl;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.raspberry.dao"})
public class Dht11ServiceImpl implements Dht11Service {

static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private Dht11DAO dht11DAO;

	@Override
	public Integer getTem() throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}
}
