package TheCoinMachine.requests;

import TheCoinMachine.controllers.CoinController;

//import com.hackerrank.test.utility.Order;
//import com.hackerrank.test.utility.OrderedTestRunner;
//import com.hackerrank.test.utility.TestWatchman;

import TheCoinMachine.domain.Transaction;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hackerrank.test.utility.OrderedTestRunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(OrderedTestRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class ControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new CoinController()).build();
        this.objectMapper = new ObjectMapper();
    }

    private void performPostAndExpect(Transaction transaction, int rightPersonExpected, int leftPersonExpected) throws Exception {
        mockMvc.perform(post("/coins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rightPerson").value(rightPersonExpected))
                .andExpect(jsonPath("$.leftPerson").value(leftPersonExpected));
    }

//    private void performPostAndExpectBadRequest(Transaction transaction) throws Exception {
//        mockMvc.perform(post("/coins")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(transaction)))
//                .andExpect(status().isBadRequest());
//    }
    
    @Test
    public void testSampleCase() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setRightPerson(List.of("P", "P", "R"));
        transaction.setLeftPerson(List.of("P", "R", "R"));

        performPostAndExpect(transaction, 4, 8);
    }

    @Test
    public void testBothShare() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setRightPerson(List.of("P", "P", "P"));
        transaction.setLeftPerson(List.of("P", "P", "P"));

        performPostAndExpect(transaction, 9, 9);
    }

    @Test
    public void testBothWait() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setRightPerson(List.of("R", "R", "R"));
        transaction.setLeftPerson(List.of("R", "R", "R"));

        performPostAndExpect(transaction, 3, 3);
    }

    @Test
    public void testInterleaved() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setRightPerson(List.of("P", "R", "P"));
        transaction.setLeftPerson(List.of("R", "P", "R"));

        performPostAndExpect(transaction, 4, 8);
    }

//    @Test
//    public void testInvalidInput() throws Exception {
//        Transaction transaction = new Transaction();
//        transaction.setRightPerson(List.of("P", "X", "R"));
//        transaction.setLeftPerson(List.of("R", "P", "R"));
//
//        performPostAndExpectBadRequest(transaction);
//    }
//
//    @Test
//    public void testDifferentLength() throws Exception {
//        Transaction transaction = new Transaction();
//        transaction.setRightPerson(List.of("P", "P"));
//        transaction.setLeftPerson(List.of("R", "R", "R"));
//
//        performPostAndExpectBadRequest(transaction);
//    }
}
