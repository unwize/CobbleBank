package com.unwize.cobblebank.orm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.Vector;
import java.util.stream.Stream;

enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    GENDERLESS("GENDERLESS");

    private String gender;

     Gender(String gender) {
        this.gender = gender;
    }

    public static Stream<Gender> stream() {
        return Stream.of(Gender.values());
    }

    @Override
    public String toString() {
         return gender;
    }
}

enum Stat {
    HP("hp"),
    SPEED("speed"),
    ATTACK("attack"),
    DEFENCE("defence"),
    SPECIAL_ATTACK("special_attack"),
    SPECIAL_DEFENCE("special_defence");

    private String stat;

    Stat(String stat) {
        this.stat = stat;
    }

    public static Stream<Stat> stream() {
        return Stream.of(Stat.values());
    }

    @Override
    public String toString() {
        return stat;
    }
}

// An abstract class that supports common functionality that's needed between EV and IV collections
abstract class StatCollection {
    protected final static Integer STAT_MAX = 0;
    protected final static Integer STAT_SUM_MAX = null;

    public HashMap<Stat, Integer> values = new HashMap<>();

    public StatCollection() {
        Stat.stream().forEach(s -> {values.put(s, 0);});
    }

    public StatCollection(@NotNull HashMap<Stat, Integer> stats) {
        Stat.stream().forEach(
                s -> {
                    values.put(s, Math.clamp(Objects.requireNonNullElse(stats.get(s), 0), 0, STAT_MAX));
                });
    }

    // Set the value of a specific stat, clamped between 0 and the stat max
    public void set(Stat stat, @NotNull Integer value) {
        values.put(stat, Math.clamp(value, 0, STAT_MAX));
    }
}

class IVs extends StatCollection {
    protected final static Integer STAT_MAX = 31;
}

class EVs extends StatCollection {
    protected final static Integer STAT_MAX = 252;
}

class BenchedMove {
    public String moveName;
    public int raisePPStages;

    public BenchedMove() {

    }

    public BenchedMove(@NotNull String moveName, @NotNull Integer raisePPStages) {
        this.moveName = moveName;
        this.raisePPStages = raisePPStages;
    }
}

class Move {
    public String moveName;
    public Integer movePP;
    public Integer RaisedPPStages;
}

class Ability {
    String abilityName;
    Boolean abilityForced;
}


@Entity
@Table(name="DepositedPokemon")
public class DepositedPokemon {
    @Id
    @GeneratedValue
    private Long id;

    public Vector<BenchedMove> benchedMoves;
    public Double scaleModifier;
    public Boolean shiny;
    public Ability ability;
    public Gender gender;
    public IVs ivs;
    public EVs evs;
}
