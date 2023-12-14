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
     /* создать копию массива, котором храним названия папок, вызвав .toArray у ArrayList. //
      В качестве аргумента передаём массив нужного типа и нужной длины. Длину извлекаем с помощью .size() 
      В результате получится массив типа File, в который записаны все названия папок на текущем уровне*/   
        
        expand.clear();
      /* освобождаем первоначальный массив для новых названий папок. старые - из предыдущего уровня - остаются в только что созданной копии */
          for (File file : expandCopy) {
      /* освобождаем первоначальный массив для новых названий папок. старые - из предыдущего уровня - остаются в только что созданной копии */    
            System.out.println(depth + " " + file);
            if (file.isDirectory()) {
              expand.addAll(Arrays.asList(file.listFiles()));
            }//if file isDirectory
          } // file expand copy
      } //for depth 0
   } // psvm
} //main class
