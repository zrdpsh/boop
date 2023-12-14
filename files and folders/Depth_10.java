import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Depth_10
{
   public static void main(String[] args)
   {
      File root = new File(".");
     /* объявить объект файла с именем "." */
     
      ArrayList<File> expand = new ArrayList<File>();
     /* объявить ArrayList, который называется expand - в него можно записать произвольное количество File" */
     
      expand.add(root);
     /* добавить в ArrayList File, созданный в первой строчке */
     
      for(int depth = 0; depth < 10; depth++) {
     /* запустить цикл на 10 повторений - нужная глубина вложенности */
        
        File[] expandCopy = expand.toArray(new File[expand.size()]);
     /* переписываем данные из списка expand в массив expandCopy//
      В качестве аргумента нужно передать инциализированный массив нужной длины. Длину из expand извлекаем с помощью .size()
      В результате получится массив ссылок на объекты типа File, в который записаны все названия папок на текущем уровне*/
        
        expand.clear();
      /* освобождаем первоначальный список для новых названий папок. старые - из предыдущего уровня - остаются в только что созданной копии */
          for (File file : expandCopy) {
      /* открываем цикл, в котором перебираем все */
            System.out.println(depth + " " + file);
            if (file.isDirectory()) {
      /* проверить, является ли файл из полученного списка папкой ... */
              expand.addAll(Arrays.asList(file.listFiles()));
      /* .. если является - получаем файлы, лежащие внутри file c помощью .listFiles() в виде массива с объектами File ->
      *   -> Arrays.asList превращает наш массив файлов в список
      *   добавляем к уже имеющимся названиям файлов в expand*/
            }//if file isDirectory
      /* на самой первой итерации папка будет всего одна - "." */
          } // file expand copy
      } //for depth 0
   } // psvm
} //main class
