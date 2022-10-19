package com.example.practica2

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica2.clases.CentroComercial
import com.example.practica2.clases.Tienda
import com.example.practica2.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0
    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var i = Intent(this, TiendasActivity::class.java)

        //Imagenes
        loadImageBonaire()
        loadImageAqua()
        loadImageSaler()
        loadImageArena()

        //Tiendas
        val zara = Tienda("Zara","Tienda de ropa, pertenece a Inditex")
        val game = Tienda("Game","Tienda de videojuegos")
        val snipes = Tienda("Snipes","Tienda de ropa para jovenes")
        val mcdonals = Tienda("McDonal's","Tienda comida rapida")
        val alcampo = Tienda("Alcampo","supermercado, vende de todo")
        val ck = Tienda("Calvin klein","Tienda de ropa")

        //Listas
        val listaBonaire = mutableListOf(zara, alcampo, snipes, mcdonals)
        val listaAqua = mutableListOf(mcdonals,game,zara,snipes)
        val listaSaler = mutableListOf(mcdonals,alcampo,zara,snipes)
        val listaArena = mutableListOf(mcdonals,ck,zara,snipes)

        //Centros comerciales
        val bonaire = CentroComercial(1,"Bonaire","Centro comercial Bonaire, Autovía del Este, Km. 345, 46960, Valencia",listaBonaire.size,listaBonaire)
        val aqua = CentroComercial(2,"Aqua","Carrer de Menorca, 19, 46023 València",listaAqua.size,listaAqua)
        val saler = CentroComercial(3,"El saler","Av. del Professor López Piñero, 16, 46013 València",listaSaler.size,listaSaler)
        val arena = CentroComercial(4,"Arena","C. de Santa Genoveva Torres, 21, 46019 València",listaArena.size,listaArena)

        //Contenido
        binding.content.tvNombreBonaire.text = bonaire.nombre+ "\n" +bonaire.direccion
        binding.content.tvTiendasBonaire.text = "${bonaire.nTiendas} Tiendas"

        binding.content.tvNombreAqua.text = aqua.nombre+ "\n" +aqua.direccion
        binding.content.tvTiendasAqua.text = "${aqua.nTiendas} Tiendas"

        binding.content.tvNombreSaler.text = saler.nombre+ "\n" +saler.direccion
        binding.content.tvTiendasSaler.text = "${saler.nTiendas} Tiendas"

        binding.content.tvNombreArena.text = arena.nombre+ "\n" +arena.direccion
        binding.content.tvTiendasArena.text = "${arena.nTiendas} Tiendas"

        binding.content.cvBonaire.setOnClickListener() {
            i.putExtra("idcc", bonaire.id)
            startActivity(i)
        }

        binding.content.cvAqua.setOnClickListener() {
            i.putExtra("idcc",aqua.id)
            startActivity(i)
        }

        binding.content.cvSaler.setOnClickListener() {
            i.putExtra("idcc",saler.id)
            startActivity(i)
        }

        binding.content.cvArena.setOnClickListener() {
            i.putExtra("idcc",arena.id)
            startActivity(i)
        }

        Log.i("lifeCycle", "onCreate")
    }

    private fun loadImageBonaire(url: String = "https://www.guiagps.com/wp-content/uploads/2020/06/tiendas-moda.jpg") {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgBonaire)
    }

    private fun loadImageAqua(url: String = "https://imgs-akamai.mnstatic.com/8d/7d/8d7d841f48b7dd908d81cdf8d8e4c525.jpg") {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgAqua)
    }

    private fun loadImageSaler(url: String = "https://agendadeisa.com/wp-content/uploads/2019/06/CC-SALER-112-1024x682.jpg") {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgSaler)
    }

    private fun loadImageArena(url: String = "https://static1.lasprovincias.es/www/multimedia/201710/05/media/cortadas/centro-comercial-kpQB-U40953887441MwD-624x385@Las%20Provincias.jpg") {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgArena)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.faliyo)

        Log.i("lifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()

        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()

        Log.i("lifeCycle", "onResum")
    }

    override fun onPause() {
        super.onPause()

        mediaPlayer?.pause()
        if (mediaPlayer != null) {
            position = mediaPlayer!!.currentPosition
        }

        Log.i("lifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        // liberamos el recurso
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

        Log.i("lifeCycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("lifeCycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("lifeCycle", "onDestroy")
    }

}