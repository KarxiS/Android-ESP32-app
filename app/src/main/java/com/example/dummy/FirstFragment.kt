package com.example.dummy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dummy.databinding.FragmentFirstBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException
import java.io.InputStream
import java.net.SocketTimeoutException
import java.net.URL
import java.net.URLConnection

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val meteoViewModel by activityViewModels<MeteoViewModel>()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}