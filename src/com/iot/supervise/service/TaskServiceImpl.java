package com.iot.supervise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.common.utilities.TimeDateUtility;
import com.iot.device.domain.Device;
import com.iot.device.dto.DeviceDO;
import com.iot.exceptions.DaoCreateException;
import com.iot.exceptions.DaoFinderException;
import com.iot.supervise.dao.SuperviseDAO;
import com.iot.supervise.dao.TaskDAO;
import com.iot.supervise.domain.Task;
import com.iot.supervise.dto.TaskDO;

@Service
@Transactional(rollbackFor = { Exception.class })
@EnableJpaRepositories(basePackages = {"com.iot.supervise.dao"})
public class TaskServiceImpl implements TaskService {
	static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Autowired
	private SuperviseDAO superviseDAO;	
	@Autowired
	private TaskDAO taskDAO;
	
	@Override
	public Task startTask(Device device) throws DaoCreateException {
		Task task = null;
		try {
			task = new Task();
			task.setDeviceid(device.getId());
			task.setStarttime(TimeDateUtility.getCurrentTimestamp());
			task.setTaskstatus("1");					

			return (Task) this.taskDAO.save(task);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	@Override
	public Task stopTask(Device device) throws DaoCreateException {
		Task task = null;
		try {
			task=this.taskDAO.findOne(device.getId());
			task.setStoptime(TimeDateUtility.getCurrentTimestamp());
			task.setTaskstatus("0");					

			return (Task) this.taskDAO.save(task);
		} catch (Exception ex) {
			log.debug("Error creating new news post", ex);
			throw new DaoCreateException(ex.getMessage());
		}
	}

	@Override
	public TaskDO findAliveTask() throws DaoFinderException {
		TaskDO fh=null;
		try {
			Integer taskstatus=1;
			Task task = (Task) this.taskDAO.findOne(taskstatus);
			if (task != null) {
				fh = new TaskDO();
	
				fh.setId(task.getId());
				fh.setDeviceid(task.getDeviceid());
				fh.setStarttime(task.getStarttime());
	
			}
			return fh;
		} catch (Exception ex) {
			log.debug("Error retrieving device article: " , ex);
			throw new DaoFinderException(ex.getMessage());
		}
	}

}