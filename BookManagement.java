import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookManagement {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManagement window = new BookManagement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookManagement() {
		initialize();
		Connect();
		table_load()
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/book management"
					+ "", "root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load()
	{
		try
		{
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1395, 798);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(625, 11, 243, 114);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 164, 593, 357);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(20, 50, 109, 74);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(20, 121, 82, 41);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(20, 147, 164, 98);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(165, 80, 256, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(165, 136, 256, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(165, 189, 256, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname, edition, price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price  = txtprice.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
		            pst.setString(1,bname);
		            pst.setString(2,edition);
		            pst.setString(3,price);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Added!");
		            table_load();
		            txtbname.setText("");
		            txtedition.setText("");
		            txtprice.setText("");
		            txtbname.requestFocus();
				}
				
				catch (SQLException el) {
					el.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(37, 559, 166, 85);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(259, 559, 166, 85);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            txtbname.setText("");
	            txtedition.setText("");
	            txtprice.setText("");
	            txtbname.requestFocus();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(466, 559, 149, 85);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JScrollPane JScrollPane = new JScrollPane();
		JScrollPane.setBounds(668, 164, 641, 419);
		frame.getContentPane().add(JScrollPane);
		
		table = new JTable();
		JScrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(37, 660, 600, 90);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(25, 41, 117, 22);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String id = txtbid.getText();
					pst = con.prepareStatement("select name,edition,price from book where id = ?");
					pst.setString(1, id);
					rs = pst.executeQuery();
					
					if(rs.next()==true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					else
					{
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
				}
				catch (SQLException e1) 
				{
					
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(165, 45, 256, 20);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1_1 = new JButton("Update");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname, edition, price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price  = txtprice.getText();
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("update book set name=?, edition=?, price=? where id=?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Updated!");
		            table_load();
		            txtbname.setText("");
		            txtedition.setText("");
		            txtprice.setText("");
		            txtbname.requestFocus();
				}
				
				catch (SQLException el) {
					el.printStackTrace();
				}
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1.setBounds(689, 665, 243, 85);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_2 = new JButton("Delete");
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("delete from book where id=?");
					pst.setString(1, bid);
					pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Deleted!");
		            table_load();
		            txtbname.setText("");
		            txtedition.setText("");
		            txtprice.setText("");
		            txtbname.requestFocus();
				}
				
				catch (SQLException el) {
					el.printStackTrace();
				}
			}
		});
		btnNewButton_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_2.setBounds(1080, 665, 229, 85);
		frame.getContentPane().add(btnNewButton_1_1_2);
	}
}
