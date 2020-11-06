/*
 * | Jose Alvarez    |
 * | SemesterProject |
 * | 09/01/2020      |
 * | Controller.java |
 */
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

public class Controller {

  @FXML
  private Label lblProductName;
  @FXML
  private Label lblManufacturer;
  @FXML
  private Label lblItemType;
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufacturer;
  @FXML
  private ComboBox<String> cmboItemType;
  @FXML
  private Button btnAddProduct;
  @FXML
  private TableView<Product> tblExistingPro;
  @FXML
  private TableColumn<?, ?> tblExistingProdName; // Tablecolumn product name

  @FXML
  private TableColumn<?, ?> tblExistingManufacturer; // Tablecolumn manufacturer

  @FXML
  private TableColumn<?, ?> tblExistingItemType; // Tablecolumn item type

  @FXML
  private Label lblExistingPro;
  @FXML
  private Label lblChooseProduct;
  @FXML
  private ListView<Product> lstProductList;
  @FXML
  private Label lblChooseQuantity;
  @FXML
  private ComboBox<String> comBoxChooseQuan;
  @FXML
  private Button btnRecordProduct;
  @FXML
  private TextArea txtArea;

  final String JDBC_DRIVER = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/ProjectDB";
  //  Database credentials
  final String USER = "";
  final String PASS = "";
  Connection conn = null;
  Statement stmt = null;


  //list the products in the combo box in the products tab (table view)
  ObservableList<Product> productLine = FXCollections.observableArrayList();
  ObservableList<Product> data = productLine();

  @FXML
  void addProduct(ActionEvent event)
  {

    updateProductLine();

    String name = txtProductName.getText();
    String manufacturer = txtManufacturer.getText();
    String type = String.valueOf(comBoxChooseQuan.getValue());

    try {
      stmt = conn.createStatement();
      String sql = "SELECT PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM,"
          + " PRODUCTIONRECORD";

      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next())
      {
        txtArea.appendText(
            ("Product Number: ") + rs.getString(1) + ("")
            + ("Product ID: ") + rs.getString(2) + ("")
                + ("Serial Number: ") + rs.getString(3));
      }
      final String insertInfo = "INSERT INTO PRODUCT(NAME, MANUFACTURER, TYPE)"
          + "VALUES(?,?,?)";

      PreparedStatement pState = conn.prepareStatement(insertInfo);
      pState.setString(1, name);
      pState.setString(2, manufacturer);
      pState.setString(3, type);

      pState.executeUpdate();

    }
    catch (SQLException e) {
      txtArea.appendText(e.toString());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

 public void updateProductLine()
  {

    tblExistingProdName.setCellValueFactory(new PropertyValueFactory("Product name"));
    tblExistingManufacturer.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    tblExistingItemType.setCellValueFactory(new PropertyValueFactory("ItemType"));

    tblExistingPro.setItems(data);


  }

 public static ObservableList<Product> productLine()
 {
  return FXCollections.observableArrayList();
  }

  @FXML
  void recordProduction(ActionEvent event)
  {
  }
  public void initialize()
  {
    updateProduct();
    outputTable();
    setCombBox();
   // setTxtArea();

    setupProductLineTable();
  }

  /*
  private void setTxtArea()
  {
    ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date());
    txtArea.setText(pr.toString());
  }*/

  private void setCombBox()
  {
    comBoxChooseQuan.setEditable(true);
    comBoxChooseQuan.getSelectionModel().selectFirst();

    for(int count = 1; count <=10; count++) {
      comBoxChooseQuan.getItems().add(String.valueOf(count));

      cmboItemType.setEditable(true);
      cmboItemType.getSelectionModel().selectFirst();
      for (ItemType it : ItemType.values()) {
        cmboItemType.getItems().add(it.code);
      }
    }
  }


  public void outputTable() // connect to Database
  {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProjectDB";
    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT* FROM PRODUCT";

      //Print out the Strings from the table
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next())
      {
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getString(4));
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



  public void updateProduct()
  {
    //Get the input text as strings
    String productName = txtProductName.getText();
    String manufacturer = txtManufacturer.getText();

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //STEP 3: Execute a query
      stmt = conn.createStatement();


      String sql = "INSERT INTO Product(type, manufacturer, name) "
          + "VALUES ( 'AUDIO', '"+ productName + "', '" + manufacturer +"' )";

      stmt.executeUpdate(sql);

      outputTable();

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void setupProductLineTable ()
  {
    lstProductList.setItems(data);
  }
  }