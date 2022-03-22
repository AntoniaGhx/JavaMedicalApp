package ro.ubb.domain;

import java.time.Duration;
import java.time.LocalTime;

public class Procedure extends BaseEntity<Integer>{
    private String name;
    private Float price;
    private Specialty specialty;
    private Integer duration;

    public Procedure() {}

    public Procedure(String name, Float price, Specialty specialty, Integer duration) {
        this.name = name;
        this.price = price;
        this.specialty = specialty;
        this.duration = duration;
    }

    public Procedure(Integer idEntity, String name, Float price, Specialty specialty, Integer duration) {
        super(idEntity);
        this.name = name;
        this.price = price;
        this.specialty = specialty;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "idEntity=" + idEntity +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", specialty=" + specialty +
                ", duration=" + duration +
                '}';
    }
}
