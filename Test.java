package Exercise8;

public class Test {

    public static void main(String[] args) {
        TicketCounter ticketCounter = new TicketCounter();
        TicketBookingThread t1 = new TicketBookingThread(ticketCounter,"John",2,"book");
        TicketBookingThread t2 = new TicketBookingThread(ticketCounter,"Martin",2,"cancel");
        
        t1.start();
        t2.start();
    }
}
class TicketBookingThread extends Thread {

	private TicketCounter ticketCounter;
	private String passengerName;
	private int noOfSeatsToBook;
        String message;
	public TicketBookingThread(TicketCounter ticketCounter,String passengerName, int noOfSeatsToBook,String msg) {
		this.ticketCounter = ticketCounter;
		this.passengerName = passengerName;
		this.noOfSeatsToBook = noOfSeatsToBook;
                message=msg;
	}
	
        @Override
	public void run() {
		ticketCounter.bookTicket(passengerName, noOfSeatsToBook);
	}
}
class TicketCounter extends TicketBookingThread
{
    private int availableSeats = 3;

	public synchronized void bookTicket(String pname, int numberOfSeats) 
        {
            if("book".equalsIgnoreCase(message));
            {
		if ((availableSeats >= numberOfSeats) && (numberOfSeats > 0)) 
                {
			System.out.println("Hi," + pname + " : " + numberOfSeats+ " Seats booked Successfully..");
			availableSeats = availableSeats- numberOfSeats;
		} 
                else
                {
			System.out.println("Hi," + pname + " : Seats Not Available");
                }
            }
            if("cancel".equalsIgnoreCase(message))
            {
                if(numberOfSeats>0)
                {
                    if((availableSeats+numberOfSeats)>10)
                    {
                        availableSeats =3;
                    }
                    availableSeats+=numberOfSeats;
                }
            }
	}
}
