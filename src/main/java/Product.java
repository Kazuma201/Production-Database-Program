/*
 * | Jose Alvarez      |
 * | SemesterProject   |
 * | 10/01/2020        |
 * | Product.java      |
 */
//abstract type called Product that will implement the Item interface.
public abstract class Product implements Item {

  //Product fields
  private int Id;
  private ItemType Type;
  private String Manufacturer;
  private String Name;

  Product(){}

  //Product constructor that will take the fields and set them
  Product(String name, String manufacturer, ItemType type)
  {
    this.Name = name;
    this.Manufacturer = manufacturer;
    this.Type = type;
  }

  @Override
  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: "
        + Type.getCode();
  }

  public int getId()
  {
    return Id;
  }
  public void setId(int id) {
    this.Id = id;
  }

  @Override
  public String getManufacturer() {
    return Manufacturer;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    Manufacturer = manufacturer;
  }
  @Override
  public String getName() {
    return Name;
  }

  @Override
  public void setName(String name) {
    Name = name;
  }

  public ItemType getType() {
    return Type;
}

  public void setType(ItemType type) {
    Type = type;
  }

  }

/*
class Widget extends Product {

  //Create a widget
  Widget(String name,String manufacturer, ItemType type )
  {
    super(name,manufacturer,type);
  }
  */

