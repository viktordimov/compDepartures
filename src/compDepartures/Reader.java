// Unused
package compDepartures;
import java.util.*;
import java.io.*;
//949000
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// For some reason calling this class makes the program really slow. 
// Write everything to App.java until learn how to avoid the bottleneck

public class Reader extends App {
	
//		Testing as main for console readings
//		public static void main(String[] args) throws FileNotFoundException {
//		public static void main(String[] args) throws IOException {
	
		public static void main(String[] args) throws IOException {
		
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
			DateTimeFormatter del = DateTimeFormatter.ofPattern("mm");
			

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
			
			System.out.println("Delayed flights");
				
			for (int i = 0; i < data.size(); i++) {
				
				LocalDate pDate = LocalDate.parse(date[i], d);
				
				if (!ld.isEqual(pDate)) {
					continue;
				} 
				
				if (dAp[i].contains("BCL")) {
					
					long x = Long.valueOf(delay[i]);
					
					LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
					LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
					
					if (aTime.plusMinutes(x).isAfter(aTime.plusMinutes(30))) {
					
					String displayDelayed = (dTime+" "+dAp[i]+" "+aAp[i]+" "+aTime+" Delay "+aTime.plusMinutes(x));
					}
				}	
			}
		}
}