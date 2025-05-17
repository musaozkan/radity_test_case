import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Client;
import model.Company;
import model.Invoice;
import services.VelocityRenderer;
import services.PDFService;

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
            List<Client> clients = mapper.readValue(
                    new URL("http://localhost:3000/clients"),
                    new TypeReference<List<Client>>() {}
            );

            List<Company> companies = mapper.readValue(
                    new URL("http://localhost:3000/companies"),
                    new TypeReference<List<Company>>() {}
            );

            Invoice invoice = invoices.get(0); // Take the first invoice
            Client client = clients.get(0); // Take the first client
            Company company = companies.get(0); // Take the first company
            String html = VelocityRenderer.renderInvoice(invoice, client, company);

            // Save HTML to PDF
            PDFService.htmlToPdf(html, "outputs/invoice.pdf");
            System.out.println("HTML output saved to: " +"outputs/invoice.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
