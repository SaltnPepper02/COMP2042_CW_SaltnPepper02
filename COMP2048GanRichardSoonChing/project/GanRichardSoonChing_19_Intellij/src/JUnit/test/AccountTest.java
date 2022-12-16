
import com.example.demo.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void getUserName() {
        Account a = new Account("Tester", 100);
        assertEquals("Tester", a.getUserName());
    }
    @Test
    void getScore(){
        Account a = new Account("Tester", 100);
        assertEquals(100, a.getScore());
    }



}

