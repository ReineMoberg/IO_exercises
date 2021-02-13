package se.lexicon.ReineMoberg;

import se.lexicon.ReineMoberg.fileIO.FileReaderWriter;
import se.lexicon.ReineMoberg.fileIO.JsonIO;
import se.lexicon.ReineMoberg.model.Car;
import se.lexicon.ReineMoberg.model.Car2;
import se.lexicon.ReineMoberg.model.Owner;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
    ex6();
    }

    public static void ex1(){
        File sourceFile = new File("src/main/java/se/lexicon/ReineMoberg/files/RandomText.txt");
        String result = FileReaderWriter.readStringFromFile(sourceFile);
        System.out.println(result);
    }

    public static void ex2(){
        File sourceFile = new File("src/main/java/se/lexicon/ReineMoberg/files/LinesOfNames.txt");
        List<String> result = FileReaderWriter.readCollectionFromFile(sourceFile);
        result.forEach(System.out::println);
    }

    public static void ex3() {
        File targetFile = new File("src/main/java/se/lexicon/ReineMoberg/files/StringObjects.txt");
        Collection<String> objects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            objects.add("This is line " + (i + 1) + ".");
        }
        FileReaderWriter.writeCollectionToFile(objects, targetFile);
    }

    public static void ex4() {
        File sourceFile = new File("src/main/java/se/lexicon/ReineMoberg/files/LinesOfNames.txt");
        File targetFile = new File("src/main/java/se/lexicon/ReineMoberg/files/LinesOfNamesCopy.txt");
        FileReaderWriter.copyAFile(sourceFile, targetFile);
    }

    public static void ex5() {
        File carFile = new File("src/main/java/se/lexicon/ReineMoberg/files/Car.ser");
        List<Car> carList = Arrays.asList(
                new Car("GTR 564", "Volvo", "C30", LocalDate.parse("2010-06-10")),
                new Car("DEW 397", "Ford", "Mondeo", LocalDate.parse("2015-08-30")),
                new Car("ABC 123", "Saab", "900", LocalDate.parse("2005-12-01"))
        );
        FileReaderWriter.serializeCar(carList, carFile);

        List<Car> deSerializedCarList = FileReaderWriter.deserializeCar(carFile);
        deSerializedCarList.forEach(System.out::println);
    }

    public static void ex6() {
        JsonIO jsonIO = new JsonIO();
        File car2File = new File("src/main/java/se/lexicon/ReineMoberg/files/Car2s.json");
        File ownerFile = new File("src/main/java/se/lexicon/ReineMoberg/files/Owners.json");
        List<Owner> ownerList = new ArrayList<>();
        List<Car2> car2List = new ArrayList<>();
        ownerList.add(new Owner("Reine Moberg", LocalDate.parse("1969-11-26")));
        ownerList.add(new Owner("Anna Andersson", LocalDate.parse("1973-03-07")));
        ownerList.add(new Owner("Per Henriksson", LocalDate.parse("1982-01-23")));
        car2List.add(new Car2("GTR 564", "Volvo", "C30",
                ownerList.get(0), LocalDate.parse("2010-06-10")));
        car2List.add(new Car2("DEW 397", "Ford", "Mondeo",
                ownerList.get(1), LocalDate.parse("2015-08-30")));
        car2List.add(new Car2("ABC 123", "Saab", "900",
                ownerList.get(2), LocalDate.parse("2005-12-01")));

        //Serialize to JSON with generic method
        jsonIO.serializeListToJson(ownerList, ownerFile);
        jsonIO.serializeListToJson(car2List, car2File);

        //Deserialize from JSON
        List<Owner> ownersResult = jsonIO.deserializeJsonToOwnerList(ownerFile);
        List<Car2> car2sResult = jsonIO.deserializeJsonToCar2List(car2File);
        ownersResult.forEach(System.out::println);
        car2sResult.forEach(System.out::println);

        //Deserialize from JSON with generic method
        List<Owner> ownersResultGenericMethod = jsonIO.deserializeJsonToList(ownerFile, Owner.class);
        List<Car2> car2sResultGenericMethod = jsonIO.deserializeJsonToList(car2File, Car2.class);
        ownersResultGenericMethod.forEach(System.out::println);
        car2sResultGenericMethod.forEach(System.out::println);
    }

}


