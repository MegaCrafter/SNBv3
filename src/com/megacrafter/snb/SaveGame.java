package com.megacrafter.snb;

import com.megacrafter.programapi.SaveFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SaveGame {

    private static SaveFile save1_file = new SaveFile("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Temp/snb_kayit1.sre");
    private static SaveFile save2_file = new SaveFile("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Temp/snb_kayit2.sre");
    private static SaveFile save3_file = new SaveFile("C:/Users/" + System.getProperty("user.name") + "/AppData/Local/Temp/snb_kayit3.sre");

    public static boolean isSaveEmpty(int value) {
        SaveFile file;
        switch (value) {
            case 1:
                file = save1_file;
                break;
            case 2:
                file = save2_file;
                break;
            case 3:
                file = save3_file;
                break;
            default:
                return true;
        }

        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstline = reader.readLine();
            reader.close();

            return firstline == null || firstline.equals(""); // hiçbir şey yazmıyorsa true dönmeli çünkü boştur.

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static void save() {

    }
}