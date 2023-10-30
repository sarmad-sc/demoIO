package com.example.demoIO.controller;

import com.example.demoIO.DemoIoApplication;
import com.example.demoIO.entity.Entity;
import com.example.demoIO.service.Service;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("saveAll")
    public String saveAll(@RequestParam("multipartFile") MultipartFile multipartFile) {

        try (InputStream file = new BufferedInputStream(multipartFile.getInputStream());
             ReadableWorkbook wb = new ReadableWorkbook(file)) {

            StopWatch watch = new StopWatch();
            watch.start();
            List<Entity> list = new ArrayList<>();

            wb.getSheets().forEach(sheet -> {

                try (Stream<Row> rows = sheet.openStream()) {
                    rows.skip(1).forEach(r -> {
                        Entity entity = DemoIoApplication.getEntity();
                        for (Cell cell : r) {
                            int columnIndex = cell.getColumnIndex();
                            if (columnIndex == 0) entity.setSR_NO(cell.asString());
                            else if (columnIndex == 1) entity.setNTN(cell.asString());
                            else if (columnIndex == 2) entity.setNAME(cell.asString());
                            else if (columnIndex == 3) entity.setBUSINESS_NAME(cell.asString());
                        }
                        list.add(entity);
                        if (list.size() == 10000) {
                            service.saveAll(list);
                            list.clear();
                        }
                    });
                } catch (Exception e) {
                    System.out.println("first catch block");
                    e.printStackTrace();
                }
            });
//---------save all method call -----------
            service.saveAll(list);
            list.clear();
            System.out.println("______saved All______");
//-----------------------------------------
            watch.stop();
            System.out.println("Processing time :::>>> " + watch.getTotalTimeSeconds());
        } catch (IOException e) {
            System.out.println("second catch block");
            throw new RuntimeException(e);
        }
        return "this is save All method of demoIO called successfully..........";
    }

    @GetMapping("memory-status")
    public HashMap<String, Long> getMemoryStatistics() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("HeapSize", Runtime.getRuntime().totalMemory());
        map.put("HeapMaxSize", Runtime.getRuntime().maxMemory());
        map.put("HeapFreeSize", Runtime.getRuntime().freeMemory());
        return map;
    }
}
