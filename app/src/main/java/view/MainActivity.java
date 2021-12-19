package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.List;

import model.CustomerDao;
import model.CustomerModel;
import site.cosmohosting.sqlitedemo.R;

public class MainActivity extends AppCompatActivity {

    //Referencia los controles de la vista
    Button btnAddCustomer, btnGetAllCustomers;
    EditText etvCustomerName, etvCustomerAge;
    Switch swIsActive;
    ListView lvCustomers;

    //Adaptador
    ArrayAdapter customerArrayAdapter;
    List<CustomerModel> customerList;
    CustomerDao customerDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Inicializa los controles de la vista
         */
        btnAddCustomer = (Button) findViewById(R.id.btn_addCustomer); //Referencia el botón Add
        btnGetAllCustomers = (Button) findViewById(R.id.btn_queryCustomers); //Referencia el botón Query
        etvCustomerName = (EditText) findViewById(R.id.etv_customerName);
        etvCustomerAge = (EditText) findViewById(R.id.etv_customerAge);
        swIsActive = (Switch) findViewById(R.id.sw_activeCustomer);
        lvCustomers = (ListView) findViewById(R.id.lv_Customers);

        refreshCustomerList();

        /**
         * Listener
         * Ejecuta las acciones del botón agregar
         */
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Crea un objeto CustomerModel y establece los valores
                CustomerModel customerModel = new CustomerModel();
                customerModel.setId(-1);
                customerModel.setName(etvCustomerName.getText().toString());
                customerModel.setAge(Integer.parseInt(etvCustomerAge.getText().toString()));
                customerModel.setIsActive(swIsActive.isChecked());

                //Crea un objeto de acceso a datos. Esta clase fue creada para acceder a la base de datos
                customerDao = new CustomerDao(MainActivity.this);
                customerDao.addOne(customerModel); //Inserta el nuevo registro

                refreshCustomerList();
            }
        });

        /**
         * Listener
         * Ejecuta las acciones del botón de consulta
         */
        btnGetAllCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                refreshCustomerList();

            }
        });

        /**
         * Elimina un registro de la vista al dar clic en el elemento
         */
        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
//                DataAccessObject dataAccessObject = new DataAccessObject(MainActivity.this);
                customerDao.deleteOne(clickedCustomer);

                refreshCustomerList();
            }
        });


    }

    /**
     * Refresca la lista de clientes
     */
    public void refreshCustomerList() {
//
        customerDao = new CustomerDao(MainActivity.this);
        customerList = customerDao.getAll();

        //Crear un array adapter conectado a la lista
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                customerList);

        //Conectar el array adapter a la lista
        lvCustomers.setAdapter(customerArrayAdapter);

    }
}