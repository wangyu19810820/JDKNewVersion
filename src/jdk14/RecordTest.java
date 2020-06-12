package jdk14;

import java.util.HashSet;

public class RecordTest {

    public static void main(String[] args) {
        var item1 = new Item("wang", 1243);
        System.out.println(item1.desc());
        System.out.println(item1.partNum());
        System.out.println(item1);

        var item2 = new Item("wang", 1243);
        System.out.println(item1.equals(item2));

        var hashSet = new HashSet<Item>();
        hashSet.add(item1);
        hashSet.add(item2);
        System.out.println(hashSet.size());

        System.out.println(Item.getId());
        System.out.println(Item.getId());

        System.out.println(new Item("yu"));
        System.out.println(item1.getUpperCaseDesc());
    }
}
