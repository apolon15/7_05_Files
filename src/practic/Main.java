package practic;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        /*
        ЗАДАНИЕ 1. Пользователь с клавиатуры вводит путь к файлу. После чего
содержимое файла отображается на экране.
ЗАДАНИЕ 2. Напиши программу в которой.:
1. Создайте файл “1.txt” в корне проекта и запишите с помощью потока
вывода фразу “Миша ездит на Жигули, а Катя на Мерседес”. (Если пока
сложно, можно после создания файла записать фразу руками)
2. Создайте в папке с проектом папку “Результат”.
3. Создайте в папке Результат файл “2.txt”.
4. Откройте два потока для ввода и вывода данных. Поток для ввода
читает информацию из файла “1.txt” массив байт.
5. Преобразуйте массив байт в тип String, замените “Жигули” на “BMW”,
а “Мерседес” на “Рено”.
6. Запишите результат п.4 в файл “2.txt”.
         */

        String text = "Миша ездит на Жигули, а Катя на Мерседес";//строка
        String text2 = new String(new BigInteger(text.getBytes()).toByteArray());//строка разложена на байты(вроде так)

        String path = System.getProperty("user.dir") + File.separator + "1.txt";//путь для создания файла
        File file1 = new File(path);
        try {
            System.out.println(file1.createNewFile());//создание файла
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(text2.getBytes());//строка байтами записана в файл

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileDir = new File(System.getProperty("user.dir") + File.separator + "Результат");//путь для создания папки
        fileDir.mkdir();//создание папки
        String path2 = System.getProperty("user.dir") + File.separator + "Результат" + File.separator + "2.txt";//путь для создания файла
        File file2 = new File(path2);
        try {
            System.out.println(file2.createNewFile());//создание файла
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(path);//открытие потока на ввод
            FileOutputStream fileOutputStream=new FileOutputStream(path2);//открытие потока на вывод
            byte[] byteMass = new byte[fileInputStream.available()];//создание массива байт размером с длину текста входящего потока
            fileInputStream.read(byteMass);//запись в массив
            String newTxt = new String(byteMass, StandardCharsets.UTF_8);//преобразование массива байт в строку
            System.out.println(Arrays.toString(byteMass));//проверочный вовыд в консоль
            //    System.out.println(newTxt);//проверочный вывод строкой
            String[] massWord = newTxt.split(" ");//создание массива строк
            StringBuilder sb = new StringBuilder();//цикл перебирает слова и заменяя нужные добавляет в StringBuilder
            for (int i = 0; i < massWord.length; i++) {
                if (massWord[i].equals("Жигули,")) {
                    massWord[i] = "BMW,";
                }
                if (massWord[i].equals("Мерседес")) {
                    massWord[i] = "Рено";
                }
                sb.append(massWord[i] + " ");
            }
            System.out.println(sb);//вывод в консоль
            String text3 = new String(new BigInteger(sb.toString().getBytes()).toByteArray());//создание строки из StringBuilder разложенного на байты
            fileOutputStream.write(text3.getBytes());//запись полученной строки после замены слов

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
