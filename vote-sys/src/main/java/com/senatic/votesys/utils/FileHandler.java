package com.senatic.votesys.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.senatic.votesys.controller.AprendicesController;
import com.senatic.votesys.exception.CsvParsingException;
import com.senatic.votesys.model.Aprendiz;
import com.senatic.votesys.model.dto.AprendizDTO;

public class FileHandler {
	
	public static List<AprendizDTO> csvToList(MultipartFile csvFile) throws CsvParsingException{
		try {
			File newCsv = new File(csvFile.getOriginalFilename());
			csvFile.transferTo(newCsv);
			MappingIterator<AprendizDTO> aprendizDtoIter = new CsvMapper()
					.readerWithTypedSchemaFor(AprendizDTO.class)
					.readValues(newCsv);
			return aprendizDtoIter.readAll();
		} catch (IllegalStateException | IOException e) {
			throw new CsvParsingException("hubo un error al intentar enviar su archivo csv");
		}
	}
	
	
	public static List<Aprendiz> getAprendicesList(MultipartFile mpf) throws CsvParsingException{
		csvToList(mpf);
		return null;
	}
}
