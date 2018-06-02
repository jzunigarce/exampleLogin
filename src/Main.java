import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uabcs.jzuniga.controller.LoginController;
import com.uabcs.jzuniga.db.DBConnection;
import com.uabcs.jzuniga.view.VLogin;

public class Main {

	public static void main(String[] args) {
		LoginController login = new LoginController();
		login.init();
	}

}
