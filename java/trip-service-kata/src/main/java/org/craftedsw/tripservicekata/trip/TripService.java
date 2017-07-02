package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {
	
	@Autowired private TripDAO tripDAO;

	public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
		
		validate(loggedInUser);
		
		return friend.isFriendsWith(loggedInUser)
						? tripsBy(friend)
						: noTrips();
			
		//1. 제일 얕은 indent
		//- 로그인되지 않은 유저이면 exception
		//if (loggedInUser != null) {
			// 두번째로 깊은 indent
			// 코드스멜 중 feature envy
			// user의 일을 tripservice가 탐내고 있다.user의 일은 user에게 물어봐야 한다.
//			for (User friend : user.getFriends()) {
//				if (friend.equals(loggedInUser)) {
//					isFriend = true;
//					break;
//				}
//			}
			//가장 깊은 indent 
//			if (user.isFriendsWith(loggedInUser)) {
//				tripList = tripsBy(user);
//			}
//			return tripList;
		//}
		// 이부분은 guard clause로 바꿀수 있다  
//		else {
//			throw new UserNotLoggedInException();
//		}
	}

	private void validate(User loggedInUser) {
		if(loggedInUser == null){
			throw new UserNotLoggedInException();
		}
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private List<Trip> tripsBy(User user) {
		return tripDAO.tripsBy(user);
		//return TripDAO.findTripsByUser(user);
	}
	
	// 서버단(모델)에서 웹프레임워크(session정보와같은것)을 직접 참조하고 있다. 
//	private User getLoggedInUser() {
//		return UserSession.getInstance().getLoggedUser();
//	}
	
}
