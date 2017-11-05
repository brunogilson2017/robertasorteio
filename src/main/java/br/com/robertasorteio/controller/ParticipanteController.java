package br.com.robertasorteio.controller;

import java.io.Serializable;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.robertasorteio.model.Participante;
import br.com.robertasorteio.repository.Participantes;
import br.com.robertasorteio.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ParticipanteController implements Serializable {

	private static final long serialVersionUID = 2984747284102625644L;

	@Inject
	private Participantes participantes;
	
	private DataFormatter formatter;
	
	@PostConstruct
	private void init() {
		formatter = new DataFormatter();
	}

	/**
	 * Ler o arquivo excel e salva os dados do Participante
	 * Upload do arquivo excel preenchido
	 * @author Gilson Silva (gilsonsilvati@gmail.com)
	 * @param event
	 * @throws Exception
	 */
	public void fileUpload(FileUploadEvent event) {
		Participante participanteExcel;

		try {
			UploadedFile arquivo = event.getFile();
			XSSFWorkbook workbook = new XSSFWorkbook(arquivo.getInputstream());
			
			// Seleciona a primeira aba
			XSSFSheet folha = workbook.getSheetAt(0);
			
			Iterator<Row> iterator = folha.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				
				// Ignora a primeira linha
				if (nextRow.getRowNum() != 0) {
					participanteExcel = new Participante();
					
					while (cellIterator.hasNext()) {
						Cell nextCell = cellIterator.next();
						int columnIndex = nextCell.getColumnIndex();
						
						switch (columnIndex) {
						case 0: 
							participanteExcel.setNome(nextCell.getStringCellValue());
							break;
						case 1:
							participanteExcel.setEmail(nextCell.getStringCellValue());
							break; 
						case 2:
							participanteExcel.setLogradouro(nextCell.getStringCellValue());
							break; 
						case 3:
							String numero = formatter.formatCellValue(nextCell);
							participanteExcel.setNumero(numero);
							break;
						case 4:
							String complemento = formatter.formatCellValue(nextCell);
							participanteExcel.setComplemento(complemento);
							break;
						case 5:
							participanteExcel.setBairro(nextCell.getStringCellValue());
							break;
						case 6:
							participanteExcel.setCidade(nextCell.getStringCellValue());
							break;
						case 7:
							participanteExcel.setUf(nextCell.getStringCellValue());
							break;
						case 8:
							String cep = formatter.formatCellValue(nextCell);
							participanteExcel.setCep(cep);
							break;
						case 9:
							participanteExcel.setPais(nextCell.getStringCellValue());
							break;
						case 10:
							boolean participando = true;
							
							if (nextCell.getStringCellValue().toUpperCase().startsWith("N"))
								participando = false;
							
							participanteExcel.setParticipandoSorteio(participando);
							break;
						default:
							String msg = "Dados da coluna " + nextCell.getColumnIndex() + " inválido. Erro: ";
							throw new IllegalArgumentException(msg + nextCell.getErrorCellValue());
						}
						
					}
					
					Participante participante = participantes.porEmail(participanteExcel.getEmail());
					
					if (participante == null)
						participantes.inserir(participanteExcel);
					
				}
				
			}

			FacesUtil.addInfoMessage("Participantes cadastrados com sucesso!");
			// Fecha o arquivo excel
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Ocorreu um problema ao processa a sua solicitação. Por favor entre em contato com o nosso suporte.");
		}

	}
	
}
