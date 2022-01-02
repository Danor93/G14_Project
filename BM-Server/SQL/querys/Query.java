package querys;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import Entities.BusinessAccountTracking;
import Entities.BussinessAccount;
import Entities.Client;
import Entities.Employer;
import Entities.MyFile;
import Entities.Order;
import Entities.PerformanceReport;
import Entities.Receipt;
import Entities.Supplier;
import Entities.User;
import Entities.homeBranches;
import controllers.ServerUIFController;
import javafx.stage.FileChooser;

/**
 * @author Danor
 * @author Sahar
 * @author Aviel
 * @author Lior
 * @author Adi
 * @author Talia This class handles all queries the server needs to perform.
 */
public class Query {

	/*
	 * importData this method import the database script.
	 */
	public static void importData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SQL", "*.sql"));
		File file = fileChooser.showOpenDialog(ServerUIFController.serveruifconroller.stage);
		String path;
		if (file != null) {
			path = file.getPath();
			if (DBConnect.conn == null) {
				ServerUIFController.serveruifconroller
						.message("First insert user and password,press Start and then import", "Error");
			} else {
				Connection connection = DBConnect.conn;
				InputStream targetStream = null;
				try {
					targetStream = new FileInputStream(path);
				} catch (FileNotFoundException e1) {
					ServerUIFController.serveruifconroller.message("The database file is incorrect", "Error");
					e1.printStackTrace();
				}
				try {
					readtScript(connection, targetStream);
				} catch (SQLException e) {
					ServerUIFController.serveruifconroller.message("The database file is incorrect", "Error");
					e.printStackTrace();
				}
				ServerUIFController.serveruifconroller.message("The database was successfully imported", "Success");
			}
		} else {
			System.out.println("error"); // or something else
		}
	}

	/**
	 * readtScript this method get script and executed it into database.
	 * 
	 * @param conn - the connection to db
	 * @param in   - the script file
	 */
	public static void readtScript(Connection conn, InputStream in) throws SQLException {
		Scanner s = new Scanner(in);
		s.useDelimiter("(;(\r)?\n)|(--\n)");
		Statement st = null;
		try {
			st = conn.createStatement();
			while (s.hasNext()) {
				String line = s.next();
				if (line.startsWith("/*!") && line.endsWith("*/")) {
					int i = line.indexOf(' ');
					line = line.substring(i + 1, line.length() - " */".length());
				}
				if (line.trim().length() > 0) {
					st.execute(line);
				}
			}
		} finally {
			if (st != null)
				st.close();
		}
	}

	/**
	 * this method load employers from the DB for the Branch Manager for
	 * confirmation.
	 * 
	 * @return - the array list of employers to confirm.
	 */
	public static ArrayList<Employer> LoadEmployers() {
		if (DBConnect.conn != null) {
			ArrayList<Employer> employers = new ArrayList<>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT w4cBusiness,companyName,companyStatus FROM company WHERE companyStatus='Not approved' or companyStatus ='Waiting'"
								+ "");
				while (rs.next()) {
					Employer employer = new Employer(rs.getString(1), rs.getString(2), rs.getString(3));
					employers.add(employer);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return employers;
		}
		return null;
	}

	/**
	 * this method update the employer status from the Branch Manager.
	 * 
	 * @param CompanyName   - the company name of the employer.
	 * @param CompanyStatus - the updated company name of the employer.
	 */
	public static void UpdateEmployers(String CompanyName, String CompanyStatus) {
		try {
			if (DBConnect.conn != null) {
				PreparedStatement stmt = DBConnect.conn.prepareStatement("UPDATE bitemedb.company SET companyStatus= '"
						+ CompanyStatus + "'" + " WHERE companyName= '" + CompanyName + "'  ;");
				stmt.executeUpdate();
			} else {
				System.out.println("Conn is null");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	/**
	 * this method check if the supplier company name equals to the supplier
	 * certified and approved in the DB.
	 * 
	 * @param supplier - the supplier to check his details.
	 * @return - true or false if the details are correct.
	 */
	public static Boolean checkSupplier(Supplier supplier) {
		if (DBConnect.conn != null) {
			try {
				String[] role1 = null;
				String[] role2 = null;
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(
						"SELECT role FROM bitemedb.import_users WHERE id = '" + supplier.getRestId() + "' ;");
				while (rs1.next()) {
					role1 = rs1.getString(1).split("-");
				}
				rs1.close();
				Statement stmt2 = DBConnect.conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(
						"SELECT role FROM bitemedb.import_users WHERE id = '" + supplier.getConfirm_Employee() + "' ;");
				while (rs2.next()) {
					role2 = rs2.getString(1).split("-");
				}
				rs2.close();
				if (role1[0].equals("Certified") && role1[1].equals(supplier.getSupplierName())
						&& role2[0].equals("Approved") && role2[1].equals(supplier.getSupplierName())) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method adds to the users and supplier tables in the DB the details of
	 * the supplier.
	 * 
	 * @param supplier - supplier for the id of the certified and id approved
	 *                 supplier.
	 */
	public static void UpdateSupplier(Supplier supplier) {
		try {
			if (DBConnect.conn != null) {
				User Certified_Employee = new User(null, null, null, null, null, null, null, null);
				User Approved_Employee = new User(null, null, null, null, null, null, null, null);
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs = stmt1.executeQuery(
						"SELECT userName,password,firstName,lastName,Email,phone,role FROM bitemedb.import_users WHERE id = '"
								+ supplier.getRestId() + "' OR id= '" + supplier.getConfirm_Employee()
								+ "' ORDER BY role;");
				while (rs.next()) {
					String[] role = rs.getString(7).split("-");
					if (role[0].equals("Approved")) {
						Approved_Employee.setId(supplier.getConfirm_Employee());
						Approved_Employee.setFirstN(rs.getString(3));
						Approved_Employee.setLastN(rs.getString(4));
						Approved_Employee.setUserName(rs.getString(1));
						Approved_Employee.setPassword(rs.getString(2));
						Approved_Employee.setEmail(rs.getString(5));
						Approved_Employee.setPhone(rs.getString(6));
						Approved_Employee.setRole(rs.getString(7));
					}
					if (role[0].equals("Certified")) {
						Certified_Employee.setId(String.valueOf(supplier.getRestId()));
						Certified_Employee.setFirstN(rs.getString(3));
						Certified_Employee.setLastN(rs.getString(4));
						Certified_Employee.setUserName(rs.getString(1));
						Certified_Employee.setPassword(rs.getString(2));
						Certified_Employee.setEmail(rs.getString(5));
						Certified_Employee.setPhone(rs.getString(6));
						Certified_Employee.setRole(rs.getString(7));
					}
				}
				rs.close();
				PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.users(userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch) VALUES(?,?,?,?,?,?,?,?,?,?)");
				stmt2.setString(1, Approved_Employee.getUserName());
				stmt2.setString(2, Approved_Employee.getPassword());
				stmt2.setString(3, "Supplier-" + Approved_Employee.getRole());
				stmt2.setString(4, Approved_Employee.getFirstN());
				stmt2.setString(5, Approved_Employee.getLastN());
				stmt2.setString(6, Approved_Employee.getId());
				stmt2.setString(7, Approved_Employee.getEmail());
				stmt2.setString(8, Approved_Employee.getPhone());
				stmt2.setString(9, "0");
				stmt2.setString(10, supplier.getHomeBranch().toString());
				stmt2.executeUpdate();

				PreparedStatement stmt3 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.users(userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch) VALUES(?,?,?,?,?,?,?,?,?,?)");
				stmt3.setString(1, Certified_Employee.getUserName());
				stmt3.setString(2, Certified_Employee.getPassword());
				stmt3.setString(3, "Supplier-" + Certified_Employee.getRole());
				stmt3.setString(4, Certified_Employee.getFirstN());
				stmt3.setString(5, Certified_Employee.getLastN());
				stmt3.setString(6, Certified_Employee.getId());
				stmt3.setString(7, Certified_Employee.getEmail());
				stmt3.setString(8, Certified_Employee.getPhone());
				stmt3.setString(9, "0");
				stmt3.setString(10, supplier.getHomeBranch().toString());
				stmt3.executeUpdate();

				PreparedStatement stmt4 = DBConnect.conn.prepareStatement(
						"INSERT INTO bitemedb.supplier(restId,supplierName,openingTime,city,address,supplierStatus,homeBranch,Confirm_Employee) VALUES(?,?,?,?,?,?,?,?)");
				stmt4.setString(1, String.valueOf(supplier.getRestId()));
				stmt4.setString(2, supplier.getSupplierName());
				stmt4.setString(3, supplier.getOpeningTime());
				stmt4.setString(4, supplier.getCity());
				stmt4.setString(5, supplier.getAddress());
				stmt4.setString(6, "Approved");
				stmt4.setString(7, supplier.getHomeBranch().toString());
				stmt4.setString(8, supplier.getConfirm_Employee());
				stmt4.executeUpdate();
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	/**
	 * this method return the user by his ID from the DB.
	 * 
	 * @param ID - the id of the user.
	 * @return - return the user from the DB
	 */
	public static User IDcheck(String ID) {
		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement("SELECT * FROM bitemedb.users WHERE ID= '" + ID + "'" + "'  ;");
				ResultSet rs = stmt.executeQuery();
				if (rs != null) {
					User user = new User(rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5),
							homeBranches.toHomeBranchType(rs.getString(10)), rs.getString(1), rs.getString(2),
							rs.getString(9));
					user.setEmail(rs.getString(7));
					user.setPhone(rs.getString(8));
					rs.close();
					return user;
				} else {
					return null;
				}
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return null;
	}

	/**
	 * this method returns array list of users from the DB to the Branch Manager.
	 * 
	 * @param Branch - the Branch of the Branch Manager to search users from his
	 *               branch.
	 * @return - Array list of users.
	 */
	public static ArrayList<User> getAccount(String Branch) {
		if (DBConnect.conn != null) {
			ArrayList<User> users = new ArrayList<>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT * FROM users WHERE Role='Customer' AND homeBranch= '" + Branch + "' ;");
				while (rs.next()) {
					User user = new User(rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5),
							homeBranches.toHomeBranchType(rs.getString(10)), rs.getString(1), rs.getString(2),
							rs.getString(9));
					user.setEmail(rs.getString(7));
					user.setPhone(rs.getString(8));
					users.add(user);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return users;
		}
		return null;
	}

	/**
	 * this method delete the user from the users,client tables and if its a
	 * business client,delete form the buss_client table in DB.
	 * 
	 * @param user - user from the Branch Manager to Delete.
	 */
	public static void DeleteAccount(User user) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn
						.prepareStatement("DELETE FROM bitemedb.users WHERE ID = '" + user.getId() + "' ;");
				stmt.executeUpdate();

				PreparedStatement stmt2 = DBConnect.conn
						.prepareStatement("DELETE FROM bitemedb.client WHERE client_id = '" + user.getId() + "' ;");
				stmt2.executeUpdate();

				Statement stmt3 = DBConnect.conn.createStatement();
				ResultSet rs = stmt3
						.executeQuery("SELECT ID FROM bitemedb.buss_client WHERE ID= '" + user.getId() + "' ;");
				while (rs.next()) {
					PreparedStatement stmt4 = DBConnect.conn
							.prepareStatement("DELETE FROM bitemedb.buss_client WHERE ID = '" + user.getId() + "' ;");
					stmt4.executeUpdate();
				}
				rs.close();
			} catch (SQLException s) {
				System.out.println(s.getMessage());
				s.printStackTrace();
			}
		}
	}

	/**
	 * this method returns true of false to the Branch Manager if the company is
	 * approved or not.
	 * 
	 * @param CompanyName - company name for find the company status in the DB
	 * @return - true of false.
	 */
	public static Boolean checkEmployerStatus(String CompanyName) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT companyStatus FROM bitemedb.company WHERE companyName= '" + CompanyName + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("Approved")) {
						return true;
					} else {
						return false;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method check if the details of the business account from the Branch
	 * Manager equals to the details from the import_users.
	 * 
	 * @param Account - business account from the Branch Manager to check his
	 *                details.
	 * @return - true or false.
	 */
	public static Boolean checkAccountDetails(BussinessAccount Account) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT firstName,lastName,Email,phone FROM bitemedb.import_users WHERE id = '"
								+ Account.getId() + "' ;");
				while (rs.next()) {
					String FirstName = rs.getString(1);
					String LastName = rs.getString(2);
					String Email = rs.getString(3);
					String Phone = rs.getString(4);
					if ((!FirstName.equals(Account.getFirstN())) || (!LastName.equals(Account.getLastN()))
							|| (!Email.equals(Account.getEmail())) || (!Phone.equals(Account.getPhone()))) {
						rs.close();
						return false;
					} else {
						rs.close();
						return true;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method adds the business account from the Branch manager to the
	 * users,client,buss_client tables in the DB.
	 * 
	 * @param BAccount - business account from the Branch Manager for add to the DB.
	 */
	public static void addNewBAccount(BussinessAccount BAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt1 = DBConnect.conn.createStatement();
				ResultSet rs = stmt1.executeQuery(
						"SELECT userName,password,role FROM import_users WHERE id= '" + BAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);
					String Role = rs.getString(3);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
									+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, BAccount.getId());
					stmt2.setString(2, Password);
					stmt2.setString(3, "Active");
					stmt2.setString(1, UserName);
					stmt2.setString(2, Password);
					stmt2.setString(3, Role);
					stmt2.setString(4, BAccount.getFirstN());
					stmt2.setString(5, BAccount.getLastN());
					stmt2.setString(6, BAccount.getId());
					stmt2.setString(7, BAccount.getEmail());
					stmt2.setString(8, BAccount.getPhone());
					stmt2.setInt(9, 0);
					stmt2.setString(10, BAccount.getBranch().toString());
					stmt2.executeUpdate();

					PreparedStatement stmt3 = DBConnect.conn
							.prepareStatement("INSERT INTO client (client_id,w4c_private,status) VALUES(?,?,?)");
					stmt3.setString(1, BAccount.getId());
					Random rand = new Random(); // instance of random class
					int int_random = rand.nextInt(1000);
					String w4cNew = "P" + String.valueOf(int_random);
					stmt3.setString(2, w4cNew);
					stmt3.setString(3, "Active");
					stmt3.executeUpdate();

					PreparedStatement stmt4 = DBConnect.conn
							.prepareStatement("INSERT INTO buss_client (ID,companyName,budget,status) VALUES(?,?,?,?)");
					stmt4.setString(1, BAccount.getId());
					stmt4.setString(2, BAccount.getCompanyName());
					stmt4.setString(3, BAccount.getBudget());
					stmt4.setString(4, "Waiting");
					stmt4.executeUpdate();
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	/**
	 * this method check if the details of the private client from the Branch
	 * Manager is equals to the details in the import_users table.
	 * 
	 * @param client - private account details from the Branch Manager.
	 * @return - true or false.
	 */
	public static Boolean checkPrivateAccount(Client client) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT id FROM bitemedb.import_users WHERE id= '" + client.getId() + "' ;");
				while (rs.next()) {
					String FirstName = rs.getString(1);
					String LastName = rs.getString(2);
					String Email = rs.getString(3);
					String Phone = rs.getString(4);
					if ((!FirstName.equals(client.getFirstN())) || (!LastName.equals(client.getLastN()))
							|| (!Email.equals(client.getEmail())) || (!Phone.equals(client.getPhone()))) {
						rs.close();
						return false;
					} else {
						rs.close();
						return true;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method adds the private account from the Branch manager to the
	 * users,client tables in the DB.
	 * 
	 * @param PAccount - private account details for add.
	 */
	public static void addNewPAccount(Client PAccount) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT userName,password,role FROM bitemedb.import_users WHERE id= '"
						+ PAccount.getId() + "' ;");
				while (rs.next()) {
					String UserName = rs.getString(1);
					String Password = rs.getString(2);
					String Role = rs.getString(3);

					PreparedStatement stmt2 = DBConnect.conn.prepareStatement(
							"INSERT INTO bitemedb.users (userName,password,Role,FirstName,LastName,ID,Email,phone,isLoggedIn,homeBranch)"
									+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
					stmt2.setString(1, UserName);
					stmt2.setString(2, Password);
					stmt2.setString(3, Role);
					stmt2.setString(4, PAccount.getFirstN());
					stmt2.setString(5, PAccount.getLastN());
					stmt2.setString(6, PAccount.getId());
					stmt2.setString(7, PAccount.getEmail());
					stmt2.setString(8, PAccount.getPhone());
					stmt2.setInt(9, 0);
					stmt2.setString(10, PAccount.getBranch().toString());
					stmt2.executeUpdate();

					PreparedStatement stmt3 = DBConnect.conn.prepareStatement(
							"INSERT INTO bitemedb.client (client_id,w4c_private,status,CreditCardNumber) VALUES(?,?,?,?)");
					stmt3.setString(1, PAccount.getId());
					Random rand = new Random(); // instance of random class
					int int_random = rand.nextInt(1000);
					String w4cNew = "P" + String.valueOf(int_random);
					stmt3.setString(2, w4cNew);
					stmt3.setString(3, "Active");
					stmt3.setString(4, PAccount.getCreditCardNumber());
					stmt3.executeUpdate();
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	/**
	 * this method returns users from the branch location to change permissions.
	 * 
	 * @param Branch - branch location for getting account from the DB.
	 * @return - array list of users for change permissions to the Branch Manager.
	 */
	public static ArrayList<User> GetAccountForFreeze(String Branch) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT * FROM users WHERE homeBranch= '" + Branch + "' AND Role='Customer'");
				ArrayList<User> users = new ArrayList<>();
				while (rs.next()) {
					User user = new User(rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5),
							homeBranches.toHomeBranchType(rs.getString(10)), rs.getString(1), rs.getString(2),
							rs.getString(9));
					user.setEmail(rs.getString(7));
					user.setPhone(rs.getString(8));
					users.add(user);
				}
				rs.close();
				return users;
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method return if the status of the client is active.
	 * 
	 * @param AccountID - to find the client with his ID.
	 * @return - true or false.
	 */
	public static Boolean CheckAccountStatusActive(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT status FROM bitemedb.client WHERE client_id= '" + AccountID + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("Active")) {
						rs.close();
						return true;
					} else {
						rs.close();
						return false;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method return if the status of the client is freeze.
	 * 
	 * @param AccountID - to find the client with his ID.
	 * @return - true or false.
	 */
	public static Boolean CheckAccountStatusFreeze(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT status FROM client WHERE client_id= '" + AccountID + "' ;");
				while (rs.next()) {
					String status = rs.getString(1);
					if (status.equals("Freeze")) {
						rs.close();
						return true;
					} else {
						rs.close();
						return false;
					}
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method update the account to active.
	 * 
	 * @param AccountID - to find the client with his ID.
	 */
	public static void UpdateAccountStatusToActive(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn
						.prepareStatement("UPDATE client SET status='Active' WHERE client_id= '" + AccountID + "'  ;");
				stmt.executeUpdate();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	/**
	 * this method update the account to Freeze.
	 * 
	 * @param AccountID - to find the client with his ID.
	 */
	public static void UpdateAccountStatusToFreeze(String AccountID) {
		if (DBConnect.conn != null) {
			try {
				PreparedStatement stmt = DBConnect.conn.prepareStatement(
						"UPDATE bitemedb.client SET status='Freeze' WHERE client_id= '" + AccountID + "'  ;");
				stmt.executeUpdate();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	/**
	 * this method return true or false if there is already report for the year and
	 * quarter given.
	 * 
	 * @param quarter - for check the quarter.
	 * @param year    - for check the year.
	 * @return - true or false.
	 */
	public static Boolean checkYearAndQuarter(String quarter, String year) {
		if (DBConnect.conn != null) {
			try {
				Statement stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT quarter,year FROM bitemedb.reports");
				while (rs.next()) {
					String quarter2 = rs.getString(1);
					String year2 = rs.getString(2);
					if ((year.equals(year2)) && (quarter.equals(quarter2))) {
						rs.close();
						return false;
					}
				}
				rs.close();
				return true;
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * this method update the MyFile file form the Branch Manager to the DB.
	 * 
	 * @param file - for update the DB.
	 */
	public static void updateFile(MyFile file) {
		if (DBConnect.conn != null) {
			String sql = "INSERT INTO reports(quarter,year,date_added,file_name,upload_file,homebranch) values(?,?,?,?,?,?)";
			try {
				Timestamp date = new java.sql.Timestamp(new Date().getTime());
				InputStream is = new ByteArrayInputStream(file.getMybytearray());
				PreparedStatement stmt = DBConnect.conn.prepareStatement(sql);
				stmt.setString(1, file.getQuarter());
				stmt.setString(2, file.getYear());
				stmt.setTimestamp(3, date);
				stmt.setString(4, file.getFileName());
				stmt.setBlob(5, is);
				stmt.setString(6, file.getHomebranch().toString());
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param branch  - for the Branch of the report.
	 * @param year    - for the Year of the report.
	 * @param quarter - Method connects to DB and get the relevant file with match
	 *                parameters and creates a MyFile object with the information
	 * @return - relevant MyFile from DB.
	 */
	public static MyFile downloadFile(String branch, String year, String quarter) {

		PreparedStatement stmt;
		try {
			if (DBConnect.conn != null) {
				stmt = DBConnect.conn.prepareStatement(
						"SELECT upload_file FROM reports WHERE homebranch=? AND year=? AND quarter=?;");
				stmt.setString(1, branch);
				stmt.setString(2, year);
				stmt.setString(3, quarter);
				ResultSet rs = stmt.executeQuery();
				MyFile downloadFile = new MyFile("");
				while (rs.next()) {
					downloadFile.setHomebranch(homeBranches.toHomeBranchType(branch));
					downloadFile.setQuarter(quarter);
					downloadFile.setYear(year);
					downloadFile.setFileName("Branch-" + branch + "_Year-" + year + "_Quarterly-" + quarter + ".pdf");
					downloadFile.setDate(new java.sql.Timestamp(new Date().getTime()).toString());
					downloadFile
							.setDescription("PDF file, Branch:" + branch + ", Year:" + year + ", Quarterly:" + quarter);
					Blob fileData = rs.getBlob(1);
					if (fileData != null) {
						int len = (int) fileData.length();
						InputStream inputFileData = fileData.getBinaryStream();
						byte[] bytes = new byte[len];
						int bytesRead = 0;
						while (bytesRead != -1)
							try {
								bytesRead = inputFileData.read(bytes);
							} catch (IOException e) {

								e.printStackTrace();
							}
						downloadFile.initArray(len);
						downloadFile.setSize(len);
						downloadFile.setMybytearray(bytes);
					} else {
						System.out.println("no pdf");
					}
				}
				rs.close();
				return downloadFile;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return null;
	}

	/**
	 * Method connect to DB and get the relevant year and quarterly who match
	 * parameter "branch" and creates a ArrayList object with the information
	 * 
	 * @param branch - for the branch of the Branch Manager.
	 * @return - ArrayList with the match year and quarterly
	 */
	public static ArrayList<String> getRelevantYearsAndQuarterly(String branch) {
		ArrayList<String> yearsAndQuarter = new ArrayList<>();
		PreparedStatement stmt;
		try {
			stmt = DBConnect.conn.prepareStatement("SELECT year,quarter FROM reports WHERE homebranch=?;");
			stmt.setString(1, branch);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String resYear = rs.getString(1);
				String resQuarter = rs.getString(2);
				yearsAndQuarter.add(resYear + "@" + resQuarter);

			}
			System.out.println(yearsAndQuarter.toString());
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return yearsAndQuarter;

	}

	/**
	 * Method for getting all the businessAccount waiting for approval. The business
	 * accounts who are waiting for approval and their status is - Waiting, and work
	 * under the same company as the HR Manager.
	 * 
	 * @param companyName
	 * @return arrayList of BusinessAccountTracking
	 */
	public static ArrayList<BusinessAccountTracking> LoadBusinessAccountDetails(String companyName) {
		ArrayList<BusinessAccountTracking> businessAccountTracking = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT ID,companyName,budget FROM bitemedb.buss_client WHERE companyName='"
							+ companyName + "' AND status ='Waiting' ORDER BY companyName" + "");
			while (rs.next()) {
				BusinessAccountTracking BAT = new BusinessAccountTracking(rs.getString(1), rs.getString(2),
						rs.getString(3));
				BAT.setStatus("Waiting");

				businessAccountTracking.add(BAT);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return businessAccountTracking;
	}

	/**
	 * Method for getting all the w4cBusiness that are in the database
	 * 
	 * @return arrayList of w4cBusiness
	 */
	public static ArrayList<String> LoadW4CBusiness() {
		ArrayList<String> w4cBusiness = new ArrayList<>();
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT w4cBusiness FROM bitemedb.company" + "");
			while (rs.next()) {
				w4cBusiness.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return w4cBusiness;
	}

	public static boolean LoadCompanyStatus(StringBuilder companyName) {
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT companyStatus FROM bitemedb.company WHERE companyName='"
					+ companyName + "' AND companyStatus='Approved' ORDER BY companyName" + "");
			while (rs.next()) {
				return true;
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Method for obtaining the telephone number of the customer who placed the
	 * order.
	 * 
	 * @param order
	 * @return phone number
	 */
	public static String LoadPhoneNumber(Order order) {
		Statement stmt;
		String phoneNumber = null;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT phone FROM bitemedb.users WHERE ID='" + order.getCostumerId() + "'" + "");
			while (rs.next()) {
				phoneNumber = rs.getString(1);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return phoneNumber;
	}

	/**
	 * Method for obtaining the name of the restaurant whose ID is the same as the
	 * ID of the certified employee.
	 * 
	 * @param ID
	 * @return restaurant name
	 */
	public static String getRestName(String ID) {
		String restName = null;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT supplierName FROM bitemedb.supplier WHERE restId='" + ID + "' ;");
			while (rs.next()) {
				restName = rs.getString(1);
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return restName;
	}

	/*
	 * Method for receiving all orders awaiting approval / approval at a specific
	 * restaurant.
	 * 
	 * @param restName - for the restaurant name to check.
	 * 
	 * @return - array list of orders.
	 */
	public static ArrayList<Order> LoadOrders(String restName) {
		if (DBConnect.conn != null) {
			ArrayList<Order> orders = new ArrayList<>();
			Statement stmt;
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT orderType,timeOfOrder,dateOfOrder,orderStatus,costumerID,rstID,totalPrice,orderNumber,usedRefund,usedBudget,EarlyOrder FROM bitemedb.order WHERE restName='"
								+ restName + "' AND orderStatus ='Waiting' OR orderStatus='Approved'" + "");
				while (rs.next()) {
					Order order = new Order(rs.getString(1), restName, rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), Float.parseFloat(rs.getString(7)));
					order.setOrderNum(Integer.parseInt(rs.getString(8)));
					order.setUseRefund(rs.getString(9));
					order.setUseBudget(Integer.parseInt(rs.getString(10)));
					order.setEarlyOrder(rs.getString(11));
					orders.add(order);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return orders;
		}
		return null;
	}

	/**
	 * Method for receiving all orders approved / sended at a specific restaurant.
	 * 
	 * @param ID
	 * @return arrayList of receipt
	 */
	public static ArrayList<Receipt> LoadOrdersApproved(String ID) {
		ArrayList<Receipt> receipts = new ArrayList<>();
		Float priceAfterCommission;
		Statement stmt;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT orderNumber,restName,totalPrice,dateOfOrder FROM bitemedb.order WHERE rstID='"
							+ ID + "' AND orderStatus='Approved' OR orderStatus='Sended';");
			while (rs.next()) {
				if (LocalDate.now().isAfter(LocalDate.parse(rs.getString(4)))
						|| LocalDate.now().isEqual(LocalDate.parse(rs.getString(4)))) {
					String[] DivededAdd = ((String) rs.getString(4)).split("-");
					ArrayList<Integer> checkMonth = new ArrayList<Integer>();
					for (String s : DivededAdd)
						checkMonth.add(Integer.parseInt(s));

					if (DivededAdd[0].equals(String.valueOf(LocalDate.now().getYear()))
							&& checkMonth.get(1) == Integer.parseInt(String.valueOf(LocalDate.now().getMonthValue()))) {
						priceAfterCommission = (float) (Float.parseFloat(rs.getString(3))
								- (Float.parseFloat(rs.getString(3)) * 0.1));
						Receipt receipt = new Receipt(rs.getInt(1), rs.getString(2), Float.parseFloat(rs.getString(3)),
								priceAfterCommission);
						receipts.add(receipt);
					}
				}
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return receipts;
	}

	/**
	 * this method returns HashMap of the Dish Type from the DB.
	 * 
	 * @param id - for the id to check.
	 * @return - HashMap of dish types.
	 */
	public static HashMap<String, Integer> getQuntitiesOfDishTypes(int id) {
		if (DBConnect.conn != null) {
			Statement stmt;
			HashMap<String, Integer> retMap = new HashMap<>();
			try {
				stmt = DBConnect.conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT dishType,quentity FROM bitemedb.dishesinorder WHERE orderNum='" + id + "'" + "");
				while (rs.next()) {
					int quentity = rs.getInt(2);
					String dishType = rs.getString(1);
					if (retMap.containsKey(dishType)) {
						quentity += retMap.get(dishType);
					}
					retMap.put(dishType, quentity);
				}
				rs.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
			return retMap;
		}
		return null;
	}

	public static ArrayList<PerformanceReport> LoadPerformanceReport(String messageData) {
		ArrayList<PerformanceReport> reports = new ArrayList<>();
		System.out.println("test");
		ArrayList<String> restNames = new ArrayList<>();
		boolean existRest = false;
		int totalOrders = 0;
		String approvedTime;
		String sendTime;
		String[] data = messageData.split("@");
		String branch = data[0];
		String month = data[1];
		String year = data[2];
		float priceAfterCommission;
		long avgDiff = 0;
		Statement stmt, stmt2, stmt3;
		try {
			stmt = DBConnect.conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT supplierName FROM bitemedb.supplier WHERE homeBranch='" + branch + "' ;");
			while (rs.next()) {
				restNames.add(rs.getString(1));
				existRest = true;
			}
			rs.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}

		if (existRest) {
			for (String name : restNames) {
				System.out.println(name);
				try {
					stmt2 = DBConnect.conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(
							"SELECT timeApproved,timeSended FROM bitemedb.order WHERE restName='" + name + "' AND orderStatus='Sended' ;");

					while (rs2.next()) {
						approvedTime = rs2.getString(1);
						sendTime = rs2.getString(2);
						long avg = LocalTime.parse(approvedTime).until(LocalTime.parse(sendTime), ChronoUnit.SECONDS);
						avgDiff += avg;
					}
					rs2.close();
				} catch (SQLException s) {
					s.printStackTrace();
				}
				try {
					stmt3 = DBConnect.conn.createStatement();
					ResultSet rs3 = stmt3.executeQuery(
							"SELECT * FROM bitemedb.performance_reports WHERE restaurant='" + name + "';");
					while (rs3.next()) {
					PerformanceReport report = new PerformanceReport(month, year, branch, name, rs3.getInt(5),
							rs3.getInt(6), rs3.getInt(7));
					int total_orders, areLate;
					total_orders = rs3.getInt(5);
					areLate = rs3.getInt(7);
					double avgCookingTime = (double)avgDiff / (double)total_orders;
					double lateRate = (double)areLate / (double)total_orders;
					report.setAvarageCookingTime(avgCookingTime);
					report.setLateRate(lateRate);
					reports.add(report);
					}
					rs3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return reports;
		}
		return null;
	}
}