package com.senatic.votesys.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.senatic.votesys.exception.CsvParsingException;
import com.senatic.votesys.model.dto.AprendizPOJO;

public class FileHandler {

	public static List<AprendizPOJO> csvToList(MultipartFile csvFile) throws CsvParsingException {
		try {
			File newCsv = new File("src/main/java/com/senatic/votesys/utils/" + csvFile.getOriginalFilename());
			newCsv.createNewFile();
			csvFile.transferTo(newCsv);
			MappingIterator<AprendizPOJO> aprendizDtoIter = new CsvMapper()
					.readerWithTypedSchemaFor(AprendizPOJO.class)
					.readValues(newCsv);
			return aprendizDtoIter.readAll();
		} catch (IllegalStateException | IOException e) {
			System.out.println(e.getMessage());
			throw new CsvParsingException("hubo un error al intentar enviar su archivo csv");
		}
	}
	
	public static boolean isCsv(MultipartFile mpf) {
		if(mpf.getOriginalFilename().endsWith(".csv")) {
			return true;
		}
		return false;
	}
	
	/*

	// Trying something different
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "ficha", "programa", "tipoDocumento", "numeroDocumento", "nombre", "apellido",
			"celular", "correoElectronico", "estado" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<AprendizPOJO> csvToAprendizPOJOs(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<AprendizPOJO> aprendicesPOJO = new ArrayList<AprendizPOJO>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				AprendizPOJO aprendizPOJO = new AprendizPOJO(
						csvRecord.get("ficha"),
						csvRecord.get("programa"),
						csvRecord.get("tipoDocumento"),
						csvRecord.get("numeroDocumento"),
						csvRecord.get("nombre"),
						csvRecord.get("apellido"),
						csvRecord.get("celular"),
						csvRecord.get("correoElectronico"),
						csvRecord.get("estado"));
				aprendicesPOJO.add(aprendizPOJO);
			}
			return aprendicesPOJO;
		} catch (IOException e) {
			return new ArrayList<AprendizPOJO>();
			// throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
	*/

	// Trying another thing
	public static List<AprendizPOJO> csvToAprendizPOJOs(MultipartFile multipart) {

		BufferedReader br;
		List<AprendizPOJO> aprendicesPOJOs = new ArrayList<>();
		try {
			String line;
			InputStream is = multipart.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				//Manipulate the line
				String[] fields = line.split(",");
				AprendizPOJO aprendizPOJO = new AprendizPOJO(
					fields[0],
					fields[1],
					fields[2],
					fields[3],
					fields[4],
					fields[5],
					fields[6],
					fields[7],
					fields[8]
				);
				aprendicesPOJOs.add(aprendizPOJO);
				}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return aprendicesPOJOs;
	}

}
