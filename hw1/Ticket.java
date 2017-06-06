package hw1;
/**
 * Model of a ticket
 * @author Sydney
 *
 */
public class Ticket {
	/**
	 * Balance of the ticket
	 */
	private int balance;
	
	/**
	 * Whether the ticket is discounted or not
	 */
	private boolean discount;
	
	/**
	 * Zone where the passenger started
	 */
	private int startZone;
	
	/**
	 * Whether the passenger is in transit or not
	 */
	private boolean transit;

	/**
	 * Constructs a ticket with a certain balance and if it's discounted or not
	 * @param value
	 * 		Balance of the ticket
	 * @param discounted
	 * 		Whether the ticket is discounted or not
	 */
	public Ticket (int value, boolean discounted){
		balance = value;
		discount = discounted;
		transit = false;
	}
	
	/**
	 * Sets the start zone for this ticket. Caller is responsible for ensuring that the argument is a valid zone number. After this method is called the ticket is now in transit.
	 * @param zone
	 * 		Start zone of the ticket
	 */
	public void beginTrip(int zone){
		startZone = zone;
		transit = true;
	}

	/**
	 * Returns start zone of the ticket
	 * @return
	 * 		start zone of ticket
	 */
	public int getStartZone(){
		if(isInTransit()){
			return startZone;
		}else{
			return TicketUtil.INVALID_ZONE;
		}
	}
	
	/**
	 * 	Checks if the ticket is discounted
	 * @return
	 * 		If ticket is discounted or not
	 */
	public boolean isDiscounted(){
		return discount;
	}

	/**
	 * Gets the balance of the ticket
	 * @return
	 * 		Balance of the ticket
	 */
	public int getBalance(){
		return balance;
	}

	/**
	 * Charges the ticket for the cost of the ride
	 * @param rideCost
	 * 		the cost of the ride
	 * @return
	 * 		if the charge could be made or not
	 */
	public boolean charge(int rideCost){
		if(balance >= rideCost){
			balance -= rideCost;
			transit = false;
			startZone = TicketUtil.INVALID_ZONE; 
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if the ticket is in transit
	 * @return
	 * 		if the ticket is in transit
	 */
	public boolean isInTransit(){
		return transit;
	}

}
