package sample.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private final StringProperty name;
	private final StringProperty surname;
	private final StringProperty city;
	private final StringProperty street;
	private final ObjectProperty<Long> phone;

	public Person() {
		this.name = new SimpleStringProperty(null);
		this.surname = new SimpleStringProperty(null);

		this.city = new SimpleStringProperty(null);
		this.street = new SimpleStringProperty(null);
		this.phone = new SimpleObjectProperty<>(null);
	}

	public Person(String name, String surname) {
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);

		this.city = new SimpleStringProperty("some street");
		this.street = new SimpleStringProperty("some street");
		this.phone = new SimpleObjectProperty<>(23L);
	}

	public Person(String name, String surname, String street, Long phone) {
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);

		this.city = new SimpleStringProperty("some street");
		this.street = new SimpleStringProperty(street);
		this.phone = new SimpleObjectProperty<>(phone);
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getSurname() {
		return surname.get();
	}

	public StringProperty surnameProperty() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}

	public String getCity() {
		return city.get();
	}

	public StringProperty cityProperty() {
		return city;
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public String getStreet() {
		return street.get();
	}

	public StringProperty streetProperty() {
		return street;
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public Long getPhone() {
		return phone.get();
	}

	public ObjectProperty<Long> phoneProperty() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone.set(phone);
	}
}
