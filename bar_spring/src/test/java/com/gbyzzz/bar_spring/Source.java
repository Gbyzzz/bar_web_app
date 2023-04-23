package com.gbyzzz.bar_spring;

import com.gbyzzz.bar_spring.entity.*;
import org.junit.jupiter.params.provider.Arguments;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Source {

    public static User user1 = new User(1L, "Admin", "132435", "Tom", "Smith",
            "7876867845", "admin@bar.com", null, User.Role.ROLE_ADMIN,
            true, new Date(2022-1900, 10-1, 25));
    public static User user2 = new User(2L, "WhiskeySour", "132435", "Will", "Anderson",
            null,"wa1984@gmail.com", null, User.Role.ROLE_USER,
            true, new Date(2022-1900, 10-1, 25));

    public static List<User> users = new ArrayList<>(){{
        add(user1);
        add(user2);
    }};

    public static User user2_1 = new User(2L, "WhiskeySour", "132435", "Will", "Anderson",
            null,"wa1984@gmail.com", null, User.Role.ROLE_USER,
            true, new Date(2022-1900, 10-1, 25));

    public static User user3 = new User(3L, "Jspm", "132435", null, null,
            null,"alcoholfan2010@gmail.com", null, User.Role.ROLE_USER,
            true, new Date(2022-1900, 10-1, 25));
    public static Cocktail cocktail1 = new Cocktail(1L, "Old Fashioned", users.get(0), 5,
            new Date(2022-1900, 10-1, 25), null,
            "В стакан олд фэшн кладём кусочек сахара, капаем на него Ангостуру и воду." +
                    "При помощи мадлера измельчаем сахар, превращая в некое подобие сиропа на дне бокала." +
                    "Наполняем стакан льдом и добавляем половину бурбона." +
                    "Тщательно перемешиваем с помощью барной ложки, охлаждая бокал и обводняя смесь." +
                    "Добавляем ещё льда и наливаем оставшуюся половину бурбона." +
                    "Снова перемешиваем." +
                    "Сбрызгиваем цедрой апельсина." +
                    "Подаём без украшения.", 33);

    public static Cocktail cocktail2 = new Cocktail(2L, "Long Island Ice Tea", users.get(1), 5,
            new Date(2022-1900, 10-1, 25), null,
            "Наполняем бокал льдом и до половины — колой." +
                    "Наполняем шейкер льдом и наливаем туда водку, джин, ром, текилу и трипл сек." +
                    "Взбиваем в шейкере.Добовляем в стакан с колой лимонный фреш." +
                    "Выливаем в стакан с колой содержимое шейкера, используя стрейнер, чтобы лишний " +
                    "лёд не попал.Стараемся, чтобы алкогольная смесь не перемешалась с колой." +
                    "Украшаем бокал зонтиком или чем угодно ещё — это же Лонг Айленд",
            20);
    public static Cocktail cocktail2_1 = new Cocktail(2L, "Long Island Ice Tea", users.get(1), 0,
            new Date(new java.util.Date().getTime()), null,
            "Наполняем бокал льдом и до половины — колой." +
                    "Наполняем шейкер льдом и наливаем туда водку, джин, ром, текилу и трипл сек." +
                    "Взбиваем в шейкере.Добовляем в стакан с колой лимонный фреш." +
                    "Выливаем в стакан с колой содержимое шейкера, используя стрейнер, чтобы лишний " +
                    "лёд не попал.Стараемся, чтобы алкогольная смесь не перемешалась с колой." +
                    "Украшаем бокал зонтиком или чем угодно ещё — это же Лонг Айленд",
            20);

    public static Vote vote1 = new Vote(1L, user1, cocktail2, 4);
    public static Vote vote1_2 = new Vote(1L, user1, cocktail2, 5);
    public static Vote vote2 = new Vote(2L, user2, cocktail1, 5);
    public static Vote vote3 = new Vote(3L, user1, cocktail1, 4);
    public static Vote vote4 = new Vote(4L, user2, cocktail2, 5);
    public static List<Ingredient> ingredients = new ArrayList<>(){{
        add(new Ingredient(1L, "Angostura bitter", 45, "drop(s)"));
        add(new Ingredient(2L, "Bourbon", 40, "ml"));
        add(new Ingredient(3L, "Sugar cube", 0, "pc(s)"));
        add(new Ingredient(4L, "Water", 0, "ml"));
        add(new Ingredient(5L, "Ice", 0, null));
        add(new Ingredient(6L, "White rum", 40, "ml"));
        add(new Ingredient(7L, "Vodka", 40, "ml"));
        add(new Ingredient(8L, "Tequila", 38, "ml"));
        add(new Ingredient(9L, "Gin", 40, "ml"));
        add(new Ingredient(10L, "Cointreau", 40, "ml"));
        add(new Ingredient(11L, "Lemon juice", 0, "ml"));
        add(new Ingredient(12L, "Cola", 0, "ml"));
    }};

    public static List<Recipe> recipesForCocktail1 = new ArrayList<>(){{
        add(new Recipe(1L, cocktail1, ingredients.get(0), 3));
        add(new Recipe(2L, cocktail1, ingredients.get(1), 50));
        add(new Recipe(3L, cocktail1, ingredients.get(2), 1));
        add(new Recipe(4L, cocktail1, ingredients.get(3), 7));
    }};

    public static List<Recipe> recipesForCocktail2 = new ArrayList<>(){{
        add(new Recipe(5L, cocktail2, ingredients.get(5), 15));
        add(new Recipe(6L, cocktail2, ingredients.get(6), 15));
        add(new Recipe(7L, cocktail2, ingredients.get(7), 15));
        add(new Recipe(8L, cocktail2, ingredients.get(8), 15));
        add(new Recipe(9L, cocktail2, ingredients.get(9), 15));
        add(new Recipe(10L, cocktail2, ingredients.get(10), 25));
        add(new Recipe(11L, cocktail2, ingredients.get(11), 150));
    }};

    public static Ingredient ingredientToUpdate = new Ingredient(5L, "Ice", 0, "g");
    public static Ingredient ingredientToAdd = new Ingredient(13L, "Campari", 25, "ml");
    static Cocktail cocktail3 = new Cocktail(3L, "Cola", users.get(1), 5.0d,
            new Date(new java.util.Date().getTime()), null,
            "Наполняем бокал льдом и колой.",
            20);
    public static List<Cocktail> cocktailList = new ArrayList<>(){{
        add(cocktail1);
        add(cocktail2);
    }};

    public static Stream<Arguments> provideCocktailsById() {
        return Stream.of(
                Arguments.of(cocktailList.get(0), 1L),
                Arguments.of(cocktailList.get(1), 2L)
        );
    }

    public static Stream<Arguments> provideAddedAndUpdatedCocktails(){
        return Stream.of(
          Arguments.of(new ArrayList<>(){{
              add(cocktailList.get(0));
              add(cocktail2_1);
          }}, cocktail2_1),
          Arguments.of(new ArrayList<>(){{
              addAll(cocktailList);
              add(cocktail3);
          }}, cocktail3)
        );
    }

    public static Stream<Arguments> provideAddedAndUpdatedIngredients(){
        return Stream.of(
                Arguments.of(ingredientToUpdate),
                Arguments.of(ingredientToAdd)
        );
    }

    public static Stream<Arguments> provideRecipesByCocktail(){
        return Stream.of(
                Arguments.of(recipesForCocktail1, cocktail1),
                Arguments.of(recipesForCocktail2, cocktail2)
                );
    }

    public static Stream<Arguments> provideRecipesToCalcAlcohol(){
        return Stream.of(
                Arguments.of(recipesForCocktail1, 35),
                Arguments.of(recipesForCocktail2, 11)
        );
    }

    public static Stream<Arguments> provideUsersById(){
        return Stream.of(
                Arguments.of(users.get(0), 1L),
                Arguments.of(users.get(1), 2L)
        );
    }

    public static Stream<Arguments> provideUsersToAddOrUpdate(){
        return Stream.of(
                Arguments.of(new ArrayList<>(){{
                    add(user1);
                    add(user2_1);
                }}, user2_1),
                Arguments.of(new ArrayList<>(){{
                    add(user1);
                    add(user2_1);
                    add(user3);
                }}, user3)
        );
    }

    public static Stream<Arguments> provideUsersToCheckUsernameAvailability(){
        return Stream.of(
                Arguments.of(true, users.get(0).getUsername()),
                Arguments.of(true, users.get(1).getUsername()),
                Arguments.of(false, "Johnny")
        );
    }

    public static Stream<Arguments> provideUsersToCheckEmailAvailability(){
        return Stream.of(
                Arguments.of(true, users.get(0).getEmail()),
                Arguments.of(true, users.get(1).getEmail()),
                Arguments.of(false, "aasdsaf@asfs.er")
        );
    }

    public static Stream<Arguments> provideUsernamesToFindUsers(){
        return Stream.of(
                Arguments.of(Optional.of(user1), users.get(0).getUsername()),
                Arguments.of(Optional.of(user2_1), users.get(1).getUsername()),
                Arguments.of(Optional.empty(), "Johnny")
                );
    }

    public static Stream<Arguments> provideVotesToAddOrUpdate(){
        return Stream.of(
                Arguments.of(vote1, vote1),
                Arguments.of(vote1_2, vote1_2),
                Arguments.of(vote2, vote2)
        );
    }

    public static Stream<Arguments> provideInfoToFindVoteByCocktailAndUser(){
        return Stream.of(
                Arguments.of(vote1_2, new Vote(null, user1, cocktail2, 0)),
                Arguments.of(vote2, new Vote(null, user2, cocktail1, 0))
        );
    }

    public static Stream<Arguments> provideCocktailsToUpdateRating(){
        return Stream.of(
                Arguments.of(new Cocktail(1L, "Old Fashioned", users.get(0), 4.5d,
                        new Date(2022-1900, 10-1, 25), null,
                        "В стакан олд фэшн кладём кусочек сахара, капаем на него Ангостуру и воду." +
                                "При помощи мадлера измельчаем сахар, превращая в некое подобие сиропа на дне бокала." +
                                "Наполняем стакан льдом и добавляем половину бурбона." +
                                "Тщательно перемешиваем с помощью барной ложки, охлаждая бокал и обводняя смесь." +
                                "Добавляем ещё льда и наливаем оставшуюся половину бурбона." +
                                "Снова перемешиваем." +
                                "Сбрызгиваем цедрой апельсина." +
                                "Подаём без украшения.", 33),
                        cocktail1, vote3),
                Arguments.of(new Cocktail(2L, "Long Island Ice Tea", users.get(1), 5.0d,
                                new Date(2022-1900, 10-1, 25), null,
                                "Наполняем бокал льдом и до половины — колой." +
                                        "Наполняем шейкер льдом и наливаем туда водку, джин, ром, текилу и трипл сек." +
                                        "Взбиваем в шейкере.Добовляем в стакан с колой лимонный фреш." +
                                        "Выливаем в стакан с колой содержимое шейкера, используя стрейнер, чтобы лишний " +
                                        "лёд не попал.Стараемся, чтобы алкогольная смесь не перемешалась с колой." +
                                        "Украшаем бокал зонтиком или чем угодно ещё — это же Лонг Айленд",
                                20),
                        cocktail2, vote4)
        );
    }
}
