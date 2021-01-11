import java.util.List;
import java.util.Vector;


public class VectorPaging<Human> {
	private Vector<Human> whole;
	private int perPage = 5; //�� �������� �������� �� 
	
	public VectorPaging(Vector<Human> whole) {
		this.whole = whole;
	}
	
	public List<Human> getList (int pageNum) { //�Ķ���Ͱ� ���� ������
		/*
		 * 				index 			subList Parameters
		 * 1page -> 	0~2				->	0, 3
		 * 2page ->		3~5				->  3, 6
		 * 3Page ->		6~8				->	6, 9		 
		 */
		
		int from = (pageNum -1) * perPage; // 1���������Ͷ��? >> 0 , 2���������Ͷ�� >> 5
		int to = from + perPage;		 // 1��������� 0 ~ 4 , 2��������� 5 ~ 9
		if (to > whole.size()) {		 // ����Ʈ�� �ִ� ���뺸�� to�� ���ٸ� 
			to = whole.size();			//������ ������ ���� 
		}
		
		return whole.subList(from, to); //subList == �迭 �ڸ��� 
	}
	
	//�� ������ �� 
	public int getTotalPageCount () {
		int size = whole.size(); //����Ʈ�� ����� ����
		int count = size / perPage; // ����Ʈ ���̰� 19�� 
		if (size % perPage != 0) { // 19/5�� 0���� �ȶ������� ++�Ѵ� 
			count++;
		}
		return count;
	}
	
	public Vector<?> getWhole() {
		return whole;
	}

	public void setWhole(Vector<Human> whole) {
		this.whole = whole;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	@Override
	public String toString() {
		return "VectorPaging [whole=" + whole + ", perPage=" + perPage + "]";
	}
	
}
