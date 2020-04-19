/**
 * 
 */
package gdc.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suhada
 *
 */
@RestController
public class DefaultController {

	@RequestMapping(value= {""})
	public Object defaultRequest() {
		return "This is a GDC service (shop service)";
	}
}
