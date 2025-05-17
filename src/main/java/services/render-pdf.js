const fs = require('fs');
const puppeteer = require('puppeteer');

(async () => {
    if (process.argv.length < 4) {
        console.error('Kullanım: node render-pdf.js [htmlYolu] [pdfYolu]');
        process.exit(1);
    }
    const htmlPath = process.argv[2];
    const pdfPath = process.argv[3];

    const html = fs.readFileSync(htmlPath, 'utf8');
    const browser = await puppeteer.launch({
        args: ['--no-sandbox', '--disable-setuid-sandbox']
    });
    const page = await browser.newPage();
    await page.setContent(html, { waitUntil: 'networkidle0' });
    await page.pdf({
        path: pdfPath,
        width: '170mm',
        height: '230mm',
        printBackground: true,
        margin: { top: '0mm', bottom: '0mm', left: '0mm', right: '0mm' }
    });
    await browser.close();
    console.log('PDF başarıyla oluşturuldu:', pdfPath);
})();