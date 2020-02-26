package designPattern;

import java.util.ArrayList;

// learn from 
// https://github.com/apriljdai/CC150/blob/master/Object-Oriented%20Design/ParkingLot.java
enum VehicleSize {Small, Medium, Large}

abstract class Vehicle{
	// use protected for inheritance, private can't be inherited
//	protected ArrayList<ParkingSpot> parkingSpots= new ArrayList<>();
	protected int spotNeeded;
	protected final VehicleSize size;
	protected boolean isParked;
	
	public Vehicle(VehicleSize size) {
		this.size = size;
	}
	public int getSpotsNeeded() {
		return spotNeeded;
	}
	
	public VehicleSize getSize() {
		return size;
	}
	
	public void setIsParked(boolean status) {
		isParked = status;
	}
	
	public boolean getIsParked() {
		return isParked;
	}
	
//	public void parkInSpot(ParkingSpot s) {
//		parkingSpots.add(s);
//	}
	
//	public void clearSpots() {
//		for(int i=0; i<parkingSpots.size(); i++) {
//			parkingSpots.get(i).removeVehicle();
//		}
//		parkingSpots.clear();
//	}
//	public abstract boolean canFitInSpot(ParkingSpot spot);
}

class Bus extends Vehicle{
	public Bus() {
		super(VehicleSize.Large);
		spotNeeded = 5;
	}

//	@Override
//	public boolean canFitInSpot(ParkingSpot spot) {
//		return spot.getSize()==VehicleSize.Large;
//	}
}

class Car extends Vehicle{
	public Car() {
		super(VehicleSize.Medium);
		spotNeeded = 2;
	}

//	@Override
//	public boolean canFitInSpot(ParkingSpot spot) {
//		return spot.getSize()==VehicleSize.Large || spot.getSize()==VehicleSize.Medium;
//	}
}

class Motor extends Vehicle{
	public Motor() {
		super(VehicleSize.Small);
		spotNeeded = 1;
	}

//	@Override
//	public boolean canFitInSpot(ParkingSpot spot) {
//		return spot.getSize()==VehicleSize.Large || spot.getSize()==VehicleSize.Medium || spot.getSize()==VehicleSize.Small;
//	}
}

class Level{
	private int floor;
	private ParkingSpot[] spots;
	private int availableSpots = 0;
	private static final int SPOT_PER_ROW = 10;
	
	public Level(int flr, int numberSpots) {
		floor = flr;
		availableSpots = numberSpots;
		spots = new ParkingSpot[numberSpots];
		
		int largeSpots = numberSpots/3;
		int mediumSpots = numberSpots/3;
		int smallSpots = numberSpots/3;
		for(int i=0; i<numberSpots; i++) {
			VehicleSize sz;
			int spotNum;
			if(i<largeSpots) {
				sz = VehicleSize.Large;
				spotNum = 5;
			}
			else if(i<largeSpots+mediumSpots) {
				sz = VehicleSize.Medium;
				spotNum = 2;
			}
			else {
				sz = VehicleSize.Small;
				spotNum = 1;
			}
			int row = i/SPOT_PER_ROW;
			//spots[i] = new ParkingSpot(this, row, i, sz);
			spots[i] = new ParkingSpot(this, row, spotNum, sz);

		}
	}
	
	public int availableSpots() {
		return availableSpots;
	}
	public boolean parkVehicle(Vehicle v){
		if(availableSpots()< v.getSpotsNeeded())
			return false;
		int spotNumber = findAvailableSpots(v);
		if(spotNumber<0)
			return false;
		return parkStartingAtSpot(spotNumber, v);
	}
	
	private boolean parkStartingAtSpot(int num, Vehicle v) {
		//v.clearSpots();
		boolean success= true;
		for(int i=num; i<num+v.spotNeeded; i++) {
			success &= spots[i].park(v);
		}
		availableSpots -= v.spotNeeded;
		return success;
	}
	
	private int findAvailableSpots(Vehicle v){
		int spotNeeded = v.getSpotsNeeded();
		int lastRow = -1;
		int spotsFound = 0;
		for(int i=0; i<spots.length; i++) {
			ParkingSpot spot = spots[i];
			if(lastRow != spots[i].getRow()) {
				spotsFound = 0;
				lastRow = spots[i].getRow();
			}
			if(spots[i].canFitVehicle(v))
				spotsFound++;
			else
				spotsFound=0;
			if(spotsFound == v.spotNeeded) {
				return i - (v.spotNeeded-1);
			}
		}
		return -1;
	}
	public void spotFree() {
		availableSpots++;
	}

}

class ParkingSpot{
    private Vehicle vehicle; 
    private VehicleSize spotSize; 
    private int spotNum; // spot size
    private int row; 
    private int spotNumber; 
    private Level level; 
    
    public ParkingSpot(Level l, int r, int spotNum, VehicleSize s) {
    	level = l;
    	row = r;
    	spotSize = s;
    	this.spotNum = spotNum;
    }
//    public ParkingSpot(Level l, int r, int n, VehicleSize s) {
//    	level = l;
//    	row = r;
//    	spotNumber = n; 
//    	spotSize = s;
//    }
    public boolean isAvailable() {
    	return vehicle==null;
    }
//    public boolean canFitVehicle(Vehicle v) {
//    	return isAvailable() && v.canFitInSpot(this);
//    }
    public boolean canFitVehicle(Vehicle v) {
    	return isAvailable() && (v.getSpotsNeeded()<=this.spotNum);
    }
    
    public boolean park(Vehicle v) {
    	if(!canFitVehicle(v))
    		return false;
    	vehicle = v;
    	v.setIsParked(true);
    	//v.parkInSpot(this);
    	return true;
    }
    public int getRow() {
    	return row;
    }
    public int getSpotNumber() {
    	return spotNumber;
    }
    public VehicleSize getSize() {
    	return spotSize;
    }
    public void removeVehicle() {
    	level.spotFree();
    	vehicle=null;
    }
}

public class ParkingLot {
	private Level[] levels;
	private final int NUM_LEVEL = 5;
	private static ParkingLot parkingLot= null;
	
	public ParkingLot() {
		levels = new Level[NUM_LEVEL];
		for(int i=0; i<NUM_LEVEL; i++)
			levels[i] = new Level(i,30);
	}
	
	public boolean parkVehicle(Vehicle v) {
		for(int i=0; i<levels.length; i++) {
			if(levels[i].parkVehicle(v))
				return true;
		}
		return false;
	}
	// singleton to create parkingLot instance
	public static ParkingLot getPL() {
		if(parkingLot==null)
			parkingLot = new ParkingLot();
		return parkingLot;
	}
	
}
