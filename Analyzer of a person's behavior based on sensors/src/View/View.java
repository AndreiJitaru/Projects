package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private JPanel panel = new JPanel();
	private JButton taskOneButton = new JButton("TASK 1");
	private JButton taskTwoButton = new JButton("TASK 2");
	private JButton taskThreeButton = new JButton("TASK 3");
	private JButton taskFourButton = new JButton("TASK 4");
	private JButton taskFiveButton = new JButton("TASK 5");

	
	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		panel.setPreferredSize(new Dimension(300, 250));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		taskOneButton.setMaximumSize(new Dimension(300, 50));
		taskTwoButton.setMaximumSize(new Dimension(300, 50));
		taskThreeButton.setMaximumSize(new Dimension(300, 50));
		taskFourButton.setMaximumSize(new Dimension(300, 50));
		taskFiveButton.setMaximumSize(new Dimension(300, 50));
		
		panel.add(taskOneButton);
		panel.add(taskTwoButton);
		panel.add(taskThreeButton);
		panel.add(taskFourButton);
		panel.add(taskFiveButton);
		
		this.add(panel);
		this.setVisible(true);
	}
	
	public void countDistinctDays(ActionListener listener) {
		taskOneButton.addActionListener(listener);
	}
	
	public void countOccurrencesForEachActivity(ActionListener listener) {
		taskTwoButton.addActionListener(listener);
	}
	
	public void countActivitiesOccurrencesForEachDay(ActionListener listener) {
		taskThreeButton.addActionListener(listener);
	}
	
	public void searchForActivitiesWithTotalDurationLongerThanTenHours(ActionListener listener) {
		taskFourButton.addActionListener(listener);
	}
	
	public void filterActivities(ActionListener listener) {
		taskFiveButton.addActionListener(listener);
	}
}