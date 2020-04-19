/**
 * 
 */
package gdc.utility.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author suhada
 *
 */
@Component
public class PropertyUtil {

	@Autowired
    private Environment env;

    public String getProperty(String key) {
        return env.getProperty(key);
    }
}
