import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SimpleAggregator {

    private final int KEY = 0;
    private final int VALUE = 1;

    public Map<String, Object> aggregate(String fileName) {
        final HashSet<String> keySet = new HashSet();
        final List<String> duplicatedKeys = new ArrayList();
        final Map<String, Integer> valueMap = new HashMap();
        final Map<String, Object> result = new HashMap();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Stream<String[]> mapList = bufferedReader.lines().map(line -> line.split("="));
            mapList.forEach(element -> {
                if(keySet.contains(element[KEY])) {duplicatedKeys.add(element[KEY]);} else keySet.add(element[KEY]);
            valueMap.compute(element[VALUE], (k, v)-> (v!=null)? v+1:1);
            });
        } catch (Exception e) {
            System.out.println(" Something goes wrong.... ");
        }

        valueMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).ifPresent(e->result.put("mfv", e.getKey()));
        result.put("dk", duplicatedKeys);

        return result;
    }

}
