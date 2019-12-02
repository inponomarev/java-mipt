package edu.phystech.jdbcdemo.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Talk {
    private final int id;
    private final String name;
    private final Conference conference;
    private final Set<Speaker> speakers;
}
