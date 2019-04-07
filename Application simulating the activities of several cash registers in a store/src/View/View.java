package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")

public class View extends JFrame {

	JPanel mainPanel = new JPanel();
	
	JPanel dataPanel = new JPanel();
	JPanel logPanel = new JPanel();
	JPanel queuesPanel = new JPanel();
	
	JLabel minArrTimeLabel = new JLabel("Min Arrival Time: ");
	JLabel maxArrTimeLabel = new JLabel("Max Arrival Time: ");
	JLabel minServTimeLabel = new JLabel("Min Service Time: ");
	JLabel maxServTimeLabel = new JLabel("Min Arrival Time: ");
	JLabel noOfQueuesLabel = new JLabel("Number of queues: ");
	JLabel simulationIntervalLabel = new JLabel("Simulation interval: ");
	JTextField minArrTimeTextField = new JTextField();
	JTextField maxArrTimeTextField = new JTextField();
	JTextField minServTimeTextField = new JTextField();
	JTextField maxServTimeTextField = new JTextField();
	JTextField noOfQueuesTextField = new JTextField();
	JTextField simulationIntervalTextField = new JTextField();
	JSlider speed = new JSlider(JSlider.HORIZONTAL,100,1900,1000);
	JButton startButton = new JButton("START");

	JTextArea loggerArea = new JTextArea();
	
	JTextArea queueOneArea = new JTextArea();
	JTextArea queueTwoArea = new JTextArea();
	JTextArea queueThreeArea = new JTextArea();
	JTextArea queueFourArea = new JTextArea();
	JTextArea queueFiveArea = new JTextArea();
	JTextArea queueSixArea = new JTextArea();

    public View() {
    	this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel auxPanel1 = new JPanel(new FlowLayout());
        JPanel auxPanel2 = new JPanel(new FlowLayout());
        JPanel auxPanel3 = new JPanel(new FlowLayout());
        JPanel auxPanel4 = new JPanel(new FlowLayout());
        JPanel auxPanel5 = new JPanel();
        JPanel auxPanel6 = new JPanel();
        auxPanel5.setLayout(new BoxLayout(auxPanel5, BoxLayout.PAGE_AXIS));
        
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
        
        
        minArrTimeTextField.setPreferredSize(new Dimension(100, 20));
    	maxArrTimeTextField.setPreferredSize(new Dimension(100, 20));
    	minServTimeTextField.setPreferredSize(new Dimension(100, 20));
    	maxServTimeTextField.setPreferredSize(new Dimension(100, 20));
    	noOfQueuesTextField.setPreferredSize(new Dimension(100, 20));
    	simulationIntervalTextField.setPreferredSize(new Dimension(100, 20));
        startButton.setPreferredSize(new Dimension(100, 30));
        speed.setMajorTickSpacing(450);
		speed.setPaintLabels(true);
		speed.setPaintTicks(true);
		speed.setInverted(true);
        
        
        auxPanel1.add(minArrTimeLabel);
        auxPanel1.add(minArrTimeTextField);
        auxPanel1.add(maxArrTimeLabel);
        auxPanel1.add(maxArrTimeTextField);
        auxPanel2.add(minServTimeLabel);
        auxPanel2.add(minServTimeTextField);
        auxPanel2.add(maxServTimeLabel);
        auxPanel2.add(maxServTimeTextField);
        auxPanel3.add(noOfQueuesLabel);
        auxPanel3.add(noOfQueuesTextField);
        auxPanel3.add(simulationIntervalLabel);
        auxPanel3.add(simulationIntervalTextField);
        auxPanel4.add(startButton);
        
        JLabel speedLabel = new JLabel("Speed:");
        auxPanel6.add(speedLabel);
        auxPanel5.add(speed);
        
        dataPanel.add(auxPanel1);
        dataPanel.add(auxPanel2);
        dataPanel.add(auxPanel3);
        dataPanel.add(auxPanel6);
        dataPanel.add(auxPanel5);
        dataPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dataPanel.add(auxPanel4);
        
        JScrollPane loggerScroll = new JScrollPane(loggerArea);
        loggerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.add(loggerScroll);
        loggerScroll.setPreferredSize(new Dimension(700, 300));
        
        
        JPanel auxLeftPanel = new JPanel();
        auxLeftPanel.setLayout(new BoxLayout(auxLeftPanel, BoxLayout.LINE_AXIS));
        auxLeftPanel.setPreferredSize(new Dimension(500, 600));
        
        auxLeftPanel.add(dataPanel);
        auxLeftPanel.add(logPanel);
        
        queueOneArea.setPreferredSize(new Dimension(120, 500));
        queueTwoArea.setPreferredSize(new Dimension(120, 500));
        queueThreeArea.setPreferredSize(new Dimension(120, 500));
        queueFourArea.setPreferredSize(new Dimension(120, 500));
        queueFiveArea.setPreferredSize(new Dimension(120, 500));
        queueSixArea.setPreferredSize(new Dimension(120, 500));
        
        JLabel queueOneLabel = new JLabel("Queue 1");
        JLabel queueTwoLabel = new JLabel("Queue 2");
        JLabel queueThreeLabel = new JLabel("Queue 3");
        JLabel queueFourLabel = new JLabel("Queue 4");
        JLabel queueFiveLabel = new JLabel("Queue 5");
        JLabel queueSixLabel = new JLabel("Queue 6");
        
        queuesPanel.setLayout(new BoxLayout(queuesPanel, BoxLayout.LINE_AXIS));
        
        JPanel queueOneAuxPanel = new JPanel();
        JPanel panel1 = new JPanel();
        panel1.add(queueOneLabel);
        queueOneAuxPanel.setLayout(new BoxLayout(queueOneAuxPanel, BoxLayout.Y_AXIS));
        queueOneAuxPanel.add(panel1);
        queueOneAuxPanel.add(queueOneArea);
        
        
        JPanel queueTwoAuxPanel = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.add(queueTwoLabel);
        queueTwoAuxPanel.setLayout(new BoxLayout(queueTwoAuxPanel, BoxLayout.Y_AXIS));
        queueTwoAuxPanel.add(panel2);
        queueTwoAuxPanel.add(queueTwoArea);
        
        
        JPanel queueThreeAuxPanel = new JPanel();
        JPanel panel3 = new JPanel();
        panel3.add(queueThreeLabel);
        queueThreeAuxPanel.setLayout(new BoxLayout(queueThreeAuxPanel, BoxLayout.Y_AXIS));
        queueThreeAuxPanel.add(panel3);
        queueThreeAuxPanel.add(queueThreeArea);
        
        
        JPanel queueFourAuxPanel = new JPanel();
        JPanel panel4 = new JPanel();
        panel4.add(queueFourLabel);
        queueFourAuxPanel.setLayout(new BoxLayout(queueFourAuxPanel, BoxLayout.Y_AXIS));
        queueFourAuxPanel.add(panel4);
        queueFourAuxPanel.add(queueFourArea);
        
        
        JPanel queueFiveAuxPanel = new JPanel();
        JPanel panel5 = new JPanel();
        panel5.add(queueFiveLabel);
        queueFiveAuxPanel.setLayout(new BoxLayout(queueFiveAuxPanel, BoxLayout.Y_AXIS));
        queueFiveAuxPanel.add(panel5);
        queueFiveAuxPanel.add(queueFiveArea);
        
        
        JPanel queueSixAuxPanel = new JPanel();
        JPanel panel6 = new JPanel();
        panel6.add(queueSixLabel);
        queueSixAuxPanel.setLayout(new BoxLayout(queueSixAuxPanel, BoxLayout.Y_AXIS));
        queueSixAuxPanel.add(panel6);
        queueSixAuxPanel.add(queueSixArea);
        
        queuesPanel.add(queueOneAuxPanel);
        queuesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        queuesPanel.add(queueTwoAuxPanel);
        queuesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        queuesPanel.add(queueThreeAuxPanel);
        queuesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        queuesPanel.add(queueFourAuxPanel);
        queuesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        queuesPanel.add(queueFiveAuxPanel);
        queuesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        queuesPanel.add(queueSixAuxPanel);        
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(auxLeftPanel);
        mainPanel.add(queuesPanel);
        
        this.add(mainPanel);
       
        this.setVisible(true);
    }

    public void startSimulation(ActionListener listener) {
		startButton.addActionListener(listener);
	}
    
    public void determineSpeed(ChangeListener listener) {
    	speed.addChangeListener(listener);
    }
    
    public int getMinArrTime() {
    	return Integer.parseInt(minArrTimeTextField.getText());
    }
    
    public int getMaxArrTime() {
    	return Integer.parseInt(maxArrTimeTextField.getText());
    }
    
    public int getMinServTime() {
    	return Integer.parseInt(minServTimeTextField.getText());
    }
    
    public int getMaxServTime() {
    	return Integer.parseInt(maxServTimeTextField.getText());
    }
    
    public int getNoOfQueues() {
    	return Integer.parseInt(noOfQueuesTextField.getText());
    }
    
    public int getSimulationInterval() {
    	return Integer.parseInt(simulationIntervalTextField.getText());
    }
    
    public JTextArea getLogger() {
    	return loggerArea;
    }

	public JTextArea getQueueOneArea() {
		return queueOneArea;
	}

	public void setQueueOneArea(JTextArea queueOneArea) {
		this.queueOneArea = queueOneArea;
	}

	public JTextArea getQueueTwoArea() {
		return queueTwoArea;
	}

	public void setQueueTwoArea(JTextArea queueTwoArea) {
		this.queueTwoArea = queueTwoArea;
	}

	public JTextArea getQueueThreeArea() {
		return queueThreeArea;
	}

	public void setQueueThreeArea(JTextArea queueThreeArea) {
		this.queueThreeArea = queueThreeArea;
	}

	public JTextArea getQueueFourArea() {
		return queueFourArea;
	}

	public void setQueueFourArea(JTextArea queueFourArea) {
		this.queueFourArea = queueFourArea;
	}

	public JTextArea getQueueFiveArea() {
		return queueFiveArea;
	}

	public void setQueueFiveArea(JTextArea queueFiveArea) {
		this.queueFiveArea = queueFiveArea;
	}

	public JTextArea getQueueSixArea() {
		return queueSixArea;
	}

	public void setQueueSixArea(JTextArea queueSixArea) {
		this.queueSixArea = queueSixArea;
	}
    
    public int getSpeed() {
    	return speed.getValue();
    }
    
    public JSlider getSpeedSlider() {
    	return speed;
    }
    
    public void displayErrorMessage(String errorMessage) {
    	JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}