import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller
{
  //---------------------------------------------
//ProductLine
@FXML
public void addProduct()
{
  System.out.println("Something");
}

  @FXML
  public void tblExistingPro(){}

  @FXML
  public void lblExistingPro(){}

  @FXML
  public void lblProductName(){}

  @FXML
  public void lblManufacturer(){}

  @FXML
  public void lblItemType(){}

  @FXML
  public void txtProductName(){}

  @FXML
  public void txtManufacturer(){}

  @FXML
  public void txtItemType(){}


  @FXML
  public void btnAddProduct(){}


  //---------------------------------------------
  //Produce
  @FXML
  public void comBoxChooseQuan(){}

  @FXML
  public void recordProduction()
  {
    System.out.println();
  }
  @FXML
  public void lblChooseProduct(){}

  @FXML
  public void lstProductList(){}

  @FXML
  public void lblChooseQuantity(){}

  @FXML
  public void btnRecordProduct(){}
  //ProductionLog
  @FXML
  public void txtArea(){}

  public void initialize()
  {
    connectToDb();
  }
  public static void connectToDb()
  {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/HRdb";

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

      String sql = "SELECT * FROM JOBS";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        System.out.println(rs.getString(1));
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
  }

