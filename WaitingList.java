import java.util.*;

public class WaitingList {
	
	// an array list where students will be added and removed as needed
	private ArrayList<Student> waitingListArray = new ArrayList<Student>();
	
	// is the database going to have a copy of the waiting list? If so, then we need to populate the waiting list
	// on opening application and logging in and then save it when logging out
	
	public WaitingList() {
		// TODO: populate waitingListArray from DB
	}
	
	public void addToWaitingList(Student studentIn) {
		waitingListArray.add(studentIn);
	}
	
	// returns the next student from top of waiting list and removes them from list
	// returns null if list is empty
	public Student retrieveNextStudent() {
		if(waitingListArray.size() > 0) {
			Student nextStudent = waitingListArray.get(0);
			waitingListArray.remove(0);
			
			return nextStudent;
		} else {
			return null;
		}		
	}
	
	// this should be called before retrieving students from waiting list
	public int getWaitingListSize() {
		return waitingListArray.size();
	}
	
	public Student peekNextStudent() {
		if(waitingListArray.size() > 0) {
			return waitingListArray.get(0);
		} else {
			return null;
		}	
	}
	
}
