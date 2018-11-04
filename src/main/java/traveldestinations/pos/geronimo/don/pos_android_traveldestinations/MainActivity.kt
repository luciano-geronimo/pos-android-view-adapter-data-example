package traveldestinations.pos.geronimo.don.pos_android_traveldestinations

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.adapters.DestinationAdapter
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.adapters.HelloAdapter
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.model.Destination
import java.net.URI

class MainActivity : AppCompatActivity() {
    val ID_NEW_LOCATION = 1000
    private var destinationList : ArrayList<Destination> = ArrayList<Destination>();
    private var noneSelected : Destination = Destination("Escolha um local", "")
    private lateinit var adapter : DestinationAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Popula a lista com os dados de teste
        populateList()
        //Usa o adapter
        adapter = DestinationAdapter(this,destinationList)
        destinations.adapter = adapter
//        destinations.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
//            val selectedDestination = destinationList[position]
//            var gmmInternalUri = Uri.parse("google.streetview:cbll=${selectedDestination.latitudeLongitude}")
//            var gmmIntent = Intent(Intent.ACTION_VIEW, gmmInternalUri)
//            gmmIntent.setPackage("com.google.android.apps.maps")
//            if(gmmIntent.resolveActivity(packageManager)!=null){
//                startActivity(gmmIntent)
//            }
//            else{
//                Toast.makeText(this, "Sem google maps, nao tem como abrir", Toast.LENGTH_SHORT).show()
//            }
//        }
        newLocation.setOnClickListener { view ->
            val intent = Intent(this, NewLocationActivity::class.java)
            startActivityForResult(intent, ID_NEW_LOCATION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ID_NEW_LOCATION){
            if(resultCode == Activity.RESULT_OK){
                //ATUALIZA A COLECAO
                val newName = data?.getStringExtra("name")
                val newCoord = data?.getStringExtra("coordinates")
                val newDestination = Destination(newName!!, newCoord!!)
                adapter.addDestination(newDestination)
            }
        }
    }

    private fun populateList(){
        destinationList.add(Destination("Mercadão de Madureira", "-22.8707293,-43.3381514"))
        destinationList.add(Destination("Copacabana", "-22.9697777,-43.1868592"))
    }
}
