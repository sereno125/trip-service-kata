package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.*;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
	
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERD_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private User loggedInUser;
	
	private TripService tripService;

	@Before
	public void initialise(){
		tripService = new TestableTripService();
		
	}
	
	@Test(expected = UserNotLoggedInException.class) public void
	should_throw_and_exception_when_is_not_logged_in(){
		
		loggedInUser = GUEST;
		
		tripService.getTripsByUser(UNUSED_USER);
	}
	
	@Test public void
	should_not_return_any_trips_when_users_are_not_friends(){
		
		loggedInUser = REGISTERD_USER;
		
		User friend = new User();
		friend.addFriend(ANOTHER_USER);
		friend.addTrip(TO_BRAZIL);
		
		List<Trip> friendTrips = tripService.getTripsByUser(friend);
		
		assertThat(friendTrips.size(), org.hamcrest.Matchers.is(0));
		
	}
	
	private class TestableTripService extends TripService{

		@Override
		protected User getLoggedInUser() {
			return loggedInUser ;
		}
		
	} 
}
