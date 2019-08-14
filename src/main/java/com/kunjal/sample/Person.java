package com.kunjal.sample;

import org.json.JSONObject;
public class Person {

    private String id;
    private String name;
    private String address;

    public Person(String id, String name, String address) {
      this.id = id;
      this.name = name;
      this.address = address;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAddress() {
      return address;
    }

    void setAddress(String address) {
      this.address = address;
    }

    @Override public String toString() {
      return "com.kunjal.sample.Person{" +
          "id='" + id + '\'' +
          ", name='" + name + '\'' +
          ", address='" + address +
          '}';
    }

    public static Person toPerson(JSONObject jsonObject) {

      Person person = new Person(jsonObject.getString("id"),
          jsonObject.getString("name"),
          jsonObject.getString("address"));

      return person;
    }
  }
