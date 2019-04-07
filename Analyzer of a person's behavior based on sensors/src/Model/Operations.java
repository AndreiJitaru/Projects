package Model;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.*;

public class Operations {

	List<MonitoredData> listOfData;
	
	public Operations() {
		listOfData = Operations.readData();
	}
	
	public static List<MonitoredData> readData() {
		try {
			return Files.lines(Paths.get("E:\\Proiecte_Andrei\\Stuff\\project5\\Activities.txt")).map(line -> line.split("		"))
																								 .map(x -> {return new MonitoredData(LocalDateTime.parse(x[0], DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")), 
																										 LocalDateTime.parse(x[1], DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")), x[2]);})
																								 .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void countDistinctDays() {
		long distinctDays = listOfData.stream()
									  .map(x -> {return x.getDayOfYearFromStartTime();})
									  .distinct()
									  .count();
		try {
			PrintWriter writer = new PrintWriter("OperationResults.txt", "UTF-8");
			writer.println("The number of distinct days that appear in the monitoring data is: " + distinctDays + ".");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Long> countOccurrencesForEachActivity(int permission){
		Map<String, Long> values =  listOfData.stream()
						 					  .collect(Collectors.groupingBy(x -> x.getActivityLabel(), Collectors.counting()));
		if(permission == 1) {
			try {
				PrintWriter writer = new PrintWriter("OperationResults.txt", "UTF-8");
				for (Entry<String, Long> entry : values.entrySet()) {
					writer.println("For activity " + entry.getKey() + " there are " + entry.getValue() + " occurrences.");
				}
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		return values;
	}
	
	public void searchForActivitiesWithTotalDurationLongerThanTenHours(){
		Map<String, Period> values =  listOfData.stream()
						 						.collect(Collectors.groupingBy(x -> x.getActivityLabel(), Collectors.summingInt(x-> x.getPeriodBetweenStartAndEndTimeInSeconds())))
						 						.entrySet()
						 						.stream()
						 						.filter(x -> x.getValue() > 36000)
						 						.collect(Collectors.toMap(e -> e.getKey(), e -> new Period((long)(e.getValue()*1000))));
		try {
			PrintWriter writer = new PrintWriter("OperationResults.txt", "UTF-8");
			for (Entry<String, Period> entry : values.entrySet()) {
				writer.println("For activity " + entry.getKey() + " the total duration is " + entry.getValue().getHours() + " hours, " + entry.getValue().getMinutes() + " minutes and " + entry.getValue().getSeconds() + " seconds.");
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	public void countActivitiesOccurrencesForEachDay(){
		Map<Long, Map<String, Long>> values =  listOfData.stream()
						 								 .collect(Collectors.groupingBy(x->x.getDayOfYearFromStartTime(),Collectors.groupingBy(x->x.getActivityLabel(),Collectors.counting())));
		try {
			PrintWriter writer = new PrintWriter("OperationResults.txt", "UTF-8");
			for (Entry<Long, Map<String, Long>> entry : values.entrySet()) {
		        Map<String, Long> childMap = entry.getValue();
		        writer.print("For day " + entry.getKey() + ": ");
		        for (Entry<String, Long> entry2 : childMap.entrySet()) 
		        	writer.println("activity " + entry2.getKey() + " occurred " + entry2.getValue() + " times ");
		        writer.println();

		    }
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public void filterActivities() { 
		List<String> values =  listOfData.stream()
						 				 .filter(x -> x.getPeriodBetweenStartAndEndTimeInSeconds() < 300)
						 				 .collect(Collectors.groupingBy(x -> x.getActivityLabel(), Collectors.counting()))
						 				 .entrySet()
						 				 .stream()
						 				 .filter(x -> x.getValue() >= 0.9*this.countOccurrencesForEachActivity(0).get(x.getKey()))
						 				 .map(x -> x.getKey())
						 				 .collect(Collectors.toList());
		try {
			PrintWriter writer = new PrintWriter("OperationResults.txt", "UTF-8");
			for(String value: values)
				writer.println("Activity " + value + " has  90% of the monitoring samples with duration less than 5 minutes.");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
		
}
