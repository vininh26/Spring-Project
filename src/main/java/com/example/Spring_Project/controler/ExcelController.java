package com.example.Spring_Project.controler;

import com.example.Spring_Project.service.ExcelImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelImportService excelImportService;

    @PostMapping("/import")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> importExcel(
            @RequestParam("file") MultipartFile file) throws Exception {

        excelImportService.importExcel(file);
        return ResponseEntity.ok("Import success");
    }
}
