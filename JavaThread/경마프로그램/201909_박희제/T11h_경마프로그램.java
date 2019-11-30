package kr.or.ddit.basic;
/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 나타내시오.
예)
1번말 ------->--------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T11h_경마프로그램 {
   public static int rank = 1; //말의 순위
   public static List<Horse> hList = new ArrayList<>();
   
   public static void main(String[] args) {
      hList.add(new Horse("01번말"));
      hList.add(new Horse("02번말"));
      hList.add(new Horse("03번말"));
      hList.add(new Horse("04번말"));
      hList.add(new Horse("05번말"));
      hList.add(new Horse("06번말"));
      hList.add(new Horse("07번말"));
      hList.add(new Horse("08번말"));
      hList.add(new Horse("09번말"));
      hList.add(new Horse("10번말"));
      
      HorsePositionDisplay hpd = new HorsePositionDisplay();
      hpd.start();
      
      for(int i=0; i<hList.size(); i++) {
         hList.get(i).start();
      }
      
      try {
         hpd.join();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      
      System.out.println();
      System.out.println("경마 종료!");
      System.out.println();
      
      Collections.sort(hList);
      System.out.println("=====================");
      System.out.println("\t경마 순위");
      System.out.println("=====================");
      for(int i=0; i<hList.size(); i++) {
         System.out.println(hList.get(i).getHorseRank() + "등" + "\t==>\t" + hList.get(i).getHorseName());
      }
   }
}

class Horse extends Thread implements Comparable<Horse>{
   private String horseName;   //말이름
   private int horseRank;      //순위
   
   public Horse(String horseName) {
      super();
      this.horseName = horseName;
   }
   
   public String getHorseName() {
      return horseName;
   }
   public void setHorseName(String horseName) {
      this.horseName = horseName;
   }
   public int getHorseRank() {
      return horseRank;
   }
   public void setHorseRank(int horseRank) {
      this.horseRank = horseRank;
   }
   public int getHorsePosi() {
      return horsePosi;
   }
   public void setHorsePosi(int horsePosi) {
      this.horsePosi = horsePosi;
   }
   private int horsePosi;      //위치

   @Override
   public int compareTo(Horse o) {
      return Integer.compare(horseRank, o.getHorseRank());
   }
   
   @Override
   public void run() {
      for(int i=0; i<50; i++) {
         try {
            Thread.sleep((int) (Math.random()*500));
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         setHorsePosi(i); //각 구간으로 말 위치 이동
      }
      this.horseRank = T11h_경마프로그램.rank;
      T11h_경마프로그램.rank++;
   }
}


class HorsePositionDisplay extends Thread{
   public void clear() {
      for(int i=0; i<10; i++) {
         System.out.println();
      }
   }
   
   @Override
   public void run() {
      String horseCourse = "--------------------------------------------------";
      
      while(true) {
         clear();
         int finishedCnt = 0;
         System.out.println("경마 시작!");
         System.out.println("==========================================================");
         System.out.println();
         for(int i=0; i<T11h_경마프로그램.hList.size(); i++) {
            if(T11h_경마프로그램.hList.get(i).getHorsePosi() != 49) {
               System.out.print(T11h_경마프로그램.hList.get(i).getHorseName() + " : ");
               System.out.print(horseCourse.substring(0, T11h_경마프로그램.hList.get(i).getHorsePosi()) + ">");
               System.out.println(horseCourse.substring(T11h_경마프로그램.hList.get(i).getHorsePosi(), 49));
            }else {
               System.out.print(T11h_경마프로그램.hList.get(i).getHorseName() + " : ");
               System.out.print(horseCourse.substring(0, T11h_경마프로그램.hList.get(i).getHorsePosi()) + "도착");
               System.out.println();
               finishedCnt++;
            }
         }
         
         if(finishedCnt == 10) {   //경주말 모두 도착하면 쓰레드 종료
            return;
         }
         
         try {
            Thread.sleep(500);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }   
   }
   
}