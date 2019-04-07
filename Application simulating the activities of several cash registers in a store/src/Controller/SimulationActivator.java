package Controller;

import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;

import Exceptions.IllegalInputException;
import Model.Processor;
import Model.Queue;
import View.View;


public class SimulationActivator {
	private View theView;
	private Processor theProcessor;
		
	public SimulationActivator(View theView, Processor theProcessor) {
		this.theView = theView;
		this.theProcessor = theProcessor;
		
		theView.startSimulation(new StartSimulationListener());
		theView.determineSpeed(new DetermineSpeedListener());
	}
	
	public class StartSimulationListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				if((theView.getMinArrTime() > theView.getMaxArrTime()) || (theView.getMinServTime() > theView.getMaxServTime())
					|| !(theView.getNoOfQueues() >= 1 && theView.getNoOfQueues() <= 6) || theView.getMinArrTime() < 1 || theView.getMinServTime() < 1)
					throw new IllegalInputException("Invalid input!");
				else {
					theProcessor = new Processor(theView.getMinArrTime(), theView.getMaxArrTime(), theView.getMinServTime(), theView.getMaxServTime(), theView.getNoOfQueues(), theView.getSimulationInterval(), 1000, theView.getLogger());
					Thread thread = new Thread(theProcessor);
					thread.start();
					updateView();
				}
			}catch(IllegalInputException e) {
				theView.displayErrorMessage(e.getMessage());
			}
			
		}	
	}
	
	public class DetermineSpeedListener implements ChangeListener {
		public synchronized void stateChanged(ChangeEvent e) {
			if(!theView.getSpeedSlider().getValueIsAdjusting()) {
				theProcessor.setSpeed(theView.getSpeed());
				for(Queue queue : theProcessor.getQueues()) 
					queue.setSpeed(theView.getSpeed());
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void updateView() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {		
			public void run() {	
            	if(theProcessor.getCurrentTime() <= theProcessor.getSimulationInterval())
            		updateQueues();
            	else
            		timer.cancel();
			}
		};
        timer.scheduleAtFixedRate(task, 0, 5);
	}
	
	public void updateQueues() {
		if(!theProcessor.getQueues().isEmpty()) 
			if(theProcessor.getNoOfQueues()==1) 
				theView.getQueueOneArea().setText(theProcessor.getQueues().get(0).displayClients());
			else if(theProcessor.getNoOfQueues()==2) {
				theView.getQueueOneArea().setText(theProcessor.getQueues().get(0).displayClients());
				theView.getQueueTwoArea().setText(theProcessor.getQueues().get(1).displayClients());
			}
			else if(theProcessor.getNoOfQueues()==3) {
				theView.getQueueOneArea().setText(theProcessor.getQueues().get(0).displayClients());
				theView.getQueueTwoArea().setText(theProcessor.getQueues().get(1).displayClients());
				theView.getQueueThreeArea().setText(theProcessor.getQueues().get(2).displayClients());
			}
			else if(theProcessor.getNoOfQueues()==4) {
				theView.getQueueOneArea().setText(theProcessor.getQueues().get(0).displayClients());
				theView.getQueueTwoArea().setText(theProcessor.getQueues().get(1).displayClients());
				theView.getQueueThreeArea().setText(theProcessor.getQueues().get(2).displayClients());
				theView.getQueueFourArea().setText(theProcessor.getQueues().get(3).displayClients());
			}
			else if(theProcessor.getNoOfQueues()==5) {
				theView.getQueueOneArea().setText(theProcessor.getQueues().get(0).displayClients());
				theView.getQueueTwoArea().setText(theProcessor.getQueues().get(1).displayClients());
				theView.getQueueThreeArea().setText(theProcessor.getQueues().get(2).displayClients());
				theView.getQueueFourArea().setText(theProcessor.getQueues().get(3).displayClients());
				theView.getQueueFiveArea().setText(theProcessor.getQueues().get(4).displayClients());
			}
			else if(theProcessor.getNoOfQueues()==6) {
				theView.getQueueOneArea().setText(theProcessor.getQueues().get(0).displayClients());
				theView.getQueueTwoArea().setText(theProcessor.getQueues().get(1).displayClients());
				theView.getQueueThreeArea().setText(theProcessor.getQueues().get(2).displayClients());
				theView.getQueueFourArea().setText(theProcessor.getQueues().get(3).displayClients());
				theView.getQueueFiveArea().setText(theProcessor.getQueues().get(4).displayClients());
				theView.getQueueSixArea().setText(theProcessor.getQueues().get(5).displayClients());
			}
	}
}
