package com.oliver;

import com.oliver.controller.PeliculasController;
import com.oliver.dto.PeliculaDTO;
import com.oliver.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeliculasController.class)
public class PeliculasControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Service service;

	private PeliculaDTO peliculaDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		peliculaDTO = new PeliculaDTO();
		peliculaDTO.setId(1);
		peliculaDTO.setTitle("Test Movie");
		// set other fields as necessary
	}

	@Test
	void testGetPeliculas() throws Exception {
		when(service.getAllProducts()).thenReturn(Arrays.asList(peliculaDTO));

		mockMvc.perform(get("/products")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(peliculaDTO.getId()))
				.andExpect(jsonPath("$[0].title").value(peliculaDTO.getTitle()));
	}

	@Test
	void testGuardarPelicula() throws Exception {
		doNothing().when(service).createProduct(any(PeliculaDTO.class));

		mockMvc.perform(post("/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1, \"title\": \"Test Movie\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void testModificarPelicula() throws Exception {
		when(service.updateProduct(eq(1), any(PeliculaDTO.class))).thenReturn(peliculaDTO);

		mockMvc.perform(put("/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\": 1, \"title\": \"Test Movie\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(peliculaDTO.getId()))
				.andExpect(jsonPath("$.title").value("Test Movie"));
	}

	@Test
	void testEliminarPelicula() throws Exception {
		doNothing().when(service).deleteProduct(1);

		mockMvc.perform(delete("/1"))
				.andExpect(status().isOk());
	}
}
