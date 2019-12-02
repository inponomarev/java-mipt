package edu.phystech.jdbcdemo.service.dao;

import edu.phystech.jdbcdemo.domain.Conference;
import edu.phystech.jdbcdemo.domain.Speaker;
import edu.phystech.jdbcdemo.domain.Talk;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class TestData {
    public final static Speaker EGOROV = new Speaker(1, "Сергей Егоров");
    public final static Speaker TOLKACHEV = new Speaker(2, "Кирилл Толкачёв");
    public final static Speaker BORISOV = new Speaker(3, "Евгений Борисов");
    public final static Speaker VALEEV = new Speaker(4, "Тагир Валеев");

    public final static Conference JPOINT = new Conference(1, "JPoint");
    public final static Conference JOKER = new Conference(2, "Joker");

    public final static Talk JAVA914 = new Talk(1,
            "Java 9-14: Маленькие оптимизации",
            JOKER,
            Collections.singleton(VALEEV));

    public final static Talk REACTIVEORNOT = new Talk(2,
            "Reactive или не reactive, вот в чем вопрос",
            JPOINT,
            new HashSet<>(Arrays.asList(BORISOV, TOLKACHEV)));

    public final static Talk SIMPSON = new Talk(3,
            "Не будь Гомером Симпсоном для своего Reactor-а!",
            JPOINT,
            Collections.singleton(EGOROV));

    public final static Talk TESTCONTAINERS = new Talk(4,
            "Testcontainers: Год спустя",
            JOKER,
            Collections.singleton(EGOROV));
}
