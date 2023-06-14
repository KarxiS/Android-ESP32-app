package com.example.dummy

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dummy.databinding.FragmentSecondBinding
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val meteoViewModel by activityViewModels<MeteoViewModel>()
    private lateinit var chart: LineChart;
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)





        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart = view.findViewById(R.id.chart) as LineChart

        val chartCubic = ChartCubicMoj(chart)
        chartCubic.applyStyling()
        meteoViewModel.meteoDataAll.observe(viewLifecycleOwner,
            { meteoDataAll ->
                val entries = meteoDataAll.map { meteoZaznam ->
                    Entry(meteoZaznam.time.toFloat(), meteoZaznam.temperature.toFloat())
                }

                chartCubic.nastavData(entries)

            })





        binding.buttonReset.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                meteoViewModel.db.meteoStanicaDao().deleteAll()
            }
        }
        binding.buttonSave.setOnClickListener{
            this.uloz("Graf")
        }
    }

    fun uloz( name:String ){



        if (chart.saveToGallery(name + "_" + System.currentTimeMillis(), 70)) Toast.makeText(
            requireContext(), "Ulozene!",
            Toast.LENGTH_SHORT
        ).show() else Toast.makeText(requireContext(), "Chyba! Povolte pristup k ulozisku!", Toast.LENGTH_SHORT)
            .show()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}