package org.unizd.rma.saric.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.unizd.rma.saric.R
import org.unizd.rma.saric.databinding.FragmentCreatureDetailBinding
import org.unizd.rma.saric.network.ApiClient

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
        val creatureId = args.creatureId
        fetchCreatureDetails(creatureId)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun fetchCreatureDetails(id: String) {
        lifecycleScope.launch {
            try {
                val response = ApiClient.starWarsApi.getCreatureById(id)
                if (response.isSuccessful && response.body() != null) {
                    val creature = response.body()!!

                    binding.tvCreatureNameDetail.text = creature.name
                    binding.tvDescription.text = creature.description ?: "Opis nije dostupan."

                    Glide.with(this@CreatureDetailFragment)
                        .load(creature.image)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(binding.imgCreatureDetail)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}