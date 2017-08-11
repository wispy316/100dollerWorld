package com.xingjiu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by xj on 2017/8/11.
 */
public class MyWorld2 {

    private class Person implements Comparable<Person> {
        public int id;
        public int money;

        @Override
        public String toString() {
            return "id=" + id + " of " + money;
        }

        @Override
        public int compareTo(Person o) {
            return this.money - o.money;
        }
    }

    List<Person> people = new ArrayList<>();
    Random r = new Random();

    public static void main(String[] args) {
        MyWorld2 world1 = new MyWorld2();
        world1.init();

        for(int i=0;i<17000;i++) {
            world1.changeMoney();
        }

        Collections.sort(world1.people);

        for (Person p : world1.people) {
            System.out.println(p);
        }

        world1.summary(world1.people);
    }

    private void init() {
        people = new ArrayList<>();
        for(int i=0; i< 100; i++) {
            Person one = new Person();
            one.id = i;
            one.money = 100;

            people.add(one);
        }
    }

    private void changeMoney() {
        for(Person p : people) {
            if(p.money > -11110) {
                r = new Random();
                int giveMoneyToWho = r.nextInt(100);
                while(giveMoneyToWho == p.id) {
//                    System.out.println("conflict " + p.id);
                    giveMoneyToWho = r.nextInt(100); // 保证不会自己给自己
                }

                p.money--;
                people.get(giveMoneyToWho).money++;
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
