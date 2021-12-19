package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object
 * Maneja todas las operaciones hacia la base de datos
 */
public class CustomerDao extends SQLiteOpenHelper {

    //Constantes para el nombre de la table
    public static final String DB_NAME = "test.db";
    public static final String TBL_CUSTOMER = "tbl_customer";
    public static final String COLUMN_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String COLUMN_CUSTOMER_AGE = "customer_age";
    public static final String COLUMN_CUSTOMER_IS_ACTIVE = "customer_is_active";

    /**
     * Constructor
     *
     * @param context
     */
    public CustomerDao(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    /**
     * Se ejecuta la primera vez que se accede la base de datos
     * Aquí se crea la base de datos
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Comando de SQL para crear una tabla
        String createTableStatement = "CREATE TABLE " + TBL_CUSTOMER + " (\n" +
                "    " + COLUMN_CUSTOMER_ID + "   INTEGER     PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_CUSTOMER_NAME + " STRING (45),\n" +
                "    " + COLUMN_CUSTOMER_AGE + "  INTEGER,\n" +
                "    " + COLUMN_CUSTOMER_IS_ACTIVE + "  BOOLEAN\n" +
                ");";

        db.execSQL(createTableStatement);

    }

    /**
     * Se ejecuta cuando hay una nueva versión de la base de datos
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Agrega un registro a la base de datos
     *
     * @param customerModel
     */
    public boolean addOne(CustomerModel customerModel) {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        contentValues.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        contentValues.put(COLUMN_CUSTOMER_IS_ACTIVE, customerModel.isActive());

        //Inserta el registro
        long insert = db.insert(TBL_CUSTOMER, null, contentValues);
        //Valida el número de registros insertados
        if (insert == -1) {
            return false;
        }
        return true;
    }

    /**
     * Consulta todos los clientes
     * @return
     */
    public List<CustomerModel> getAll(){
        List<CustomerModel> customerList = new ArrayList<>();

        String queryString = "SELECT " +
                COLUMN_CUSTOMER_ID +
                ", " + COLUMN_CUSTOMER_NAME +
                ", " + COLUMN_CUSTOMER_AGE +
                ", " + COLUMN_CUSTOMER_IS_ACTIVE +
                " FROM " + TBL_CUSTOMER;
        SQLiteDatabase db = this.getReadableDatabase(); //Abre la base de datos de solo lectura
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.getCount() > 0){
            while(cursor.moveToNext()){
                int customerId = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge  =cursor.getInt(2);
                boolean customerIsActive = cursor.getInt(3) ==1 ? true : false;

                CustomerModel customerModel = new CustomerModel(customerId,customerName, customerAge, customerIsActive);
                customerList.add(customerModel);
            }
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return customerList;
    }

    /**
     * Borra un registro de la base de datos si lo encuentra
     * @param customerModel
     * @return
     */
    public boolean deleteOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteString = "DELETE FROM " +
                TBL_CUSTOMER +
                " WHERE " + COLUMN_CUSTOMER_ID +
                "= " + customerModel.getId();

        Cursor cursor = db.rawQuery(deleteString, null);
        //Si no tiene registros, significa que no eliminó ningún registro
        if(cursor.getCount() ==0){
            return false;
        }

        return true;
    }
}
