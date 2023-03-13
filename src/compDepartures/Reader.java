// Unused
package compDepartures;
import java.util.*;
import java.io.*;
//949000

// For some reason calling this class makes the program really slow. 
// Write everything to App.java until learn how to avoid the bottleneck

public class Reader {
	
//		Testing as main for console readings
//		public static void main(String[] args) throws FileNotFoundException {
//		public static void main(String[] args) throws IOException {
	
		public ArrayList<String[]> data() throws IOException {
		
		ArrayList<String[]> data = new ArrayList<String[]>();
		
//		Slow method
//		File csv = new File("Flights.csv");
//		
//		@SuppressWarnings("resource")
//		Scanner reader = new Scanner(csv);
//		

//		while (reader.hasNextLine()) {
//			
//			String row = reader.nextLine();
//			String col[] = row.split(",");
//			data.add(col);
//			
//		}

//		Unbelievably faster. 
		 
		BufferedReader reader = new BufferedReader(new FileReader("Flights.csv"));
		String line = "";
		
		while ((line = reader.readLine()) != null) {
			
			String col[] = line.split(",");
			data.add(col);
							
		}


		reader.close();
		return data;
	}
		
//		Encapsulating each column into its own function
//		
//		public  String[] date() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] date = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//				
//				date[i] = all.get(i)[0]; 		
//				
//			}
//			
//			// Date needs to be parsed since the format is dd/mm/yyy
//			// https://www.baeldung.com/java-string-to-date
//			
//			return date;
//		}
//		
//		public  String[] depTime() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] depTime = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//				
//				depTime[i] = all.get(i)[1];
//				
//			}
//			
//			return depTime;
//		}
//		
//		public  String[] arrTime() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] arrTime= new String[all.size()];
//
//			for (int i = 0; i < all.size(); i++) {
//
//				arrTime[i] = all.get(i)[2];
//
//			}
//			
//			return arrTime;
//		}
//		
//		public  String[] duration() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] duration = new String[all.size()];
//
//			for (int i = 0; i < all.size(); i++) {
//
//				duration[i] = all.get(i)[3];
//
//			}
//			
//			return duration;
//		}
//		
//		public  String[] miles() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] miles = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//				
//				miles[i] = all.get(i)[4];
//				
//			}
//			
//			return miles;
//		}
//		
//		public  String[] delay() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] delay = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//				
//				delay[i] = all.get(i)[5];
//				
//			}
//			
//			return delay;
//		}
//		
//		public  String[] depAirport() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] depAirport = new String[all.size()];
//
//			for (int i = 0; i < all.size(); i++) {
//
//				depAirport[i] = all.get(i)[6];
//				
//			}
//			
//			return depAirport;
//		}
//		
//		public  String[] depCity() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] depCity = new String[all.size()];
//
//			for (int i = 0; i < all.size(); i++) {
//				
//				depCity[i] = all.get(i)[7];
//				
//			}
//			
//			return depCity;
//		}
//		
//		public  String[] arrAirport() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//			
//			String[] arrAirport = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//
//				arrAirport[i] = all.get(i)[8];
//				
//			}
//			
//			return arrAirport;
//		}
//		
//		public  String[] arrCity() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//
//			String[] arrCity = new String[all.size()];
//
//			for (int i = 0; i < all.size(); i++) {
//
//				arrCity[i] = all.get(i)[9];
//				
//			}
//			
//			return arrCity;
//		}
//		
//		public  String[] flightNo() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//
//			String[] flightNo = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//				
//				flightNo[i] = all.get(i)[10];
//				
//			}
//			
//			return flightNo;
//		}
//
//		public  String[] airline() throws IOException {
//			
//			Reader alls = new Reader();
//			
//			ArrayList<String[]> all = alls.data();
//
//			String[] airline = new String[all.size()];
//			
//			for (int i = 0; i < all.size(); i++) {
//
//				airline[i] = all.get(i)[11];
//				
//			}
//			
//			return airline;
//		}
}	
		
		

