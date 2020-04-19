/**
 * 
 */
package gdc.shop.datamanager.access;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.shop.datamanager.dao.EmployeeRoleRepository;
import gdc.shop.datamanager.pojo.EmployeeRole;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;
import gdc.utility.dataservice.Status;

/**
 * @author suhada
 *
 */
@Repository("employeeRoleAccess")
@Transactional
public class EmployeeRoleAccess {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeRoleAccess.class);
	
	@Autowired
	private EmployeeRoleRepository employeeRoleRepository;
	
	public EmployeeRole getById(int id) {
		logger.debug("------>> start getById <<------");
		logger.debug("------>> id: "+id);
		EmployeeRole role = null;
		try {
			Optional<EmployeeRole> op = this.employeeRoleRepository.findById(id);
			if(op.isPresent()) {
				role = op.get();
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> user: "+role);
		logger.debug("------>> end getById <<------");
		return role;
	}

	public DataTransfer addRole(DataTransfer dataTrans) {
		logger.debug("------>>start save<<------");
		try {
			EmployeeRole employeeRole = (EmployeeRole)dataTrans.getInput(Key.POJO_EMPLOYEE_ROLE);
			this.employeeRoleRepository.save(employeeRole);
			if(employeeRole.getId()>0) {
				dataTrans.setStatus(Status.SUCCESS);
				dataTrans.addOutput(Key.POJO_EMPLOYEE_ROLE, employeeRole);
			}else {
				dataTrans.setStatus(Status.FAIL);
			}
		} catch (Exception e) {
			logger.error("------>> Error ",e);
			dataTrans.setStatus(Status.ERROR);
			dataTrans.addOutput(Key.ERROR, "error in saving employee role");
		}
		logger.debug("------>>end save<<------");
		return dataTrans;
	}

	/**
	 * @return
	 */
	public List<EmployeeRole> getAllRoles() {
		logger.debug("------>>start getAllRoles<<------");
		List<EmployeeRole> list = null;
		try {
			list = this.employeeRoleRepository.findAll();
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> EmployeeRole:"+list);
		logger.debug("------>>end getAllRoles<<------");
		return list;
	}

	/**
	 * @param code
	 * @return
	 */
	public EmployeeRole getByCode(String code) {
		logger.debug("------>>start getByCode<<------");
		EmployeeRole role = null;
		try {
			role = this.employeeRoleRepository.findByCode(code);
		} catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>> EmployeeRole:"+role);
		logger.debug("------>>end getByCode<<------");
		return role;
	}
	
}
