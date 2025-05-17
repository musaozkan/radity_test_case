package services;

import java.io.*;

public class PDFService {
    public static void htmlToPdf(String html, String outputPdf) throws Exception {
        File tempHtml = File.createTempFile("invoice-", ".html");
        try (FileWriter writer = new FileWriter(tempHtml)) {
            writer.write(html);
        }

        String nodeScript = "src/main/java/services/render-pdf.js"; // Ana dizinde olduğuna emin ol
        ProcessBuilder pb = new ProcessBuilder(
                "node", nodeScript, tempHtml.getAbsolutePath(), outputPdf
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Puppeteer PDF render failed! Exit code: " + exitCode);
        }
        tempHtml.delete();
    }
}