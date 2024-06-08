import java.io.*;

public class Main {
    private static final String regex = "<style.*/>|<br/>|<icon.*/>|<string.*/>|<entry name=\"|\"/>|\t";
    public static void main(String[] args) {
        try {
            String basePath = "D:\\steam\\steamapps\\common\\Warhammer 40000 Gladius - Relics of War\\Data\\Core\\Languages\\English";
            File[] fileList = new File(basePath).listFiles();
            assert fileList != null;
            for (File fin: fileList) {
                FileInputStream fis = new FileInputStream(fin);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    if (!line.startsWith("	<entry") || line.contains("<string")) {
                        continue;
                    }
                    sb.append(line.replaceAll(regex, "").replace("\" value=\"", ": "))
                            .append("\r\n");
                }
                br.close();
                fis.close();

                String fileName = fin.getName().replace(".xml", "");
                File file = new File("C:\\Users\\wangzhanglu\\Desktop\\warhammer40k\\" + fileName + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fileWriter);
                String data = sb.toString();
                bw.write(data);
                bw.close();
                System.out.println("Done " + fileName + "!");
            }
            System.out.println("All done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}