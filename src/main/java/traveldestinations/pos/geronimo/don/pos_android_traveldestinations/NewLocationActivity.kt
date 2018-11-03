package traveldestinations.pos.geronimo.don.pos_android_traveldestinations

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_location.*

class NewLocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_location)
        setResult(Activity.RESULT_CANCELED)
    }

    fun onNewDestinationClick(view: View){
        val latitude = edtLatitude.text.toString()
        val longitude = edtLongitude.text.toString()
        val name = edtName.text.toString()
        val latLon = "$latitude,$longitude"
        var returnIntent = Intent()
        returnIntent.putExtra("name", name)
        returnIntent.putExtra("coordinates", latLon)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

}
