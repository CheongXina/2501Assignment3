package com.example.demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.demo.Controller.OrderController;
import com.example.demo.Models.BookOrder;
import com.example.demo.OrderRepo;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/orders")
public class TestOrderController {

    final String MICRO_A = "http://localhost:8081/books/";
    @Mock
    private OrderRepo orderRepo;

    private OrderController orderController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderController = new OrderController();
        orderController.setOrderRepo(orderRepo);
    }
    @Test
    public List<BookOrder> getAllBookOrders() {
        return orderRepo.findAll(); // Return all book orders from the database
    }
    @Test
    public ResponseEntity<String> placeOrder(@PathVariable Long id) {
        try {
            String url = MICRO_A + id;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject jsonResponse = new JSONObject(response.toString());
                String name = jsonResponse.getString("name");
                double price = jsonResponse.getDouble("price");
                BookOrder newOrder = new BookOrder();
                newOrder.setBookId(jsonResponse.getLong("id"));
                return ResponseEntity.ok("Book found: " + "Name: " + name + ", Price: " + price +
                        " ORDER BOOKID: " + newOrder.getBookId()
                );

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order failed: Book not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order failed: Internal server error");
        }
    }
    @Test
    public String testMsg() {
        try {
            String url = "http://localhost:8081/books/test";
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return "Micro B recieved: " + response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error contacting the server.";
        }
    }

}
