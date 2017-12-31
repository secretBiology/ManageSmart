package com.secretbiology.managesmart.common;

import com.secretbiology.managesmart.R;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */

public enum IconRelations {

    APPLE(R.drawable.icon_apple, "apple, fruit"),
    BEER(R.drawable.icon_beer, "beer, drink"),
    BIRTHDAY(R.drawable.icon_birthday_cake, "birthday, cake, party"),
    BREAD(R.drawable.icon_bread, "bread, breakfast, snack"),
    BROCCOLI(R.drawable.icon_broccoli, "vegetables, broccoli, salad, market"),
    BURGER(R.drawable.icon_burger, "burger, snack, macd, restaurant, kfc"),
    CHEESE(R.drawable.icon_cheese, "cheese, glossaries, market"),
    CHICKEN(R.drawable.icon_chicken, "chicken, food, lunch, dinner"),
    CHOCOLATE(R.drawable.icon_chocolate, "chocolate, candy, bar, sweet"),
    COFFEE(R.drawable.icon_coffee, "coffee, drink, beverages, date"),
    WATER(R.drawable.icon_water, "water, drink, drop"),
    LIGHT(R.drawable.icon_light, "light, electricity, flash"),
    FISH(R.drawable.icon_fish, "fish, animal, aquatic"),
    SHRIMP(R.drawable.icon_shrimp, "shrimp, prawns, meal"),
    COKE(R.drawable.icon_coke, "coke, drink, beverages, pepsi"),
    DINNER(R.drawable.icon_dinner, "dinner, food, cooking"),
    EGG(R.drawable.icon_egg, "egg, breakfast, omelette"),
    GRAPES(R.drawable.icon_grapes, "grapes, fruits, salad"),
    HOTDOG(R.drawable.icon_hotdog, "hotdog, snacks, street"),
    ICECREAM(R.drawable.icon_icecream, "icecream, dessert, sweet"),
    MILK(R.drawable.icon_milk, "milk, store, drink, breakfast"),
    PIZZA(R.drawable.icon_pizza, "pizza, dinner, snack"),
    POPCORN(R.drawable.icon_popcorn, "popcorn, movie, break"),
    RICE(R.drawable.icon_rice, "rice, lunch, food"),
    TEA(R.drawable.icon_tea, "tea, drink, beverages"),
    WINE(R.drawable.icon_tea, "wine, drink, party"),
    FOOD(R.drawable.icon_food, "food, restaurant, dining"),
    MUSIC(R.drawable.icon_music, "music, song, concert"),
    PIE(R.drawable.icon_pie, "pie, pastry, bakery"),
    CIGAR(R.drawable.icon_cigar, "cigar, smoking"),
    PACKAGE(R.drawable.icon_package, "package, amazon, flipkart, box, post"),
    BOWLING(R.drawable.icon_bowling, "bowling, game, fun"),
    TICKET(R.drawable.icon_ticket, "ticket, movie, admission"),
    DICE(R.drawable.icon_die, "random, gamble, dice"),
    FOOTBALL(R.drawable.icon_football, "football, soccer, sports, ball"),
    VIDEO_GAME(R.drawable.icon_video_game, "video game, online"),
    BUS(R.drawable.icon_bus, "bus, public transport, transportation"),
    TAXI(R.drawable.icon_taxi, "taxi, cab, uber, ola"),
    TRAM(R.drawable.icon_tram, "tram, electric"),
    METRO(R.drawable.icon_metro, "metro, train, public transport"),
    PLANE(R.drawable.icon_plane, "airplane, flight"),
    TRAIN(R.drawable.icon_train, "train, public transport"),
    FUEL(R.drawable.icon_fuel, "fuel, petrol, diesel, tank, fill"),
    FERRY(R.drawable.icon_ferry, "ferry, ship, boat"),
    CAR(R.drawable.icon_car, "car, vehicle, personal"),
    BIKE(R.drawable.icon_motor, "motorcycle, bike, scooter"),
    CYCLE(R.drawable.icon_bicycle, "bicycle, bike"),
    MAP(R.drawable.icon_map, "map, travel, world"),
    SCHOOL(R.drawable.icon_school, "school, collage, education"),
    HOME(R.drawable.icon_house, "home, house, place"),
    HOSPITAL(R.drawable.icon_hospital, "hospital, medicine, surgery, bill"),
    BANK(R.drawable.icon_bank, "bank, insurance, investment, money"),
    HOTEL(R.drawable.icon_hotel, "hotel, holiday, vacation"),
    DESKTOP(R.drawable.icon_desktop, "computer, desktop, screen, monitor"),
    MOBILE(R.drawable.icon_mobile, "cell, mobile, iphone, android, recharge"),
    MONEY(R.drawable.icon_money, "money, cash, investment"),
    SHOES(R.drawable.icon_shoes, "shoes, heel, footwear, party"),
    CAMERA(R.drawable.icon_camera, "camera, photography"),
    FLOWER(R.drawable.icon_flower, "flower, donation, contribution, help"),
    ART(R.drawable.icon_art, "art, painting, drawing, color"),
    CARD(R.drawable.icon_card, "card, debit, credit, swipe, atm"),
    REPAIR(R.drawable.icon_repair, "repair, hammer, workshop"),
    MICROSCOPE(R.drawable.icon_microscope, "science, microscope, education"),
    PILL(R.drawable.icon_pill, "pill, tablet, medicine, medical"),
    PARTY(R.drawable.icon_party, "party, blast, invite"),
    EMAIL(R.drawable.icon_email, "email, web, online"),
    GIFT(R.drawable.icon_gift, "gift, wrap, surprise"),
    YOGA(R.drawable.icon_yoga, "yoga, exercise, meditation, calm"),
    VACATION(R.drawable.icon_vacation, "vacation, trip, holiday, foreign"),
    MOVIE(R.drawable.icon_movie, "movie, film, video, pvr, multiplex, inox"),
    SHOPPING(R.drawable.icon_cart, "shopping cart, market, bazar, mart"),
    FINANCE(R.drawable.icon_finance, "finance, investment, shares"),
    NEWS(R.drawable.icon_news, "news paper, morning, read"),
    BOOK(R.drawable.icon_book, "book, magazine, read"),
    WRITING(R.drawable.icon_writing, "writing, stationary, pencil, pen, paper"),
    UNKNOWN(R.drawable.icon_unknwon, "unknown, blank, question"),
    EXPENSE(R.drawable.icon_expense, "expense"),
    NOTE(R.drawable.icon_note, "note, dollar"),
    EXCLAMATION(R.drawable.icon_exclamation, "exclamation, miscellaneous, information"),
    ADD(R.drawable.icon_add, "add new, add, extra, plus");

    private int icon;
    private String association;

    IconRelations(int icon, String association) {
        this.icon = icon;
        this.association = association;
    }

    public int getIcon() {
        return icon;
    }

    public String getAssociation() {
        return association;
    }
}
