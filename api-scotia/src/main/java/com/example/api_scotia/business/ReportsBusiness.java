package com.example.api_scotia.business;

import com.example.api_scotia.entities.CustomerEntity;
import com.example.api_scotia.service.ReportsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportsBusiness implements ReportsService {


    @Override
    public ByteArrayInputStream customersToExcel(List<CustomerEntity> customers) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Customers");

            // Crear el encabezado
            Row headerRow = sheet.createRow(0);
            String[] headers = { "ID", "Name", "Email", "Phone", "Address" };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Rellenar datos de clientes
            int rowIdx = 1;
            for (CustomerEntity customer : customers) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(customer.getCustomerId());
                row.createCell(1).setCellValue(customer.getName());
                row.createCell(2).setCellValue(customer.getEmail());
                row.createCell(3).setCellValue(customer.getNumberPhone());
                row.createCell(4).setCellValue(customer.getAddress());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel: " + e.getMessage());
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

}
