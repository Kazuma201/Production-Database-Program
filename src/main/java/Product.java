
//abstract type called Product that will implement the Item interface.
public abstract class Product implements Item {

  //Product fields
 private int Id;
 private String Type;
 private String Manufacturer;
 private String Name;


 Product(String name, String manufacturer, String type)
 {
   this(0,name,manufacturer,type);
 }

//Product constructor that will take the fields and set them
  Product(int id, String name, String manufacturer, String type)
  {
    this.Name = name;
    this.Manufacturer = manufacturer;
    this.Type = type;
    this.Id = id;

  }
/*
  public Product(String name, ItemType audio, String manufacturer) {
  }*/

  @Override
  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: "
        + Type;
  }

  public int getId()
  {
    return Id;
  }
  public void setId(int id){
    Id = id;
  }

  public String getType() {
    return Type;
  }

  public void setType(String type){
    Type = type;
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
}

