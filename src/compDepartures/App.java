package compDepartures;

import java.awt.EventQueue;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {

	private JDialog frame;

	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//String date = data.get(i)[0]
		//String depTime = data.get(i)[1]
		//String arrTime = data.get(i)[2]
		//String duration = data.get(i)[3]
		//String miles = data.get(i)[4]
		//String delay = data.get(i)[5]
		//String depAir = data.get(i)[6]
		//String depCt = data.get(i)[7]
		//String arrAir = data.get(i)[8]
		//String arrCt = data.get(i)[9]
		//String flightNo = data.get(i)[10]
		//String airline = data.get(i)[11]
		
ArrayList<String[]> data = new ArrayList<String[]>();
		 
		 
		BufferedReader reader = new BufferedReader(new FileReader("Flights.csv"));
		String line = "";
		
		while ((line = reader.readLine()) != null) {
			
			String col[] = line.split(",");
			data.add(col);
							
		}
		reader.close();
		
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();

		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm");
		
		System.out.println("Flights for today");
		

		for (int i = 0; i < data.size() ; i++) {
			
			String dCt = data.get(i)[7];
			String aCt = data.get(i)[9];
			String dAp = data.get(i)[6];
			String aAp = data.get(i)[8];
			
			LocalDate date = LocalDate.parse(data.get(i)[0], d);
			
			if (ld.isEqual(date)) {
				
				boolean check = ld.isEqual(date);
				System.out.println(check);
				
				LocalTime dTime = LocalTime.parse(data.get(i)[1], t);
				LocalTime aTime = LocalTime.parse(data.get(i)[2], t);
				
				System.out.println(dCt+"("+dAp+") to "+aCt+"("+aAp+") -- "+dTime+" to "+aTime);
			}
			
		}
		
	}

		

	

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 1280, 700);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		
		JLabel map = new JLabel("");
		map.setLabelFor(map);
		Image img = new ImageIcon(this.getClass().getResource("/map.png")).getImage();
		
		JLabel AMS = new JLabel("AMS");
		AMS.setBounds(454, 170, 32, 23);
		layeredPane.add(AMS);
		
		JLabel BCL = new JLabel("BCL");
		BCL.setBounds(419, 170, 30, 23);
		layeredPane.add(BCL);
		
		JLabel DUB = new JLabel("DUB");
		DUB.setBounds(392, 170, 24, 23);
		layeredPane.add(DUB);
		
		JLabel ATH = new JLabel("ATH");
		ATH.setBounds(493, 219, 28, 23);
		layeredPane.add(ATH);
		
		JLabel BKK = new JLabel("BKK");
		BKK.setBounds(733, 302, 29, 23);
		layeredPane.add(BKK);
		
		JLabel CAIRO = new JLabel("CAI");
		CAIRO.setBounds(519, 248, 25, 23);
		layeredPane.add(CAIRO);
		
		JLabel DEL = new JLabel("DEL");
		DEL.setBounds(656, 253, 28, 23);
		layeredPane.add(DEL);
		
		JLabel DXB = new JLabel("DXB");
		DXB.setBounds(587, 264, 29, 23);
		layeredPane.add(DXB);
		
		JLabel HKG = new JLabel("HKG");
		HKG.setBounds(773, 267, 29, 23);
		layeredPane.add(HKG);
		
		JLabel JNB = new JLabel("JNB");
		JNB.setBounds(508, 438, 29, 23);
		layeredPane.add(JNB);
		
		JLabel LOS = new JLabel("LOS");
		LOS.setBounds(436, 321, 29, 23);
		layeredPane.add(LOS);
		
		JLabel LVS = new JLabel("LVS");
		LVS.setBounds(91, 227, 27, 23);
		layeredPane.add(LVS);
		
		JLabel LIS = new JLabel("LIS");
		LIS.setBounds(390, 212, 26, 23);
		layeredPane.add(LIS);
		
		JLabel MAD = new JLabel("MAD");
		MAD.setBounds(415, 213, 31, 23);
		layeredPane.add(MAD);
		
		JLabel RAK = new JLabel("RAK");
		RAK.setBounds(399, 235, 30, 23);
		layeredPane.add(RAK);
		
		JLabel MEX = new JLabel("MEX");
		MEX.setBounds(113, 276, 30, 23);
		layeredPane.add(MEX);
		
		JLabel SVO = new JLabel("SVO");
		SVO.setBounds(518, 149, 31, 23);
		layeredPane.add(SVO);
		
		JLabel JFK = new JLabel("JFK");
		JFK.setBounds(220, 203, 29, 23);
		layeredPane.add(JFK);
		
		JLabel CDG = new JLabel("CDG");
		CDG.setBounds(436, 190, 26, 23);
		layeredPane.add(CDG);
		
		JLabel FCO = new JLabel("FCO");
		FCO.setBounds(462, 204, 29, 23);
		layeredPane.add(FCO);
		
		JLabel GRU = new JLabel("GRU");
		GRU.setBounds(275, 428, 30, 23);
		layeredPane.add(GRU);
		
		JLabel ARN = new JLabel("ARN");
		ARN.setBounds(469, 147, 30, 23);
		layeredPane.add(ARN);
		
		JLabel SYD = new JLabel("SYD");
		SYD.setBounds(875, 459, 30, 23);
		layeredPane.add(SYD);
		
		JLabel DFW = new JLabel("DFW");
		DFW.setBounds(131, 243, 32, 23);
		layeredPane.add(DFW);
		
		JLabel HND = new JLabel("HND");
		HND.setBounds(829, 219, 31, 23);
		layeredPane.add(HND);
		
		JLabel YYZ = new JLabel("YYZ");
		YYZ.setBounds(194, 194, 26, 23);
		layeredPane.add(YYZ);
		map.setIcon(new ImageIcon(img));
		map.setBounds(10, 11, 944, 617);
		layeredPane.add(map);
		
		JLabel timeNow = new JLabel("Implement Real time Date and Time here");
		timeNow.setBounds(10, 0, 270, 14);
		layeredPane.add(timeNow);
		
		frame.getContentPane().setLayout(groupLayout);
	}
	
}
