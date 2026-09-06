package org.unizd.rma.saric.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.unizd.rma.saric.adapters.CreatureAdapter
import org.unizd.rma.saric.databinding.FragmentCreaturesBinding
import org.unizd.rma.saric.network.ApiClient
import org.unizd.rma.saric.utils.PreferencesManager

class CreaturesFragment : Fragment() {

    private var _binding: FragmentCreaturesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CreatureAdapter
    private lateinit var preferencesManager: PreferencesManager

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
        preferencesManager = PreferencesManager(requireContext())

        setupRecyclerView()
        fetchCreatures()
    }

    private fun setupRecyclerView() {
        adapter = CreatureAdapter { creature ->
            creature._id?.let { preferencesManager.saveLastSelectedCreatureId(it) }
            val action = CreaturesFragmentDirections
                .actionCreaturesFragmentToCreatureDetailFragment(creature)
            findNavController().navigate(action)
        }

        binding.rvCreatures.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCreatures.adapter = adapter
    }

    private fun fetchCreatures() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val response = ApiClient.starWarsApi.getCreatures()
                binding.progressBar.visibility = View.GONE

                if (response.isSuccessful && response.body() != null) {
                    val list = response.body()!!
                    adapter.updateData(list)
                } else {
                    binding.tvError.text = "Greška pri dohvatu podataka."
                    binding.tvError.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.tvError.text = "Mrežna greška: ${e.localizedMessage}"
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}