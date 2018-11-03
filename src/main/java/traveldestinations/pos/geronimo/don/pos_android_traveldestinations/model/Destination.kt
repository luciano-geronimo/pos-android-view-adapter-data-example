package traveldestinations.pos.geronimo.don.pos_android_traveldestinations.model

class Destination : Comparable<Destination> {
    var name:String
    var latitudeLongitude:String

    constructor(n:String, ll:String){
        name = n
        latitudeLongitude = ll
    }

    override fun compareTo(other: Destination): Int {
        return name.compareTo(other.name)
    }

    override fun equals(other: Any?): Boolean {
        if(other is Destination){
            return name.equals(other.name)
        }
        else{
            return false;
        }
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}