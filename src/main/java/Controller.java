/*
 * | Jose Alvarez    |
 * | SemesterProject |
 * | 09/01/2020      |
 * | Controller.java |
 */

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
  private ComboBox<String> comboItemType;
  @FXML
  private ComboBox<Integer> comBoxChooseQuan;
  //-------------------------------
  //Buttons
  @FXML
  private Button btnAddProduct;
  @FXML
  private Button btnRecordProduct;


  //----------------------------------------------
  //Global for the Database
  //-----------------------------------------------
  final String JDBC_DRIVER = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/ProjectDB";
  //  Database credentials
  final String USER = "";
  final String PASS = "";
  Connection conn = null;
  Statement stmt = null;
//-------------------------------------------------

  //list the products in the combo box in the products tab (table view)
  final ObservableList<Product> productLine = FXCollections
      .observableArrayList();

  ///List for the Production Record
  final ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  /* ---------------------------
   *
   * Initialize method
   *
   * ---------------------------*/
  public void initialize() {
    //connect to database
    //connectDB();

    ProductionRecord recordProd = new ProductionRecord(0);
    String product = recordProd.toString();
    txtArea.setText(product);

    //Add to combo box
    setCombBox();
    //Add to combo box item
    setItemBox();

    setupProductLineTable(productLine);
    //  loadProductionLog(productionRun);
    // loadProductList(productLine);

  }

  @FXML
  void recordProduction(ActionEvent event) {

    int amount = Integer.parseInt(String.valueOf(
        comBoxChooseQuan.getSelectionModel().getSelectedItem()));

    Product recordProd = lstProductList.getSelectionModel().getSelectedItem();

    ProductionRecord p;

    for (int i = 0; i < amount; i++) {
      p = new ProductionRecord(recordProd, i);
      productionRun.add(p);
    }

    addToDB(productionRun);
    showProduction(productionRun);
    //loadProductionLog(productionRun);

  }

  @FXML
    /* -------------------------------
     *
     * addProduct Method
     * Add product Button: when clicked
     *
     * --------------------------------*/
  void addProduct(ActionEvent event) {

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProjectDB";
    //  Database credentials
    final String USER = "";
    final String PASS = "";

    //Get the input text as strings
    String name = txtProductName.getText();
    String type = comboItemType.getValue();
    String manufacturer = txtManufacturer.getText();

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "INSERT INTO Product(name, type, manufacturer) "
          + "VALUES (  'iPod','AUDIO', 'Apple' )";

      //Print out the Strings from the table
      stmt.executeUpdate(sql);
      // STEP 4: Clean-up environment
      stmt.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //Clears the items in text fields
    txtProductName.clear();
    txtManufacturer.clear();

    //add to table view and list view
    //  setupProductLineTable(productLine);
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
    ObservableList<Integer> ProduceList = FXCollections
        .observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    comBoxChooseQuan.setItems(ProduceList);
    comBoxChooseQuan.getSelectionModel().selectFirst();
    comBoxChooseQuan.setEditable(true);

  }

  private void setItemBox() {
    // Add the values from the enum item type to the combo box in the product line tab
    ObservableList<String> pLList = FXCollections.observableArrayList();
    for (ItemType it : ItemType.values()) {
      pLList.add(String.valueOf(it));
    }
    comboItemType.getItems().addAll(pLList);
  }

  private void setupProductLineTable
      (ObservableList<Product> productLine) {
    // System.out.println(productName + type + manufacturer);
    // Product widget = new Widget(productName,ItemType.valueOf(type),manufacturer);
    // productLine.add(widget);

    tblExistingProdName.setCellValueFactory(new PropertyValueFactory("ID"));
    tblExistingProdName.setCellValueFactory(new PropertyValueFactory("NAME"));
    tblExistingItemType.setCellValueFactory(new PropertyValueFactory("TYPE"));
    tblExistingManufacturer
        .setCellValueFactory(new PropertyValueFactory("MANUFACTURER"));

    tblExistingPro.setItems(productLine);
    //  lstProductList.setItems(productLine);
  }

  private void loadProductList(ObservableList<Product> productLine) {
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM PRODUCT";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        String name = rs.getString(2);
        String type = rs.getString(3);
        String manufacturer = rs.getString(4);

        Product productDB = new Product(name, ItemType.valueOf(type),
            manufacturer) {
        };

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

