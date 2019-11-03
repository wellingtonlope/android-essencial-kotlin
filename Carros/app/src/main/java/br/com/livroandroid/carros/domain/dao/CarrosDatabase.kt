package br.com.livroandroid.carros.domain.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.livroandroid.carros.domain.Carro

@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase: RoomDatabase() {
    abstract fun carroDAO(): CarroDAO
}