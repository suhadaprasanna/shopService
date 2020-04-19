/**
 * 
 */
package gdc.shop.controllers.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import gdc.shop.datamanager.pojo.User;
import gdc.shop.dto.PersonDTO;
import gdc.shop.dto.UserDTO;
import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

/**
 * @author suhada
 *
 */
@Component
public class ResponseForUserGetList extends ResponseGeneratorImpl{

	private static final Logger logger = LoggerFactory.getLogger(ResponseForUserGetList.class);

	/* (non-Javadoc)
	 * @see gdc.shop.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer, java.util.HashMap)
	 */
	@Override
	public HashMap<String, Object> generate(DataTransfer dataTrans, HashMap<String, Object> res) {
		logger.debug("------>>Start generate<<------");
		try {
			// create inputs
			HashMap<String, Object> inputs = new HashMap<String, Object>();
			this.processInputs(dataTrans, inputs);
			
			// create outputs
			HashMap<String, Object> outputs = new HashMap<String, Object>();
			this.processOutputs(dataTrans, outputs);
			
			res.put(Key.INPUTS, inputs);
			res.put(Key.OUTPUTS, outputs);
		}catch (Exception e) {
			logger.error("Error while generating response "+e);
		}
		logger.debug("------>>End generate<<------");
		return res;
	}

	/**
	 * @param dataTrans
	 * @param outputs
	 */
	private void processOutputs(DataTransfer dataTrans, HashMap<String, Object> outputs) {
		logger.debug("------>>Start processOutputs<<------");
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<User> list = (List<User>)dataTrans.getOutput(Key.USER_LIST);
		if(list != null && list.size()>0) {
			List user_list = new ArrayList<>();
			for (User user : list) {
				HashMap<String, Object> _user = new HashMap<>();
				_user.put("id", user.getId());
				_user.put("person_id", user.getPerson());
				_user.put("status", user.getStatus());
				logger.debug("------>> person dto: "+user.getPersonDTO());
				if(user.getPersonDTO() != null) {
					PersonDTO person = user.getPersonDTO();
					HashMap<String, Object> _person = new HashMap<>();
					_person.put("id", person.getId());
					_person.put("first_name", person.getFirst_name());
					_person.put("last_name", person.getLast_name());
					_person.put("middle_name", person.getMiddle_name());
					_person.put("sur_name", person.getSur_name());
					_person.put("nic", person.getNic());
					_person.put("gender", person.getGender());
					_person.put("status", person.getStatus());
					_person.put("birth_day", person.getBirth_day() != null ? sdf.format(person.getBirth_day()) : "-");
					_user.put("person", _person);
				}
				user_list.add(_user);
			}
			outputs.put(Key.USER_LIST, user_list);
		}
		}catch (Exception e) {
			logger.error("------>> Error ",e);
		}
		logger.debug("------>>End processOutputs<<------");
	}

	/**
	 * @param dataTrans
	 * @param inputs
	 */
	private void processInputs(DataTransfer dataTrans, HashMap<String, Object> inputs) {
		logger.debug("------>>Start processInputs<<------");
		inputs.put("form", dataTrans.getInput("reqParam"));
		logger.debug("------>>End processInputs<<------");
	}

	/* (non-Javadoc)
	 * @see gdc.shop.controllers.response.ResponseGenerator#generate(gdc.utility.dataservice.DataTransfer)
	 */
	@Override
	public Object generate(DataTransfer dataTrans) {
		// TODO Auto-generated method stub
		return null;
	}
}
