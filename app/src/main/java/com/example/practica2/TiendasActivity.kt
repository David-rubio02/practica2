package com.example.practica2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.clases.CentroComercial
import com.example.practica2.clases.Tienda
import com.example.practica2.databinding.ActivityScrollingBinding
import com.example.practica2.databinding.ActivityTiendasBinding


class TiendasActivity : AppCompatActivity() {

    lateinit var binding: ActivityTiendasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTiendasBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

        var id: Int = 0
        val extras = intent.extras
        if (extras != null) {
            id = extras.getInt("idcc")

            var centro = bonaire
            if (id == 1){
                centro = bonaire
            }
            if (id == 2){
                centro = aqua
            }
            if (id == 3){
                centro = saler
            }
            if (id == 4){
                centro = arena
            }

            for ((index, tienda) in centro.listaTiendas.withIndex()) {
                if (index == 0) {
                    binding.tvNombreTienda1.text = tienda.nombre
                    binding.tvDescripcionTienda1.text = tienda.descripcion
                }
                if (index == 1) {
                    binding.tvNombreTienda2.text = tienda.nombre
                    binding.tvDescripcionTienda2.text = tienda.descripcion
                }
                if (index == 2) {
                    binding.tvNombreTienda3.text = tienda.nombre
                    binding.tvDescripcionTienda3.text = tienda.descripcion
                }
                if (index == 3) {
                    binding.tvNombreTienda4.text = tienda.nombre
                    binding.tvDescripcionTienda4.text = tienda.descripcion
                }
            }
        }
    }
}