//package compDepartures;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//public class TEST {
//
//	public static void main(String[] args) throws IOException {
//
//		//String date = data.get(i)[0]
//		//String depTime = data.get(i)[1]
//		//String arrTime = data.get(i)[2]
//		//String duration = data.get(i)[3]
//		//String miles = data.get(i)[4]
//		//String delay = data.get(i)[5]
//		//String depAir = data.get(i)[6]
//		//String depCt = data.get(i)[7]
//		//String arrAir = data.get(i)[8]
//		//String arrCt = data.get(i)[9]
//		//String flightNo = data.get(i)[10]
//		//String airline = data.get(i)[11]
//		
//ArrayList<String[]> data = new ArrayList<String[]>();
//		 
//		 
//		BufferedReader reader = new BufferedReader(new FileReader("Flights.csv"));
//		String line = "";
//		
//		while ((line = reader.readLine()) != null) {
//			
//			String col[] = line.split(",");
//			data.add(col);
//							
//		}
//		reader.close();
//		
//		LocalDate ld = LocalDate.now();
//		LocalTime lt = LocalTime.now();
//
//		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/uuuu");
//		DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm");
//		
//		System.out.println("Flights for today");
//		
//
//		for (int i = 0; i < data.size() ; i++) {
//			
//			String dCt = data.get(i)[7];
//			String aCt = data.get(i)[9];
//			String dAp = data.get(i)[6];
//			String aAp = data.get(i)[8];
//			
//			LocalDate date = LocalDate.parse(data.get(i)[0], d);
//			
//			if (ld.isEqual(date)) {
//				
//				boolean check = ld.isEqual(date);
//				System.out.println(check);
//				
//				LocalTime dTime = LocalTime.parse(data.get(i)[1], t);
//				LocalTime aTime = LocalTime.parse(data.get(i)[2], t);
//				
//				System.out.println(dCt+"("+dAp+") to "+aCt+"("+aAp+") -- "+dTime+" to "+aTime);
//			}
//			
//		}
//		
//for (int i = 0; i < data.size(); i++) {
//			
//			LocalDate pDate = LocalDate.parse(date[i], d);
//			
//			if (!ld.isEqual(pDate)) {
//				continue;
//			} 
//			
//			if (dAp[i].contains("BCL")) {
//				
//				LocalTime dTime = LocalTime.parse(depTime[i].toString(), t);
//				LocalTime aTime = LocalTime.parse(arrTime[i].toString(), t);
//				
//				System.out.println(dCt[i]+"("+dAp[i]+") to "+aCt[i]+"("+aAp[i]+") -- "+dTime+" to "+aTime);		
//			}
//		
////		
//				
//		}
//		
//	}
//
//}
