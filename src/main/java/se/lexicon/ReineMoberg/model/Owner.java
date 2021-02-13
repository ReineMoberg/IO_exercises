package se.lexicon.ReineMoberg.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Owner implements Serializable {

    private UUID ownerId;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

    public Owner() {
    }

    public Owner(String name, LocalDate birthDate) {
        this.ownerId = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
