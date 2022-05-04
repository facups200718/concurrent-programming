package com.skillsoft.concurrency;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

@AllArgsConstructor
public class PageDownloader implements Runnable {
    String[] urlsList;

    @Override
    public void run() {
        try {
            for (String urlString: urlsList) {
                var url = new URL(urlString);
                var filename = urlString.substring(urlString.lastIndexOf("/" )+ 1).trim() + ".html";
                var reader = new BufferedReader(new InputStreamReader(url.openStream()));
                var writer = new BufferedWriter(new FileWriter(filename));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }

                System.out.println("Page downloaded to " + filename);

                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var urls = new String[] { "https://www.skillsoft.com/home",
                "https://www.skillsoft.com/partners",
                "https://www.skillsoft.com/about",
                "https://www.skillsoft.com/industries",
                "https://www.skillsoft.com/about/awards",
                "https://www.skillsoft.com/leadership-team",
                "https://www.skillsoft.com/elearning-events",
                "https://www.skillsoft.com/about/culture",
                "https://www.skillsoft.com/about/global-career-opportunities",
                "https://www.skillsoft.com/skillsoft-effect",
                "https://www.skillsoft.com/industries/financial-services",
                "https://www.skillsoft.com/industries/manufacturing"
        };

        var downloaderOne = new Thread(new PageDownloader(
                Arrays.copyOfRange(urls, 0, urls.length)
        ));

        try {
            long startTime = System.currentTimeMillis();
            downloaderOne.start();
            downloaderOne.join();
            long endTime = System.currentTimeMillis();

            System.out.println("Total time taken: " + (endTime - startTime) / 1000 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
