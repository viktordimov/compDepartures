// Program takes around 20 seconds to load 
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
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class App {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 1500, 950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		reader.close();
//		-----------------------------------------------------------------------------------------------------------------------------------		
		// Define Local Date and Time and patterns for later use
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();

		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm");
		String now = ld.format(d);
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
		
		
//		Create ArrayLists for individual city buttons with separate delay and on time
//		-----------------------------------------------------------------------------------------------------------------------------------
//		BCL - candidate template
		ArrayList<String> bcl = new ArrayList<String>();
		ArrayList<String> bcl_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("BCL")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+"Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
						+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				bcl.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
					String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
							+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
									+"Airline "+airline[i]+"\n"
							+"-----------------------------------------------");
				bcl_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(bcl);
		Collections.sort(bcl_delayed);
		String sorted_bcl = String.join("\n",  bcl);
		String sorted_bcl_delayed = String.join("\n", bcl_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		ARN 
		ArrayList<String> arn = new ArrayList<String>();
		ArrayList<String> arn_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("ARN")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				arn.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				arn_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(arn);
		Collections.sort(arn_delayed);
		String sorted_arn = String.join("\n",  arn);
		String sorted_arn_delayed = String.join("\n", arn_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		LVS
		ArrayList<String> lvs = new ArrayList<String>();
		ArrayList<String> lvs_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("LVS")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				lvs.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				lvs_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(lvs);
		Collections.sort(lvs_delayed);
		String sorted_lvs = String.join("\n",  lvs);
		String sorted_lvs_delayed = String.join("\n", lvs_delayed);

//		-----------------------------------------------------------------------------------------------------------------------------------
//		DFW
		ArrayList<String> dfw = new ArrayList<String>();
		ArrayList<String> dfw_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("DFW")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				dfw.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				dfw_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(dfw);
		Collections.sort(dfw_delayed);
		String sorted_dfw = String.join("\n",  dfw);
		String sorted_dfw_delayed = String.join("\n", dfw_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		MEX
		ArrayList<String> mex = new ArrayList<String>();
		ArrayList<String> mex_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("MEX")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				mex.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				mex_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(mex);
		Collections.sort(mex_delayed);
		String sorted_mex = String.join("\n",  mex);
		String sorted_mex_delayed = String.join("\n", mex_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		YYZ
		ArrayList<String> yyz = new ArrayList<String>();
		ArrayList<String> yyz_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("YYZ")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				yyz.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				yyz_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(yyz);
		Collections.sort(yyz_delayed);
		String sorted_yyz = String.join("\n",  yyz);
		String sorted_yyz_delayed = String.join("\n", yyz_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		JFK
		ArrayList<String> jfk = new ArrayList<String>();
		ArrayList<String> jfk_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("JFK")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				jfk.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				jfk_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(jfk);
		Collections.sort(jfk_delayed);
		String sorted_jfk = String.join("\n",  jfk);
		String sorted_jfk_delayed = String.join("\n", jfk_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		DUB
		ArrayList<String> dub = new ArrayList<String>();
		ArrayList<String> dub_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("DUB")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				dub.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				dub_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(dub);
		Collections.sort(dub_delayed);
		String sorted_dub = String.join("\n",  dub);
		String sorted_dub_delayed = String.join("\n", dub_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		AMS
		ArrayList<String> ams = new ArrayList<String>();
		ArrayList<String> ams_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("AMS")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				ams.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				ams_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(ams);
		Collections.sort(ams_delayed);
		String sorted_ams = String.join("\n",  ams);
		String sorted_ams_delayed = String.join("\n", ams_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		LIS
		ArrayList<String> lis = new ArrayList<String>();
		ArrayList<String> lis_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("LIS")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				lis.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				lis_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(lis);
		Collections.sort(lis_delayed);
		String sorted_lis = String.join("\n",  lis);
		String sorted_lis_delayed = String.join("\n", lis_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		RAK
		ArrayList<String> rak = new ArrayList<String>();
		ArrayList<String> rak_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("RAK")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				rak.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				rak_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(rak);
		Collections.sort(rak_delayed);
		String sorted_rak = String.join("\n",  rak);
		String sorted_rak_delayed = String.join("\n", rak_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		MAD
		ArrayList<String> mad = new ArrayList<String>();
		ArrayList<String> mad_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("MAD")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				mad.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				mad_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(mad);
		Collections.sort(mad_delayed);
		String sorted_mad = String.join("\n",  mad);
		String sorted_mad_delayed = String.join("\n", mad_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		FCO
		ArrayList<String> fco = new ArrayList<String>();
		ArrayList<String> fco_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("FCO")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				fco.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				fco_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(fco);
		Collections.sort(fco_delayed);
		String sorted_fco = String.join("\n",  fco);
		String sorted_fco_delayed = String.join("\n", fco_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		CDG
		ArrayList<String> cdg = new ArrayList<String>();
		ArrayList<String> cdg_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("CDG")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				cdg.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				cdg_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(cdg);
		Collections.sort(cdg_delayed);
		String sorted_cdg = String.join("\n",  cdg);
		String sorted_cdg_delayed = String.join("\n", cdg_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		ATH
		ArrayList<String> ath = new ArrayList<String>();
		ArrayList<String> ath_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("ATH")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				ath.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				ath_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(ath);
		Collections.sort(ath_delayed);
		String sorted_ath = String.join("\n",  ath);
		String sorted_ath_delayed = String.join("\n", ath_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		DXB
		ArrayList<String> dxb = new ArrayList<String>();
		ArrayList<String> dxb_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("DXB")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				dxb.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				dxb_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(dxb);
		Collections.sort(dxb_delayed);
		String sorted_dxb = String.join("\n",  dxb);
		String sorted_dxb_delayed = String.join("\n", dxb_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		DEL
		ArrayList<String> del = new ArrayList<String>();
		ArrayList<String> del_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("DEL")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				del.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				del_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(del);
		Collections.sort(del_delayed);
		String sorted_del = String.join("\n",  del);
		String sorted_del_delayed = String.join("\n", del_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		HKG
		ArrayList<String> hkg = new ArrayList<String>();
		ArrayList<String> hkg_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("HKG")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				hkg.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				hkg_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(hkg);
		Collections.sort(hkg_delayed);
		String sorted_hkg = String.join("\n",  hkg);
		String sorted_hkg_delayed = String.join("\n", hkg_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		HND
		ArrayList<String> hnd = new ArrayList<String>();
		ArrayList<String> hnd_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("HND")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				hnd.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				hnd_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(hnd);
		Collections.sort(hnd_delayed);
		String sorted_hnd = String.join("\n",  hnd);
		String sorted_hnd_delayed = String.join("\n", hnd_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		SVO
		ArrayList<String> svo = new ArrayList<String>();
		ArrayList<String> svo_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("SVO")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				svo.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				svo_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(svo);
		Collections.sort(svo_delayed);
		String sorted_svo = String.join("\n",  svo);
		String sorted_svo_delayed = String.join("\n", svo_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		CAI
		ArrayList<String> cai = new ArrayList<String>();
		ArrayList<String> cai_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("CAI")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				cai.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				cai_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(cai);
		Collections.sort(cai_delayed);
		String sorted_cai = String.join("\n",  cai);
		String sorted_cai_delayed = String.join("\n", cai_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		LOS
		ArrayList<String> los = new ArrayList<String>();
		ArrayList<String> los_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("LOS")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				los.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				los_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(los);
		Collections.sort(los_delayed);
		String sorted_los = String.join("\n",  los);
		String sorted_los_delayed = String.join("\n", los_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		BKK
		ArrayList<String> bkk = new ArrayList<String>();
		ArrayList<String> bkk_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("BKK")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				bkk.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				bkk_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(bkk);
		Collections.sort(bkk_delayed);
		String sorted_bkk = String.join("\n",  bkk);
		String sorted_bkk_delayed = String.join("\n", bkk_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		GRU
		ArrayList<String> gru = new ArrayList<String>();
		ArrayList<String> gru_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("GRU")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				gru.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				gru_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(gru);
		Collections.sort(gru_delayed);
		String sorted_gru = String.join("\n",  gru);
		String sorted_gru_delayed = String.join("\n", gru_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		JNB
		ArrayList<String> jnb = new ArrayList<String>();
		ArrayList<String> jnb_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("JNB")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				jnb.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				jnb_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(jnb);
		Collections.sort(jnb_delayed);
		String sorted_jnb = String.join("\n",  jnb);
		String sorted_jnb_delayed = String.join("\n", jnb_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
//		SYD
		ArrayList<String> syd = new ArrayList<String>();
		ArrayList<String> syd_delayed = new ArrayList<String>();
		
		for (int i = 0; i < data.size(); i++) {
			
			LocalDate pDate = LocalDate.parse(date[i], d);
			LocalTime pTime = LocalTime.parse(depTime[i], t);
			
			if (!ld.isEqual(pDate)) {continue;} 
			if (lt.isAfter(pTime)) {continue;}
			if (dAp[i].contains("SYD")) {
				
				long x = Long.valueOf(delay[i]);
				
				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
				
				if (dTime.plusMinutes(x).isBefore(dTime.plusMinutes(30))) {
				String onTime = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" on time\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				syd.add(onTime);
				}
				
				if (dTime.plusMinutes(x).isAfter(dTime.plusMinutes(30))) {
				
				String onDelay = ("\n"+dTime+" Flight " + flightNo[i] + " from " +dCt[i]+"\n"
						+"Arriving to "+aCt[i]+" at "+ aTime+" delayed\n"
								+"Airline "+airline[i]+"\n"
						+"-----------------------------------------------");
				syd_delayed.add(onDelay);
				}
					
			}	
		}
		Collections.sort(syd);
		Collections.sort(syd_delayed);
		String sorted_syd = String.join("\n",  syd);
		String sorted_syd_delayed = String.join("\n", syd_delayed);
//		-----------------------------------------------------------------------------------------------------------------------------------
		
		
//		-----------------------------------------------------------------------------------------------------------------------------------
//		-----------------------------------------------------------------------------------------------------------------------------------
//		GUI specific code Starts here
		JLayeredPane layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 1476, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 943, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		Image img = new ImageIcon(this.getClass().getResource("/map.png")).getImage();
		
		
		
		TextArea sidePanel = new TextArea();
		sidePanel.setText("");
		sidePanel.setFont(new Font("Monospaced", Font.BOLD, 16));
		sidePanel.setEditable(false);
		sidePanel.setBackground(Color.WHITE);
		sidePanel.setBounds(969, 87, 497, 846);
		layeredPane.add(sidePanel);
		// BCL departures and delays as default data
		TextArea delayPanel = new TextArea();
		delayPanel.setBounds(10, 586, 947, 347);
		delayPanel.setBackground(Color.LIGHT_GRAY);
		delayPanel.setFont(new Font("Monospaced", Font.BOLD, 16));
		sidePanel.setText("Departures from BCL\n"+sorted_bcl+"\n");
		delayPanel.setText("Delayed flights from BCL\n"+sorted_bcl_delayed);
		delayPanel.setEditable(false);
		sidePanel.setCaretPosition (1);
		delayPanel.setCaretPosition (1);
		layeredPane.add(delayPanel);
		

		// Introduce data inside sidePanel on-click	
		JLabel BCL = new JLabel("BCL");
		BCL.setBounds(420, 169, 30, 23);
		BCL.setFont(new Font("Tahoma", Font.BOLD, 11));
		BCL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from BCL\n"+sorted_bcl+"\n");
				delayPanel.setText("Delayed flights from BCL\n"+sorted_bcl_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel AMS = new JLabel("AMS");
		AMS.setBounds(455, 169, 32, 23);
		AMS.setFont(new Font("Tahoma", Font.BOLD, 11));
		AMS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from AMS\n"+sorted_ams+"\n");
				delayPanel.setText("Delayed flights from AMS\n"+sorted_ams_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel DUB = new JLabel("DUB");
		DUB.setBounds(393, 169, 24, 23);
		DUB.setFont(new Font("Tahoma", Font.BOLD, 11));
		DUB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from DUB\n"+sorted_dub+"\n");
				delayPanel.setText("Delayed flights from DUB\n"+sorted_dub_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel ATH = new JLabel("ATH");
		ATH.setBounds(494, 218, 28, 23);
		ATH.setFont(new Font("Tahoma", Font.BOLD, 11));
		ATH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from ATH\n"+sorted_ath+"\n");
				delayPanel.setText("Delayed flights from ATH\n"+sorted_ath_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel BKK = new JLabel("BKK");
		BKK.setBounds(734, 301, 29, 23);
		BKK.setFont(new Font("Tahoma", Font.BOLD, 11));
		BKK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from BKK\n"+sorted_bkk+"\n");
				delayPanel.setText("Delayed flights from BKK\n"+sorted_bkk_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel CAI = new JLabel("CAI");
		CAI.setBounds(509, 263, 25, 23);
		CAI.setFont(new Font("Tahoma", Font.BOLD, 11));
		CAI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from CAI\n"+sorted_cai+"\n");
				delayPanel.setText("Delayed flights from CAI\n"+sorted_cai_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel DEL = new JLabel("DEL");
		DEL.setBounds(657, 252, 28, 23);
		DEL.setFont(new Font("Tahoma", Font.BOLD, 11));
		DEL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from DEL\n"+sorted_del+"\n");
				delayPanel.setText("Delayed flights from DEL\n"+sorted_del_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel DXB = new JLabel("DXB");
		DXB.setBounds(588, 263, 29, 23);
		DXB.setFont(new Font("Tahoma", Font.BOLD, 11));
		DXB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from DXB\n"+sorted_dxb+"\n");
				delayPanel.setText("Delayed flights from DXB\n"+sorted_dxb_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel HKG = new JLabel("HKG");
		HKG.setBounds(774, 266, 29, 23);
		HKG.setFont(new Font("Tahoma", Font.BOLD, 11));
		HKG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from HKG\n"+sorted_hkg+"\n");
				delayPanel.setText("Delayed flights from HKG\n"+sorted_hkg_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel JNB = new JLabel("JNB");
		JNB.setBounds(509, 437, 29, 23);
		JNB.setFont(new Font("Tahoma", Font.BOLD, 11));
		JNB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from JNB\n"+sorted_jnb+"\n");
				delayPanel.setText("Delayed flights from JNB\n"+sorted_jnb_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel LOS = new JLabel("LOS");
		LOS.setBounds(437, 320, 29, 23);
		LOS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LOS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from LOS\n"+sorted_los+"\n");
				delayPanel.setText("Delayed flights from LOS\n"+sorted_los_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel LVS = new JLabel("LVS");
		LVS.setBounds(92, 226, 27, 23);
		LVS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LVS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from LVS\n"+sorted_lvs+"\n");
				delayPanel.setText("Delayed flights from LVS\n"+sorted_lvs_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel LIS = new JLabel("LIS");
		LIS.setBounds(391, 211, 26, 23);
		LIS.setFont(new Font("Tahoma", Font.BOLD, 11));
		LIS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from LIS\n"+sorted_lis+"\n");
				delayPanel.setText("Delayed flights from LIS\n"+sorted_lis_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel MAD = new JLabel("MAD");
		MAD.setBounds(416, 212, 31, 23);
		MAD.setFont(new Font("Tahoma", Font.BOLD, 11));
		MAD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from MAD\n"+sorted_mad+"\n");
				delayPanel.setText("Delayed flights from MAD\n"+sorted_mad_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel RAK = new JLabel("RAK");
		RAK.setBounds(400, 234, 30, 23);
		RAK.setFont(new Font("Tahoma", Font.BOLD, 11));
		RAK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from RAK\n"+sorted_rak+"\n");
				delayPanel.setText("Delayed flights from RAK\n"+sorted_rak_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel MEX = new JLabel("MEX");
		MEX.setBounds(114, 275, 30, 23);
		MEX.setFont(new Font("Tahoma", Font.BOLD, 11));
		MEX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from MEX\n"+sorted_mex+"\n");
				delayPanel.setText("Delayed flights from MEX\n"+sorted_mex_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel SVO = new JLabel("SVO");
		SVO.setBounds(527, 156, 31, 23);
		SVO.setFont(new Font("Tahoma", Font.BOLD, 11));
		SVO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from SVO\n"+sorted_svo+"\n");
				delayPanel.setText("Delayed flights from SVO\n"+sorted_svo_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel JFK = new JLabel("JFK");
		JFK.setBounds(221, 202, 29, 23);
		JFK.setFont(new Font("Tahoma", Font.BOLD, 11));
		JFK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from JFK\n"+sorted_jfk+"\n");
				delayPanel.setText("Delayed flights from JFK\n"+sorted_jfk_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel CDG = new JLabel("CDG");
		CDG.setBounds(437, 189, 26, 23);
		CDG.setFont(new Font("Tahoma", Font.BOLD, 11));
		CDG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from CDG\n"+sorted_cdg+"\n");
				delayPanel.setText("Delayed flights from CDG\n"+sorted_cdg_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel FCO = new JLabel("FCO");
		FCO.setBounds(463, 203, 29, 23);
		FCO.setFont(new Font("Tahoma", Font.BOLD, 11));
		FCO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from FCO\n"+sorted_fco+"\n");
				delayPanel.setText("Delayed flights from FCO\n"+sorted_fco_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel GRU = new JLabel("GRU");
		GRU.setBounds(276, 427, 30, 23);
		GRU.setFont(new Font("Tahoma", Font.BOLD, 11));
		GRU.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from GRU\n"+sorted_gru+"\n");
				delayPanel.setText("Delayed flights from GRU\n"+sorted_gru_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel ARN = new JLabel("ARN");
		ARN.setBounds(470, 146, 30, 23);
		ARN.setFont(new Font("Tahoma", Font.BOLD, 11));
		ARN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from ARN\n"+sorted_arn+"\n");
				delayPanel.setText("Delayed flights from ARN\n"+sorted_arn_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel SYD = new JLabel("SYD");
		SYD.setBounds(876, 458, 30, 23);
		SYD.setFont(new Font("Tahoma", Font.BOLD, 11));
		SYD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from SYD\n"+sorted_syd+"\n");
				delayPanel.setText("Delayed flights from SYD\n"+sorted_syd_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel DFW = new JLabel("DFW");
		DFW.setBounds(132, 242, 32, 23);
		DFW.setFont(new Font("Tahoma", Font.BOLD, 11));
		DFW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from DFW\n"+sorted_dfw+"\n");
				delayPanel.setText("Delayed flights from DFW\n"+sorted_dfw_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel HND = new JLabel("HND");
		HND.setBounds(830, 218, 31, 23);
		HND.setFont(new Font("Tahoma", Font.BOLD, 11));
		HND.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from HND\n"+sorted_hnd+"\n");
				delayPanel.setText("Delayed flights from HND\n"+sorted_hnd_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
		JLabel YYZ = new JLabel("YYZ");
		YYZ.setBounds(195, 193, 26, 23);
		YYZ.setFont(new Font("Tahoma", Font.BOLD, 11));
		YYZ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sidePanel.setBackground(Color.LIGHT_GRAY);
				sidePanel.setText("Departures from YYZ\n"+sorted_yyz+"\n");
				delayPanel.setText("Delayed flights from YYZ\n"+sorted_yyz_delayed);
				delayPanel.setCaretPosition (1);
				sidePanel.setCaretPosition (1);
				
			}
		});
		
//		Current date
		JLabel today = new JLabel(now);
		today.setBounds(12, 12, 168, 39);
		today.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JLabel lblNewLabel = new JLabel("Delayed departures");
		lblNewLabel.setBounds(10, 559, 234, 31);
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		layeredPane.setLayout(null);
		layeredPane.add(lblNewLabel);
		layeredPane.add(today);
		layeredPane.add(ARN);
		layeredPane.add(LVS);
		layeredPane.add(DFW);
		layeredPane.add(MEX);
		layeredPane.add(YYZ);
		layeredPane.add(JFK);
		layeredPane.add(DUB);
		layeredPane.add(BCL);
		layeredPane.add(AMS);
		layeredPane.add(LIS);
		layeredPane.add(RAK);
		layeredPane.add(MAD);
		layeredPane.add(FCO);
		layeredPane.add(CDG);
		layeredPane.add(ATH);
		layeredPane.add(DXB);
		layeredPane.add(DEL);
		layeredPane.add(HKG);
		layeredPane.add(HND);
		layeredPane.add(SVO);
		layeredPane.add(CAI);
		layeredPane.add(LOS);
		layeredPane.add(BKK);
		layeredPane.add(GRU);
		layeredPane.add(JNB);
		layeredPane.add(SYD);
		
		
		
		JLabel map = new JLabel("");
		map.setBounds(10, 61, 947, 519);
		map.setLabelFor(map);
		map.setIcon(new ImageIcon(img));
		layeredPane.add(map);
		
		JLabel lblBclDepartures = new JLabel("BCL Airport departures");
		lblBclDepartures.setBounds(575, 16, 234, 39);
		layeredPane.add(lblBclDepartures);
		
		JButton btnNewButton = new JButton("Exit departures");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.exit(0); // Changed to frame.dispose() to avoid conflicts with parent program once components are merged
				frame.dispose(); 
			}
		});
		btnNewButton.setBounds(1310, 12, 154, 31);
		layeredPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("To see departure information below, click on an airport from the map");
		lblNewLabel_1.setBounds(969, 61, 497, 15);
		layeredPane.add(lblNewLabel_1);
		
		
		frame.getContentPane().setLayout(groupLayout);
	}
}
