package adapter

import Interface.CardListener
import Model.Kendaraan
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appparkir.R
import com.example.appparkir.databinding.ListCardBinding

class ListKendaraanRVAdapter(val listUser: ArrayList<Kendaraan>, val cardListener: CardListener):
    RecyclerView.Adapter<ListKendaraanRVAdapter.viewHolder>() {

    class viewHolder(itemView: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemView) {

        //bind
        val binding = ListCardBinding.bind(itemView)
        fun setData(data:Kendaraan){
            binding.nopolTextViewListCard.text= data.nopol
            binding.namaTextViewListCard.text= data.namapemilik



            if(!data.imageUri!!.isEmpty()){
                binding.imagePosterListCardLayout.setImageURI(Uri.parse(data.imageUri))
            }
            itemView.setOnClickListener{
                cardListener1.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKendaraanRVAdapter.viewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_card, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: ListKendaraanRVAdapter.viewHolder, position: Int) {
        holder.setData(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}