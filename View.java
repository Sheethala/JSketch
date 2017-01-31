// HelloMVC: a simple MVC example
// the model is just a counter
// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class View extends JPanel implements Observer {

	// the view's main user interface
	private JButton button;
	Boolean shapeObjectCreated;
	Boolean shapeObjectAdded;

	// the model that this view is showing
	private Model model;

	View(Model model_) {
		// create the view UI
		// a GridBagLayout with default constraints centres
		// the widget in the window
		//this.setLayout(new GridBagLayout());
		doToolPalette();
		// set the model
		model = model_;
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)

	}

	public void createTools(){
		JPanel holder = new JPanel();

	}



	public JPanel createThicknessTools(){
		JPanel box = new JPanel(new BorderLayout(8,8));
		JButton thickness1 = new JButton(new ImageIcon("thickness1.png"));
		JButton thickness2 = new JButton(new ImageIcon("thickness2.png"));
		JButton thickness3 = new JButton(new ImageIcon("thickness3.png"));
		box.add(thickness1, BorderLayout.NORTH);
		box.add(thickness2, BorderLayout.CENTER);
		box.add(thickness3, BorderLayout.SOUTH);
		thickness1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					model.chosenStrokeThickness = 2.0f;
			}
		});
		thickness2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					model.chosenStrokeThickness = 6.0f;
			}
		});
		thickness3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					model.chosenStrokeThickness = 9.0f;
			}
		});
		return box;
	}

	public JPanel createColourPalette(){
			JPanel colourBox = new JPanel();
			colourBox.setLayout(new GridLayout(3,2));
			ImageIcon[] icons = new ImageIcon[6];
			for (int i=0; i<6; i++){
				icons[i]=new ImageIcon("colour"+i+".png");
				final JToggleButton jtb = new JToggleButton(icons[i]);
				jtb.setPreferredSize(new Dimension(20, 20));
				jtb.setOpaque(true);
				if(i == 0){
					jtb.setSelected(false);
					jtb.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent ev) {
							if(ev.getStateChange()==ItemEvent.SELECTED){
								model.setColour("blue");
								jtb.doClick();
							}
						}
					});
				}
				if(i == 1){
					jtb.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent ev) {
							if(ev.getStateChange()==ItemEvent.SELECTED){
								model.setColour("green");
								jtb.doClick();
							}
						}
					});
				}
				if(i == 2){
					jtb.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent ev) {
							if(ev.getStateChange()==ItemEvent.SELECTED){
								model.setColour("yellow");
								jtb.doClick();

							}
						}
					});
				}
				if(i == 3){
					jtb.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent ev) {
							if(ev.getStateChange()==ItemEvent.SELECTED){
								model.setColour("orange");
								jtb.doClick();
							}
						}
					});
				}
				if(i == 4){
					jtb.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent ev) {
							if(ev.getStateChange()==ItemEvent.SELECTED){
								model.setColour("red");
								jtb.doClick();
							}
						}
					});
				}
				if(i == 5){
					jtb.addItemListener(new ItemListener(){
						public void itemStateChanged(ItemEvent ev) {
							if(ev.getStateChange()==ItemEvent.SELECTED){
								model.setColour("violet");
								jtb.doClick();
							}
						}
					});
				}
				colourBox.add(jtb);
			}
			return colourBox;
	}

	public void doToolPalette(){
		JPanel holder = new JPanel();
		//holder.setLayout(new GridLayout(8,2));
		holder.setBackground(Color.RED);
		Box vertBox = Box.createVerticalBox();
		JPanel toolBox = new JPanel();
		toolBox.setLayout(new GridLayout(3,2));
		ImageIcon[] icons = new ImageIcon[6];
		for (int i=0; i<6; i++){
			icons[i]=new ImageIcon(""+i+".GIF");
		}
		for (int i=0; i<6; i++){
			final JToggleButton jtb = new JToggleButton(icons[i]);
			if(i == 3){
				jtb.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent ev) {
						if(ev.getStateChange()==ItemEvent.SELECTED){
							model.drawLine();
							jtb.doClick();
						}
					}
				});
			}
			else if(i == 0){
				jtb.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent ev) {
						if(ev.getStateChange()==ItemEvent.SELECTED){
							model.selectionTool();
							jtb.doClick();
						}
					}
				});
			}
			else if(i == 4){
				jtb.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent ev) {
						if(ev.getStateChange()==ItemEvent.SELECTED){
							model.drawSquare();
							jtb.doClick();
						}
					}
				});
			}
			else if(i == 5){
				jtb.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent ev) {
						if(ev.getStateChange()==ItemEvent.SELECTED){
							model.drawCircle();
							jtb.doClick();
						}
					}
				});
			}
			toolBox.add(jtb);
		}
		JPanel colour = createColourPalette();
		JPanel thicknessTools = createThicknessTools();
  	vertBox.add(toolBox);
		vertBox.add(colour);
		vertBox.add(thicknessTools);
		holder.add(vertBox, BorderLayout.NORTH);
		this.add(holder, BorderLayout.WEST);
	}

	// Observer interface
	@Override
	public void update(Observable arg0, Object arg1) {
	}
}
