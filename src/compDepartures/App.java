package compDepartures;

import java.awt.EventQueue;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	}

	

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public App() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JDialog();
		frame.setBounds(100, 100, 1368, 700);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		-----------------------------------------------------------------------------------------------------------------------------------
		// Create ArrayList to store all the CSV data
		ArrayList<String[]> data = new ArrayList<String[]>();
//		-----------------------------------------------------------------------------------------------------------------------------------
		//Read the CSV data and write to ArrayList
		BufferedReader reader = new BufferedReader(new FileReader("Flights.csv"));
		String line = "";
		
		while ((line = reader.readLine()) != null) {
			
			String col[] = line.split(",");
			data.add(col);
							
		}
//		-----------------------------------------------------------------------------------------------------------------------------------
		// Close the reader
		reader.close();
//		-----------------------------------------------------------------------------------------------------------------------------------		
		// Define Local Date and Time and patterns for later use
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();

		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm");
		String now = ld.format(d);
//		-----------------------------------------------------------------------------------------------------------------------------------
		//Define what time it is now and set a timer tu update every second
		
//		LocalTime atm = LocalTime.now();
//		LocalTime now= LocalTime.of(atm.getHour(), atm.getMinute());
//		-----------------------------------------------------------------------------------------------------------------------------------
// 		Define Arrays for individual columns from the ArrayList
		String[] date = new String[data.size()];
		String[] depTime = new String[data.size()];
		String[] arrTime = new String[data.size()];
		String[] duration = new String[data.size()]; //In decimal hours 1 = 100
		String[] miles = new String[data.size()];
		String[] delay = new String[data.size()]; //In minutes
		String[] dAp = new String[data.size()];
		String[] dCt = new String[data.size()];
		String[] aAp = new String[data.size()];
		String[] aCt = new String[data.size()];
		String[] flightNo = new String[data.size()];
		String[] airline = new String[data.size()];
//		-----------------------------------------------------------------------------------------------------------------------------------		
		// Populate the Arrays
		for (int i = 0; i < data.size() ; i++) {

			date[i] = data.get(i)[0];
			depTime[i] = data.get(i)[1];
			arrTime[i] = data.get(i)[2];
			duration[i] = data.get(i)[3];
			miles[i] = data.get(i)[4];
			delay[i] = data.get(i)[5];
			dAp[i] = data.get(i)[6];
			dCt[i] = data.get(i)[7];
			aAp[i] = data.get(i)[8];
			aCt[i] = data.get(i)[9];
			flightNo[i] = data.get(i)[10];
			airline[i] = data.get(i)[11];
		}
		
		
		ArrayList<String> delayed = new ArrayList<String>();
//		-----------------------------------------------------------------------------------------------------------------------------------
		//Display flights delayed by at least 30 minutes
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {
				continue;
			} 
			if (lt.isAfter(pTime)) {
				continue;
			}
			 
			if (dAp[i].contains("BCL")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (aTime.plusMinutes(x).isAfter(aTime.plusMinutes(30))) {
				
				String displayDelayed = (dTime+" "+dAp[i]+" "+aAp[i]+" "+aTime+" Delay "+aTime.plusMinutes(x));
				delayed.add(displayDelayed);
				}
					
			}	
		}
		//Make ArrayList nice and presentable
		Collections.sort(delayed);
		String fDelayed = String.join("\n", delayed);
		
		
//		-----------------------------------------------------------------------------------------------------------------------------------
//		-----------------------------------------------------------------------------------------------------------------------------------
//		GUI specific code Starts here
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
		
		JLabel BCL = new JLabel("BCL");
		BCL.setFont(new Font("Tahoma", Font.BOLD, 11));
		BCL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		BCL.setBounds(419, 170, 30, 23);
		layeredPane.add(BCL);
		
		JLabel AMS = new JLabel("AMS");
		AMS.setFont(new Font("Tahoma", Font.BOLD, 11));
		AMS.setBounds(454, 170, 32, 23);
		layeredPane.add(AMS);
		
		
		JLabel DUB = new JLabel("DUB");
		DUB.setFont(new Font("Tahoma", Font.BOLD, 11));
		DUB.setBounds(392, 170, 24, 23);
		layeredPane.add(DUB);
		
		JLabel ATH = new JLabel("ATH");
		ATH.setFont(new Font("Tahoma", Font.BOLD, 11));
		ATH.setBounds(493, 219, 28, 23);
		layeredPane.add(ATH);
		
		JLabel BKK = new JLabel("BKK");
		BKK.setFont(new Font("Tahoma", Font.BOLD, 11));
		BKK.setBounds(733, 302, 29, 23);
		layeredPane.add(BKK);
		
		JLabel CAIRO = new JLabel("CAI");
		CAIRO.setFont(new Font("Tahoma", Font.BOLD, 11));
		CAIRO.setBounds(519, 248, 25, 23);
		layeredPane.add(CAIRO);
		
		JLabel DEL = new JLabel("DEL");
		DEL.setFont(new Font("Tahoma", Font.BOLD, 11));
		DEL.setBounds(656, 253, 28, 23);
		layeredPane.add(DEL);
		
		JLabel DXB = new JLabel("DXB");
		DXB.setFont(new Font("Tahoma", Font.BOLD, 11));
		DXB.setBounds(587, 264, 29, 23);
		layeredPane.add(DXB);
		
		JLabel HKG = new JLabel("HKG");
		HKG.setFont(new Font("Tahoma", Font.BOLD, 11));
		HKG.setBounds(773, 267, 29, 23);
		layeredPane.add(HKG);
		
		JLabel JNB = new JLabel("JNB");
		JNB.setFont(new Font("Tahoma", Font.BOLD, 11));
		JNB.setBounds(508, 438, 29, 23);
		layeredPane.add(JNB);
		
		JLabel LOS = new JLabel("LOS");
		LOS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LOS.setBounds(436, 321, 29, 23);
		layeredPane.add(LOS);
		
		JLabel LVS = new JLabel("LVS");
		LVS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LVS.setBounds(91, 227, 27, 23);
		layeredPane.add(LVS);
		
		JLabel LIS = new JLabel("LIS");
		LIS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LIS.setBounds(390, 212, 26, 23);
		layeredPane.add(LIS);
		
		JLabel MAD = new JLabel("MAD");
		MAD.setFont(new Font("Tahoma", Font.BOLD, 11));
		MAD.setBounds(415, 213, 31, 23);
		layeredPane.add(MAD);
		
		JLabel RAK = new JLabel("RAK");
		RAK.setFont(new Font("Tahoma", Font.BOLD, 11));
		RAK.setBounds(399, 235, 30, 23);
		layeredPane.add(RAK);
		
		JLabel MEX = new JLabel("MEX");
		MEX.setFont(new Font("Tahoma", Font.BOLD, 11));
		MEX.setBounds(113, 276, 30, 23);
		layeredPane.add(MEX);
		
		JLabel SVO = new JLabel("SVO");
		SVO.setFont(new Font("Tahoma", Font.BOLD, 11));
		SVO.setBounds(518, 149, 31, 23);
		layeredPane.add(SVO);
		
		JLabel JFK = new JLabel("JFK");
		JFK.setFont(new Font("Tahoma", Font.BOLD, 11));
		JFK.setBounds(220, 203, 29, 23);
		layeredPane.add(JFK);
		
		JLabel CDG = new JLabel("CDG");
		CDG.setFont(new Font("Tahoma", Font.BOLD, 11));
		CDG.setBounds(436, 190, 26, 23);
		layeredPane.add(CDG);
		
		JLabel FCO = new JLabel("FCO");
		FCO.setFont(new Font("Tahoma", Font.BOLD, 11));
		FCO.setBounds(462, 204, 29, 23);
		layeredPane.add(FCO);
		
		JLabel GRU = new JLabel("GRU");
		GRU.setFont(new Font("Tahoma", Font.BOLD, 11));
		GRU.setBounds(275, 428, 30, 23);
		layeredPane.add(GRU);
		
		JLabel ARN = new JLabel("ARN");
		ARN.setFont(new Font("Tahoma", Font.BOLD, 11));
		ARN.setBounds(469, 147, 30, 23);
		layeredPane.add(ARN);
		
		JLabel SYD = new JLabel("SYD");
		SYD.setFont(new Font("Tahoma", Font.BOLD, 11));
		SYD.setBounds(875, 459, 30, 23);
		layeredPane.add(SYD);
		
		JLabel DFW = new JLabel("DFW");
		DFW.setFont(new Font("Tahoma", Font.BOLD, 11));
		DFW.setBounds(131, 243, 32, 23);
		layeredPane.add(DFW);
		
		JLabel HND = new JLabel("HND");
		HND.setFont(new Font("Tahoma", Font.BOLD, 11));
		HND.setBounds(829, 219, 31, 23);
		layeredPane.add(HND);
		
		JLabel YYZ = new JLabel("YYZ");
		YYZ.setFont(new Font("Tahoma", Font.BOLD, 11));
		YYZ.setBounds(194, 194, 26, 23);
		layeredPane.add(YYZ);
		map.setIcon(new ImageIcon(img));
		map.setBounds(10, 11, 944, 617);
		layeredPane.add(map);
		
		//Display current date and time on JDialog, with timer
		
		JLabel today = new JLabel(now);
		today.setFont(new Font("Monospaced", Font.PLAIN, 32));
		today.setBounds(29, 11, 392, 78);
		layeredPane.add(today);
		
		TextArea delayPanel = new TextArea();
		delayPanel.setBackground(new Color(255, 255, 255));
		delayPanel.setFont(new Font("Monospaced", Font.BOLD, 16));
		delayPanel.setText(fDelayed);
		delayPanel.setEditable(false);
		delayPanel.setBounds(960, 37, 362, 591);
		layeredPane.add(delayPanel);
		
		JLabel lblNewLabel = new JLabel("Delays BCL");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblNewLabel.setBounds(1089, 0, 131, 31);
		layeredPane.add(lblNewLabel);
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
