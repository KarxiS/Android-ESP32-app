package com.example.dummy;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

class ChartCubicMoj{
    private LineChart chart;
    private Typeface tfRegular;
    private Typeface tfLight;

    public ChartCubicMoj(LineChart chart) {
        this.chart = chart;
    }

    /**
     * tu nastavujem vzhlad -podla githubu autora tohto grafu
     */
    public void aplikujStyl() {

        chart.setViewPortOffsets(0, 0, 0, 0);
        chart.setBackgroundColor(Color.rgb(104, 241, 175));
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);


        chart.setPinchZoom(true);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(10);
        XAxis x = chart.getXAxis();
        x.setEnabled(true);
        YAxis y = chart.getAxisLeft();
        y.setTypeface(tfLight);
        y.setLabelCount(6, true);
        y.setTextColor(Color.BLACK);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
        chart.getAxisRight().setEnabled(false);

    }

    /**
     * nastavujem kolekciu dat do grafu a natavim im dodatocnu stylistiku a formatovanie
     * @param entries
     */
    public void nastavData(List<Entry> entries) {
        LineDataSet dataSet = new LineDataSet(entries, "Teplota");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setCubicIntensity(0.2f);
        dataSet.setDrawFilled(true);
        dataSet.setDrawCircles(true);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet.setColor(Color.BLACK);
        dataSet.setFillColor(Color.WHITE);
        dataSet.setFillAlpha(100);
        dataSet.setDrawHorizontalHighlightIndicator(false);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }
}