package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
	// 위 테스트대상인 static method와 동일하게 돌도록 메소드를 만듬.
	// 점차적으로 이 메소드를 쓰도록 기존코드를 바꿔나간다.
	// 그러면 안정적으로 바꿀수있고 다되었을때 throw부분을 여기다 넣고, 옛날 소스 지우면 된다.
	public List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
}