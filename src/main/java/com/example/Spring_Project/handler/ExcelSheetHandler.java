package com.example.Spring_Project.handler;

import com.example.Spring_Project.model.Dto.ExcelRowData;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExcelSheetHandler extends DefaultHandler {

    private final SharedStringsTable sst;
    private final Consumer<ExcelRowData> rowConsumer;

    private ExcelRowData rowData;
    private String currentColumn;
    private String lastContents;
    private boolean nextIsString;
    private int rowNum = 0;

    public ExcelSheetHandler(SharedStringsTable sst,
                             Consumer<ExcelRowData> rowConsumer) {
        this.sst = sst;
        this.rowConsumer = rowConsumer;
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) {

        if ("row".equals(name)) {
            rowData = new ExcelRowData();
        }

        if ("c".equals(name)) {
            String cellRef = attributes.getValue("r");
            currentColumn = cellRef.replaceAll("\\d", "");
            nextIsString = "s".equals(attributes.getValue("t"));
        }

        lastContents = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        lastContents += new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String name) {

        if (nextIsString && "v".equals(name)) {
            lastContents = sst.getItemAt(Integer.parseInt(lastContents)).getString();
        }

        if ("v".equals(name)) {
            BiConsumer<ExcelRowData, String> setter =
                    COLUMN_MAPPING.get(currentColumn);
            if (setter != null) {
                setter.accept(rowData, lastContents.trim());
            }
        }

        if ("row".equals(name)) {
            if (rowNum++ > 0) { // skip header
                rowConsumer.accept(rowData);
            }
        }
    }

    public static final Map<String, BiConsumer<ExcelRowData, String>> COLUMN_MAPPING =
            Map.of(
                    "A", ExcelRowData::setName,
                    "B", ExcelRowData::setEmail,
                    "C", ExcelRowData::setPhone,
                    "D", ExcelRowData::setAddress,
                    "E", ExcelRowData::setCompany,
                    "F", ExcelRowData::setText,
                    "G", ExcelRowData::setDescription,
                    "H", ExcelRowData::setJobTitle
            );
}
