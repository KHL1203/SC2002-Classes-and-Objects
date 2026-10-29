import java.util.Scanner;

public class Plane{
    private PlaneSeat[] seat;
    private int numEmptySeat;

    public Plane(){
        this.numEmptySeat = 12;
        this.seat = new PlaneSeat[12];
        for (int i = 0; i < 12; i++){
            this.seat[i] = new PlaneSeat(i + 1);
        }
    }

    public PlaneSeat[] sortSeats(){
        // Create a copy of the seat array
        PlaneSeat[] copy = new PlaneSeat[this.seat.length];
        for (int i = 0; i < this.seat.length; i++){
            copy[i] = this.seat[i];
        }

        for (int i = 0; i < copy.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < copy.length; j++){
                if (copy[j].getCustomerID()<copy[minIndex].getCustomerID()){
                    minIndex = j;
                }
            }

            PlaneSeat temp = copy[minIndex];
            copy[minIndex] = copy[i];
            copy[i] = temp;
        }

        return copy;
        
    }

    public void showNumEmptySeats(){
        System.out.println("There are" + " " + this.numEmptySeat + " empty seats.");
    }
    
    public void showEmptySeats(){
        System.out.println("The following seats are empty:");
        for (PlaneSeat s: this.seat){
            if (!s.isOccupied()){
                System.out.println("SeatId" + s.getSeatID());
            }
        }

    }

    public void showAssignedSeats(boolean bySeatId){
        System.out.println("The following seats are assigned:");
        PlaneSeat[] displayseat;
        if (bySeatId){
            displayseat = this.seat;
        }else{
            displayseat = sortSeats();
        }

        for (PlaneSeat s: displayseat){
            if (s.isOccupied()){
                System.out.println("SeatID" + s.getSeatID() + "assigned to CustomerID" + s.getCustomerID());
            }
        }
    }

    public void assignSeat(int seatId, int cust_id){
        int index = seatId -1;
        if (index < 0 || index >= this.seat.length){
            System.out.println("Invalid SeatID! Seat must be between 1 and 12.");
            return;
        }

        if (this.seat[index].isOccupied()){
            System.out.println("Seat already assigned to a customer.");
        }else{
            this.seat[index].assign(cust_id);
            System.out.println("Seat Assigned!");
            this.numEmptySeat --;
        }
    }

    public void unAssignSeat(int seatId){
        int index = seatId -1;
        if (index < 0 || index >= this.seat.length){
            System.out.println("Invalid SeatID! Seat must be between 1 and 12.");
            return;
        }

        if (!this.seat[index].isOccupied()){
            System.out.println("Seat is not assigned yet.");
        }else{
            this.seat[index].unAssign();
            System.out.println("Seat Unassigned!");
            this.numEmptySeat ++;
        }

        }
}