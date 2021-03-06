package br.com.alura.technews.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.alura.technews.R
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.extensions.mostraErro
import br.com.alura.technews.ui.fragment.VisualizaNoticiaFragment
import br.com.alura.technews.ui.viewmodel.VisualizaNoticiaViewModel
import kotlinx.android.synthetic.main.activity_visualiza_noticia.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


private const val TITULO_APPBAR = "Notícia"

class VisualizaNoticiaActivity : AppCompatActivity() {

    private val noticiaId: Long by lazy {
        intent.getLongExtra(NOTICIA_ID_CHAVE, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_noticia)
        title = TITULO_APPBAR

        //declaração de transaction
        val transaction = supportFragmentManager.beginTransaction()

        //declaração programada de fragment
        val fragment = VisualizaNoticiaFragment()

        //bundle de dados a ser adicionado como argumento do fragment
        val dados = Bundle()
        dados.putLong(NOTICIA_ID_CHAVE, noticiaId)
        fragment.arguments = dados
        transaction.add(R.id.activity_visualiza_noticia_container,fragment)

        //toda transaction deve possuir um commit para execução
        transaction.commit()
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)

        if(fragment is VisualizaNoticiaFragment){
            fragment.quandoFinalizaTela = { finish() }
            fragment.quandoEditaNoticia = {abreFormularioEdicao()}
        }
    }

    private fun abreFormularioEdicao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticiaId)
        startActivity(intent)
    }

}
