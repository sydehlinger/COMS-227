package hw1;
/**
 * Util class that contains the constants of the ride cost and zone costs and the static methods that calculate the ticket prices
 * @author Sydney
 *
 */
public class TicketUtil {
	
	/**
	 * Private constructor of TicketUtil so no one will attempt to create an instance for TicketUtil
	 */
	private TicketUtil(){
	}
	
	/**
	*	Fare	(in	cents)	for	a	trip	within	a	zone.
	*/
	public static final int RIDE_COST =	200;

	/**
	*	Discounted	fare	(in	cents)	for	a	trip	within	a	zone.
	*/
	public static final int RIDE_COST_DISCOUNTED =	150;

	/**
	*	Additional	fare	(in	cents)	for	travel	between	zones.
	*/
	public static final int ZONE_COST =	175;

	/**
	*	Additional	discounted	fare	(in	cents)	for	travel	between	zones.
	*/
	public static final int ZONE_COST_DISCOUNTED =	120;
	
	/**
	*	Constant	representing	a	nonexistent	start	zone.
	*/
	public static final int INVALID_ZONE =	-1;
	
	/**
	 * Calculates the cost of ride between the start zone and end zone using a discounted fare if discounted is true
	 * @param startZone
	 * 		start zone of the ticket
	 * @param endZone
	 * 		end zone of the ticket
	 * @param discounted
	 * 		whether or not the ticket is discounted
	 * @return
	 * 		the cost of the ride
	 */
	public static int calculateRideCost(int startZone, int endZone, boolean discounted){
		if(startZone == endZone && discounted){
			return RIDE_COST_DISCOUNTED;
		}else if(startZone == endZone && !discounted){	
			return RIDE_COST;
		}else if(startZone != endZone && !discounted){
			int zonesPassed = Math.abs(startZone - endZone);
			return RIDE_COST + zonesPassed * ZONE_COST;
		}else{
			int zonesPassed = Math.abs(startZone - endZone);
			return RIDE_COST_DISCOUNTED + zonesPassed * ZONE_COST_DISCOUNTED;
		}
	}
	
	/**
	 * Calculates the cost for the number of rides between the start zone and the end zone using a discounted fare if discounted is true
	 * @param numRides
	 * 		number of rides between the start zone and end zone
	 * @param startZone
	 * 		start zone of the ticket
	 * @param endZone
	 * 		end zone of the ticket
	 * @param discounted
	 * 		whether or not the ticket is discounted
	 * @return
	 * 		cost of the ride
	 */
	public static int calculateRideCost(int numRides, int startZone, int endZone, boolean discounted){
		if(startZone == endZone && discounted){
			return numRides * RIDE_COST_DISCOUNTED;
		}else if(startZone == endZone && !discounted){	
			return numRides * RIDE_COST;
		}else if(startZone != endZone && !discounted){
			int zonesPassed = Math.abs(startZone - endZone);
			return numRides * (RIDE_COST + zonesPassed * ZONE_COST);
		}else{
			int zonesPassed = Math.abs(startZone - endZone);
			return numRides * (RIDE_COST_DISCOUNTED + zonesPassed * ZONE_COST_DISCOUNTED);
		}
	}
	
	/**
	 * Gives the minimum fare needed to ride within a zone using discounted fare if discounted is true
	 * @param discounted
	 * 		whether the ticket is discounted or not
	 * @return
	 * 		cost of the ride
	 */
	public static int getMinimumFare(boolean discounted){
		if(discounted){
			return RIDE_COST_DISCOUNTED;
		}else{
			return RIDE_COST;
		}
	}
}
