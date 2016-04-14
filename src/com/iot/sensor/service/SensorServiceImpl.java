package com.iot.sensor.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoDeleteException;
import com.iot.exceptions.DaoFinderException;
import com.iot.exceptions.DaoUpdateException;
import com.iot.sensor.dao.SensorDAO;
import com.iot.sensor.dao.SensorTypeDAO;
import com.iot.sensor.domain.Sensor;
import com.iot.sensor.dto.SensorDO;
import com.iot.usermgmt.service.UserServiceImpl;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.sensor.dao"})
public class SensorServiceImpl implements SensorService {

static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private SensorDAO sensorDAO;
	
	@Autowired
	private SensorTypeDAO sensorTypeDAO;

	@Override
	public Integer getTem() throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SensorDO> retrieveAllSensor(Pageable pgble, Integer deviceid) throws DaoFinderException {
		try {
			List<Sensor> page = null;
			
			page = this.sensorDAO.findByDeviceidOrderByPostdateDesc(deviceid);
			
			ArrayList<SensorDO> list = new ArrayList<SensorDO>();
			if ((page != null) ) {
				for (Sensor sensor : page) {
					SensorDO sd = new SensorDO();
					
					sd.setId(sensor.getId());
					sd.setSensorname(sensor.getSensorname());
					sd.setSensortype(sensor.getSensortype());
					sd.setSensorparameter(sensor.getSensorparameter());
					sd.setSensorparameter2(sensor.getSensorparameter2());
					sd.setDeviceid(sensor.getDeviceid());
					
					list.add(sd);
				}
			}
			return list;
		} catch (Exception ex) {
			log.debug("Error retrieving notice for user", ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}
	
	@Override
	public Sensor createSensor(SensorDO sensorForm) throws DaoCreateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SensorDO> searchSensorContent(String searchTerm, String receiptState, Pageable pgble)
			throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorDO getSensorDetailById(Integer sensorId, String username) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor updateSensor(SensorDO sensorForm) throws DaoUpdateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSensor(Integer sensorId) throws DaoDeleteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getNumSearchSensor(String name, String receiptsign) throws DaoFinderException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
