package RRPSS;

public class MenuItem {
	private int menuItemId;
	private String name;
	private String description;
	private double price;
	
	private MenuType menuType;
	
	public enum MenuType{
		MAIN("Main Dish"),
		SIDE("Side Dish"), 
		DRINK("Drink"), 
		DESSERT("Dessert");
		
		private final String desc;
		
		MenuType(String desc){
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
	}

	public MenuItem(int menuItemId, String name, String description, double price, MenuType menuType) {
		super();
		this.menuItemId = menuItemId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.menuType = menuType;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	
	
	
}
