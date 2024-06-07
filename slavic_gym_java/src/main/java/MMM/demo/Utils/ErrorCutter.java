package MMM.demo.Utils;

public class ErrorCutter {
  public static String cut(String errorMessageOrig) {
    String errorMessage = new String(errorMessageOrig);
    int errorIndex = errorMessage.indexOf("ERROR");
    if (errorIndex != -1) {
      errorMessage = errorMessage.substring(errorIndex);
    }
    int whereIndex = errorMessage.indexOf("Where:");
    if (whereIndex != -1) {
      errorMessage = errorMessage.substring(0, whereIndex).trim();
    }
    return errorMessage;
  }
}