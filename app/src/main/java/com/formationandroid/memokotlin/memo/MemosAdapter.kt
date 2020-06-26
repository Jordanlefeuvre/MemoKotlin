package com.formationandroid.memokotlin.memo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.memokotlin.activity.DetailActivity
import com.formationandroid.memokotlin.bdd.MemoDTO
import com.formationandroid.memokotlin.fragments.DetailFragment
import com.formationandroid.memokotlin.R
import com.formationandroid.memokotlin.bdd.AppDatabaseHelper
import com.formationandroid.memokotlin.memo.MemosAdapter.MemosViewHolder
import java.util.*

class MemosAdapter(listeMemoDTOS: MutableList<MemoDTO>, appCompatActivity: AppCompatActivity) : RecyclerView.Adapter<MemosViewHolder?>() {
    private var listeMemoDTOS: MutableList<MemoDTO?> = listeMemoDTOS.toMutableList()
    private var activity: AppCompatActivity? = null

    init {
        this.activity = appCompatActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemosViewHolder {
        val viewMemo: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.memo_item_liste, parent, false)
        return MemosViewHolder(viewMemo)
    }

    override fun onBindViewHolder(holder: MemosViewHolder, position: Int) {
        holder.textViewLibelleMemo.setText(listeMemoDTOS[position]?.intitule)
    }

    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(listeMemoDTOS, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }

    fun onItemDismiss(view: RecyclerView.ViewHolder) {
        if (view.adapterPosition > -1) {
            AppDatabaseHelper.getDatabase(view.itemView.context).MemosDAO()?.delete(listeMemoDTOS[view.adapterPosition])
            listeMemoDTOS.removeAt(view.adapterPosition)
            notifyItemRemoved(view.adapterPosition)
        }
    }

    inner class MemosViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textViewLibelleMemo: TextView

        init {
            textViewLibelleMemo = itemView.findViewById(R.id.libelle_memo)
            textViewLibelleMemo.setOnClickListener { view ->
                val memoDTO: MemoDTO? = listeMemoDTOS[getAdapterPosition()]

                if (activity!!.findViewById<View?>(R.id.conteneur_detail) == null) {
                    val intent = Intent(view.context, DetailActivity::class.java)
                    intent.putExtra("memo", memoDTO?.intitule)
                    view.context.startActivity(intent)
                } else {
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putString("memo", memoDTO?.intitule)
                    fragment.setArguments(bundle)
                    val fragmentManager =
                        activity!!.supportFragmentManager
                    val fragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.conteneur_detail, fragment, "detail")
                    fragmentTransaction.commit()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listeMemoDTOS.size
    }
}
