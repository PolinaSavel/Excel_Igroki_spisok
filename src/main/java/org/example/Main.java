//Требуется сгенерировать 15 000 записей в формате Excel для таблицы рейтинга игроков на сервере.
// Каждая запись должна содержать: Имя игрока. Количество очков — случайное число от 1 до 150 000.
//Записи должны быть отсортированы по количеству очков в порядке убывания: игроки с большим количеством
// очков должны находиться вверху списка. Сгенерированный файл должен быть в формате Excel (.xlsx).
//Стек технологий:
// 1. Java: Для основной логики генерации данных.
// 2. Apache POI: Для создания и записи данных в Excel-файл. Это популярная библиотека для работы с Excel в Java.
// 3. Collections API: Для сортировки данных.
// 4. Java Faker или Random: Для генерации случайных имён и чисел.
// 5. Maven/Gradle: Для управления зависимостями и сборки проекта.

package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int numberOfLines = (15000);   // kolichestvo strok = 15.000

        FileOutputStream fos = new FileOutputStream("C:/Users/Polina/Desktop/igroki.xls");
        // sozdali potok dlya sozdaniya faila, kuda budet pisatsya nasha kniga
        Workbook wb = new HSSFWorkbook();            // otkrili knigu

        Sheet sheet = wb.createSheet("mySheet");    //sozdaem stranicu v excel
        sheet.setColumnWidth(0, 256 * 30);       // shirina pervogo stolbca
        sheet.setColumnWidth(1, 256 * 10);       // shirina vtorogo stolbca

        Random random = new Random();

        for (int i = 0; i < numberOfLines; i++) {
            Row row = sheet.createRow(i); // sozdayem pervuyu stroku
            row.createCell(0).setCellValue(RandomNameGenerator.getRandomName());

            for (int j = 0; j < numberOfLines; j++) {
                int randomNumber = random.nextInt(150000); // Generiruem sluchaynie chisla do 150 000
                row.createCell(1).setCellValue(randomNumber); // Zapolnyaem 2oy stolec sluchaynimi chislami
            }
        }

        List<Row> rows = new ArrayList<>();

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {  // sohranit vse stroki
            Row row = sheet.getRow(i);
            if (row != null) {
                rows.add(row);
            }
        }

        // sortirovka strok po chislam vo vtorom stolbce (i:1)
        Collections.sort(rows, new Comparator<Row>() {
            @Override
            public int compare(Row r1, Row r2) {
                Cell cell1 = r1.getCell(1);
                Cell cell2 = r2.getCell(1);
                if (cell1 == null || cell2 == null) return 0; // na sluchai pustih yacheek

                // Sravnivaem chisla
                double val1 = cell1.getCellType() == CellType.NUMERIC ? cell1.getNumericCellValue() : Double.MAX_VALUE;
                double val2 = cell2.getCellType() == CellType.NUMERIC ? cell2.getNumericCellValue() : Double.MAX_VALUE;
                return Double.compare(val1, val2);
            }
        });

        Sheet sortedSheet = wb.createSheet("Sorted Data"); //noviy list dlya zapisi otsortirovannih dannih
        sortedSheet.setColumnWidth(0, 256 * 30);       // shirina pervogo stolbca
        sortedSheet.setColumnWidth(1, 256 * 10);


        for (int i = 0; i < rows.size(); i++) {                    // Zapisivaem otsortirovanniy spisok v noviy list
            Row newRow = sortedSheet.createRow(i);
            Row oldRow = rows.get(i);
            for (int j = 0; j < oldRow.getLastCellNum(); j++) {
                Cell oldCell = oldRow.getCell(j);
                Cell newCell = newRow.createCell(j);
                if (oldCell != null) {
                    switch (oldCell.getCellType()) {
                        case NUMERIC:
                            newCell.setCellValue(oldCell.getNumericCellValue());
                            break;
                        case STRING:
                            newCell.setCellValue(oldCell.getStringCellValue());
                            break;

                        default:
                            newCell.setCellValue(oldCell.toString());
                    }
                }
            }
        }


        wb.write(fos);   //zapisali
        wb.close();
        fos.close();   // zakrivaem nash potok
        System.out.println("Fail sozdan.");
    }
}
