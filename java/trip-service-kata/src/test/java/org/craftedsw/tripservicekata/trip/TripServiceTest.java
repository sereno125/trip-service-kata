package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
	@Test(expected = UserNotLoggedInException.class) public void
	should_throw_and_exception_when_is_not_logged_in(){
		
		TripService tripService = new TestableTripService();
		
		tripService.getTripsByUser(null);
	}
	
	private class TestableTripService extends TripService{

		@Override
		protected User getLoggedInUser() {
			return null;
		}
		
	}
}
