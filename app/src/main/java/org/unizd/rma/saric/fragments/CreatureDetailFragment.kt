package org.unizd.rma.saric.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.unizd.rma.saric.R
import org.unizd.rma.saric.databinding.FragmentCreatureDetailBinding

class CreatureDetailFragment : Fragment() {

    private var _binding: FragmentCreatureDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CreatureDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatureDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val creature = args.creature

        binding.tvCreatureNameDetail.text = creature.name
        binding.tvDescription.text = creature.description ?: "Opis nije dostupan."
        binding.tvHomeworld.text = creature.homeworld ?: "Nepoznato"
        binding.tvSpecies.text = creature.species ?: "Nepoznato"
        binding.tvHeight.text = creature.height ?: "Nepoznato"

        Glide.with(this)
            .load(creature.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.imgCreatureDetail)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}