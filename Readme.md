# HTML to PDF Invoice Generator

A hybrid Java/Node.js automation that generates PDF invoices from HTML templates using Apache Velocity for template processing and Puppeteer for PDF conversion.

## Features

- HTML template processing with Apache Velocity
- PDF generation from HTML using Puppeteer
- Background colors and images support
- Zero-margin PDF rendering
- JSON data processing with Jackson
- Mock data generation using Mockoon

## Prerequisites

Following dependencies should be installed:
- Java Development Kit (JDK) 22
- Node.js (version 14 or higher)
- Maven
- npm (comes with Node.js)
- Mockoon (for mock data generation)

## Mock Data Configuration

The project uses Mockoon for generating realistic mock data. 

### Mockoon Features Used
- Faker.js integration for realistic data
- Repeatable data structures (10 items per endpoint)
- Nested objects and arrays
- Random number ranges
- Boolean flags
- Dynamic image URLs (using DiceBear API)

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd radity_test_case
```

### 2. Install Node.js Dependencies
```bash
npm install
```

This will install the required Node.js packages:
- Puppeteer (v24.8.2) for HTML to PDF conversion

### 3. Install Java Dependencies
```bash
mvn clean install
```

This will install the required Java dependencies:
- Jackson Databind (v2.15.2) for JSON processing
- Apache Velocity Engine (v2.3) for template processing

### 4. Configure Mockoon
1. Install Mockoon from [https://mockoon.com/](https://mockoon.com/)
2. Import the provided Mockoon environment file at /src/main/resources/radity_test.json (you can also check https://mockoon.com/templates/)
3. Start the mock server

## Usage

### 1. Generate Mock Data with Mockoon
1. Start your Mockoon server (default port: 3000)
2. Access the following endpoints to generate mock data:
   - `GET /companies` - Generates company information with logos
   - `GET /customers` - Generates customer data
   - `GET /invoices` - Generates detailed invoice data with line items

Example curl commands:
```bash
# Get company data
curl http://localhost:3000/companies

# Get customer data
curl http://localhost:3000/customers

# Get invoice data
curl http://localhost:3000/invoices
```

### 2. Data Models
The application uses three main data models:

1. Company Model:
   - Basic company information
   - Logo generation using DiceBear API
   - Contact details and address

2. Customer Model:
   - Customer information
   - Contact details
   - Active status tracking

3. Invoice Model:
   - Invoice details
   - Billing information
   - Line items with pricing
   - Payment status and instructions

### 3. Template Processing
After obtaining mock data:
1. The application processes the data using Jackson for JSON parsing
2. Velocity engine merges the data with HTML templates
3. Templates are located in `/src/main/resources/templates`
4. Processed templates are saved to the `/outputs` directory

### 4. PDF Generation
Convert the processed HTML templates to PDF.


## PDF Configuration

The PDF generation is configured with the following settings:
- Margins: 0mm (full-content rendering)
- Background graphics: Enabled
- Print media: Enabled

## Development Notes

- The project uses UTF-8 encoding for all files
- Template processing is handled by Velocity Engine 2.3
- JSON processing uses Jackson Databind 2.15.2
- PDF generation runs Chromium in headless mode
- Mock data is generated using Mockoon with Faker.js templates

## License

This project is licensed under the ISC License.

## Additional Resources

- Mockoon Invoice Template (provided)
- Word document template reference (provided)
- Mockoon environment file with mock data configuration
