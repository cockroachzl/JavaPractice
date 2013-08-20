import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.io.Serializable;

public class List1 implements ListSelectionListener, Serializable {
	private static final long serialVersionUID = 7644671978197990115L;
	JList list;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	public static void main(String[] args){
		List1 gui = new List1();
		gui.go();
	}
	
	public void go(){
		String [] listEntries = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta"};
		list = new JList(listEntries);
		
		JScrollPane scroller = new JScrollPane(list);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel.add(scroller);
		
		list.setVisibleRowCount(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(350,300);
		frame.setVisible(true);
	}
	
	public void valueChanged(ListSelectionEvent lse){
		if (!lse.getValueIsAdjusting()){
			String selection = (String)list.getSelectedValue();
			System.out.println(selection);
		}
	}
}
