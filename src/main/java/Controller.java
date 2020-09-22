import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
  private ComboBox<?> cmboItemType;

  @FXML
  private Button btnAddProduct;

  @FXML
  private TableView<?> tblExistingPro;

  @FXML
  private Label lblExistingPro;

  @FXML
  private Label lblChooseProduct;

  @FXML
  private ListView<?> lstProductList;

  @FXML
  private Label lblChooseQuantity;

  @FXML
  private ComboBox<String> comBoxChooseQuan;

  @FXML
  private Button btnRecordProduct;

  @FXML
  private TextArea txtArea;

  @FXML
  void addProduct(ActionEvent event) {
    updateProduct();
  }

  @FXML
  void recordProduction(ActionEvent event) {

  }
  public void initialize()
  {

    comBoxChooseQuan.setEditable(true);
    comBoxChooseQuan.getSelectionModel().selectFirst();

    for(int count = 1; count <=10; count++) {
      comBoxChooseQuan.getItems().add(String.valueOf(count));
    }
  }

  public void outputTable()
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
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProjectDB";
    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

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

}