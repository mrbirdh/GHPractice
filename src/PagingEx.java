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
		
		model = new DefaultListModel<Human>(); //�߰� �� ������ ���⿡ �� ����
		paging = new VectorPaging<Human>(data); //�����ʹ� ����� ��� ������ 
		list = new JList<Human>(model);
		list.setPrototypeCellValue(new Human("1111111111111", "100", ""));
		list.setVisibleRowCount(paging.getPerPage()); // �� ��ŭ�� ���� 
		
		setListData();
		
		btnNext = new JButton(">");
		btnPre = new JButton("<");
		
		cBox = new JComboBox<String>();
		cBox.setPrototypeDisplayValue("xxxxxxxxxxxxx");
		setPages();
	}
	
	//������ �����͸� ����  
	private void setListData () { 
		List<Human> humanList = paging.getList(currentPage);
		model.removeAllElements(); //���� ������ �� ���� 
		for (Human h : humanList) {
			model.addElement(h);
		}
	}
	
	//cBox �������� �� 
	private void setPages () {
		cBox.removeAllItems(); //���� �������� ���� 
		int count = paging.getTotalPageCount(); //�ִ� ������ 
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
								"ù �������Դϴ�."
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
								"������ �������Դϴ�."
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
		setTitle("����¡!");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PagingEx();
	}
	
}
