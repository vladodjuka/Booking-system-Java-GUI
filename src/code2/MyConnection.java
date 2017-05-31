package code2;

import java.sql.*;

public class MyConnection {

	private String connectionString;
	private String password;
	private String user;
	private Connection connection;

	public MyConnection(String connectionString, String password, String user) {
		super();
		this.connectionString = connectionString;
		this.password = password;
		this.user = user;
		try {
			this.connection = DriverManager.getConnection(connectionString,
					user, password);
		} catch (SQLException e) {
			System.out.println("Greska u konekciji!");
		}

	}

	public String pronadjiPodatke(int i) {
		String upit = "SELECT gostid,gostjmbg,ime,prezime,datumrodjenja,pol FROM gost WHERE gostid="
				+ i;

		Statement s;
		String podaci = "";
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				podaci += rs.getString("gostid") + "/"
						+ rs.getString("gostjmbg") + "/" + rs.getString("ime")
						+ "/" + rs.getString("prezime") + "/"
						+ rs.getString("datumrodjenja") + "/"
						+ rs.getString("pol");
			}
			return podaci;
		} catch (SQLException e) {
			return null;
		}

	}

	public int findGostID(String jmbg) {
		String upit = "SELECT gostid,gostjmbg FROM gost";
		Statement s;
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				if (rs.getString("gostjmbg").equalsIgnoreCase(jmbg)) {
					return rs.getInt("gostid");
				}
			}
			return 0;
		} catch (SQLException e) {
			return 0;
		}
	}

	public String pronadjiStatus() {
		String upit = "SELECT nazivstatusa FROM status_rezervacije";
		Statement s;
		String status = "";
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				status += rs.getString("nazivstatusa") + "/";
			}
			return status;
		} catch (SQLException e) {
			return null;
		}
	}

	public String pronadjiVrstu() {
		String upit = "SELECT nazivvrste FROM vrsta_apartmana";
		Statement s;
		String vrste = "";
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);

			while (rs.next()) {
				vrste += rs.getString("nazivvrste") + "/";
			}
			return vrste;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				return false;
			}
		}
		return true;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
