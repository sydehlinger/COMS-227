package hw1;
/**
 * Model of a machine that dispenses tickets
 * @author Sydney
 *
 */
public class TicketMachine {
	/**
	 * Start zone of ticket
	 */
	private int start;
	
	/**
	 * End zone of ticket
	 */
	private int end;
	
	/**
	 * Whether the ticket is discounted or not
	 */
	private boolean discount;
	
	/**
	 * Balance of the ticket
	 */
	private int balance;
	
	/**
	 * Number of rides between zones
	 */
	private int numberOfRides;
	
	/**
	 * Number of tickets that have been dispensed
	 */
	private int numberOfTickets;
	
	/**
	 * Total cost of all the tickets that have been dispensed
	 */
	private int totalCost;
	
	/**
	 * Constructor of the ticket machine
	 */
	public TicketMachine(){	
	}
	
	/**
	 * Gives a ticket that has the balance needed for a ride between the start zone and end zone using a discounted fare if discounted is true
	 * @param startZone
	 * 		start zone of the ticket
	 * @param endZone
	 * 		end zone of the ticket
	 * @param discounted
	 * 		whether the ticket is discounted or not
	 * @return
	 * 		the ticket
	 */
	public Ticket purchaseTicket(int startZone, int endZone, boolean discounted){
		start = startZone;
		end = endZone;
		discount = discounted;
		balance = TicketUtil.calculateRideCost(start, end, discount);
		Ticket ticket = new Ticket(balance, discount);
		numberOfTickets += 1;
		totalCost += balance;
		return ticket;
	}
	
	/**
	 * Gives a ticket that has the balanced needed for the number of rides between the start zone and end zone using a discounted fare if discounted is true
	 * @param numRides
	 * 		number of rides between the zones
	 * @param startZone
	 * 		start zone of the ticket
	 * @param endZone
	 * 		end zone of the ticket
	 * @param discounted
	 * 		whether the ticket is discounted or not
	 * @return
	 * 		the ticket
	 */
	public Ticket purchaseTicket(int numRides, int startZone, int endZone, boolean discounted){
		numberOfRides = numRides;
		start = startZone;
		end = endZone;
		discount = discounted;
		balance = TicketUtil.calculateRideCost(numberOfRides, start, end, discount);
		Ticket ticket = new Ticket(balance, discount);
		numberOfTickets += 1;
		totalCost += balance;
		return ticket;
	}
	
	/**
	 * Gives a ticket with the balance give and whether or not the ticket is discounted
	 * @param amount
	 * 		balance of the ticket
	 * @param discounted
	 * 		whether the ticket is discounted or not
	 * @return
	 * 		the ticket
	 */
	public Ticket purchaseTicket(int amount, boolean discounted){
		balance = amount;
		discount = discounted;
		Ticket ticket = new Ticket(balance, discount);
		numberOfTickets += 1;
		totalCost += balance;
		return ticket;
	}
	
	/**
	 * Gives the total number of tickets sold from the machine
	 * @return
	 * 		the number of tickets sold from the machine
	 */
	public int totalTickets(){
		return numberOfTickets;
	}
	
	/**
	 * Gives the total cost for all the tickets sold from the machine
	 * @return
	 * 		the total cost of all the tickets sold from the machine
	 */
	public int totalCost(){
		return totalCost;
	}
}
