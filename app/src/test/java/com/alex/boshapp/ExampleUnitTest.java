package com.alex.boshapp;

import com.alex.boshapp.datasource.CSVFile;
import com.alex.boshapp.datasource.City;
import com.alex.boshapp.ui.MainActivityCallback;
import com.alex.boshapp.viewmodel.MainActivityViewModel;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MainActivityCallback callback;
    private MainActivityViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        callback = mock(MainActivityCallback.class);
        viewModel = new MainActivityViewModel(prepareListOfCities(), callback);
    }

    private List<City> prepareListOfCities() {
        InputStream inputStream = getClass().getResourceAsStream("world_cities.csv");
        CSVFile file = new CSVFile();
        return file.read(inputStream);
    }

    @Test
    public void testSearch() throws Exception {
        assertEquals(7204, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("n");
        assertEquals(299, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("ne");
        assertEquals(52, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("a");
        assertEquals(462, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("al");
        assertEquals(108, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("");
        assertEquals(7204, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("asdfg");
        assertEquals(0, viewModel.getPreviousSearch().size());
    }

    @Test
    public void testWrite() throws Exception {
        assertEquals(7204, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("n");
        assertEquals(299, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("ne");
        assertEquals(52, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("a");
        assertEquals(462, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("al");
        assertEquals(108, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("");
        assertEquals(7204, viewModel.getPreviousSearch().size());
        viewModel.setSearchCity("asdfg");
        assertEquals(0, viewModel.getPreviousSearch().size());
    }
}