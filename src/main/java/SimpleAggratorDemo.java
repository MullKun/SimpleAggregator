import java.util.Map;

public class SimpleAggratorDemo {
    public static void main(String[] args) {
        SimpleAggregator aggregator = new SimpleAggregator();
        Map<String, Object> result = aggregator.aggregate(args[0]);
        System.out.println("Duplicate Keys: ");
        System.out.println(result.get("dk"));
        System.out.println("Most frequent value: ");
        System.out.println(result.get("mfv"));
    }
}
