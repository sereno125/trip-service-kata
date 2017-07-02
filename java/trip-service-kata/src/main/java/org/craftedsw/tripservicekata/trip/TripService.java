package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		//loggedUser가 나. 내가user의 친구이면 목록 볼수있다. 
		User loggedUser = getLoggedInUser();
		boolean isFriend = false;
		//1. 제일 얕은 indent
		//- 로그인되지 않은 유저이면 exception
		if (loggedUser != null) {
			// 두번째로 깊은 indent
			// 코드스멜 중 feature envy
			// user의 일을 tripservice가 탐내고 있다.user의 일은 user에게 물어봐야 한다.
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			//가장 깊은 indent 
			if (isFriend) {
				tripList = tripsBy(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	
}
