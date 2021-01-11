import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PagingEx extends JFrame {
	private JList<Human> list;
	private Vector<Human> data;
	private DefaultListModel<Human> model;
	
	private VectorPaging<Human> paging;
	
	private JComboBox<String> cBox;
	private JButton btnPre;
	private JButton btnNext;
	private int currentPage = 1;
	
	public PagingEx () {
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	
	private void init () {
		data = new Vector<Human>();
		for (int i = 0; i < 23; i++) {
			data.add(new Human(String.valueOf(i),String.valueOf(i),String.valueOf(i)));
		}
		
		model = new DefaultListModel<Human>(); //추가 된 내용은 여기에 다 저장
		paging = new VectorPaging<Human>(data); //데이터는 저장된 모든 데이터 
		list = new JList<Human>(model);
		list.setPrototypeCellValue(new Human("1111111111111", "100", ""));
		list.setVisibleRowCount(paging.getPerPage()); // 이 만큼만 보여 
		
		setListData();
		
		btnNext = new JButton(">");
		btnPre = new JButton("<");
		
		cBox = new JComboBox<String>();
		cBox.setPrototypeDisplayValue("xxxxxxxxxxxxx");
		setPages();
	}
	
	//보여줄 데이터를 들고옴  
	private void setListData () { 
		List<Human> humanList = paging.getList(currentPage);
		model.removeAllElements(); //현재 내용을 다 지움 
		for (Human h : humanList) {
			model.addElement(h);
		}
	}
	
	//cBox 보여지는 곳 
	private void setPages () {
		cBox.removeAllItems(); //현재 페이지를 지움 
		int count = paging.getTotalPageCount(); //최대 페이지 
		for (int i = 1; i <= count; i++) {
			cBox.addItem(String.valueOf(i));
		}
	}
	
	private void setDisplay () {
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnPre);
		pnlSouth.add(cBox);
		pnlSouth.add(btnNext);
		
		add(new JScrollPane(list), BorderLayout.CENTER);
		add(pnlSouth ,BorderLayout.SOUTH);
	}
	
	private void addListener () {
		ActionListener aListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object src = e.getSource();
				if (src == btnPre) {
					currentPage--;
					if (currentPage == 0) {
						currentPage = 1;
						JOptionPane.showMessageDialog(
								PagingEx.this,
								"첫 페이지입니다."
						);
					} else {
						setListData();
					}
				} else  {
					currentPage++;
					int last = Integer.parseInt(
							cBox.getItemAt(cBox.getItemCount() - 1)
					);
					
					if (currentPage > last) {
						JOptionPane.showMessageDialog(
								PagingEx.this,
								"마지막 페이지입니다."
						);
						currentPage--;
					} else {
						setListData();
					}
					
				}
				cBox.setSelectedIndex(currentPage - 1);
			}
		};
		btnNext.addActionListener(aListener);
		btnPre.addActionListener(aListener);
		
		cBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					currentPage = Integer.parseInt(
							cBox.getSelectedItem().toString()
					);
					setListData();
				}
			}
		});
	}
	
	private void showFrame () {
		setTitle("페이징!");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PagingEx();
	}
	
}
