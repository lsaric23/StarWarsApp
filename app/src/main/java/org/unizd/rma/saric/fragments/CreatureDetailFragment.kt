package org.unizd.rma.saric.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.unizd.rma.saric.databinding.FragmentCreatureDetailBinding
import org.unizd.rma.saric.model.Creature

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
        displayCreatureDetails(args.creature)
        setupBackButton()
    }

    private fun displayCreatureDetails(creature: Creature) {
        binding.apply {
            tvName.text = creature.name
            tvDescription.text = creature.description ?: "Nema opisa"
            tvHomeworld.text = "Matični planet: ${creature.homeworld ?: "Nepoznato"}"
            tvSpecies.text = "Vrsta: ${creature.species ?: "Nepoznato"}"
            tvHeight.text = "Visina: ${creature.height ?: "Nepoznato"}"

            Glide.with(requireContext())
                .load(creature.image)
                .into(ivCreatureDetailImage)
        }
    }

    private fun setupBackButton() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}