package org.unizd.rma.saric.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.unizd.rma.saric.R
import org.unizd.rma.saric.databinding.ItemCreatureBinding
import org.unizd.rma.saric.model.Creature

class CreatureAdapter(
    private var creatureList: List<Creature> = emptyList(),
    private val onCreatureClick: (Creature) -> Unit
) : RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder>() {

    inner class CreatureViewHolder(val binding: ItemCreatureBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureViewHolder {
        val binding = ItemCreatureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CreatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreatureViewHolder, position: Int) {
        val creature = creatureList[position]

        holder.binding.tvCreatureName.text = creature.name
        holder.binding.tvCreatureSpecies.text = "Vrsta: ${creature.species ?: "Nepoznato"}"

        Glide.with(holder.itemView.context)
            .load(creature.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.imgCreature)

        holder.itemView.setOnClickListener {
            onCreatureClick(creature)
        }
    }

    override fun getItemCount(): Int = creatureList.size

    fun updateData(newList: List<Creature>) {
        creatureList = newList
        notifyDataSetChanged()
    }
}