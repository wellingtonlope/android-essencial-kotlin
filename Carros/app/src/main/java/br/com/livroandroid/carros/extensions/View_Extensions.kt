package br.com.livroandroid.carros.extensions

// Utiliza onClick ao invés do setOnClickListener
fun android.view.View.onClick(l: (v: android.view.View?) -> Unit) {
    setOnClickListener(l)
}