import com.google.gson.Gson;
//import com.dampcake.bencode.Bencode; 

public class Main {
  private static final Gson gson = new Gson();

  public static void main(String[] args) throws Exception {

    System.out.println("Logs from your program will appear here!");
    String command = args[0];
    if("decode".equals(command)) {
      String bencodedValue = args[1];
      String decoded;
      try {
      decoded = decodeBencode(bencodedValue);
      } catch(RuntimeException e) {
      System.out.println(e.getMessage());
      return;
      }
      System.out.println(gson.toJson(decoded));

    } else {
      System.out.println("Unknown command: " + command);
    }

  }

  static String decodeBencode(String bencodedString) {
    if (Character.isDigit(bencodedString.charAt(0))) {
      int firstColonIndex = 0;
      for(int i = 0; i < bencodedString.length(); i++) { 
        if(bencodedString.charAt(i) == ':') {
          firstColonIndex = i;
          break;
        }
      }
      int length = Integer.parseInt(bencodedString.substring(0, firstColonIndex));
      return bencodedString.substring(firstColonIndex+1, firstColonIndex+1+length);
    } else {
      throw new RuntimeException("Only strings are supported at the moment");
    }
  }
  
}
