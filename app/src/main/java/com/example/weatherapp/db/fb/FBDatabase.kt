package com.example.weatherapp.db.fb


import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
class FBDatabase {

    interface Listener {
        fun onUserLoaded(user: FBUser)
        fun onUserSignOut()
        fun onCityAdded(city: FBCity)
        fun onCityUpdated(city: FBCity)
        fun onCityRemoved(city: FBCity)
    }
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var citiesListReg: ListenerRegistration? = null
    private var listener : Listener? = null

    init {
        auth.addAuthStateListener { auth ->
            if (auth.currentUser == null) {
                citiesListReg?.remove()
                listener?.onUserSignOut()
                return@addAuthStateListener
            }
            val refCurrUser = db.collection("users").document(auth.currentUser!!.uid)
            refCurrUser.get().addOnSuccessListener {
                it.toObject(FBUser::class.java)?.let { user ->
                    listener?.onUserLoaded(user)
                }
            }
            citiesListReg = refCurrUser.collection("cities")
                .addSnapshotListener { snapshots, ex ->
                    if (ex != null) return@addSnapshotListener
                    snapshots?.documentChanges?.forEach { change ->
                        val fbCity = change.document.toObject(FBCity::class.java)
                        if (change.type == DocumentChange.Type.ADDED) {
                            listener?.onCityAdded(fbCity)
                        } else if (change.type == DocumentChange.Type.MODIFIED) {
                            listener?.onCityUpdated(fbCity)
                        } else if (change.type == DocumentChange.Type.REMOVED) {
                            listener?.onCityRemoved(fbCity)
                        }
                    }
                }
        }
    }

    fun setListener(listener: Listener? = null) {
        this.listener = listener
    }

    fun register(user: FBUser) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).set(user)
            .addOnFailureListener {
                Log.e("FBDatabase", "Failed to register user", it)
            }
    }

    fun add(city: FBCity) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val cityName = city.name?.trim()
        if (cityName.isNullOrEmpty())
            throw RuntimeException("City with null or empty name!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("cities")
            .document(cityName).set(city)
            .addOnFailureListener {
                Log.e("FBDatabase", "Failed to add city '$cityName'", it)
            }
    }

    fun remove(city: FBCity) {
        if (auth.currentUser == null)
            throw RuntimeException("User not logged in!")
        val cityName = city.name?.trim()
        if (cityName.isNullOrEmpty())
            throw RuntimeException("City with null or empty name!")
        val uid = auth.currentUser!!.uid
        db.collection("users").document(uid).collection("cities")
            .document(cityName).delete()
            .addOnFailureListener {
                Log.e("FBDatabase", "Failed to remove city '$cityName'", it)
            }
    }

    fun update(city: FBCity) {
        if (auth.currentUser == null) throw RuntimeException("Not logged in!")
        val uid = auth.currentUser!!.uid
        val changes = mapOf("lat" to city.lat,"lng" to city.lng,
            "monitored" to city.monitored )
        db.collection("users").document(uid)
            .collection("cities").document(city.name!!).update(changes)
    }
}