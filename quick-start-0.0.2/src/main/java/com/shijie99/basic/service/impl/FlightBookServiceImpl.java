package com.shijie99.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shijie99.basic.dao.FlightBookMapper;
import com.shijie99.basic.pojo.FlightBookExample;
import com.shijie99.basic.service.FlightBookService;

@Service("flightBookService")
public class FlightBookServiceImpl implements FlightBookService {
	@Autowired
	FlightBookMapper flightBookMapper;

	@Override
	public int countByExample(FlightBookExample flightBookExample) {
		return flightBookMapper.countByExample(flightBookExample);
	}
}
