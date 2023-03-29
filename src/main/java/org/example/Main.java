package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person constantijn = new Person("Constantijn", "Oranje", "male", 52);
        Person laurentien = new Person("Laurentien", "Oranje", "female", 50);
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);
        Person eloise = new Person("Eloise", "Oranje", "female", 20);
        Person claus = new Person("Claus", "Oranje", "male", 18);

        constantijn.addSibling(alex);
        alex.addChild(amalia);
        alex.addChild(alexia);
        constantijn.addChild(eloise);
        laurentien.addChild(eloise);
        constantijn.addChild(claus);

        Person output1 = eloise.getAllNieces().get(0);
        Person output2 = eloise.getAllNieces().get(1);








    }
}