/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonov.elparser.impl.domain;

import com.antonov.elparser.pojo.User;
import com.antonov.elparser.pojo.UserInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Antonov
 */
public class ExcelWorker {

    private final String filePath;
    private final Logger logger = LoggerFactory.getLogger(getClass().getCanonicalName());

    private static final int HEADER_HEIGHT = 3;
    private static final int COLUMN_UNIVERSITY = 0;
    private static final int ROW_UNIVERSITY = 0;

    private static final int COLUMN_FIO = 1;
    private static final int COLUMN_AMOUNT_LETTERS = 2;
    private static final int COLUMN_HIRSH = 3;
    private static final int COLUMN_IMPACT_PUBLISH = 4;

    private static final int DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS = 0;

    public ExcelWorker(String filePath) {
        this.filePath = filePath;
    }

    public String getUniversity() throws Exception {

        String result = null;

        try (FileInputStream is = new FileInputStream(filePath)) {

            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(ROW_UNIVERSITY);
            Cell cell = row.getCell(COLUMN_UNIVERSITY);
            result = cell.getStringCellValue().split("-")[1].trim();
        } catch (Throwable ex) {
            String message = "При попытке распознавания университета произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
        return result;
    }

    public List<User> getUsers() throws Exception {
        List<User> result = new ArrayList<>();
        try (FileInputStream is = new FileInputStream(filePath)) {

            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);

            int amountRows = sheet.getPhysicalNumberOfRows();

            for (int i = HEADER_HEIGHT; i < amountRows; i++) {
                User user = new User();

                Row row = sheet.getRow(i);
                Cell cell = row.getCell(COLUMN_FIO);
                String fio = cell.getStringCellValue().trim();
                user.setFIO(fio);
                user.setRow(i);

                result.add(user);
            }
        } catch (Throwable ex) {
            String message = "При попытке распознавания авторов произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
        return result;
    }

    public void write(List<User> listUser) throws Exception {

        try (FileInputStream is = new FileInputStream(filePath)) {

            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);

            for (User user : listUser) {

                int row = user.getRow();
                UserInfo info = user.getInfo();

                if (info != null) {
                    Long amountLetters = info.getAMOUNT_LETTERS();
                    String hirsh = info.getHIRSH();
                    Double impactPublish = info.getIMPACT_PUBLISH();

                    if (amountLetters != null) {
                        sheet.getRow(row).getCell(COLUMN_AMOUNT_LETTERS).setCellValue(amountLetters);
                    } else {
                        sheet.getRow(row).getCell(COLUMN_AMOUNT_LETTERS).setCellValue(DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS);
                    }

                    if (hirsh != null) {
                        sheet.getRow(row).getCell(COLUMN_HIRSH).setCellValue(hirsh);
                    } else {
                        sheet.getRow(row).getCell(COLUMN_HIRSH).setCellValue(DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS);
                    }

                    if (impactPublish != null) {
                        sheet.getRow(row).getCell(COLUMN_IMPACT_PUBLISH).setCellValue(impactPublish);
                    } else {
                        sheet.getRow(row).getCell(COLUMN_IMPACT_PUBLISH).setCellValue(DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS);
                    }
                } else {
                    sheet.getRow(row).getCell(COLUMN_AMOUNT_LETTERS).setCellValue(DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS);
                    sheet.getRow(row).getCell(COLUMN_HIRSH).setCellValue(DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS);
                    sheet.getRow(row).getCell(COLUMN_IMPACT_PUBLISH).setCellValue(DEFAULT_VALUE_FOR_NULL_INFO_PARAMETERS);
                }
            }

            try (FileOutputStream os = new FileOutputStream(filePath)) {
                wb.write(os);
            }
        } catch (Throwable ex) {
            String message = "При попытке записи данных в файл произошла ошибка";
            logger.error(message, ex);
            throw new Exception(message, ex);
        }
    }

}
