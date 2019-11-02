package br.com.livroandroid.carros.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.fragments.CarrosFragment

class TabsAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    // Quantidade de Tabs
    override fun getCount(): Int = 3

    // retorna o tipo pela posição
    fun getTipoCarro(position: Int) = when (position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        else -> TipoCarro.luxo
    }

    // Titulo da Tab
    override fun getPageTitle(position: Int): CharSequence? {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    // Fragment que vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments?.putSerializable("tipo", tipo)
        return f
    }
}