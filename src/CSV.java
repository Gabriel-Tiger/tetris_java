import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {
    String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    CSV() {
        CodeSource codeSource = SerginhoSimulator.class.getProtectionDomain().getCodeSource();
        File jarFile = null;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String jarDir = jarFile.getParentFile().getPath();
        setUrl(jarDir);
    }

    public void writeCSV(String url, String data, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(url + "/" + fileName + ".csv"))) {

            StringBuilder sb = new StringBuilder();

            sb.append(data);

            writer.write(sb.toString());
            writer.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        try {
            BufferedReader test = new BufferedReader(new FileReader(url + "/" + "score" + ".csv"));
        } catch (Exception e) {
            try (PrintWriter writer = new PrintWriter(new File(url + "/score.csv"))) {
                StringBuilder sb = new StringBuilder();
                sb.append("0,0,0,0");
                writer.write(sb.toString());
                writer.close();
                System.out.println("done!");

            } catch (FileNotFoundException exe) {
                System.out.println(exe.getMessage());
            }
        }

        try {
            BufferedReader test = new BufferedReader(new FileReader(url + "/" + "nick" + ".csv"));
        } catch (Exception e) {
            try (PrintWriter writer = new PrintWriter(new File(url + "/nick.csv"))) {
                StringBuilder sb = new StringBuilder();
                sb.append("-,-,-,-");
                writer.write(sb.toString());
                writer.close();
                System.out.println("done!");

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }


    public String readCSVFile(String url, String fileName) {
        StringBuilder data = new StringBuilder();

        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(url + "/" + fileName + ".csv"));
            String linha = buffer.readLine();
            while (linha != null) {

                data.append(linha);

                linha = buffer.readLine();
            }
            System.out.println(data);
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toString();
    }

}
