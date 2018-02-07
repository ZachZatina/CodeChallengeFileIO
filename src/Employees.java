
public class Employees {
	// instance variables
	private String name;
	private int age;
	private String favFood;
	
	public Employees() {
		
	}
	
	public Employees(String name, int age, String favFood) {
		this.name = name;
		this.age = age;
		this.favFood = favFood;
	}
	// getters and setters list
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavFood() {
		return favFood;
	}

	public void setFavFood(String favFood) {
		this.favFood = favFood;
	}
	
	@Override
	public String toString() {
		return name + "," + age + "," + favFood;
	}
	

}
