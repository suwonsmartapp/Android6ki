package com.example.excel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXCEL_GET = 1000;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.result);
    }

    public void getExcelFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.ms-excel");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_EXCEL_GET);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EXCEL_GET && resultCode == RESULT_OK) {
            try {
                showExcel(data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showExcel(Uri data) throws IOException {
        String path = data.getLastPathSegment().replace("raw:", "");

        FileInputStream inputStream = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(inputStream);

        Cell cell = workbook.getSheetAt(0).getRow(0).getCell(1);

        Toast.makeText(this, cell.getStringCellValue(), Toast.LENGTH_SHORT).show();

        workbook.close();
        inputStream.close();
    }
}
