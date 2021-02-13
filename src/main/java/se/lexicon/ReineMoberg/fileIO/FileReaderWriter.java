package se.lexicon.ReineMoberg.fileIO;

import se.lexicon.ReineMoberg.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileReaderWriter {

    /*
    * Read content from text file using FileReader. One byte
    * (character coding) at a time. Return as a string.
    * */
    public static String readStringFromFile(File source) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(source)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                char letter = (char) i;
                stringBuilder.append(letter);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /*
    * Read lines of text from text file using BufferedReader. One line
    * at a time. Return as list of lines
    * */
    public static List<String> readCollectionFromFile(File source) {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    /*
    * Write lines of text to a file using BufferedWriter. One line at a time.
    * */
    public static void writeCollectionToFile(Collection<String> stringCollection, File target) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(target))) {
            if (!target.exists()) {
                target.createNewFile();
            }
            for (String line : stringCollection) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Copy a file of any content using BufferedInputStream and BufferedOutputStream.
    * */
    public static void copyAFile(File source, File target) {
        try (
                BufferedInputStream bufferedInputStream =
                        new BufferedInputStream(new FileInputStream(source));
                BufferedOutputStream bufferedOutputStream =
                        new BufferedOutputStream(new FileOutputStream(target));
        ) {
            if (!target.exists()) {
                target.createNewFile();
            }
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
                bufferedOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Save list of Car objects by serialize to a file
    * */
    public static void serializeCar(List<Car> carList, File target) {
        try (
                ObjectOutputStream objectOutputStream =
                        new ObjectOutputStream(new FileOutputStream(target))
        ) {
            if (!target.exists()) {
                target.createNewFile();
            }
            objectOutputStream.writeObject(carList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Generic version of above method
    * */
    public static <T> void serializeObject(List<T> t, File target) {

        /*Example of automated file naming:
        String fileName = "src/main/java/se/lexicon/ReineMoberg/files/"
                + t.getClass().getName() + ".ser";*/

        try (
                ObjectOutputStream objectOutputStream =
                        new ObjectOutputStream(new FileOutputStream(target))
        ) {
            if (!target.exists()) {
                target.createNewFile();
            }
            objectOutputStream.writeObject(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Read Car objects from a file to a list using deserialize from a file
    *  */
    public static List<Car> deserializeCar(File source) {
        List<Car> result = null;
        try (
                ObjectInputStream objectInputStream =
                        new ObjectInputStream(new FileInputStream(source))
        ) {
            result = (List<Car>) objectInputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * Generic version of above method
     * */
    public static <T> List<T> deserializeObject(File source){
        List<T> result = null;
        try (
                ObjectInputStream objectInputStream =
                        new ObjectInputStream(new FileInputStream(source))
        ) {
            result = (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
