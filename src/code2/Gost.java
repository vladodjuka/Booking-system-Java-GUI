package code2;

import java.sql.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Gost {

	private int id;
	private String jmbg;
	private String ime;
	private String prezime;
	private String datumRodjenja;
	private String pol;

	private Connection connection;

	public Gost(String jmbg, String ime, String prezime, String datumRodjenja,
			String pol, Connection connection) {
		super();
		this.jmbg = jmbg;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.pol = pol;
		this.connection = connection;
	}

	public Gost(int id, Connection connection) {
		this.id = id;
		this.connection = connection;
	}

	public Gost(Connection connection) {
		this.connection = connection;
	}
	
	public DefaultTableModel findLastname(String lastname) {
		String upit = "SELECT gostid,gostjmbg,ime,prezime,datumrodjenja,pol FROM gost WHERE prezime like '" + lastname + "%'";

		DefaultTableModel model = new DefaultTableModel();
		model.getDataVector().removeAllElements();
		String[] columns = { "ID" };
		String[] columns2 = { "JMBG" };
		String[] columns3 = { "IME" };
		String[] columns4 = { "PREZIME" };
		String[] columns5 = { "DATUM RODJENJA" };
		String[] columns6 = { "POL" };
		model.addColumn("ID", columns);
		model.addColumn("JMBG", columns2);
		model.addColumn("IME", columns3);
		model.addColumn("PREZIME", columns4);
		model.addColumn("DATUM RODJENJA", columns5);
		model.addColumn("POL", columns6);
		
		Statement s;

		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);

			while (rs.next()) {
				String[] rows = { rs.getInt("gostid") + "",
						rs.getString("gostjmbg"), rs.getString("ime"),
						rs.getString("prezime"), rs.getString("datumrodjenja"),
						rs.getString("pol") };
				model.addRow(rows);
			}

		} catch (SQLException e) {
			return null;
		}

		return model;
		
	}

	public DefaultTableModel fillTable() {
		String upit = "SELECT gostid,gostjmbg,ime,prezime,datumrodjenja,pol FROM gost";

		DefaultTableModel model = new DefaultTableModel();
		String[] columns = { "ID" };
		String[] columns2 = { "JMBG" };
		String[] columns3 = { "IME" };
		String[] columns4 = { "PREZIME" };
		String[] columns5 = { "DATUM RODJENJA" };
		String[] columns6 = { "POL" };
		model.addColumn("ID", columns);
		model.addColumn("JMBG", columns2);
		model.addColumn("IME", columns3);
		model.addColumn("PREZIME", columns4);
		model.addColumn("DATUM RODJENJA", columns5);
		model.addColumn("POL", columns6);

		Statement s;

		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);

			while (rs.next()) {
				String[] rows = { rs.getInt("gostid") + "",
						rs.getString("gostjmbg"), rs.getString("ime"),
						rs.getString("prezime"), rs.getString("datumrodjenja"),
						rs.getString("pol") };
				model.addRow(rows);
			}

		} catch (SQLException e) {
			return null;
		}

		return model;
	}

	public String pronadjiPodatkeOGostu() {
		String upit = "SELECT ime,prezime,gostjmbg FROM gost WHERE gostid="
				+ id;

		Statement s;
		String podaci = "";

		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				podaci = "Gost " + rs.getString("ime") + " "
						+ rs.getString("prezime") + " ("
						+ rs.getString("gostjmbg") + ")";
			}
			// connection.close();
			return podaci;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean izbrisiGosta() {
		String upit = "DELETE FROM gost WHERE gostid=" + id;

		Statement s;
		// System.out.println(upit);
		try {
			s = connection.createStatement();
			s.executeUpdate(upit);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean updateGost(int id) {
		String upit = "UPDATE gost SET ime='" + ime + "',prezime='" + prezime
				+ "',datumrodjenja='" + datumRodjenja + "',pol='" + pol
				+ "' WHERE gostid=" + id;

		Statement s;
		System.out.println(upit);
		try {
			s = connection.createStatement();
			s.executeUpdate(upit);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean insertGost() {
		int gostID = findMaxID() + 1;
		String upit = "INSERT INTO gost VALUES(" + gostID + ",'" + jmbg + "','"
				+ ime + "','" + prezime + "','" + datumRodjenja + "','" + pol
				+ "');";
		// System.out.println(upit);
		Statement s;
		try {
			s = connection.createStatement();
			s.executeUpdate(upit);
			return true;
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
			return false;
		}
	}

	public int findMaxID() {
		String upit = "SELECT max(gostid) as max FROM gost";
		Statement s;
		int id = 0;

		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				id = rs.getInt("max");
			}
			// connection.close();
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
}
