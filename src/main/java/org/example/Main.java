//��������� ������������� 15 000 ������� � ������� Excel ��� ������� �������� ������� �� �������.
// ������ ������ ������ ���������: ��� ������. ���������� ����� � ��������� ����� �� 1 �� 150 000.
//������ ������ ���� ������������� �� ���������� ����� � ������� ��������: ������ � ������� �����������
// ����� ������ ���������� ������ ������. ��������������� ���� ������ ���� � ������� Excel (.xlsx).
//���� ����������:
// 1. Java: ��� �������� ������ ��������� ������.
// 2. Apache POI: ��� �������� � ������ ������ � Excel-����. ��� ���������� ���������� ��� ������ � Excel � Java.
// 3. Collections API: ��� ���������� ������.
// 4. Java Faker ��� Random: ��� ��������� ��������� ��� � �����.
// 5. Maven/Gradle: ��� ���������� ������������� � ������ �������.

package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

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

        wb.write(fos);
        fos.close();   // zakrivaem nash potok
        System.out.println("Fail sozdan.");
    }
}
