package br.com.robertasorteio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.robertasorteio.model.Participante;
import br.com.robertasorteio.repository.Participantes;
import br.com.robertasorteio.util.jsf.FacesProducer;
import br.com.robertasorteio.util.jsf.FacesUtil;

/**
 * @author gilsonsilvati@gmail.com (Gilson Silva)
 *
 * 25 de out de 2017
 */
@Named
@ViewScoped
public class ParticipanteController implements Serializable {

  private static final long serialVersionUID = 1L;

  private FacesProducer facesProducer;
  
  @Inject
  private Participantes participantes;

  private StreamedContent file;

  /**
   * Download do arquivo excel para ser preenchido
   */
  public void fileDownload() {
    InputStream stream = facesProducer.getExternalContext()
        .getResourceAsStream("/resources/doc/participantes.xlsx");

    file = new DefaultStreamedContent(stream,
        "application/vnd.ms-excel; application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        "downloaded_participantes.xlsx");
  }

  /**
   * Ler o arquivo excel e salva os dados do Participante
   * Upload do arquivo excel preenchido
   * 
   * @param event
   */
  @SuppressWarnings({ "unchecked", "resource", "deprecation" })
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
                /*case 0:
                  participanteExcel.setNumero(coluna.getStringCellValue());
                  break;
                case 1:
                  participanteExcel.setProduto(coluna.getStringCellValue());
                  break;
                case 2:
                  participanteExcel.setDataPedido(coluna.getDateCellValue());
                  break;
                case 3:
                  participanteExcel.setDataValidade(coluna.getDateCellValue());
                  break;
                case 4:
                  participanteExcel.setNomeCliente(coluna.getStringCellValue());
                  break;
                case 5:
                  participanteExcel.setCpfCnpj(coluna.getStringCellValue());
                  break;
                case 6:
                  participanteExcel.setTelefone(Long.parseLong(coluna.getStringCellValue()));
                  break;
                case 7:
                  participanteExcel.setEmail(coluna.getStringCellValue());
                  break;*/
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
            participantes.cadastrar(participanteExcel);
        }

        if ((participanteExcel == null) && (linha.getRowNum() != 0))
          break;
      }

    } catch (IOException e) {
      FacesUtil.addErrorMessage("Ocorreu um problema ao processa a sua solicitação. Por favor entre em contato com o nosso suporte.");
    }
    
  }

  public StreamedContent getFile() {
    return file;
  }

}
