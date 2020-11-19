import java.util.Date;

public class ProductionRecord {

  //---------------------
  // Private fields
  //---------------------
  //fields set to private for class production record
  public int productionNumber;
  public int productID;
  public String serialNumber;
  public Date dateProduced;
  public int itemCount;
  public static int audioCount;
  public static int visualCount;
  public static int audioMobile;
  public static int visualMobile;

  // ---------------------
  //Accessors and Mutators
  //----------------------

  //Method will get production number
  public int getProductionNum() {
    return productionNumber;
  }

  //Method that sets the production number
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  //Method will get production id
  public int getProductID() {
    return productID;
  }

  // Method that sets the production id
  public void setProductID(int productID) {
    this.productID = productID;
  }

  //Method will get serial number
  public String getSerialNum() {
    return serialNumber;
  }

  //Method that will set the serial number
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  //Method that will get dateProduced
  public Date getProdDate() {
    return dateProduced;
  }

  //Method that will set the dateProduced
  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  //----------------------
  //Basic Constructor
  //----------------------
  //Product record constructor that will take the fields and assigns them
  public ProductionRecord(int productionNumber, int productID,
      String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(dateProduced.getTime());

  }

  //Constructor with just productID parameter
  public ProductionRecord(int productID) {
    //parameter for product id, for when user records production
    this.productID = productID;
    //set productionNumber to 0 (int value)
    productionNumber = 0;
    //set the serial number to "0" (string value)
    serialNumber = "0";
    //set the date to current date
    Date dateProduced = new Date();
  }

  @Override
  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID
        + " Serial Num: " + serialNumber + " Date: " + dateProduced + "\n";
  }



  public ProductionRecord(Product productProduced) {
    this.dateProduced = new Date();

    if(productProduced.Type == ItemType.AUDIO )
    {
      generateSerial(productProduced, audioCount);
      audioCount++;
    }
    else if (productProduced.Type == ItemType.VISUAL)
    {
      generateSerial(productProduced,visualCount);
      visualCount++;
    }
    else if (productProduced.Type == ItemType.AUDIO_MOBILE)
    {
      generateSerial(productProduced,audioMobile);
      audioMobile++;
    }
    else if (productProduced.Type == ItemType.VISUAL_MOBILE)
    {
      generateSerial(productProduced,visualMobile);
      visualMobile++;
    }
  }

  public void generateSerial(Product productProduced, int itemCount)
  {
    setSerialNum(productProduced.getManufacturer().substring(0, 3) + productProduced
        .getType().code + String.format("%05d", itemCount));
  }
}