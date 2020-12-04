import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
// class fields

  StringBuilder name;
  String username;
  String password;
  String email;

//---------------
// Constructor
//---------------

  Employee(String name, String password) {
  this.name = new StringBuilder(name);
  if (checkName(name)){
    setUsername(name);
    setEmail(name);
  }
  else{
    username = "default";
    email = "user@oracleacademy.Test";
  }
  if (isValidPassword(password)){
    this.password = password;
  }
  else {
    this.password = "pw";
  }
  }

//---------
// Methods
//---------

  private void setUsername(String name) {
    String[] createName = name.split(" ");
    username = createName[0].substring(0,1) + createName[1];
    username = username.toLowerCase();
  }
  public String getUsername() {
    return username;
  }

//checkName method

  private boolean checkName(String name) {
    if (name.contains(" ") && name.length() - 1 != name.indexOf(" ")) {
      return true;
    }
    else
      {
      return false;
      }
  }


  private void setEmail(String name) {
    email = (name.substring(0, name.indexOf(" ")) + "."
        + name.substring(name.indexOf(" ") + 1)).toLowerCase()
        + "@oracleacademy.Test";
    //this.email = name +  "@oracleacademy.Test";
  }
  public String getEmail()
  {
    return email;
  }

  private boolean isValidPassword(String password)
  {
    String exceptions = "([!@#$%^&*()-_=+<>?].*?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+<>?]).*";
    Pattern pattern = Pattern.compile(exceptions);
    Matcher match = pattern.matcher(password);

    boolean validPassword = match.find();
    return validPassword;
  }

  public String getPassword()
  {
    return password;
  }

  public String toString() {
    return "Employee Details : " + "\n"
        + "Name : " + name + "\n"
        + "Username : " + username.toLowerCase() + "\n"
        + "Email : " + email + "\n" + "Initial Password : " + password;
  }

  public String reverseString(String pw) {
    if(pw.isEmpty())
    {
      return pw;
    }
    else
    {
      return reverseString(pw.substring(1))+pw.charAt(0);
    }
  }

}


