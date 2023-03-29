package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    private String name;
    private String middleName;
    private String lastName;
    private String sex;
    private int age;
    private Person mother;
    private Person father;
    private List<Person> siblings;
    private List<Person> children;
    private List<Pet> pets;
    private Person partner;

    public Person(String name, String lastName, String sex, int age) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

    public Person(String name, String middleName, String lastName, String sex, int age) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

    public void addParents(Person p1, Person p2) {
        // Set mother and father only if both don't exist
        if(this.getFather()== null && this.getMother() == null) {
            if (p1.getSex().equals("female")) {
                this.setMother(p1);
                this.setFather(p2);
            } else {
                this.setMother(p2);
                this.setFather(p1);
            }
            p1.addChild(this);
            p2.addChild(this);
        }
    }

    public void addChild(Person person) {

        // if children is empty, make person to list children
        if (this.getChildren() == null) {
            this.setChildren(Arrays.asList(person));
        } else {
            // fill new list with children old list and add new child
            if (!this.getChildren().contains(person)) {
                List<Person> updateChildren = new ArrayList<>();
                for (Person child : this.getChildren()) {
                    updateChildren.add(child);
                }
                updateChildren.add(person);
                this.setChildren(updateChildren);
                this.getPartner().setChildren(updateChildren); // Bonus 3
            }
        }
        // if child has no father or mother, set father or mother
        if(person.getFather() == null && this.sex.equals("male")) {
            person.setFather(this);
            person.setMother(this.getPartner()); // Bonus 3
        } else if(person.getMother() == null && this.sex.equals("female")) {
            person.setMother(this);
            person.setFather((this.getPartner())); // Bonus 3
        }
    }

    // Similar to add child: make new list, add old pets and add new pet
    public void addPet(Pet pet) {
        if (this.getPets() == null) {
            this.setPets(Arrays.asList(pet));
        } else {
            // fill new list with pets old list and add new pet
            if (!this.getPets().contains(pet)) {
                List<Pet> updatePets = new ArrayList<>();
                for (Pet oldPet : this.getPets()) {
                    updatePets.add(oldPet);
                }
                updatePets.add(pet);
                this.setPets(updatePets);
            }
        }
        // And set a new Owner to pet
        pet.setOwner(this);
    }

    public String getName() {
        return name;
    }

    // Recursive method, mutual relation
    public void addSibling(Person person) {
        List<Person> updateSiblings = new ArrayList<>();
        if(this.getSiblings()!= null) {
            if(this!=person && !this.getSiblings().contains(person)) {
                for(Person sibling: this.getSiblings()) {
                    updateSiblings.add(sibling);
                }
                this.setSiblings(updateSiblings);
                person.addSibling(this);
            }
        } else {
            this.setSiblings(Arrays.asList(person));
            // Calls itself
            person.addSibling(this);
        }
    }

    public List<Person> getGrandChildren() {
        List<Person> grandChildren = new ArrayList<>();
        for (Person child: this.getChildren()) {
            for (Person grandChild: child.getChildren()) {
                grandChildren.add(grandChild);
            }
        }
        return grandChildren;
    }

    // Bonus 1
    public List<Pet> getPetsOfGrandchildren() {
        List<Person> grandchildren = this.getGrandChildren();
        List<Pet> allPets = new ArrayList<>();
        for (Person grandchild: grandchildren) {
            allPets.addAll(grandchild.getPets());
        }
        return allPets;
    }

    // Bonus 2
    public List<Person> getAllNieces() {
        List<Person> auntsAndUncles = new ArrayList<>();
        List<Person>niecesAndNewphes = new ArrayList<>();
        List<Person> nieces = new ArrayList<>();

        // get parents
        if(this.getFather()!= null && this.getMother()!= null) {
            List<Person> parents = Arrays.asList(this.getMother(), this.getFather());

            // get siblings parents
            for (Person parent: parents) {
                if(parent.getSiblings()!=null) {
                    auntsAndUncles.addAll(parent.getSiblings());
                } else{
                    continue;
                }
                // get children of siblings
                for (Person auntUncle: auntsAndUncles) {
                    if(auntUncle.getChildren()!= null) {
                        niecesAndNewphes.addAll(auntUncle.getChildren());
                    } else {
                        continue;
                    }
                    // Test for female
                    for (Person person: niecesAndNewphes) {
                        if(person.getSex().equals("female")) {
                            nieces.add(person);
                        }
                    }
                }
            }
        }
        return nieces;
    }


    // Bonus 3
    public void addPartner(Person person) {
        this.setPartner(person);
        person.setPartner(this);
    }

    public void addParents(Person person) {
        this.addParents(person, person.getPartner());
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public List<Person> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<Person> siblings) {
        this.siblings = siblings;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
