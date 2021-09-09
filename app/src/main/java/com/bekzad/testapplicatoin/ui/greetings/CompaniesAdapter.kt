package com.bekzad.testapplicatoin.ui.greetings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bekzad.testapplicatoin.data.domain.Company
import com.bekzad.testapplicatoin.databinding.CompanyListItemBinding

class CompaniesAdapter : RecyclerView.Adapter<CompaniesAdapter.CompanyViewHolder>()
{
    var data = listOf<Company>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class CompanyViewHolder(private var binding: CompanyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Company) {
            binding.company = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CompanyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CompanyListItemBinding.inflate(layoutInflater,
                    parent, false)
                return CompanyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}