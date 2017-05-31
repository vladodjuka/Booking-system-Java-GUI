package code2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Rezervacija {

	private int apartmanID;
	private int gostID;
	private String datumOD;
	private String datumDO;
	private String statusID;
	
	private Connection connection;

	public Rezervacija(int apartmanID,int gostID, String datumOD, String datumDO,
			String statusID, Connection connection) {
		super();
		this.apartmanID = apartmanID;
		this.gostID = gostID;
		this.datumOD = datumOD;
		this.datumDO = datumDO;
		this.statusID = statusID;
		this.connection = connection;
	}
	
	public Rezervacija(Connection connection){
		this.connection = connection;
	}
	
	public DefaultTableModel fillTable() {
		String upit = "SELECT apartmanid,g.ime,g.prezime,datumod,datumdo,s.nazivstatusa FROM rezervacija_apartmana r,gost g,status_rezervacije s WHERE r.gostid = g.gostid and r.statusid = s.statusid";

		DefaultTableModel model = new DefaultTableModel();
		model.getDataVector().removeAllElements();
		String[] columns = { "APARTMAN ID" };
		String[] columns2 = { "IME" };
		String[] columns6 = { "PREZIME" };
		String[] columns3 = { "OD" };
		String[] columns4 = { "DO" };
		String[] columns5 = { "STATUS" };
		model.addColumn("APARTMAN ID", columns);
		model.addColumn("IME", columns2);
		model.addColumn("PREZIME", columns6);
		model.addColumn("OD", columns3);
		model.addColumn("DO", columns4);
		model.addColumn("STATUS", columns5);

		Statement s;

		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);

			while (rs.next()) {
				String[] rows = { rs.getInt("apartmanid") + "",
						rs.getString("g.ime"),rs.getString("g.prezime"), rs.getString("datumod"),
						rs.getString("datumdo"), rs.getString("s.nazivstatusa")};
				model.addRow(rows);
			}

		} catch (SQLException e) {
			return null;
		}

		return model;
	}
	
	public boolean rezervisi(){
		int statusID = findStatusID();
		String upit = "INSERT INTO rezervacija_apartmana VALUES("+ apartmanID +","+ gostID +",'" + datumOD + "','"+ datumDO +"',"+ statusID+");";
		//System.out.println(upit);
		Statement s;
		try {
			s = connection.createStatement();
			s.executeUpdate(upit);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean checkApartmanID(){
		String upit = "SELECT apartmanid FROM apartman";
		Statement s;
		int id = 0;
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				if (rs.getInt("apartmanid") == apartmanID) {
					return true;
				}
			}
			//connection.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public int findStatusID() {
		String upit = "SELECT statusid FROM status_rezervacije WHERE nazivstatusa = '" + statusID + "'";
		Statement s;
		int id = 0;
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(upit);
			while (rs.next()) {
				id = rs.getInt("statusid");
			}
			//connection.close();
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	
}
