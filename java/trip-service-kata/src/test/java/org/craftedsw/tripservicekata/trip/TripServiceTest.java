package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {
	
	private static final User GUEST = null;
	private static final User UNUSED_USER = null;
	private static final User REGISTERD_USER = new User();
	private static final User ANOTHER_USER = new User();
	private static final Trip TO_BRAZIL = new Trip();
	private static final Trip TO_LONDON = new Trip();
	
	@Mock private TripDAO tripDAO;
	
	@InjectMocks @Spy private TripService tripService = new TripService();
	
	//private TripService tripService;

//	@Before
//	public void initialise(){
//		tripService = new TestableTripService();
//	}
	
	@Test(expected = UserNotLoggedInException.class) public void
	should_throw_and_exception_when_is_not_logged_in(){
		tripService.getFriendTrips(UNUSED_USER, GUEST);
	}
	
	@Test public void
	should_not_return_any_trips_when_users_are_not_friends(){
		
		//빌더를 통해 코드의 가독성이 매우 좋아짐.  
		User friend = UserBuilder.aUSer()
										.friendsWith(ANOTHER_USER)
										.withTrips(TO_BRAZIL)
										.build() ;
		
		List<Trip> friendTrips = tripService.getFriendTrips(friend, REGISTERD_USER);
		
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
		
		//Mock객체가 어떻게 행동할지 설정을 해주어야 한다. 아래의 testabl 클래스의 tripsBy메서드와 동일 기능.
		org.mockito.BDDMockito.given(tripDAO.tripsBy(friend)).willReturn(friend.trips());
		
		List<Trip> friendTrips = tripService.getFriendTrips(friend, REGISTERD_USER);
		
		assertThat(friendTrips.size(), org.hamcrest.Matchers.is(2));
	}
	
//	private class TestableTripService extends TripService{
//
//		@Override
//		protected List<Trip> tripsBy(User user) {
//			return user.trips() ;
//		}
//		
//	} 
}
