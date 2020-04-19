/**
 * 
 */
package gdc.shop.controllers;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gdc.shop.datamanager.pojo.LabelPack;
import gdc.shop.dto.PersonDTO;
import gdc.utility.apiconnector.PersonAPIServiceConnector;

/**
 * @author suhada
 *
 */
@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private PersonAPIServiceConnector conn;
	
	@GetMapping(value="/test")
	public Object test() {
		logger.debug("------>> start test <<------");
		LabelPack l = new LabelPack();
		l.setSIN("abc");
		
		Field f=null;
		Object s = null;
		try {
			f = LabelPack.class.getDeclaredField("SIN");
			logger.debug("----- f: "+f);
			f.setAccessible(true);
			logger.debug("----- dec class: "+f.getDeclaringClass());
			s = f.get(l);
			logger.debug("----- s: "+s);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("------>> end test <<------");
		return s;
	}
}
