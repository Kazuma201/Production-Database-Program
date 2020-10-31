import java.util.Date;

public class ProductionRecord {

  //---------------------
  // Private fields
  //---------------------

  //fields set to private for class production record
  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  // ---------------------
  //Accessors and Mutators
  //----------------------

  //Method will get production number
  public int getProductionNum()
  {
    return productionNumber;
  }
  //Method that sets the production number
  public void setProductionNum(int productionNumber)
  {
    this.productionNumber = productionNumber;
  }
  //Method will get production id
  public int getProductID()
  {
    return productID;
  }
  // Method that sets the production id
  public void setProductID(int productID)
  {
    this.productID = productID;
  }
  //Method will get serial number
  public String getSerialNum()
  {
    return serialNumber;
  }
  //Method that will set the serial number
  public void setSerialNum(String serialNumber)
  {
    this.serialNumber = serialNumber;
  }
  //Method that will get dateProduced
  public Date getProdDate()
  {
    return dateProduced;
  }
  //Method that will set the dateProduced
  public void setProdDate(Date dateProduced)
  {
    this.dateProduced = dateProduced;
  }

  //----------------------
  //Basic Constructor
  //----------------------
  //Product record constructor that will take the fields and assigns them
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced)
  {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;

  }
  //Constructor with just productID parameter
  public ProductionRecord(int productID)
  {
    //parameter for product id, for when user records production
    this.productID = productID;
    //set productionNumber to 0 (int value)
    productionNumber = 0;
    //set the serial number to "0" (string value)
    serialNumber = "0";
    //set the date to current date
    dateProduced = new Date();
  }

  @Override
  public String toString()
  {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID
        + " Serial Num: " + serialNumber + " Date: " + dateProduced;
  }
  //
  //ProductionRecord


}