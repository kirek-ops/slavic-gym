package MMM.demo.Utils;

public class ErrorCutter {
  public static String cut(String errorMessage) {
    int errorIndex = errorMessage.indexOf("ERROR");
    if (errorIndex != -1) {
      errorMessage = errorMessage.substring(errorIndex);
    } else {
      return errorMessage;
    }
    int whereIndex = errorMessage.indexOf("Where:");
    if (whereIndex != -1) {
      errorMessage = errorMessage.substring(0, whereIndex).trim();
    }
    return errorMessage;
  }
}