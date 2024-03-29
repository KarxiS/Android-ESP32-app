package com.example.dummy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dummy.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val meteoViewModel by activityViewModels<MeteoViewModel>()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * nastavenie bindingu, zaklad
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    /**
     * pri vytvoreni pohladu prepojim observera na livedata z databazy a updatujem UI podla selectov
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meteoViewModel.meteoDataAll.observe(viewLifecycleOwner,
            { meteoDataAll ->
                binding.outputVlhkost.text = meteoViewModel.meteoLiveHumidity.value.toString();
                binding.outputAktualnaTeplota.text = meteoViewModel.meteoLiveTemp.value.toString();
                binding.outputNajnizsiaTeplota.text = meteoViewModel.meteoLowestTemp.value.toString();
                binding.outputNajvyssiaTeplota.text = meteoViewModel.meteoHighestTemp.value.toString();
                binding.outputPriemernaTeplota.text = meteoViewModel.meteoAvgTemp.value.toString();

            })



    }

    /**
     * binding na null, memory leaky fix
     *
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}