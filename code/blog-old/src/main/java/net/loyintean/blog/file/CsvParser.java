package net.loyintean.blog.file;

import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CsvParser {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("E:\\360Downloads\\区号错误.txt"), Charsets.ISO_8859_1);

        Path result = Paths.get("E:\\360Downloads\\区号错误-1.txt");


        StringBuilder sb = new StringBuilder(1024);

        String companyAreaCode;
        String familyAreaCode;
        for (String line : lines) {
            sb.append(line);

            int companyPhoneIndex = sb.indexOf("\"companyPhone\"\"\"\":\"\"\"\"") + 22;
            if (companyPhoneIndex > 21) {
                companyAreaCode = line.substring(companyPhoneIndex, companyPhoneIndex + 4).replace("-", "");
            } else {
                companyAreaCode = StringUtils.EMPTY;
            }


            int familyPhoneIndex = sb.indexOf("\"familyPhone\"\"\"\":\"\"\"\"") + 21;
            if (familyPhoneIndex > 20) {
                familyAreaCode = line.substring(familyPhoneIndex, familyPhoneIndex + 4).replace("-", "");
            } else {
                familyAreaCode = StringUtils.EMPTY;
            }

            Files.write(result, (companyAreaCode + "    " + familyAreaCode + "\r\n").getBytes(),
                    StandardOpenOption.APPEND);

            sb.delete(0, sb.length());
        }
    }
}
