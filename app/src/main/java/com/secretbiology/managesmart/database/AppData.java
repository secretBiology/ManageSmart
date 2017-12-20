package com.secretbiology.managesmart.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by Rohit Suratekar on 18-12-17 for ManageSmart.
 * All code is released under MIT License.
 */
@Database(entities = {ExpenseEntry.class, ExpenseCategory.class, ExpenseSubCategory.class, ExpenseMedium.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class AppData extends RoomDatabase {

    private static final String DATABASE_NAME = "ManageSmart";
    private static AppData INSTANCE;

    public abstract EntryDao entries();

    public abstract CategoryDao categories();

    public abstract SubCategoryDao subCategories();

    public abstract MediumDao mediums();

    public static AppData getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppData.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration() //Need this to remove all old databases
                            .allowMainThreadQueries() //TODO
                            .build();
        }
        return INSTANCE;
    }

    public static EntryDao entries(Context context) {
        return getDatabase(context).entries();
    }

    public static CategoryDao categories(Context context) {
        return getDatabase(context).categories();
    }

    public static SubCategoryDao subCategories(Context context) {
        return getDatabase(context).subCategories();
    }

    public static MediumDao mediums(Context context) {
        return getDatabase(context).mediums();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
