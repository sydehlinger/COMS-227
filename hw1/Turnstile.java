package hw1;
/**
 * Model of a turnstile 
 * @author Sydney
 *
 */
public class Turnstile {
	/**
	 * Zone number that the turnstile is from
	 */
	private int zoneNumber;

	/**
	 * Zone that the ticket starts at
	 */
	private int startZone;

	/**
	 * Zone that the ticket ends at
	 */
	private int endZone;

	/**
	 * Count of the number of tickets that have been successfully swiped in
	 */
	private int entranceCount;
	
	/**
	 * Count of the number of tickets that have been successfully swiped out
	 */
	private int exitCount;

	/**
	 * Constructs a turnstile with a zone for the station where the turnstile is located
	 * @param zone
	 * 		zone for the station where the turnstile is located
	 */
	public Turnstile(int zone){
		zoneNumber = zone;
	}

	/**
	 * Records this turnstile as start zone on the given ticket. If balance on the ticket is not enough for any ride it will return false. If the ticket is already in transit, it will charge the ticket for a ride to this turnstile.
	 * @param ticket
	 * 		ticket being swiped in
	 * @return
	 * 		if the swipe in is successful or not
	 */
	public boolean swipeIn(Ticket ticket){
		if(ticket.getBalance() >= TicketUtil.getMinimumFare(ticket.isDiscounted()) && !ticket.isInTransit()){
			startZone = zoneNumber;
			ticket.beginTrip(startZone);
			entranceCount += 1;
			return true;
		}else if(ticket.getBalance() >= TicketUtil.getMinimumFare(ticket.isDiscounted()) && ticket.isInTransit()){
			if(ticket.getBalance() >= TicketUtil.calculateRideCost(ticket.getStartZone(), endZone, ticket.isDiscounted())){
				endZone= zoneNumber;
				ticket.charge(TicketUtil.calculateRideCost(ticket.getStartZone(), endZone, ticket.isDiscounted()));
				ticket.beginTrip(endZone);
				exitCount += 1;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * Charges the ticket from the ticket's start zone to this turnstile. If ticket balance is not enough, will return false. If the ticket is not in transit, will return false.
	 * @param ticket
	 * 		ticket being swiped out
	 * @return
	 * 		if swipe out was successful or not
	 */
	public boolean swipeOut(Ticket ticket){
		if(ticket.getBalance() >= TicketUtil.getMinimumFare(ticket.isDiscounted()) && ticket.isInTransit()){
			endZone= zoneNumber;
			if(ticket.getBalance() >= TicketUtil.calculateRideCost(ticket.getStartZone(), endZone, ticket.isDiscounted())){
				ticket.charge(TicketUtil.calculateRideCost(ticket.getStartZone(), endZone, ticket.isDiscounted()));
				exitCount += 1;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * Gets the number of tickets that have been successfully swiped in
	 * @return
	 * 		the number of tickets that have been successfully swiped in
	 */
	public int getEntranceCount(){
		return entranceCount;
	}
	
	/**
	 * Gets the number of tickets that have been successfully swiped out
	 * @return
	 * 		the number of tickets that have been successfully swiped out
	 */
	public int getExitCount(){
		return exitCount;
	}

}
