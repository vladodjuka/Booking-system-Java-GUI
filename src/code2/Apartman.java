package code2;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class Apartman {

	private int brojSoba;
	private int brojSpavacihSoba;
	private int brojKupatila;
	private String vrstaID;
	
	private Connection connection;

	public Apartman(int brojSoba, int brojSpavacihSoba, int brojKupatila,
			String vrstaID,Connection connection) {
		super();
		this.brojSoba = brojSoba;
		this.brojSpavacihSoba = brojSpavacihSoba;
		this.brojKupatila = brojKupatila;
		this.vrstaID = vrstaID;
		this.connection = connection;
	}
	
	public Apartman(Connection connection){
		this.connection = connection;
	}
	
	public int findVrstaID(){
		String upit = "SELECT vrstaid FROM vrsta_apartmana WHERE nazivvrste = '" + vrstaID + "'";
		Statement s;
		int id = 0;
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				id = rs.getInt("vrstaid");
			}
			//connection.close();
			return id;
		} catch (SQLException e) {
			return 0;
		}
		
	}
	
	public DefaultTableModel fillTable() {
		String upit = "SELECT apartmanid,brojsoba,brojspavacihsoba,brojkupatila,r.nazivvrste FROM apartman a,vrsta_apartmana r WHERE a.vrstaid = r.vrstaid";

		DefaultTableModel model = new DefaultTableModel();
		model.getDataVector().removeAllElements();
		String[] columns = { "ID" };
		String[] columns2 = { "SOBE" };
		String[] columns3 = { "SPAVACE" };
		String[] columns4 = { "KUPATILA" };
		String[] columns5 = { "NAZIV VRSTE" };
		model.addColumn("ID", columns);
		model.addColumn("SOBE", columns2);
		model.addColumn("SPAVACE", columns3);
		model.addColumn("KUPATILA", columns4);
		model.addColumn("NAZIV VRSTE", columns5);

		Statement s;
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);

			while (rs.next()) {
				String[] rows = { rs.getInt("apartmanid") + "",
						rs.getInt("brojsoba")+"", rs.getInt("brojspavacihsoba")+"",
						rs.getInt("brojkupatila")+"", rs.getString("nazivvrste")};
				model.addRow(rows);
			}

		} catch (SQLException e) {
			return null;
		}

		return model;
	}
	
	public int findMaxID() {
		String upit = "SELECT max(apartmanid) as max FROM apartman";
		Statement s;
		int id = 0;
		
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				id = rs.getInt("max");
			}
			//connection.close();
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public boolean insertApartman(){
		int id = findMaxID()+1;
		int vrstaid = findVrstaID();
		String upit = "INSERT INTO apartman VALUES(" + id +"," + brojSoba + "," + brojSpavacihSoba + "," + brojKupatila + "," + vrstaid + ");";
		//System.out.println(upit);
		Statement s;
		try {
			s = connection.createStatement();
			s.executeUpdate(upit);
			
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	
}
