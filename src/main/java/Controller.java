/*
 * | Jose Alvarez    |
 * | SemesterProject |
 * | 09/01/2020      |
 * | Controller.java |
 */

import static java.lang.Integer.parseInt;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

  //-----------------------------
  //TextFields
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufacturer;
  //-----------------------------
  //Table Columns, lists, text area
  @FXML
  private TableView<Product> tblExistingPro;
  @FXML
  private TableColumn<?, ?> tblExistingProdName; // Table column product name
  @FXML
  private TableColumn<?, ?> tblExistingManufacturer; // Table column manufacturer
  @FXML
  private TableColumn<?, ?> tblExistingItemType; // Table column item type
  @FXML
  private TextArea txtArea;
  @FXML
  private ListView<Product> lstProductList;
  //------------------------------
  //ComboBoxes
  @FXML
  private ChoiceBox<String> choiceBox;
  @FXML
  private ComboBox<String> comBoxChooseQuan;
  //-------------------------------
  //Buttons
  @FXML
  private Button btnAddProduct;
  @FXML
  private Button btnRecordProduct;


  //----------------------------------------------
  //Global for the Database
  //-----------------------------------------------

  //  Database credentials
  final String USER = "";
  final String PASS = "";
  Connection conn;
  Statement stmt;
  ResultSet rs;

//-------------------------------------------------

  //list the products in the combo box in the products tab (table view)
   ObservableList<Product> productLine = FXCollections
      .observableArrayList();


  /* ---------------------------
   *
   * Initialize method
   *
   * ---------------------------*/
  public void initialize() {
    //Add to combo box
    setCombBox();
    //Add to combo box item
    setChoiceBox();

    loadProductList();
    setupProductLineTable();
    // loadProductionLog();
  }

  @FXML
  void recordProduction(ActionEvent event) {

    int amount = parseInt(comBoxChooseQuan.getValue());

    Product recordProd = lstProductList.getSelectionModel().getSelectedItem();


   for (int i = 1; i <= amount; i++) {
      ProductionRecord pr = new ProductionRecord(recordProd);

      txtArea.appendText(pr.toString());
    }
    //loadProductionLog();
  }

  @FXML
    /* -------------------------------
     *
     * addProduct Method
     * Add product Button: when clicked
     *
     * --------------------------------*/
  void addProduct(ActionEvent event) throws SQLException {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProjectDB";
    //  Database credentials
    final String USER = "";
    final String PASS = "";

    //Get the input text as strings
    String AddName = txtProductName.getText();
    String AddItem = choiceBox.getValue();
    String AddManufacturer = txtManufacturer.getText();
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "INSERT INTO PRODUCT(NAME, TYPE, MANUFACTURER)"
          + "VALUES('" + AddName + "','" + AddItem + "','" + AddManufacturer + "')";

      stmt.executeUpdate(sql);
      //Print out the Strings from the table

      System.out.println(sql);
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    //Clears the items in text fields
    txtProductName.clear();
    txtManufacturer.clear();

    //productLine.add(new Widget(AddName,AddItem,AddManufacturer));
    //add to table view and list view
    //setupProductLineTable(productLine);
    //loads the items to table view and list view
    // loadProductList(productLine);
  }

  //Function that will add the information from production record to the database
  public void addToDB(ArrayList<ProductionRecord> productionRun) {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProjectDB";
    //  Database credentials
    final String USER = "";
    final String PASS = "";
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      for (ProductionRecord p : productionRun) {
        int productId = p.getProductID();
        String serialNumber = p.getSerialNum();
        //this might work better than date
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, "
            + "SERIAL_NUM,DATE_PRODUCED) VALUES(?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, productId);
        stmt.setString(2, serialNumber);
        stmt.setTimestamp(3, timestamp);

        //showProduction(productionRun);
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void setCombBox() {
    // Add the numbers to be selected in the produce combo box
   for(int i = 1; i <= 10; i++){
     comBoxChooseQuan.getItems().add(String.valueOf(i));
   }
    comBoxChooseQuan.getSelectionModel().selectFirst();
    comBoxChooseQuan.setEditable(true);
    comBoxChooseQuan.setValue("1");
  }

  private void setChoiceBox() {
    // Add the values from the enum item type to the combo box in the product line tab
    for (ItemType it : ItemType.values()) {
      choiceBox.getItems().add(String.valueOf(it));
    }
  }

  private void setupProductLineTable() {

    tblExistingProdName.setCellValueFactory(new PropertyValueFactory("Name"));
    tblExistingItemType.setCellValueFactory(new PropertyValueFactory("Type"));
    tblExistingManufacturer
        .setCellValueFactory(new PropertyValueFactory("Manufacturer"));

    tblExistingPro.setItems(productLine);
    lstProductList.setItems(productLine);
  }

  private void loadProductList() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProjectDB";
    //  Database credentials
    final String USER = "";
    final String PASS = "";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM PRODUCT";
      rs = stmt.executeQuery(sql);

      while (rs.next()) {
        String name = rs.getString(2);
        String type = rs.getString(3);
        String manufacturer = rs.getString(4);

        Product productDB = new Widget(name, ItemType.valueOf(type), manufacturer);

        productLine.add(productDB);
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void showProduction(ArrayList<ProductionRecord> productionRun) {
    txtArea.setText(productionRun.toString());
  }

/*
  private void loadProductionLog(ArrayList<ProductionRecord> productionRun) {
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM PRODUCTIONRECORD";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next())
      {
        int productNum = rs.getInt("PRODUCTION_NUM");
        int productId = rs.getInt("PRODUCT_ID");
        String serial = rs.getString("SERIAL_NUM");
        Date date = rs.getDate("DATE_PRODUCED");

        ProductionRecord addToProdRecord =
            new ProductionRecord(productNum,productId, serial,date);

        productionRun.add(addToProdRecord);

        //Display productionRun to txtArea
        showProduction(productionRun);
      }

    } catch(
        ClassNotFoundException e)
    { e.printStackTrace();
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }
*/

}

