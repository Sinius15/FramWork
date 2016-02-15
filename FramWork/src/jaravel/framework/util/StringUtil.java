package jaravel.framework.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sijmen on 13-1-2016.
 */
public class StringUtil {

    public static String normalize(String input){
        if(input == null)
            return null;
        input = input.trim();
        if(input.endsWith("/"))
            input = input.substring(0, input.length()-1);
        if(input.startsWith("/"))
            input = input.substring(1);
        input = input.toLowerCase();
        return input;
    }

    public static String readFile(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(encoded);
    }

}
