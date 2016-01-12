package it.sijmen.webframework.framwork.util;

import java.util.HashMap;

/**
 * Created by Sinius on 12-1-2016.
 */
public class RegExE {

    public static final char LEFT_BRACKE = '{';
    public static final char RIGHT_BRACKE = '}';

    private static HashMap<String, HashMap<String, String>> cacheMap = new HashMap<>();

    /**
     * Accepts:
     *   "abc/{1}/def/{2}/ghi", "abc//def//ghi"
     *   "abc/{1}/def/{2}/ghi", "abc/1456/def/1456/ghi"
     * @param template the tamplate
     * @param value the value to match
     * @return a hashmap with the keys from the template and the value from the value
     */
    public static HashMap<String, String> decodeNamedGroup(String template, String value){
        String cacheKey = template + "~|!" + value;
        if(cacheMap.containsKey(cacheKey))
            return cacheMap.get(cacheKey);
        System.out.println("t: " + template + "\t v: " + value);

        HashMap<String, String> out = new HashMap<>();

        int valueCounter = 0;
        int templateCounter = 0;

        while(templateCounter < template.length() && valueCounter < value.length()){
            char tmpCur = template.charAt(templateCounter);
            char varCur = value.charAt(valueCounter);

            if(tmpCur == varCur){
                templateCounter++;
                valueCounter++;
            }else if(tmpCur == LEFT_BRACKE) {
                String partKey = "";
                for (templateCounter += 1; template.charAt(templateCounter) != RIGHT_BRACKE; templateCounter++)
                    partKey += template.charAt(templateCounter);
                char next;
                templateCounter+=1;
                if(templateCounter >= template.length())
                    next = '~';
                else
                    next = template.charAt(templateCounter);
                String partValue = "";
                while (valueCounter < value.length() && value.charAt(valueCounter) != next) {
                    partValue += value.charAt(valueCounter);
                    valueCounter++;
                }
                out.put(partKey, partValue);
            }else{
                cacheMap.put(cacheKey, null);
                return null;
            }
        }
        cacheMap.put(cacheKey, out);
        if(templateCounter < template.length() || valueCounter < value.length())
            return null;
        return out;
    }

    public static boolean matches(String template, String value){
        return decodeNamedGroup(template, value) != null;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = decodeNamedGroup("abc/{1}/def/{2}/", "abc/a/def/e/");
        if(map == null) {
            System.out.println("map is null");
            return;
        }

        for(String key : map.keySet()){
            System.out.println(key + "\t:\t" + map.get(key));
        }
    }

}
