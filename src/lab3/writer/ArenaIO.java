package lab3.writer;

import lab3.arena.Arena;

import java.io.*;

public class ArenaIO {
    public static void write(Arena arena, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objWriter = new ObjectOutputStream(fileOut);
            objWriter.writeObject(arena);
            objWriter.close();

            System.out.println("Battle was saved to file: record.obj");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Arena read(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Arena arena = (Arena) ois.readObject();
        ois.close();

        System.out.println("Reading battle from file:");

        return arena;
    }
}
