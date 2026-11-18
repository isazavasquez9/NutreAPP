package com.example.NutreAPP;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.NutreAPP.models.Categoria;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.models.Visualizacion;
import com.example.NutreAPP.repositories.IVideosRepositorio;
import com.example.NutreAPP.repositories.IVisualizacionRepositorio;

@SpringBootTest
@Transactional
class NutreAppApplicationTests {

	@Autowired
	private IVideosRepositorio videoRepositorio;

	@Autowired
	private IVisualizacionRepositorio visualizacionRepositorio;

	@Test
	void contextLoads() {
	}

	@Test
	void searchesVideosByCategoryTitleDescriptionUrlAndCombinedFields() {
		videoRepositorio.saveAll(List.of(
				new Video(null, "Rutina diaria", "Habitos para mejorar el bienestar", "https://example.test/video-1",
						Categoria.TALENTO),
				new Video(null, "rutina diaria", "Plan de ejercicios", "https://example.test/video-2", Categoria.SST),
				new Video(null, "Pausa activa", "Otros habitos de descanso", "https://example.test/video-3",
						Categoria.TALENTO)));

		assertEquals(2, videoRepositorio.findByCategoria(Categoria.TALENTO).size());
		assertEquals(2, videoRepositorio.findByTituloIgnoreCase("RUTINA DIARIA").size());
		assertEquals(2, videoRepositorio.findByDescripcionContainingIgnoreCase("HABITOS").size());
		assertEquals(1, videoRepositorio.findByUrl("https://example.test/video-1").size());
		assertEquals(1,
				videoRepositorio.findByTituloIgnoreCaseAndCategoria("rutina diaria", Categoria.TALENTO).size());
	}

	@Test
	void searchesVisualizationsByDateAndCompletion() {
		LocalDate today = LocalDate.now();
		visualizacionRepositorio.saveAll(List.of(
				new Visualizacion(null, today, false),
				new Visualizacion(null, today, true),
				new Visualizacion(null, today.minusDays(1), false)));

		assertEquals(2, visualizacionRepositorio.findByFechaVisualizacion(today).size());
		assertEquals(2, visualizacionRepositorio.findByCompletadoFalse().size());
		assertEquals(1, visualizacionRepositorio.findByCompletadoTrue().size());
	}
}
