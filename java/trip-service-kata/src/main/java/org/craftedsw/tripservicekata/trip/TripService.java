package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		//loggedUser가 나. 내가user의 친구이면 목록 볼수있다. 
		if(getLoggedInUser() == null){
			throw new UserNotLoggedInException();
		}
		
		
		return user.isFriendsWith(getLoggedInUser())
						? tripsBy(user)
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

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	
}
