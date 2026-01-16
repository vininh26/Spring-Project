package com.example.Spring_Project.controler;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ImportDataService importDataService;

    @GetMapping("/import-data")
    public String importData() throws Exception {
        int countCompany = importDataService.importDataCompanyFromJsonFile();
        return "Total Company Import: " + countCompany;
    }
}
