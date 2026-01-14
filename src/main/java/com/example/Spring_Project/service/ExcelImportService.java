package com.example.Spring_Project.service;

import com.example.Spring_Project.entity.EmployeeJobEntity;
import com.example.Spring_Project.handler.ExcelSheetHandler;
import com.example.Spring_Project.model.Dto.ExcelRowData;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ExcelImportService {
    private EntityManager em;
    private static final int BATCH_SIZE = 1000;

    @Transactional
    public void importExcel(MultipartFile file) throws Exception {

        int[] counter = {0};

        Consumer<ExcelRowData> rowConsumer = data -> {
            EmployeeJobEntity employeeJobEntity = new EmployeeJobEntity();
            employeeJobEntity.setName(data.getName());
            employeeJobEntity.setEmail(data.getEmail());
            employeeJobEntity.setPhone(data.getPhone());
            employeeJobEntity.setAddress(data.getAddress());
            employeeJobEntity.setCompany(data.getCompany());
            employeeJobEntity.setText(data.getText());
            employeeJobEntity.setDescription(data.getDescription());
            employeeJobEntity.setJobTitle(data.getJobTitle());

            em.persist(employeeJobEntity);
            counter[0]++;

            if (counter[0] % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        };

        OPCPackage pkg = OPCPackage.open(file.getInputStream());
        XSSFReader reader = new XSSFReader(pkg);
        SharedStringsTable sst = (SharedStringsTable) reader.getSharedStringsTable();

        XMLReader parser = XMLHelper.newXMLReader();
        parser.setContentHandler(new ExcelSheetHandler(sst, rowConsumer));

        try (InputStream sheet = reader.getSheetsData().next()) {
            parser.parse(new InputSource(sheet));
        }

        em.flush();
        em.clear();
        pkg.close();
    }
}
