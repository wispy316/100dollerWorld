package com.xingjiu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by xj on 2017/8/11.
 */
public class MyWorld5 {

    private class Person implements Comparable<Person> {
        public int id;
        public int money;

        public boolean hasBreak;
        public int hasBreakAge;
        public List<Integer> hasBreakAgeList = new ArrayList<>();

        public boolean isRichChild;

        @Override
        public String toString() {
            return "id=" + id + " of " + money
                    + (hasBreak ? " [break]@"+hasBreakAge+" " + hasBreakAgeList.size() : "")
                    + (isRichChild ? " rich " : "");
        }

        @Override
        public int compareTo(Person o) {
            return this.money - o.money;
        }
    }

    List<Person> people = new ArrayList<>();
    Random r = new Random();

    public static void main(String[] args) {
        MyWorld5 world1 = new MyWorld5();
        world1.init();

        for(int i=0;i<17000;i++) {
            world1.changeMoney(i);
        }

        Collections.sort(world1.people);

        for (int i=0;i<world1.people.size();i++) {
            if(i != 0 && i % 10 == 0) {
                System.out.println("-------" + i + "--------");
            }
            System.out.println(world1.people.get(i));
        }

        world1.summary(world1.people);
    }

    private void init() {
        people = new ArrayList<>();
        for(int i=0; i< 90; i++) {
            Person one = new Person();
            one.id = i;
            one.money = 100;

            people.add(one);
        }


        for(int i=90; i< 100; i++) {
            Person one = new Person();
            one.id = i;
            one.money = 500;
            one.isRichChild = true;

            people.add(one);
        }
    }

    private void changeMoney(int age) {
        for(Person p : people) {
            if(p.money > -999) {
                r = new Random();
                int giveMoneyToWho = r.nextInt(100);
                while(giveMoneyToWho == p.id) {
//                    System.out.println("conflict " + p.id);
                    giveMoneyToWho = r.nextInt(100); // 保证不会自己给自己
                }

                p.money--;
                people.get(giveMoneyToWho).money++;
                if(p.money <= 0) {
                    p.hasBreak = true;
                    if(p.hasBreakAge == 0) {
                        p.hasBreakAge = age;
                    }
                    p.hasBreakAgeList.add(age);
                }
            }
        }
    }

    private void summary(List<Person> people) {
        int sumMoney = 0;
        for(Person p : people) {
            sumMoney += p.money;
        }
        System.out.println("avg is " + sumMoney/people.size());
        System.out.println("middle is " + people.get(50).money);
    }

}
