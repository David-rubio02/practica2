package com.example.practica2.clases

data class CentroComercial(val id: Int, var nombre: String, var direccion: String, var nTiendas: Int, var listaTiendas: MutableList<Tienda>){

}
