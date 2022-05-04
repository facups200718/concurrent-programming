package com.skillsoft.concurrency;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

@AllArgsConstructor
public class PageDownloaderMultiThreading implements Runnable {
    String[] urlsList;

    @Override
    public void run() {
        try {
            for (String urlString: urlsList) {
                //Este codigo es por si queremos interrumpir el thread sin que esté en sleep
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException(Thread.currentThread().getName() + " interrupted");
                }

                URL url = new URL(urlString);
                var filename = urlString.substring(urlString.lastIndexOf("/" )+ 1).trim() + ".html";
                var reader = new BufferedReader(new InputStreamReader(url.openStream()));
                var writer = new BufferedWriter(new FileWriter(filename));

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                }

                System.out.println("Page downloaded to " + filename);

                writer.close();
                //Thread.sleep(4000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] urls = new String[] { "https://www.skillsoft.com/why-skillsoft",
                "https://www.skillsoft.com/leadership-and-business-skills",
                "https://www.skillsoft.com/tech-dev",
                "https://www.skillsoft.com/compliance-leaders",
                "https://www.skillsoft.com/meet-skillsoft-percipio",
                "https://www.skillsoft.com/skillport-pivot",
                "https://www.skillsoft.com/partners",
                "https://www.skillsoft.com/integration-partners",
                "https://www.skillsoft.com/resellers",
                "https://www.skillsoft.com/contact-sales",
                "https://www.skillsoft.com/request-a-demo",
                "https://www.skillsoft.com/skillsoft-support-success-and-services"
        };

        var downloaderOne = new Thread(new PageDownloaderMultiThreading(Arrays.copyOfRange(urls, 0, 3)));
        var downloaderTwo = new Thread(new PageDownloaderMultiThreading(Arrays.copyOfRange(urls, 3, 6)));
        var downloaderThree = new Thread(new PageDownloaderMultiThreading(Arrays.copyOfRange(urls, 6, 9)));
        var downloaderFour = new Thread(new PageDownloaderMultiThreading(Arrays.copyOfRange(urls, 9, urls.length)));

        try {
            long startTime = System.currentTimeMillis();
            downloaderOne.start();
            downloaderTwo.start();
            downloaderThree.start();
            downloaderFour.start();

            Thread.sleep(4000);
            downloaderOne.interrupt();
            downloaderTwo.interrupt();
            downloaderThree.join();
            downloaderFour.join();
            long endTime = System.currentTimeMillis();

            System.out.println("Total time taken: " + (endTime - startTime) / 1000 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}