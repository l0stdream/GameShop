package org.JavaPro.repository.sql;

public class GamePostgreSQL {
    public static final String SAVE =
            """
                            INSERT INTO public."Games"(name, release_date, rating, price, description, type)
                            VALUES (?, ?, ?, ?, ?, ?)
                    """;

    public static final String DELETE_BY_NAME =
            """
                            DELETE FROM public."Games"
                            WHERE name = ?
                    """;

    public static final String GET_ALL =
            """
                            SELECT id, name, release_date, rating, price, description, type, creation_date
                            FROM public."Games"
                    """;

    public static final String GET_BY_NAME =
            """
                                   SELECT id, name, release_date, rating, price, description, type, creation_date
                                    FROM public."Games"
                                    WHERE name = ?
                    """;


    public static final String GET_FILTERED_BY_PRICE =
            """
                            SELECT id, name, release_date, rating, price, description, type, creation_date
                            FROM public."Games"
                            WHERE price = ?
                    """;


    public static final String GET_FILTERED_BY_TYPE =
            """
                            SELECT id, name, release_date, rating, price, description, type, creation_date
                            FROM public."Games"
                            WHERE type = ?
                    """;


    public static final String GET_ALL_SORTED_BY_CREATION_DATE =
            """
                            SELECT id, name, release_date, rating, price, description, type, creation_date
                            FROM public."Games"
                            ORDER BY creation_date;
                    """;

}
