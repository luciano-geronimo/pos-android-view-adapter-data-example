package traveldestinations.pos.geronimo.don.pos_android_traveldestinations.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.R
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.model.Destination

class DestinationAdapter(context: Context, collection:MutableList<Destination>) : BaseAdapter() {
    private var ctx:Context
    init{
        ctx = context
    }

    private var mCollection: MutableList<Destination> = collection
    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getCount():Int {
        return mCollection.size
    }

    override fun getItem(position:Int):Any{
        return mCollection[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        //Pega a view
        view = this.mInflator.inflate(R.layout.destination_row, parent, false)
        //pega os componentes da view
        var label = view?.findViewById(R.id.tvDestinationName) as TextView
        var deleteButton = view?.findViewById(R.id.btnDeleteDestination) as Button
        var openButton = view?.findViewById(R.id.btnOpenDestination) as Button
        //seta eles
        label.text = mCollection[position].name
        deleteButton.tag = mCollection[position]
        deleteButton.setOnClickListener { v ->
            //Operação de remoção.
            val dest = v.tag as Destination
            mCollection.remove(dest)
            notifyDataSetChanged()
        }
        openButton.tag = mCollection[position]
        openButton.setOnClickListener { v ->
            //abertura do google street view
            val dest = v.tag as Destination
            var gmmInternalUri = Uri.parse("google.streetview:cbll=${dest.latitudeLongitude}")
            var gmmIntent = Intent(Intent.ACTION_VIEW, gmmInternalUri)
            gmmIntent.setPackage("com.google.android.apps.maps")
            if(gmmIntent.resolveActivity(ctx.packageManager)!=null){
                ctx.startActivity(gmmIntent)
            }
            else{
                Toast.makeText(ctx, "Sem google maps, nao tem como abrir", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    fun addDestination(newDestination: Destination) {
        mCollection.add(newDestination)
        notifyDataSetChanged()
    }


}