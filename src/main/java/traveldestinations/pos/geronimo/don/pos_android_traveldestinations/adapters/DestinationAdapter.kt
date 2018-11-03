package traveldestinations.pos.geronimo.don.pos_android_traveldestinations.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.R
import traveldestinations.pos.geronimo.don.pos_android_traveldestinations.model.Destination

class DestinationAdapter(context: Context, collection:List<Destination>) : BaseAdapter() {
    private var mCollection:List<Destination> = collection
    private val mInflator:LayoutInflater = LayoutInflater.from(context)

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
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.destination_row, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text = mCollection[position].name
        return view
    }

    private class ListRowHolder(row:View?){
        val label: TextView
        init{
            this.label = row?.findViewById(R.id.tvDestinationName)as TextView
        }
    }

}