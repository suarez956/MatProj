package core.other;

public class Modifier {

	private int distance_throw = 1;
	private int hittable_count = 1;
	private boolean flammable = false;
	private int max_throwed = 3;
	
	public Modifier(int distance_throw, int hittable_count, boolean flammable, int max_throwed) {
		setDistance_throw(distance_throw);
		setFlammable(flammable);
		setHittable_count(hittable_count);
		setMax_throwed(max_throwed);
	}
	
	
	public int getDistance_throw() {
		return distance_throw;
	}
	public int getHittable_count() {
		return hittable_count;
	}
	public int getMax_throwed() {
		return max_throwed;
	}
	public boolean getFlammable() {
		return flammable;
	}
	
	public void setDistance_throw(int distance_throw) {
		this.distance_throw = distance_throw;
	}
	public void setFlammable(boolean flammable) {
		this.flammable = flammable;
	}
	public void setHittable_count(int hittable_count) {
		this.hittable_count = hittable_count;
	}
	public void setMax_throwed(int max_throwed) {
		this.max_throwed = max_throwed;
	}
	
}
