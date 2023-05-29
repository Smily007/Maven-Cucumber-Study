package utility;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;

public class EnvironmentHelperUtil {
	
	public Map<String, String> getEnvironments(){
		
		String envProp = "stageEnv";//System.getProperty("envProp"); //"stageEnv";
		Map<String, String> credentials = new HashedMap<>();
		InputStream input = EnvironmentHelperUtil.class.getClassLoader().getResourceAsStream("configs/" + envProp);
		
		if (envProp != null) {
			try {
				input = EnvironmentHelperUtil.class.getClassLoader().getResourceAsStream("configs/" + envProp);
			} catch (Exception e) {
				throw e;
			}
		}
		
		Properties prop = new Properties();
		try {
			if (input != null) {
			
				prop.load(input);
				credentials.put("portal.url", prop.getProperty("portal.url"));
			}
		}
			catch (Exception e) {
				 e.printStackTrace();
			}
		return credentials;
		
		
	}

}
