import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelBaseClass {

    public static XSSFWorkbook excelFile;
    public static XSSFSheet excelSpreadsheet;
    public String filePath;
    public static FileOutputStream fileOutputStream;
    public static File file;

    public ExcelBaseClass(String filePath){

        this.filePath = filePath;
        file = new File(filePath);
        try {
            fileOutputStream = new FileOutputStream(file);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();

        }

        try {
            excelFile.write(fileOutputStream);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}
