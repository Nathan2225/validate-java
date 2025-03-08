//package src;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validate {

  public static boolean zip(String input) {
    Pattern fiveDigits = Pattern.compile("^[0-9]{5}$");
    Matcher match = fiveDigits.matcher(input);
    return match.matches();
  }

  public static boolean minor(int age) {
		if(age > 17) {
			return false;
		}
		
		return true;
	}

  public static boolean email(String input) {
		if(input.contains("@") && input.contains("."))
      return true;
		
		return false;
	}

  public static boolean isLat(Double value) {
    return value >= -90 && value <= 90;
  }

  public static boolean isLng(Double value) {
    return value >= -180 && value <= 180;
  }

  public static boolean isDomain(String input){
		//not perfect, but probably 95% accurate.
		Pattern p = Pattern.compile("^[a-zA-Z0-9-]{2,253}\\.[a-zA-Z]{1}[a-zA-Z0-9]{1,}$");
		Matcher m = p.matcher(input);
		
		return m.find();
	}

  public static boolean isInDomain(String domain, String url) {
		return url.indexOf(domain) > -1 ;
	}
  
  public static boolean isUrl(String input) {
		//not perfect, but probably 95% accurate.
		Pattern p = Pattern.compile("^http(s)?:\\/\\/(.*?)\\.[a-z]{2,52}\\/.*$");
		Matcher m = p.matcher(input);
		
		return m.find();
	}
  
  /**
   * TODO: Does this look correct? Can you improve it to pass more tests?
   */
  public static char grade(Double value) {
    if (value == null || value.isNaN()){
      throw new IllegalArgumentException();

    }
    
    if(value < 60){
      return 'F';
    }else if(value < 70){
      return 'D';
    }else if(value < 80){
      return 'C';
    }else if(value < 90){
      return 'B';
    } else {return 'A';
    }
  }

  /**
   * TODO: Does this look correct? Can you improve it to pass more tests?
   */
  public static String sanitize(String sql) {
    if (sql == null){
      return "";
    }
    sql = sql.toUpperCase();
    String[] words = {"ADMIN", "OR", "COLLATE", "DROP", "AND", "UNION"};
    for (String keyword : words) {
        sql = sql.replace(keyword, "");
    }
    String[] Chars = {"/*", "*/", "//", ";", "||", "&&", "--", "#", "=", "!=", "<>"};
    for (String ch : Chars) {
        sql = sql.replaceAll(Pattern.quote(ch), "");
    }

    return sql;
  }


  /**
   * TODO: Does this look correct? Can you improve it to pass more tests?
   */
  public static String stripNull(String sn) {
    sn = sn.toUpperCase().replace("NULL", "");
    
    if (sn == null) {
      return "";  
    }

    return sn.replaceAll("\\bNULL\\b", "");
  }

  /**
   * TODO: Implement IP address validation
   * Consider white-listing, regex
   */
  public static boolean ip(String input){
    String regex = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    return Pattern.matches(regex, input);
  }

  /**
   * TODO: Implement MAC address validation
   * Consider white-listing, regex
   * allow : - and whitespaces
   */
  public static boolean mac(String input){
    return Pattern.matches("^([0-9A-Fa-f]{2}[:-]?){5}([0-9A-Fa-f]{2})$", input);
  }

  /**
   * TODO: Implement hash value validation
   * Consider white-listing, regex
   */
  public static boolean md5(String input){
    return Pattern.matches("^[a-fA-F0-9]{32}$", input);
  }







}
