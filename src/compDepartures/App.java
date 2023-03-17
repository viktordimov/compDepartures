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
		frame.setBounds(100, 100, 1368, 1121);
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
		
		TextArea delayPanel = new TextArea();
		delayPanel.setBackground(new Color(255, 255, 255));
		delayPanel.setFont(new Font("Monospaced", Font.BOLD, 16));
		delayPanel.setText(fDelayed);
		delayPanel.setEditable(false);
		delayPanel.setBounds(10, 612, 1317, 372);
		layeredPane.add(delayPanel);
		BCL.setBounds(595, 148, 30, 23);
		layeredPane.add(BCL);
		
		JLabel AMS = new JLabel("AMS");
		AMS.setFont(new Font("Tahoma", Font.BOLD, 11));
		AMS.setBounds(630, 148, 32, 23);
		layeredPane.add(AMS);
		
		
		JLabel DUB = new JLabel("DUB");
		DUB.setFont(new Font("Tahoma", Font.BOLD, 11));
		DUB.setBounds(568, 148, 24, 23);
		layeredPane.add(DUB);
		
		JLabel ATH = new JLabel("ATH");
		ATH.setFont(new Font("Tahoma", Font.BOLD, 11));
		ATH.setBounds(669, 197, 28, 23);
		layeredPane.add(ATH);
		
		JLabel BKK = new JLabel("BKK");
		BKK.setFont(new Font("Tahoma", Font.BOLD, 11));
		BKK.setBounds(909, 280, 29, 23);
		layeredPane.add(BKK);
		
		JLabel CAIRO = new JLabel("CAI");
		CAIRO.setFont(new Font("Tahoma", Font.BOLD, 11));
		CAIRO.setBounds(695, 226, 25, 23);
		layeredPane.add(CAIRO);
		
		JLabel DEL = new JLabel("DEL");
		DEL.setFont(new Font("Tahoma", Font.BOLD, 11));
		DEL.setBounds(832, 231, 28, 23);
		layeredPane.add(DEL);
		
		JLabel DXB = new JLabel("DXB");
		DXB.setFont(new Font("Tahoma", Font.BOLD, 11));
		DXB.setBounds(763, 242, 29, 23);
		layeredPane.add(DXB);
		
		JLabel HKG = new JLabel("HKG");
		HKG.setFont(new Font("Tahoma", Font.BOLD, 11));
		HKG.setBounds(949, 245, 29, 23);
		layeredPane.add(HKG);
		
		JLabel JNB = new JLabel("JNB");
		JNB.setFont(new Font("Tahoma", Font.BOLD, 11));
		JNB.setBounds(684, 416, 29, 23);
		layeredPane.add(JNB);
		
		JLabel LOS = new JLabel("LOS");
		LOS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LOS.setBounds(612, 299, 29, 23);
		layeredPane.add(LOS);
		
		JLabel LVS = new JLabel("LVS");
		LVS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LVS.setBounds(267, 205, 27, 23);
		layeredPane.add(LVS);
		
		JLabel LIS = new JLabel("LIS");
		LIS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LIS.setBounds(566, 190, 26, 23);
		layeredPane.add(LIS);
		
		JLabel MAD = new JLabel("MAD");
		MAD.setFont(new Font("Tahoma", Font.BOLD, 11));
		MAD.setBounds(591, 191, 31, 23);
		layeredPane.add(MAD);
		
		JLabel RAK = new JLabel("RAK");
		RAK.setFont(new Font("Tahoma", Font.BOLD, 11));
		RAK.setBounds(575, 213, 30, 23);
		layeredPane.add(RAK);
		
		JLabel MEX = new JLabel("MEX");
		MEX.setFont(new Font("Tahoma", Font.BOLD, 11));
		MEX.setBounds(289, 254, 30, 23);
		layeredPane.add(MEX);
		
		JLabel SVO = new JLabel("SVO");
		SVO.setFont(new Font("Tahoma", Font.BOLD, 11));
		SVO.setBounds(694, 127, 31, 23);
		layeredPane.add(SVO);
		
		JLabel JFK = new JLabel("JFK");
		JFK.setFont(new Font("Tahoma", Font.BOLD, 11));
		JFK.setBounds(396, 181, 29, 23);
		layeredPane.add(JFK);
		
		JLabel CDG = new JLabel("CDG");
		CDG.setFont(new Font("Tahoma", Font.BOLD, 11));
		CDG.setBounds(612, 168, 26, 23);
		layeredPane.add(CDG);
		
		JLabel FCO = new JLabel("FCO");
		FCO.setFont(new Font("Tahoma", Font.BOLD, 11));
		FCO.setBounds(638, 182, 29, 23);
		layeredPane.add(FCO);
		
		JLabel GRU = new JLabel("GRU");
		GRU.setFont(new Font("Tahoma", Font.BOLD, 11));
		GRU.setBounds(451, 406, 30, 23);
		layeredPane.add(GRU);
		
		JLabel ARN = new JLabel("ARN");
		ARN.setFont(new Font("Tahoma", Font.BOLD, 11));
		ARN.setBounds(645, 125, 30, 23);
		layeredPane.add(ARN);
		
		JLabel SYD = new JLabel("SYD");
		SYD.setFont(new Font("Tahoma", Font.BOLD, 11));
		SYD.setBounds(1051, 437, 30, 23);
		layeredPane.add(SYD);
		
		JLabel DFW = new JLabel("DFW");
		DFW.setFont(new Font("Tahoma", Font.BOLD, 11));
		DFW.setBounds(307, 221, 32, 23);
		layeredPane.add(DFW);
		
		JLabel HND = new JLabel("HND");
		HND.setFont(new Font("Tahoma", Font.BOLD, 11));
		HND.setBounds(1005, 197, 31, 23);
		layeredPane.add(HND);
		
		JLabel YYZ = new JLabel("YYZ");
		YYZ.setFont(new Font("Tahoma", Font.BOLD, 11));
		YYZ.setBounds(370, 172, 26, 23);
		layeredPane.add(YYZ);
		map.setIcon(new ImageIcon(img));
		map.setBounds(186, -54, 970, 704);
		layeredPane.add(map);
		
		//Display current date and time on JDialog, with timer
		
		JLabel today = new JLabel(now);
		today.setFont(new Font("Monospaced", Font.PLAIN, 26));
		today.setBounds(10, 0, 180, 39);
		layeredPane.add(today);
		
		JLabel lblNewLabel = new JLabel("Delays BCL");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblNewLabel.setBounds(622, 561, 131, 31);
		layeredPane.add(lblNewLabel);
		
		JLabel lblBclDepartures = new JLabel("BCL Departures");
		lblBclDepartures.setBounds(1219, 0, 123, 39);
		layeredPane.add(lblBclDepartures);
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
