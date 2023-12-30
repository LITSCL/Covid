package cl.inacap.covid.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacienteSQLiteHelper extends SQLiteOpenHelper {

    private final String sqlCreate = "CREATE TABLE paciente" +
        "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
        ",rut TEXT" +
        ",nombre TEXT" +
        ",apellido TEXT" +
        ",fecha_examen TEXT" +
        ",area_trabajo TEXT" +
        ",presenta_sintomas_covid TEXT" +
        ",temperatura REAL" +
        ",presenta_tos TEXT" +
        ",presion_arterial INTEGER)";

    public PacienteSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS paciente");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
