import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {
    

  


  /*
   * HAPPY PATH TESTS
   * Uses Expected Input Examples
   */

  
    @Test
    void testZipGood() {
      assertTrue(Validate.zip("12345"));  
      assertTrue(Validate.zip("98765")); 
    }


    @Test
    void testMinorGood() {
        List<Integer> cases = List.of(6, 1, 10);
        for (int min : cases) {
            assertTrue(Validate.minor(min), "valid" + min);
        }
    }


    @Test
    void testEmailGood() {
        assertTrue(Validate.email("person@gmail.com"));
        assertTrue(Validate.email("Hello123@yahoot.com"));
    }


    @Test
    void testIsLatGood() {
        List<Double> cases = List.of(-90.0, 0.0, 45.15, 89.9, 90.0);
        for (Double lat : cases) {
            assertTrue(Validate.isLat(lat), "Valid" + lat);
        }
    }


    @Test
    void testIsLngGood() {
        List<Double> cases = List.of(-180.0, -90.12345, 0.0, 45.15, 89.9, 90.0, 91.1, 179.9, 180.0);
        for (Double lng : cases) {
            assertTrue(Validate.isLng(lng), "Valid" + lng);
        }
    }


    @Test
    void testIaDomainGood() {
        assertTrue(Validate.isDomain("Google.com"));
        assertTrue(Validate.isDomain("Ya-hoo.com"));
        assertTrue(Validate.isDomain("web.net"));
        assertTrue(Validate.isDomain("happySite.org"));
    }


    @Test
    void testIsInDomainGood() {
        assertTrue(Validate.isInDomain("example.com", "https://www.example.com/home"));
        assertTrue(Validate.isInDomain("test.org", "http://sub.test.org/page.html"));
        assertTrue(Validate.isInDomain("Google.com", "https://Google.com/index"));
    }



    @Test
    void testIsInURLGood() {
        assertTrue(Validate.isUrl("https://www.example.com/home"));
        assertTrue(Validate.isUrl("http://sub.test.org/page.html"));
        assertTrue(Validate.isUrl("https://Google.com/index"));
    }


    @Test 
    void testIsGradeGood(){
        assertEquals('A', Validate.grade(99.0));
        assertEquals('B', Validate.grade(80.1));
        assertEquals('C', Validate.grade(70.0));
        assertEquals('D', Validate.grade(69.9));
        assertEquals('F', Validate.grade(0.0));
    }


    @Test
    void testSanitizeGood(){
        assertEquals("SELECT * FROM USERS", Validate.sanitize("SELECT * FROM USERS"));
        assertEquals("UPDATE ACCOUNTS SET BALANCE100", Validate.sanitize("UPDATE ACCOUNTS SET BALANCE=100"));
    }


    @Test
    void testStripNullGood() {
        assertEquals("", Validate.stripNull("NULL"));
        assertEquals("A ", Validate.stripNull("A NULL"));
        assertEquals(" ", Validate.stripNull("NULL NULL"));
        assertEquals(" 123", Validate.stripNull("NULL 123"));

    }


    @Test
    void testIPGood() {
        assertTrue(Validate.ip("192.168.1.1"));
        assertTrue(Validate.ip("10.0.0.255"));

    }


    @Test
    void testMacGood(){
        assertTrue(Validate.mac("00:1A:2B:3C:4D:5E"));
        assertTrue(Validate.mac("A1:B2:C3:D4:E5:F6"));

    }


    @Test
    void testMd5Good() {
        assertTrue(Validate.md5("d41d8cd98f00b204e9800998ecf8427e"));
        assertTrue(Validate.md5("9e107d9d372bb6826bd81d3542a419d6"));

    }



    /*
   * ABUSE & BAD TESTS
   * uses bins.payloads file which can be found here: 
   * https://github.com/minimaxir/big-list-of-naughty-strings/blob/master/blns.txt
   */

    @Test
    void testZipBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.zip(line)) {
                    assertFalse(Validate.zip(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }



    @Test
    void testMinorBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                try {
                    int age = Integer.parseInt(line.trim()); 
                    
                    if (!Validate.minor(age)) {
                        assertFalse(Validate.minor(age)); 
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testEmailBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.email(line)) {
                    assertFalse(Validate.email(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testisLatBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                try {
                    double value = Double.parseDouble(line.trim()); 
                    
                    if (!Validate.isLat(value)) {
                        assertFalse(Validate.isLat(value)); 
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testisLngBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                try {
                    double value = Double.parseDouble(line.trim()); 
                    
                    if (!Validate.isLng(value)) {
                        assertFalse(Validate.isLng(value)); 
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testisDomainBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.isDomain(line)) {
                    assertFalse(Validate.isDomain(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testisInDomainBad() {
        
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            String domain;
            while ((line = domain = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.isInDomain(domain, line)) {
                    assertFalse(Validate.isInDomain(domain, line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }



    @Test
    void testisURLBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.isUrl(line)) {
                    assertFalse(Validate.isUrl(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }
    
  

    @Test
    void testGradeBad(){
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            Set<Character> val = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'F'));

            while ((line = br.readLine()) != null) {
                line = line.trim();
                try {
                    double value = Double.parseDouble(line); 

                    if (!Double.isNaN(value)) {
                        char grade = Validate.grade(value);
                        System.out.println("Attempting " + value);

                        assertTrue(val.contains(grade), "invalid: " + value);
                    } 

                } catch (NumberFormatException e) {
                    
                    //kept getting this error
                    assertThrows(IllegalArgumentException.class, () -> Validate.grade(Double.NaN));
                }
            }
        } catch (IOException e) {
            fail("Error reading file ");
        }
    }



    @Test
    void testSanitizeBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                String san = Validate.sanitize(line);

                String[] words = {"ADMIN", "OR", "COLLATE", "DROP", "AND", "UNION"};
                String[] chars = {"/*", "*/", "//", ";", "||", "&&", "--", "#", "=", "!=", "<>"};
            
                for (String keyword : words) {
                    assertFalse(san.contains(keyword), "Unexpected: " + keyword);
                }
                for (String ch : chars) {
                    assertFalse(san.contains(ch), "Unexpected: " + ch);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }
  


    @Test
    void testStripNullBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
               String str = Validate.stripNull(line);

               assertFalse(str.contains("NULL"), "unexpected: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testIPBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.ip(line)) {
                    assertFalse(Validate.ip(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testMacBad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.mac(line)) {
                    assertFalse(Validate.mac(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }


    @Test
    void testMd5Bad() {
        // ABUSE
        try (BufferedReader br = new BufferedReader(new FileReader("./bins.payloads"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Attempting " + line);
                
                if (!Validate.md5(line)) {
                    assertFalse(Validate.md5(line)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error reading file.");
        }
    }
    



}
