package org.unizd.rma.saric.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.unizd.rma.saric.databinding.ItemCreatureBinding
import org.unizd.rma.saric.model.Creature

class CreatureAdapter(
    private var creatureList: List<Creature> = emptyList(),
    private val onCreatureClick: (Creature) -> Unit
) : RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatureViewHolder {
        val binding = ItemCreatureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CreatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreatureViewHolder, position: Int) {
        holder.bind(creatureList[position])
    }

    override fun getItemCount(): Int = creatureList.size

    fun updateData(newList: List<Creature>) {
        creatureList = newList
        notifyDataSetChanged()
    }

    inner class CreatureViewHolder(
        private val binding: ItemCreatureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(creature: Creature) {
            // Prikaz 1 do 2 atributa za osnovni pregled (Slika + Ime)
            binding.tvCreatureName.text = creature.name

            Glide.with(binding.ivCreatureImage.context)
                .load(creature.image)
                .into(binding.ivCreatureImage)

            binding.root.setOnClickListener {
                onCreatureClick(creature)
            }
        }
    }
}