/*
 * | Jose Alvarez      |
 * | SemesterProject   |
 * | 10/01/2020        |
 * | Product.java      |
 */
//abstract type called Product that will implement the Item interface.
public abstract class Product implements Item {

  //Product fields
  public int Id;
  public ItemType Type;
  public String Manufacturer;
  public String Name;

  Product() {
  }

  //Product constructor that will take the fields and set them
  Product(String name, ItemType type, String manufacturer) {
    this.Name = name;
    this.Type = type;
    this.Manufacturer = manufacturer;
  }

  @Override
  public String toString() {
    return "Name: " + Name + "\n" + "Type: " + Type.code + "\n"
        + "Manufacturer: " + Manufacturer;
  }

  public int getId() {
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
    this.Manufacturer = manufacturer;
  }

  @Override
  public String getName() {
    return Name;
  }

  @Override
  public void setName(String name) {
    this.Name = name;
  }

  public ItemType getType() {
    return Type;
  }

  public void setType(ItemType type) {
    this.Type = type;
  }

}




