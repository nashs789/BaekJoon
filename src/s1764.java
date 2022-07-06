import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class s1764 {

    public static final Pattern namePattern = Pattern.compile("^[a-z]*$");

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] twoNumber = br.readLine().split(" ");
            isValidNumber(twoNumber);

            int neverHeardCnt = Integer.parseInt(twoNumber[0]);
            int neverSeenCnt = Integer.parseInt(twoNumber[1]);
            Map<String, String> neverHeardMap = getNames(neverHeardCnt, br);
            Map<String, String> neverSeenMap = getNames(neverSeenCnt, br);

            List<String> list = getDupName(neverHeardMap, neverSeenMap);

            System.out.println(list.size());
            list.stream().forEach(System.out::println);

        } catch(IOException ioe){
            System.out.println("잘못된 입력 입니다. " + ioe.getMessage());
        } catch(Exception e){
            System.out.println("예외가 발생 했습니다. " + e.getMessage());
        }
    }

    private static List<String> getDupName(Map<String, String> neverHeardMap, Map<String, String> neverSeenMap) {
        return neverHeardMap.keySet()
                            .stream()
                            .filter(ele -> neverSeenMap.containsKey(ele))
                            .sorted()
                            .collect(Collectors.toList());
    }

    private static Map<String, String> getNames(int cnt, BufferedReader br) throws IOException{
        Map<String, String> resultMap = new HashMap<>();

        for(int idx = 0; idx < cnt; idx++){
            String name = br.readLine();
            isValidName(name);
            resultMap.put(name, name);
        }

        return resultMap;
    }

    private static void isValidName(String name) throws IOException{
        if(!Pattern.matches(namePattern.pattern(), name) || name.length() > 20){
            throw new IOException("Invalid Name");
        }
    }

    private static void isValidNumber(String[] twoNumber) throws IOException {
        if(twoNumber.length > 2){
            throw new IOException("Too Many Numbers");
        }
    }
}
