package compDepartures;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

@SuppressWarnings("unused")
public class TimeParser {

	public static void main(String[] args) {

			LocalDateTime lt = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss");
			String now = lt.format(formatter);
			System.out.println(now);
		
		
	}

}


