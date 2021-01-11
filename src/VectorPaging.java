import java.util.List;
import java.util.Vector;


public class VectorPaging<Human> {
	private Vector<Human> whole;
	private int perPage = 5; //현 페이지에 보여지는 양 
	
	public VectorPaging(Vector<Human> whole) {
		this.whole = whole;
	}
	
	public List<Human> getList (int pageNum) { //파라미터가 현재 페이지
		/*
		 * 				index 			subList Parameters
		 * 1page -> 	0~2				->	0, 3
		 * 2page ->		3~5				->  3, 6
		 * 3Page ->		6~8				->	6, 9		 
		 */
		
		int from = (pageNum -1) * perPage; // 1페이지부터라면? >> 0 , 2페이지부터라면 >> 5
		int to = from + perPage;		 // 1페이지라면 0 ~ 4 , 2페이지라면 5 ~ 9
		if (to > whole.size()) {		 // 리스트에 있는 내용보다 to가 많다면 
			to = whole.size();			//벡터의 끝으로 보냄 
		}
		
		return whole.subList(from, to); //subList == 배열 자르기 
	}
	
	//총 페이지 수 
	public int getTotalPageCount () {
		int size = whole.size(); //리스트에 저장된 길이
		int count = size / perPage; // 리스트 길이가 19다 
		if (size % perPage != 0) { // 19/5는 0으로 안떨어진다 ++한다 
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
