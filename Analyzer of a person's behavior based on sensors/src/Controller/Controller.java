package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Operations;
import View.View;

public class Controller {
	private Operations theOperations;
	private View theView;
	
	public Controller(Operations theOperations, View theView) {
		this.theOperations = theOperations;
		this.theView = theView;
		
		this.theView.countDistinctDays(new CountDistinctDaysListener());
		this.theView.countActivitiesOccurrencesForEachDay(new countActivitiesOccurrencesForEachDayListener());
		this.theView.countOccurrencesForEachActivity(new countOccurrencesForEachActivityListener());
		this.theView.filterActivities(new filterActivitiesListener());
		this.theView.searchForActivitiesWithTotalDurationLongerThanTenHours(new searchForActivitiesListener());
	}
	
	public class CountDistinctDaysListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theOperations.countDistinctDays();
		}
	}
	
	public class countActivitiesOccurrencesForEachDayListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theOperations.countActivitiesOccurrencesForEachDay();
		}
	}
	
	public class countOccurrencesForEachActivityListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theOperations.countOccurrencesForEachActivity(1);
		}
	}
	
	public class filterActivitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theOperations.filterActivities();
		}
	}
	
	public class searchForActivitiesListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theOperations.searchForActivitiesWithTotalDurationLongerThanTenHours();
		}
	}
}
