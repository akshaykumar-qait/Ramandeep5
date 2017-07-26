package Tests.tatoc.advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlT {

	public static void main(String args[]) {
		try {
			MySqlT obj = new MySqlT();

			System.out.println(obj.getResult("id", "7", "credentials", 3));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	String getResult(String first_col, String id, String table_name, int get_cols) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://10.0.1.86/tatoc", "tatocuser", "tatoc01");

			Statement stmt = con.createStatement();
			
			System.out.println("select * from " + table_name + " where " + first_col + "=" + id);
			ResultSet rs = stmt.executeQuery("select * from " + table_name + " where " + first_col + "=" + id);

			String data = "";
			if (rs.next()) {
				for (int a = 1; a <= get_cols; a++) {
					if (data != "") {
						data = data + "," + rs.getString(a);
					} else {
						data = data + rs.getString(a);
					}
				}

				return data;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return "ops..something went wrong";
	}

}
