import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Invoice;

import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Fetch invoice data from Mockoon
            ObjectMapper mapper = new ObjectMapper();
            List<Invoice> invoices = mapper.readValue(
                    new URL("http://localhost:3000/invoices"),
                    new TypeReference<List<Invoice>>() {}
            );
            Invoice invoice = invoices.get(0); // Take the first invoice

            // Print output to verify
            System.out.println(invoice.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
