package com.link3.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static Object[][] getCSVData(

            String filePath

    ) {

        List<Object[]> data =

                new ArrayList<>();

        try {

            BufferedReader br =

                    new BufferedReader(
                            new FileReader(filePath)
                    );

            String line;

            // Skip CSV header
            br.readLine();

            while((line = br.readLine()) != null) {

                String[] values =
                        line.split(",");

                data.add(values);
            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data.toArray(
                new Object[0][]
        );
    }
}