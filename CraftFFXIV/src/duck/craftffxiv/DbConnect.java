package duck.craftffxiv;

import java.io.File;
import java.io.FileReader;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DbConnect {
	private String host;
	private String port;
	private String url;
	private String user;
	private String passwd;
	private String db;
	
	String getUrl() {
		return url;
	}

	String getUser() {
		return user;
	}

	String getPasswd() {
		return passwd;
	}

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public DbConnect() {
		String filePath = new File("").getAbsolutePath();
		String fullFilePath = filePath.concat("/config.json");
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(fullFilePath));
			JSONObject json = (JSONObject)obj;
			host = (String)json.get("host");
			port = (String)json.get("port");
			user = (String)json.get("user");
			passwd = (String)json.get("password");
			db = (String)json.get("database");
		}
		catch(Exception e) {
			LOGGER.severe(e.getMessage());
		}
		
		url = "jdbc:mysql://" + host + ":" + port + "/" + db;
	}
	
	public static void main(String[] args) {
		DbConnect dbcon = new DbConnect();
		
		LOGGER.info("Connecting to the DB...");
		
		try (Connection connection = DriverManager.getConnection(dbcon.getUrl(), dbcon.getUser(), dbcon.getPasswd())) {
			LOGGER.info("DB is connected!");
		} catch (SQLException e) {
			LOGGER.severe("DB connection didn't work");
			LOGGER.severe(e.getMessage());
		}
	}
}
