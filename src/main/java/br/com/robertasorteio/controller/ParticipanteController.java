package br.com.robertasorteio.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.robertasorteio.model.Participante;
import br.com.robertasorteio.repository.Participantes;
import br.com.robertasorteio.util.jpa.Transactional;
import br.com.robertasorteio.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ParticipanteController implements Serializable {

	private static final long serialVersionUID = 2984747284102625644L;

	@Inject
	private Participantes participantes;
	
	private String codigo = "";

	/**
	 * Ler o arquivo excel e salva os dados do Participante
	 * Upload do arquivo excel preenchido
	 * 
	 * @param event
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	public void fileUpload(FileUploadEvent event) {

		try {
			UploadedFile arquivo = event.getFile();
			XSSFWorkbook workbook = new XSSFWorkbook(arquivo.getInputstream());
			// Seleciona a primeira aba
			XSSFSheet folha = workbook.getSheetAt(0);

			Participante participanteExcel = null;

			for (Row linha : folha) {
				// Ignora a primeira linha
				if (linha.getRowNum() != 0) {
					participanteExcel = new Participante();

					for (Cell coluna : linha) {
						if (!(coluna.getCellType() == Cell.CELL_TYPE_BLANK)) {
							try {
								int index = coluna.getColumnIndex();

								switch (index) {
								 	case 0: 
								 		participanteExcel.setNome(coluna.getStringCellValue());
								 		break;
								 	case 1:
								 		 participanteExcel.setCpf(coluna.getStringCellValue());
								 		break; 
								 	case 2:
								 		 participanteExcel.setEmail(coluna.getStringCellValue());
								 		break; 
								 	case 3:
								 		 participanteExcel.setTelefone(coluna.getStringCellValue());
								 		break; 
								 	default:
								 		String msg = "Dados da coluna " + coluna.getColumnIndex() + " inválido. Erro: ";
								 		throw new IllegalArgumentException(msg + coluna.getErrorCellValue());
								}
							} catch (Exception e) {
								FacesUtil.addErrorMessage("Ocorreu um problema ao processa a sua solicitação. Por favor entre em contato com o nosso suporte.");
							}
						} else {
							participanteExcel = null;
							break;
						}
					}

					if (participanteExcel != null)
						participantes.inserir(participanteExcel);
				}

				if ((participanteExcel == null) && (linha.getRowNum() != 0))
					break;
			}

		} catch (IOException e) {
			FacesUtil.addErrorMessage("Ocorreu um problema ao processa a sua solicitação. Por favor entre em contato com o nosso suporte.");
		}

		System.out.println("Finalizando rotina...");
	}
	
	@Transactional
	public void inscricao(){
		
		Participante participante = participantes.porCodigo(codigo);
		
		if(participante != null){
			if(!participante.isCadastrado()){
				participante.setCadastrado(true);
				participantes.atualizar(participante);
				System.out.println("Participante cadastrado no sorteio.");
				FacesUtil.addInfoMessage("Participante cadastrado no sorteio.");
			}else{
				FacesUtil.addErrorMessage("Participante já cadastrado no sorteio!");
			}
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
