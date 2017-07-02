package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

public class UserBuilder{

	private User[] friends = new User[] {};
	private Trip[] trips   = new Trip[] {};
	
	public static UserBuilder aUSer() {
		return new UserBuilder();
	}

	public UserBuilder friendsWith(User...friends) {
		this.friends  = friends;
		return this;
	}
	
	public UserBuilder withTrips(Trip... trips) {
		this.trips = trips;
		return this;
	}
	
	// 실제로 객체를 생성한다 
	public User build() {
		User user = new User();
		addTripsTo(user);
		addFriendsTo(user);
		return user;
	}

	private void addFriendsTo(User user) {
		for(User friend : friends){
			user.addFriend(friend); 
		}
	}


	private void addTripsTo(User user) {
		for (Trip trip : trips ) {
			user.addTrip(trip);
		}
	}
}