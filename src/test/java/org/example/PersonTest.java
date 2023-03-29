package org.example;




import org.junit.jupiter.api.Test;

import java.nio.channels.ScatteringByteChannel;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void checkAddParents() {
        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);
        // add parents to child
        alex.addPartner(maxima);
        amalia.addParents(alex, maxima);
        alexia.addParents(alex, maxima);




        Person output1 = amalia.getFather();
        Person output2 = amalia.getMother();
        Person output3 = maxima.getChildren().get(0);
        Person output4 = maxima.getChildren().get(1);

        // Check if child has both parents
        assertEquals(alex, output1);
        assertEquals(maxima, output2);

        // check if mother has child
        assertEquals(amalia,output3);
        assertEquals(alexia,output4);


    }

    @Test
    void checkAddChild() {
        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);

        maxima.addPartner(alex);
        // add two children to mother
        maxima.addChild(amalia);
        maxima.addChild(alexia);

        Person output1 = maxima.getChildren().get(0);
        Person output2 = maxima.getChildren().get(1);
        Person output3 = amalia.getMother();
        Person output4 = alexia.getMother();

        // check if mother has child
        assertEquals(amalia, output1);
        assertEquals(alexia, output2);

        // check if child has mother
        assertEquals(maxima, output3);
        assertEquals(maxima, output4);
    }

    @Test
    void checkAddPet() {
        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );
        Pet bella = new Pet("Bella", 7, "dog");
        Pet simba = new Pet("Simba", 5, "cat");

        alex.addPet(bella);
        alex.addPet(simba);


        Pet output1 = alex.getPets().get(0);
        Pet output2 = alex.getPets().get(1);
        Person output3 = bella.getOwner();


        assertEquals(bella, output1);
        assertEquals(simba, output2);
        // check owner
        assertEquals(alex, output3);
    }

    @Test
    void checkAddSiblings() {
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);
        // add sibling
        alexia.addSibling(amalia);
        alexia.addSibling(amalia);

        Person output1 = alexia.getSiblings().get(0);
        Person output2 = amalia.getSiblings().get(0);
        int output3 = alexia.getSiblings().size();
        int output4 = amalia.getSiblings().size();

        // check if relation is mutual(recursive method)
        assertEquals(amalia, output1);
        assertEquals(alexia, output2);

        // check if adding stops with both siblings after double try: alexia.addSibling(amalia)
        assertEquals(1, output3);
        assertEquals(1, output4);
    }

    @Test
    void checkGetGrandchildren() {
        Person beatrix = new Person("Beatrix", "Oranje", "female", 80);
        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );

        // add parents to child
        alex.addPartner(maxima);

        beatrix.addChild(alex);
        alex.addChild(amalia);
        alex.addChild(alexia);

        Person output1 = beatrix.getGrandChildren().get(0);
        Person output2 = beatrix.getGrandChildren().get(1);

        assertEquals(amalia, output1);
        assertEquals(alexia, output2);
    }

    // Bonus 1
    @Test
    void checkGetPetsOfGrandchildren() {
        Person beatrix = new Person("Beatrix", "Oranje", "female", 80);
        Person clausSR = new Person("Claus Senior", "Amsberg", "male", 90);
        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);
        Pet bella = new Pet("Bella", 7, "dog");
        Pet simba = new Pet("Simba", 5, "cat");

        beatrix.addChild(alex);
        beatrix.addPartner(clausSR);
        alex.addPartner(maxima);
        alex.addChild(amalia);
        alex.addChild(alexia);
        amalia.addPet(bella);
        alexia.addPet(simba);

        Pet output1 = beatrix.getPetsOfGrandchildren().get(0);
        Pet output2 = beatrix.getPetsOfGrandchildren().get(1);

        assertEquals(bella, output1);
        assertEquals(simba, output2);
    }

    // Bonus 2
    @Test
    void checkForNieces() {

        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );
        Person constantijn = new Person("Constantijn", "Oranje", "male", 52);
        Person laurentien = new Person("Laurentien", "Oranje", "female", 50);
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);
        Person eloise = new Person("Eloise", "Oranje", "female", 20);
        Person claus = new Person("Claus", "Oranje", "male", 18);

        alex.addPartner(maxima);
        constantijn.addPartner(laurentien);

        constantijn.addSibling(alex);
        amalia.addParents(alex, maxima);
        alexia.addParents(alex, maxima);
        eloise.addParents(constantijn, laurentien);
        claus.addParents(constantijn, laurentien);


        Person output1 = eloise.getAllNieces().get(0);
        Person output2 = eloise.getAllNieces().get(1);
        Person output3 = alexia.getAllNieces().get(0);
        Person output4 = claus.getAllNieces().get(0);
        Person output5 = claus.getAllNieces().get(1);

        assertEquals(amalia, output1);
        assertEquals(alexia, output2);
        assertEquals(eloise, output3);
        assertEquals(amalia, output4);
        assertEquals(alexia, output5);

    }

    // Bonus 3
    @Test
    void checkAddPartner() {
        Person alex = new Person("Alex", "Oranje", "male", 55 );
        Person maxima = new Person("Maxima", "Oranje", "female", 51 );
        Person amalia = new Person("Amalia", "Oranje", "female", 18);
        Person alexia = new Person("Alexia", "Oranje", "female", 16);

        alex.addPartner(maxima);
        amalia.addParents(alex);

        alex.addChild(alexia);


        Person output1 = alex.getPartner();
        Person output2 = maxima.getPartner();
        Person output3 = amalia.getMother();
        Person output4 = maxima.getChildren().get(1);

        // check partner
        assertEquals(maxima, output1);
        assertEquals(alex, output2);

        // check if partner has child with addParents method
        assertEquals(maxima, output3);

        // check if partner has child with addChild method
        assertEquals(alexia, output4);

    }

}