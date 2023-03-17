////unused
//// painfully slow because of the bottleneck issue
//package compDepartures;
//
//import java.io.IOException;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.util.Timer;
//
////String date = data.get(i)[0]
////String depTime = data.get(i)[1]
////String arrTime = data.get(i)[2]
////String duration = data.get(i)[3]
////String miles = data.get(i)[4]
////String delay = data.get(i)[5]
////String depAir = data.get(i)[6]
////String depCt = data.get(i)[7]
////String arrAir = data.get(i)[8]
////String arrCt = data.get(i)[9]
////String flightNo = data.get(i)[10]
////String airline = data.get(i)[11]
//
//@SuppressWarnings("unused")
//public class TimeParser {
//
//	public static void main(String[] args) throws IOException {
////
////		The interface should also list all flights from BCL 
////		airport for the current day, that are departing after the current live time.
////		This list should update every 5 minutes. All departure flights from BCL 
////		airport within the next 30 minutes should be displayed in red. 
//		
////		Timer myTimer = new Timer ();
////		TimerTask myTask = new TimerTask () {
////		    @Override
////		    public void run () {
////		        // your code 
////		        callSpeak().execute() // Your method
////		    }
////		};
////
////		myTimer.scheduleAtFixedRate(myTask , 0l, 5 * (60*1000)); // Runs every 5 mins
//
//			Reader x = new Reader();
//			
//			
//			
//			LocalDate ld = LocalDate.now();
//			LocalTime lt = LocalTime.now();
//
//			DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/uuuu");
//			DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm");
//			
//			System.out.println("Flights for today");
//			
//
//			for (int i = 312000; i < x.data().size() ; i++) {
//				
//				String dCt = x.data().get(i)[7];
//				String aCt = x.data().get(i)[9];
//				String dAp = x.data().get(i)[6];
//				String aAp = x.data().get(i)[8];
//				
//				LocalDate date = LocalDate.parse(x.data().get(i)[0], d);
//				
//				if (ld.isEqual(date)) {
//					
//					boolean check = ld.isEqual(date);
//					System.out.println(check);
//					
//					LocalTime dTime = LocalTime.parse(x.data().get(i)[1], t);
//					LocalTime aTime = LocalTime.parse(x.data().get(i)[2], t);
//					
//					System.out.println(dCt+"("+dAp+") to "+aCt+"("+aAp+") -- "+dTime+" to "+aTime);
//				}
//				
//			}
//			
//			
////			LocalDate xDate = LocalDate.parse(x.date()[1], d);
////			LocalTime xTime = LocalTime.parse(x.depTime()[1], h);
////			System.out.println(xDate + " " + xTime);
////			
////			if (xDate.isBefore(lt)) {
////				System.out.println("This flight is from too long ago.\n"
////						+ "or your machine has the wrong time set.");
////			String now = lt.format(dmy);
////			String y = x.date()[3];
////			String z = y.format(formatter);
//			
////			}
//		
//		
//	}
//
//}


