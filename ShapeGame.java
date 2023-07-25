import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class ShapeGame  extends JFrame {
	private AnimationViewer panel;  // panel for bouncing area
	JButton borderButton, loadButton;
	JComboBox<ShapeType> shapesComboBox;
	JComboBox<PathType> pathComboBox;
	JTextField labelTextField, filenameTextField;

	
	class LoadListener implements ActionListener{
	    public void actionPerformed(ActionEvent e){
	        String fileName = filenameTextField.getText();
	        boolean fileReadSuccesful = panel.loadShapesFromFile(fileName);
	        if(!fileReadSuccesful){
	            filenameTextField.setText("Invalid filename.");
	        }
	    }
	}
	
	class BorderListener implements ActionListener{
	    public void actionPerformed(ActionEvent e){
	        Color color = JColorChooser.showDialog(null, "Border Color", null);
	        if(color != null){
	        panel.setCurrentBorderColor(color);
	       }
	    }
	}
	
	class LabelListener implements ActionListener{
	    public void actionPerformed(ActionEvent e){
	        String textEntered = labelTextField.getText();
	        if(!textEntered.equals("")){
	            panel.setCurrentLabel(textEntered);
	            }
	        }
	}


	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ShapeGame();
			}
		});
	}
	public ShapeGame() {
		super("Bouncing Application");
		panel = new AnimationViewer();
		add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT));
		add(setUpToolsPanel(), BorderLayout.NORTH);
		addComponentListener(
			new ComponentAdapter() { // resize the frame and reset all margins for all shapes
				public void componentResized(ComponentEvent componentEvent) {
					panel.resetMarginSize();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		pack();
		setVisible(true);
	}
	public JPanel setUpToolsPanel() {
		shapesComboBox = new JComboBox<ShapeType>(new DefaultComboBoxModel<ShapeType>(ShapeType.values()));
		shapesComboBox.addActionListener( new ShapeActionListener()) ;
		pathComboBox = new JComboBox<PathType>(new DefaultComboBoxModel<PathType>(PathType.values()));
		pathComboBox.addActionListener( new PathActionListener());
		labelTextField = new JTextField("");
		labelTextField.addActionListener( new LabelListener());
		borderButton = new JButton("Border");
		borderButton.addActionListener( new BorderListener());
		loadButton = new JButton("Load");
		loadButton.addActionListener( new LoadListener());
		filenameTextField = new JTextField("example.txt");
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add(borderButton);
		toolsPanel.add( new JLabel(" Label: ", JLabel.RIGHT));
		toolsPanel.add(labelTextField);
		toolsPanel.add( new JLabel(" Filename: ", JLabel.RIGHT));
		toolsPanel.add(filenameTextField);
		toolsPanel.add(loadButton);
		return toolsPanel;
	}
	class ShapeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.setCurrentShapeType((ShapeType)shapesComboBox.getSelectedItem());
		}
	}
	class PathActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.setCurrentPathType((PathType)pathComboBox.getSelectedItem());
		}
	}

}

