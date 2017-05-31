package gui;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JRadioButton;

import code2.Apartman;
import code2.Gost;
import code2.MyConnection;
import code2.Rezervacija;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnRegistracija;
	private JLabel lblDodajApartman;
	private JLabel lblVrstaApartmana;
	private JLabel lblBrojSoba;
	private JLabel lblBrojSpavacihSoba;
	private JLabel lblBrojKupatila;
	private JTextField textBrojSoba;
	private JTextField textSpavace;
	private JTextField textKupatila;
	private JComboBox comboBoxVrsta;
	private JButton btnDodaj;
	private JButton btnPrikaziGoste;
	private JButton btnApartmani;
	private JButton btnIznajmljivanja;
	private JPanel panelPrikaz;
	private JLabel lblNewLabel;
	private JLabel lblIznajmiApartman;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblDatumDo;
	private JLabel lblStatus;
	private JTextField textJMBG;
	private JComboBox comboBoxStatus;
	private JButton btnRezervisi;
	private JPanel panel;
	private JLabel lblRegistracijaGosta;
	private JLabel lblNewLabel_3;
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblDatumRodjenja;
	private JLabel lblPol;
	private JTextField textRegText;
	private JTextField textIme;
	private JTextField textPrezime;
	private JRadioButton rdbtnMusko;
	private JRadioButton rdbtnZ;
	private JButton btnRegistruj;
	private JTextField textPretraga;
	private JButton btnPretraga;
	private JDateChooser dateOD;
	private JDateChooser dateDO;
	private JDateChooser dateRodjenje;
	private JLabel lblPretragaGostijuPo;
	private JLabel lblIzmjenaGostaGost;
	private JTextField textIzmjena;
	private JButton btnIzmjena;
	private JButton btnBrisanje;
	private JLabel labelIzmjena;
	private Connection connection;
	private ButtonGroup genderGroup;
	private MyConnection connectClass;
	private JFrame frame;
	private JLabel lblApartmanId;
	private JTextField textApartmanID;
	private String provjera;
	private int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Iznajmljivanje apartmana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		connectClass = new MyConnection(
				"jdbc:mysql://localhost:3306/javaproject", "", "root");
		connection = connectClass.getConnection();
		contentPane.setLayout(null);
		contentPane.add(getTable());
		contentPane.add(getBtnRegistracija());
		contentPane.add(getLblDodajApartman());
		contentPane.add(getLblVrstaApartmana());
		contentPane.add(getLblBrojSoba());
		contentPane.add(getLblBrojSpavacihSoba());
		contentPane.add(getLblBrojKupatila());
		contentPane.add(getTextBrojSoba());
		contentPane.add(getTextSpavace());
		contentPane.add(getTextKupatila());
		contentPane.add(getComboBoxVrsta());
		contentPane.add(getBtnDodaj());
		contentPane.add(getPanelPrikaz());
		contentPane.add(getLblIznajmiApartman());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblDatumDo());
		contentPane.add(getLblStatus());
		contentPane.add(getTextJMBG());
		contentPane.add(getComboBox_1());
		contentPane.add(getBtnRezervisi());
		contentPane.add(getPanel());
		contentPane.add(getTextField_3());
		contentPane.add(getBtnPretraga());
		contentPane.add(getDateChooser());
		contentPane.add(getDateChooser2());
		contentPane.add(getLblPretragaGostijuPo());
		contentPane.add(getLblIzmjenaGostaGost());
		contentPane.add(getTextIzmjena());
		contentPane.add(getBtnIzmjena());
		contentPane.add(getBtnBrisanje());
		genderGroup = new ButtonGroup();
		genderGroup.add(getRdbtnMusko());
		genderGroup.add(getRdbtnZ());
		contentPane.add(getLblApartmanId());
		contentPane.add(getTextApartmanID());
		frame = this;
	}

	private JDateChooser getDateChooser() {
		if (dateOD == null) {
			dateOD = new JDateChooser();
			dateOD.setDateFormatString("yyyy-MM-dd");
			dateOD.setBounds(586, 377, 102, 20);
		}
		return dateOD;
	}

	private JDateChooser getDateChooser2() {
		if (dateDO == null) {
			dateDO = new JDateChooser();
			dateDO.setDateFormatString("yyyy-MM-dd");
			dateDO.setBounds(586, 404, 102, 20);
		}
		return dateDO;
	}

	private JDateChooser getDateChooserRodjenja() {
		if (dateRodjenje == null) {
			dateRodjenje = new JDateChooser();
			dateRodjenje.setDateFormatString("yyyy-MM-dd");
			dateRodjenje.setBounds(108, 129, 113, 20);
			;
		}
		return dateRodjenje;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBounds(10, 81, 517, 195);
		}
		return table;
	}

	private JButton getBtnRegistracija() {
		if (btnRegistracija == null) {
			btnRegistracija = new JButton("Registracija gosta");
			btnRegistracija.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					lblRegistracijaGosta.setVisible(true);
					panel.setVisible(true);
					provjera = "registracija";

				}
			});
			btnRegistracija.setBounds(534, 253, 154, 23);
		}
		return btnRegistracija;
	}

	private JLabel getLblDodajApartman() {
		if (lblDodajApartman == null) {
			lblDodajApartman = new JLabel("Dodaj apartman:");
			lblDodajApartman.setFont(new Font("Tahoma",
					Font.BOLD | Font.ITALIC, 13));
			lblDodajApartman.setBounds(41, 287, 125, 14);
		}
		return lblDodajApartman;
	}

	private JLabel getLblVrstaApartmana() {
		if (lblVrstaApartmana == null) {
			lblVrstaApartmana = new JLabel("Vrsta apartmana:");
			lblVrstaApartmana.setBounds(10, 410, 109, 14);
		}
		return lblVrstaApartmana;
	}

	private JLabel getLblBrojSoba() {
		if (lblBrojSoba == null) {
			lblBrojSoba = new JLabel("Broj soba:");
			lblBrojSoba.setBounds(10, 331, 89, 14);
		}
		return lblBrojSoba;
	}

	private JLabel getLblBrojSpavacihSoba() {
		if (lblBrojSpavacihSoba == null) {
			lblBrojSpavacihSoba = new JLabel("Broj spavacih soba:");
			lblBrojSpavacihSoba.setBounds(10, 356, 119, 14);
		}
		return lblBrojSpavacihSoba;
	}

	private JLabel getLblBrojKupatila() {
		if (lblBrojKupatila == null) {
			lblBrojKupatila = new JLabel("Broj kupatila:");
			lblBrojKupatila.setBounds(10, 381, 109, 14);
		}
		return lblBrojKupatila;
	}

	private JTextField getTextBrojSoba() {
		if (textBrojSoba == null) {
			textBrojSoba = new JTextField();
			textBrojSoba.setBounds(129, 328, 100, 20);
			textBrojSoba.setColumns(10);
		}
		return textBrojSoba;
	}

	private JTextField getTextSpavace() {
		if (textSpavace == null) {
			textSpavace = new JTextField();
			textSpavace.setBounds(129, 353, 100, 20);
			textSpavace.setColumns(10);
		}
		return textSpavace;
	}

	private JTextField getTextKupatila() {
		if (textKupatila == null) {
			textKupatila = new JTextField();
			textKupatila.setBounds(129, 378, 100, 20);
			textKupatila.setColumns(10);
		}
		return textKupatila;
	}

	private JComboBox getComboBoxVrsta() {
		if (comboBoxVrsta == null) {
			comboBoxVrsta = new JComboBox();
			comboBoxVrsta.setBounds(129, 407, 100, 20);
			String[] vrste = connectClass.pronadjiVrstu().split("/");
			for (int i = 0; i < vrste.length; i++) {
				comboBoxVrsta.addItem((String) vrste[i]);
			}
		}
		return comboBoxVrsta;
	}

	private String validacijaApartaman() {
		int brojSoba = 0;
		try {
			brojSoba = Integer.parseInt(textBrojSoba.getText());

			if (brojSoba <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			return "Molimo vas unesite validan broj soba";
		}

		int brojKupatila = 0;
		try {
			brojKupatila = Integer.parseInt(textKupatila.getText());

			if (brojKupatila <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			return "Molimo vas unesite validan broj kupatila";
		}
		int brojSpavacih = 0;
		try {
			brojSpavacih = Integer.parseInt(textSpavace.getText());

			if (brojSpavacih <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			return "Molimo vas unesite validan broj spavacih soba";
		}

		return null;
	}

	private JButton getBtnDodaj() {
		if (btnDodaj == null) {
			btnDodaj = new JButton("Dodaj");
			btnDodaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String message = validacijaApartaman();

					if (message != null) {
						JOptionPane.showMessageDialog(frame, message,
								"Upozorenje", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						int brojKupatila = Integer.parseInt(textKupatila
								.getText());
						int brojSoba = Integer.parseInt(textBrojSoba.getText());
						int brojSpavacih = Integer.parseInt(textSpavace
								.getText());

						String apartman = (String) comboBoxVrsta
								.getSelectedItem();

						Apartman a = new Apartman(brojSoba, brojSpavacih,
								brojKupatila, apartman, connection);
						if (a.insertApartman()) {
							JOptionPane.showMessageDialog(frame, "Uneseno",
									"Obavjestenje", JOptionPane.OK_OPTION);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Unos nije validan", "Upozorenje",
									JOptionPane.ERROR_MESSAGE);
						}

						textKupatila.setText("");
						textBrojSoba.setText("");
						textSpavace.setText("");
					}

				}
			});
			btnDodaj.setBounds(61, 465, 89, 23);
		}
		return btnDodaj;
	}

	private JButton getBtnPrikaziGoste() {
		if (btnPrikaziGoste == null) {
			btnPrikaziGoste = new JButton("Gosti");
			btnPrikaziGoste.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Gost g = new Gost(connectClass.getConnection());
					DefaultTableModel model = g.fillTable();
					if (model==null) {
						JOptionPane.showMessageDialog(frame,
								"Nema podataka u tabeli", "Upozorenje",
								JOptionPane.ERROR_MESSAGE);
						return;
					}else{
					table.setModel(model);
					}
				}
			});
			btnPrikaziGoste.setBounds(10, 30, 125, 23);
		}
		return btnPrikaziGoste;
	}

	private JButton getBtnApartmani() {
		if (btnApartmani == null) {
			btnApartmani = new JButton("Apartmani");
			btnApartmani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Apartman a = new Apartman(connectClass.getConnection());
					DefaultTableModel model = a.fillTable();
					
					if (model==null) {
						JOptionPane.showMessageDialog(frame,
								"Nema podataka u tabeli", "Upozorenje",
								JOptionPane.ERROR_MESSAGE);
						return;
					}else{
					table.setModel(model);
					}
				}
			});
			btnApartmani.setBounds(166, 30, 117, 23);
		}
		return btnApartmani;
	}

	private JButton getBtnIznajmljivanja() {
		if (btnIznajmljivanja == null) {
			btnIznajmljivanja = new JButton("Iznajmljivanja");
			btnIznajmljivanja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Rezervacija a = new Rezervacija(connectClass.getConnection());
					DefaultTableModel model = a.fillTable();
					if (model==null) {
						JOptionPane.showMessageDialog(frame,
								"Nema podataka u tabeli", "Upozorenje",
								JOptionPane.ERROR_MESSAGE);
						return;
					}else{
					table.setModel(model);
					}
					
				}
			});
			btnIznajmljivanja.setBounds(314, 30, 125, 23);
		}
		return btnIznajmljivanja;
	}

	private JPanel getPanelPrikaz() {
		if (panelPrikaz == null) {
			panelPrikaz = new JPanel();
			panelPrikaz.setBackground(Color.LIGHT_GRAY);
			panelPrikaz.setBounds(10, 11, 449, 59);
			panelPrikaz.setLayout(null);
			panelPrikaz.add(getBtnPrikaziGoste());
			panelPrikaz.add(getBtnApartmani());
			panelPrikaz.add(getBtnIznajmljivanja());
			panelPrikaz.add(getLblNewLabel());
		}
		return panelPrikaz;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Prikaz u tabeli:");
			lblNewLabel
					.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			lblNewLabel.setBounds(10, 5, 139, 14);
		}
		return lblNewLabel;
	}

	private JLabel getLblIznajmiApartman() {
		if (lblIznajmiApartman == null) {
			lblIznajmiApartman = new JLabel("Rezervisi apartman:");
			lblIznajmiApartman.setFont(new Font("Tahoma", Font.BOLD
					| Font.ITALIC, 13));
			lblIznajmiApartman.setBounds(539, 287, 142, 14);
		}
		return lblIznajmiApartman;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("JMBG gosta:");
			lblNewLabel_1.setBounds(502, 354, 74, 14);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Datum od:");
			lblNewLabel_2.setBounds(502, 380, 74, 14);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblDatumDo() {
		if (lblDatumDo == null) {
			lblDatumDo = new JLabel("Datum do:");
			lblDatumDo.setBounds(502, 407, 74, 14);
		}
		return lblDatumDo;
	}

	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("Status:");
			lblStatus.setBounds(502, 437, 57, 14);
		}
		return lblStatus;
	}

	private JTextField getTextJMBG() {
		if (textJMBG == null) {
			textJMBG = new JTextField();
			textJMBG.setBounds(586, 349, 101, 20);
			textJMBG.setColumns(10);
		}
		return textJMBG;
	}

	private JComboBox getComboBox_1() {
		if (comboBoxStatus == null) {
			comboBoxStatus = new JComboBox();
			comboBoxStatus.setBounds(586, 434, 101, 20);
			String[] status = connectClass.pronadjiStatus().split("/");
			for (int i = 0; i < status.length; i++) {
				comboBoxStatus.addItem(status[i]);
			}
		}
		return comboBoxStatus;
	}

	private String validacijaRezervacije() {
		try {
			int broj = Integer.parseInt(textApartmanID.getText());
		} catch (NumberFormatException ex) {
			return "Molimo vas unesite validan ID apartmana";
		}
		if (textJMBG.getText().length() < 13
				|| textJMBG.getText().length() > 13) {
			return "Molimo vas unesite JMBG koji ima 13 karaktera";
		}

		String dateFormat = ((JTextField) dateOD.getDateEditor()
				.getUiComponent()).getText();
		if (dateFormat.equals("")) {
			return "Molimo vas unesite validan datum";
		}
		String dateFormat2 = ((JTextField) dateDO.getDateEditor()
				.getUiComponent()).getText();
		if (dateFormat2.equals("")) {
			return "Molimo vas unesite validan datum";
		}

		return null;
	}

	private JButton getBtnRezervisi() {
		if (btnRezervisi == null) {
			btnRezervisi = new JButton("Rezervisi");
			btnRezervisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String message = validacijaRezervacije();

					if (message != null) {
						JOptionPane.showMessageDialog(frame, message,
								"Upozorenje", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						int apartamanID = Integer.parseInt(textApartmanID
								.getText());
						int gostID = connectClass.findGostID(textJMBG.getText());
						if (gostID == 0) {
							JOptionPane.showMessageDialog(frame,
									"Uneseni gost ne postoji", "Upozorenje",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
						String datumOD = ((JTextField) dateOD.getDateEditor()
								.getUiComponent()).getText();
						String datumDO = ((JTextField) dateDO.getDateEditor()
								.getUiComponent()).getText();
						String status = (String) comboBoxStatus
								.getSelectedItem();

						Rezervacija r = new Rezervacija(apartamanID, gostID,
								datumOD, datumDO, status, connection);

						if (!r.checkApartmanID()) {
							JOptionPane.showMessageDialog(frame,
									"Uneseni apartman ne postoji",
									"Upozorenje", JOptionPane.WARNING_MESSAGE);
							return;
						}

						if (r.rezervisi()) {
							JOptionPane.showMessageDialog(frame, "Rezervisano",
									"Upozorenje", JOptionPane.OK_OPTION);
						} else {
							JOptionPane.showMessageDialog(frame,
									"Rezervacija nije uspjela", "Upozorenje",
									JOptionPane.ERROR_MESSAGE);
						}

						try {
							textApartmanID.setText("");
							textJMBG.setText("");
							dateDO.setCalendar(null);
							dateOD.setCalendar(null);
						} catch (Exception e) {
							System.out.println("Greska");
						}

					}

				}
			});
			btnRezervisi.setBounds(552, 465, 89, 23);
		}
		return btnRezervisi;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(239, 280, 247, 221);
			panel.setLayout(null);
			panel.add(getLblNewLabel_3());
			panel.add(getLblRegistracijaGosta());
			panel.add(getLblIme());
			panel.add(getLblPrezime());
			panel.add(getLblDatumRodjenja());
			panel.add(getLblPol());
			panel.add(getTextField_1());
			panel.add(getTextField_2());
			panel.add(getTextField_1_1());
			panel.add(getRdbtnMusko());
			panel.add(getRdbtnZ());
			panel.add(getBtnRegistruj());
			panel.add(getDateChooserRodjenja());
			panel.add(getLabelIzmjena());
			panel.setVisible(false);
		}
		return panel;
	}

	private JLabel getLblRegistracijaGosta() {
		if (lblRegistracijaGosta == null) {
			lblRegistracijaGosta = new JLabel("Registracija gosta:");
			lblRegistracijaGosta.setBounds(75, 9, 123, 16);
			lblRegistracijaGosta.setFont(new Font("Tahoma", Font.BOLD
					| Font.ITALIC, 13));
			lblRegistracijaGosta.setVisible(false);
		}
		return lblRegistracijaGosta;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("JMBG:");
			lblNewLabel_3.setBounds(10, 49, 46, 14);
		}
		return lblNewLabel_3;
	}

	private JLabel getLblIme() {
		if (lblIme == null) {
			lblIme = new JLabel("Ime:");
			lblIme.setBounds(10, 75, 46, 14);
		}
		return lblIme;
	}

	private JLabel getLblPrezime() {
		if (lblPrezime == null) {
			lblPrezime = new JLabel("Prezime:");
			lblPrezime.setBounds(10, 103, 46, 14);
		}
		return lblPrezime;
	}

	private JLabel getLblDatumRodjenja() {
		if (lblDatumRodjenja == null) {
			lblDatumRodjenja = new JLabel("Datum rodjenja:");
			lblDatumRodjenja.setBounds(10, 132, 100, 14);
		}
		return lblDatumRodjenja;
	}

	private JLabel getLblPol() {
		if (lblPol == null) {
			lblPol = new JLabel("Pol:");
			lblPol.setBounds(10, 158, 46, 14);
		}
		return lblPol;
	}

	private JTextField getTextField_1() {
		if (textRegText == null) {
			textRegText = new JTextField();
			textRegText.setBounds(108, 46, 113, 20);
			textRegText.setColumns(10);
		}
		return textRegText;
	}

	private JTextField getTextField_2() {
		if (textIme == null) {
			textIme = new JTextField();
			textIme.setBounds(108, 73, 113, 20);
			textIme.setColumns(10);
		}
		return textIme;
	}

	private JTextField getTextField_1_1() {
		if (textPrezime == null) {
			textPrezime = new JTextField();
			textPrezime.setBounds(108, 100, 113, 20);
			textPrezime.setColumns(10);
		}
		return textPrezime;
	}

	private JRadioButton getRdbtnMusko() {
		if (rdbtnMusko == null) {
			rdbtnMusko = new JRadioButton("M");
			rdbtnMusko.setBackground(Color.LIGHT_GRAY);
			rdbtnMusko.setBounds(108, 153, 46, 23);
		}
		return rdbtnMusko;
	}

	private JRadioButton getRdbtnZ() {
		if (rdbtnZ == null) {
			rdbtnZ = new JRadioButton("Z");
			rdbtnZ.setBackground(Color.LIGHT_GRAY);
			rdbtnZ.setBounds(175, 153, 46, 23);
		}
		return rdbtnZ;
	}

	private String validacijaRegistracije() {
		if (textRegText.getText().equals("")) {
			int i = 0;
			try {
				i = Integer.parseInt(textRegText.getText());

				if (textRegText.getText().length() < 13
						|| textRegText.getText().length() > 13) {
					throw new NumberFormatException();
				}

			} catch (NumberFormatException e) {
				return "Molimo vas unesite validan JMBG";
			}
		}

		if (textIme.getText().equals("")) {
			return "Molimo vas unesite ime";
		}

		if (textPrezime.getText().equals("")) {
			return "Molimo vas unesite prezime";
		}
		String dateFormat = ((JTextField) dateRodjenje.getDateEditor()
				.getUiComponent()).getText();
		if (dateFormat.equals("")) {
			return "Molimo vas unesite validan datum";
		}

		if (!rdbtnMusko.isSelected() && !rdbtnZ.isSelected()) {
			return "Odaberite pol";
		}

		return null;
	}

	private JButton getBtnRegistruj() {
		if (btnRegistruj == null) {
			btnRegistruj = new JButton("Registruj");
			btnRegistruj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					lblRegistracijaGosta.setVisible(false);
					labelIzmjena.setVisible(false);
					panel.setVisible(false);

					String message = validacijaRegistracije();

					if (message != null) {
						JOptionPane.showMessageDialog(frame, message,
								"Upozorenje", JOptionPane.ERROR_MESSAGE);
						panel.setVisible(true);
						return;
					} else {
						if (provjera.equalsIgnoreCase("registracija")) {

							String jmbg = textRegText.getText();
							String ime = textIme.getText();
							String prezime = textPrezime.getText();
							String date = ((JTextField) dateRodjenje
									.getDateEditor().getUiComponent())
									.getText();
							String pol = "";
							if (rdbtnMusko.isSelected()) {
								pol = "m";
							} else {
								pol = "z";
							}

							Gost g = new Gost(jmbg, ime, prezime, date, pol,
									connectClass.getConnection());

							if (g.insertGost()) {
								JOptionPane.showMessageDialog(frame,
										"Gost je registrovan", "Obavjestenje",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(frame,
										"Rezervacija nije uspjela", "Greska",
										JOptionPane.ERROR_MESSAGE);
							}

							textRegText.setText("");
							textIme.setText("");
							textPrezime.setText("");
							dateRodjenje.setCalendar(null);
							rdbtnMusko.setSelected(false);
							rdbtnZ.setSelected(false);
							panel.setVisible(false);

						} else {

							String jmbg = textRegText.getText();
							String ime = textIme.getText();
							String prezime = textPrezime.getText();
							String date = ((JTextField) dateRodjenje
									.getDateEditor().getUiComponent())
									.getText();
							String pol = "";
							if (rdbtnMusko.isSelected()) {
								pol = "m";
							} else {
								pol = "z";
							}

							Gost g = new Gost(jmbg, ime, prezime, date, pol,
									connectClass.getConnection());

							if (g.updateGost(id)) {
								JOptionPane.showMessageDialog(frame,
										"Gost je izmijenjen", "Obavjestenje",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(frame,
										"Izmjena nije uspjela", "Greska",
										JOptionPane.ERROR_MESSAGE);
							}

							btnIzmjena.setEnabled(true);

							textRegText.setText("");
							textIme.setText("");
							textPrezime.setText("");
							dateRodjenje.setCalendar(null);
							rdbtnMusko.setSelected(false);
							rdbtnZ.setSelected(false);
							panel.setVisible(false);
						}

					}

				}
			});
			btnRegistruj.setBounds(79, 187, 89, 23);
		}
		return btnRegistruj;
	}

	private JTextField getTextField_3() {
		if (textPretraga == null) {
			textPretraga = new JTextField();
			textPretraga.setBounds(537, 64, 136, 20);
			textPretraga.setColumns(10);
		}
		return textPretraga;
	}
	
	private String validacijaPretrage(){
		if (textPretraga.getText().equals("")) {
			return "Molimo vas unesite prezime za pretragu";
		}
		return null;
	}

	private JButton getBtnPretraga() {
		if (btnPretraga == null) {
			btnPretraga = new JButton("Pretraga gostiju");
			btnPretraga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String message = validacijaPretrage();
					
					if (message != null) {
						JOptionPane.showMessageDialog(frame,
								message, "Greska",
								JOptionPane.ERROR_MESSAGE);
						return;
					}else {
						String prezime = textPretraga.getText();
						
						Gost g = new Gost(connectClass.getConnection());
						DefaultTableModel model = g.findLastname(prezime);
						if (model==null) {
							JOptionPane.showMessageDialog(frame,
									"Nema gosta sa izabranim prezimenom", "Upozorenje",
									JOptionPane.ERROR_MESSAGE);
							return;
						}else{
						table.setModel(model);
						}
					}
					
				}
			});
			btnPretraga.setBounds(537, 95, 136, 23);
		}
		return btnPretraga;
	}

	private JLabel getLblPretragaGostijuPo() {
		if (lblPretragaGostijuPo == null) {
			lblPretragaGostijuPo = new JLabel("Pretraga po prezimenu:");
			lblPretragaGostijuPo.setFont(new Font("Tahoma", Font.BOLD
					| Font.ITALIC, 13));
			lblPretragaGostijuPo.setBounds(507, 39, 174, 14);
		}
		return lblPretragaGostijuPo;
	}

	private JLabel getLblIzmjenaGostaGost() {
		if (lblIzmjenaGostaGost == null) {
			lblIzmjenaGostaGost = new JLabel("Izmjena - gost ID");
			lblIzmjenaGostaGost.setFont(new Font("Tahoma", Font.BOLD
					| Font.ITALIC, 13));
			lblIzmjenaGostaGost.setBounds(537, 129, 144, 14);
		}
		return lblIzmjenaGostaGost;
	}

	private JTextField getTextIzmjena() {
		if (textIzmjena == null) {
			textIzmjena = new JTextField();
			textIzmjena.setBounds(537, 154, 136, 20);
			textIzmjena.setColumns(10);
		}
		return textIzmjena;
	}

	private String validacijaGosta() {
		int id = 0;
		try {
			id = Integer.parseInt(textIzmjena.getText());

			if (id <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			return "Molimo vas unesite validan ID gosta";
		}
		return null;
	}

	private JButton getBtnIzmjena() {
		if (btnIzmjena == null) {
			btnIzmjena = new JButton("Izmjena");
			btnIzmjena.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String message = validacijaGosta();

					if (message != null) {
						JOptionPane.showMessageDialog(frame,
								"ID gosta nije validan", "Greska",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {

						id = Integer.parseInt(textIzmjena.getText());

						String pronadjiPodatke1 = connectClass
								.pronadjiPodatke(id);
						if (pronadjiPodatke1.equals(null)
								|| pronadjiPodatke1.equals("")) {
							JOptionPane.showMessageDialog(frame,
									"ID gosta ne postoji u bazi", "Greska",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							String[] pronadjiPodatke = pronadjiPodatke1
									.split("/");

							textRegText.setText(pronadjiPodatke[1]);
							textRegText.setEnabled(false);
							textIme.setText(pronadjiPodatke[2]);
							textPrezime.setText(pronadjiPodatke[3]);

							try {
								Date d = new SimpleDateFormat("yyyy-MM-dd")
										.parse(pronadjiPodatke[4]);
								dateRodjenje.setDate(d);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							if (pronadjiPodatke[5].equalsIgnoreCase("m")) {
								rdbtnMusko.setSelected(true);
							} else {
								rdbtnZ.setSelected(true);
							}

							labelIzmjena.setVisible(true);
							panel.setVisible(true);
							provjera = "izmjena";
							btnIzmjena.setEnabled(false);
						}
					}
				}
			});
			btnIzmjena.setBounds(537, 185, 138, 23);
		}
		return btnIzmjena;
	}

	private JButton getBtnBrisanje() {
		if (btnBrisanje == null) {
			btnBrisanje = new JButton("Brisanje");
			btnBrisanje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String message = validacijaGosta();

					if (message != null) {
						JOptionPane.showMessageDialog(frame,
								"ID gosta nije validan", "Greska",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						
						id = Integer.parseInt(textIzmjena.getText());
						
						Gost g = new Gost(id, connectClass.getConnection());
						
						String podaci = g.pronadjiPodatkeOGostu();
						
						if (podaci.equals("")) {
							JOptionPane.showMessageDialog(frame,
									"Gost ne postoji u bazu", "Greska",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (g.izbrisiGosta()) {
							JOptionPane.showMessageDialog(frame,
									podaci +" je uspjesno uklonjen", "Obavjestenje",
									JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(frame,
									"ID gosta ne postoji u bazi", "Greska",
									JOptionPane.ERROR_MESSAGE);
						}
						
						textIzmjena.setText("");

					}
				}

			});
			btnBrisanje.setBounds(537, 219, 138, 23);
		}
		return btnBrisanje;
	}

	private JLabel getLabelIzmjena() {
		if (labelIzmjena == null) {
			labelIzmjena = new JLabel("Izmjena gosta:");
			labelIzmjena
					.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			labelIzmjena.setBounds(61, 9, 123, 16);
			labelIzmjena.setVisible(false);
		}
		return labelIzmjena;
	}

	private JLabel getLblApartmanId() {
		if (lblApartmanId == null) {
			lblApartmanId = new JLabel("Apartman ID:");
			lblApartmanId.setBounds(502, 328, 76, 14);
		}
		return lblApartmanId;
	}

	private JTextField getTextApartmanID() {
		if (textApartmanID == null) {
			textApartmanID = new JTextField();
			textApartmanID.setColumns(10);
			textApartmanID.setBounds(587, 323, 101, 20);
		}
		return textApartmanID;
	}
}
