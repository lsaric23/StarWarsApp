package org.unizd.rma.saric.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.unizd.rma.saric.adapters.CreatureAdapter
import org.unizd.rma.saric.databinding.FragmentCreaturesBinding
import org.unizd.rma.saric.model.Creature
import org.unizd.rma.saric.network.ApiClient

class CreaturesFragment : Fragment() {

    private var _binding: FragmentCreaturesBinding? = null
    private val binding get() = _binding!!

    private lateinit var creatureAdapter: CreatureAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchCreatures()
    }

    private fun setupRecyclerView() {
        creatureAdapter = CreatureAdapter { selectedCreature ->
            navigateToCreatureDetail(selectedCreature)
        }
        binding.rvCreatures.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = creatureAdapter
        }
    }

    private fun fetchCreatures() {
        showLoading()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.starWarsApi.getCreatures()

                if (response.isSuccessful && response.body() != null) {
                    val creaturesList = response.body()!!
                    displayCreatures(creaturesList)
                } else {
                    showError("Greška: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                showError("Greška pri dohvaćanju podataka: ${e.message}")
            }
        }
    }

    private fun displayCreatures(creatures: List<Creature>) {
        binding.progressBar.visibility = View.GONE
        binding.rvCreatures.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE

        creatureAdapter.updateData(creatures)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvCreatures.visibility = View.GONE
        binding.tvError.visibility = View.GONE
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.rvCreatures.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCreatureDetail(creature: Creature) {
        // Navigacija na detaljni pregled (možete proslijediti cijeli objekt ili id/ime)
        val action = CreaturesFragmentDirections
            .actionCreaturesFragmentToCreatureDetailFragment(creature)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}