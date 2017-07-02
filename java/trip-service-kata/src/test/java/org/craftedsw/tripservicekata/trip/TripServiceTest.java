package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.assertThat;


import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERD_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_LONDON = new Trip();
	
	private TripService tripService;

	@Before
	public void initialise(){
		tripService = new TestableTripService();
	}
	
	@Test(expected = UserNotLoggedInException.class) public void
	should_throw_and_exception_when_is_not_logged_in(){
		tripService.getTripsByUser(UNUSED_USER, GUEST);
	}
	
	@Test public void
	should_not_return_any_trips_when_users_are_not_friends(){
		
		//빌더를 통해 코드의 가독성이 매우 좋아짐.  
		User friend = UserBuilder.aUSer()
										.friendsWith(ANOTHER_USER)
										.withTrips(TO_BRAZIL)
										.build() ;
		
		List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERD_USER);
		
		assertThat(friendTrips.size(), org.hamcrest.Matchers.is(0));
		
	}
	
	@Test public void
	should_return_friend_trips_when_users_are_friends(){
		//절대 소스는 copy & paste 하지 마라  
		
		User friend = UserBuilder.aUSer()
										.friendsWith(ANOTHER_USER, REGISTERD_USER)
										.withTrips(TO_BRAZIL, TO_LONDON)
										.build();
		
		//중복코드 제거를 위해 빌더 적용 
//		User friend = new User();
//		friend.addFriend(ANOTHER_USER);
//		friend.addFriend(loggedInUser);
//		friend.addTrip(TO_BRAZIL);
//		friend.addTrip(TO_LONDON);
		
		List<Trip> friendTrips = tripService.getTripsByUser(friend, REGISTERD_USER);
		
		assertThat(friendTrips.size(), org.hamcrest.Matchers.is(2));
	}
	
	private class TestableTripService extends TripService{

		protected List<Trip> tripsBy(User user) {
			return user.trips() ;
		}
		
	} 
}
