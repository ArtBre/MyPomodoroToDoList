import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainPomListWindow {
	private JFrame frame;
	private JTextField workHours;
	private JTextField workMinutes;
	private JTextField workSeconds;
	private JTextField breakHours;
	private JTextField breakMinutes;
	private JTextField breakSeconds;
	private JLabel     infoWorkLabel;
	private JLabel     infoBreakLabel;
	private JTextField repCycles;
	private JLabel     repLabel;
	private JTextField addToListField;
	private File fileManager=new File();
	
	HashMap <String, MyPomodoroTask>  taskMap = new HashMap<String, MyPomodoroTask>();
    MyPomodoroTimer pomTimer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPomListWindow window = new MainPomListWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPomListWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 757, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JLabel titleLabel = new JLabel("MyPomodoroListApp");
		titleLabel.setFont(new Font("Consolas", Font.BOLD, 23));
		titleLabel.setBounds(255, 11, 226, 27);
		frame.getContentPane().add(titleLabel);
		
		JLabel timerLabel = new JLabel("00:00:00 work rep 00");
		timerLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		timerLabel.setBounds(199, 49, 462, 36);
		frame.getContentPane().add(timerLabel);
		
		workHours = new JTextField();
		workHours.setText("0");
		workHours.setBounds(188, 108, 42, 20);
		frame.getContentPane().add(workHours);
		workHours.setColumns(10);
		
		workMinutes = new JTextField();
		workMinutes.setText("25");
		workMinutes.setColumns(10);
		workMinutes.setBounds(240, 108, 42, 20);
		frame.getContentPane().add(workMinutes);
		
		workSeconds = new JTextField();
		workSeconds.setText("0");
		workSeconds.setColumns(10);
		workSeconds.setBounds(292, 108, 42, 20);
		frame.getContentPane().add(workSeconds);
		
		breakHours = new JTextField();
		breakHours.setText("0");
		breakHours.setColumns(10);
		breakHours.setBounds(188, 139, 42, 20);
		frame.getContentPane().add(breakHours);
		
		breakMinutes = new JTextField();
		breakMinutes.setText("5");
		breakMinutes.setColumns(10);
		breakMinutes.setBounds(240, 139, 42, 20);
		frame.getContentPane().add(breakMinutes);
		
		breakSeconds = new JTextField();
		breakSeconds.setText("0");
		breakSeconds.setColumns(10);
		breakSeconds.setBounds(292, 139, 42, 20);
		frame.getContentPane().add(breakSeconds);
		
		infoWorkLabel = new JLabel("Work:hh:mm:ss");
		infoWorkLabel.setBounds(344, 111, 101, 14);
		frame.getContentPane().add(infoWorkLabel);
		
		infoBreakLabel = new JLabel("Break:hh:mm:ss");
		infoBreakLabel.setBounds(344, 142, 101, 14);
		frame.getContentPane().add(infoBreakLabel);
		
		JButton startPauseButton = new JButton(Consts.Writings.start);
		startPauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(startPauseButton.getText()==Consts.Writings.start){
					 SuppFunct.nullToZero(workHours); 
					 SuppFunct.nullToZero(workMinutes); 
					 SuppFunct.nullToZero(workSeconds); 
					 SuppFunct.nullToZero(breakHours); 
					 SuppFunct.nullToZero(breakMinutes); 
					 SuppFunct.nullToZero(breakSeconds); 
					 SuppFunct.nullToZero(repCycles); 
					 
					 MyPomodoroTask myTask= new MyPomodoroTask(Integer.parseInt(workHours.getText()),Integer.parseInt(workMinutes.getText()),Integer.parseInt(workSeconds.getText()),Integer.parseInt(breakHours.getText()),Integer.parseInt(breakMinutes.getText()),Integer.parseInt(breakSeconds.getText()),Integer.parseInt(repCycles.getText()));
				      pomTimer= new MyPomodoroTimer(myTask,timerLabel);
				      pomTimer.timerInitStart();
					 startPauseButton.setText(Consts.Writings.pause);
				
				 }
				 else if (startPauseButton.getText()==Consts.Writings.pause)
				 {
					
					 pomTimer.pause();
					startPauseButton.setText(Consts.Writings.continue_);
				 } 
				 
				 else if (startPauseButton.getText()==Consts.Writings.continue_){
					
					 pomTimer.start();
					startPauseButton.setText(Consts.Writings.pause);
				 }
				
				
					
		}
		});
		startPauseButton.setBounds(443, 122, 89, 23);
		frame.getContentPane().add(startPauseButton);
		
		JButton resetButton = new JButton(Consts.Writings.reset);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pomTimer.timerReset();
				startPauseButton.setText(Consts.Writings.start);
				
			}
		});
		resetButton.setBounds(545, 122, 89, 23);
		frame.getContentPane().add(resetButton);
		
		repCycles = new JTextField();
		repCycles.setText("1");
		repCycles.setColumns(10);
		repCycles.setBounds(110, 122, 42, 20);
		frame.getContentPane().add(repCycles);
		
		repLabel = new JLabel("REP");
		repLabel.setBounds(162, 125, 42, 14);
		frame.getContentPane().add(repLabel);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> toDoList = new JList<String>(listModel);
		toDoList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			if(taskMap.containsKey(listModel.getElementAt(toDoList.getSelectedIndex()))){
				MyPomodoroTask selTask = taskMap.get(listModel.getElementAt(toDoList.getSelectedIndex()));
				 workHours.setText(Integer.toString(selTask.workHours));
				 workMinutes.setText(Integer.toString(selTask.workMinutes));
				 workSeconds.setText(Integer.toString(selTask.workSeconds));
				 breakHours.setText(Integer.toString(selTask.breakHours));
				 breakMinutes.setText(Integer.toString(selTask.breakMinutes));
				 breakSeconds.setText(Integer.toString(selTask.breakSeconds));
				 repCycles.setText(Integer.toString(selTask.rep));
				
			}
				
			
			
				
			}
		});
		toDoList.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		toDoList.setBounds(188, 185, 383, 174);
		frame.getContentPane().add(toDoList);
		JScrollPane scrollList= new JScrollPane(toDoList);
		scrollList.setBounds(188, 185, 383, 174);
		frame.getContentPane().add(scrollList);
		fileManager.getElementsFileToList(listModel, Consts.Paths.listPath);
		
		
		addToListField = new JTextField();
		addToListField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					if(addToListField.getText().isEmpty()!=true)
					{
						listModel.addElement(addToListField.getText());
						taskMap.put(addToListField.getText(),new MyPomodoroTask(Integer.parseInt(workHours.getText()),Integer.parseInt(workMinutes.getText()),Integer.parseInt(workSeconds.getText()),Integer.parseInt(breakHours.getText()),Integer.parseInt(breakMinutes.getText()),Integer.parseInt(breakSeconds.getText()),Integer.parseInt(repCycles.getText())));
						addToListField.setText("");
						try {
							fileManager.updateTaskFile(listModel, Consts.Paths.listPath);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			}
		});
		addToListField.setBounds(110, 386, 383, 20);
		frame.getContentPane().add(addToListField);
		addToListField.setColumns(10);
		
		JButton addButton = new JButton("ADD");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addToListField.getText().isEmpty()!=true)
				{
					listModel.addElement(addToListField.getText());
					taskMap.put(addToListField.getText(),new MyPomodoroTask(Integer.parseInt(workHours.getText()),Integer.parseInt(workMinutes.getText()),Integer.parseInt(workSeconds.getText()),Integer.parseInt(breakHours.getText()),Integer.parseInt(breakMinutes.getText()),Integer.parseInt(breakSeconds.getText()),Integer.parseInt(repCycles.getText())));
					addToListField.setText("");
					try {
						fileManager.updateTaskFile(listModel, Consts.Paths.listPath);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				
				
				
				}
			}
		});
		addButton.setBounds(501, 385, 70, 23);
		frame.getContentPane().add(addButton);
		
		JButton remButton = new JButton("REM");
		remButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!listModel.isEmpty() && toDoList.isSelectedIndex(toDoList.getSelectedIndex()))listModel.remove(toDoList.getSelectedIndex());
			
			
			try {
				fileManager.updateTaskFile(listModel, Consts.Paths.listPath);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			}
		});
		remButton.setBounds(564, 385, 70, 23);
		frame.getContentPane().add(remButton);
		
		JButton swapButton = new JButton("SWAP");
		swapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<String> valueList =toDoList.getSelectedValuesList();
					int[] indexList =toDoList.getSelectedIndices();
					
					listModel.setElementAt(valueList.get(1), indexList[0]);
					listModel.setElementAt(valueList.get(0), indexList[1]);
				}
				catch(Exception ex) {
					
				}	
				
				try {
					fileManager.updateTaskFile(listModel, Consts.Paths.listPath);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		swapButton.setBounds(633, 385, 70, 23);
		frame.getContentPane().add(swapButton);
	
		
	
	}
}
