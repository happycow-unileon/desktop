package es.unileon.happycow.procedures;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import java.io.File;
import jxl.Workbook;
import jxl.write.WritableWorkbook;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.evaluation.DefaultEvaluationModel;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.CellView;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

/**
 * Class for generate an excel
 * @author amdiaz8
 */
public class Excel {
    /**
     * Excel
     */
    private WritableWorkbook workbook;
    /**
     * actual hoja de excel
     */
    private WritableSheet actualSheet;

    /**
     * Formato de celda rojo
     */
    private WritableCellFormat redBackground;
    /**
     * Formato de celda azul
     */
    private WritableCellFormat blueBackground;
    /**
     * Formato de celda amarillo
     */
    private WritableCellFormat yellowBackground;
    /**
     * Formato de celda dorado
     */
    private WritableCellFormat goldBackground;

    /**
     * Constructor
     * @param file in which we write 
     */
    public Excel(File file) {
        try {
            workbook = Workbook.createWorkbook(file);
            createColors();
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create the colors for the cels
     */
    private void createColors() {
        redBackground = new WritableCellFormat();
        blueBackground = new WritableCellFormat();
        yellowBackground = new WritableCellFormat();
        goldBackground = new WritableCellFormat();

        try {
            redBackground.setBackground(Colour.RED);
            blueBackground.setBackground(Colour.PERIWINKLE);
            yellowBackground.setBackground(Colour.YELLOW);
            goldBackground.setBackground(Colour.GOLD);
        } catch (WriteException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Export the data of the given users with their farms
     * @param list
     * @return 
     */
    public boolean exportDataUsers(LinkedHashMap<User, LinkedList<Farm>> list) {
        int numberUser = 0;
        int row = 1;
        try {
            //for every user...
            for (User user : list.keySet()) {
                //create a sheet
                actualSheet = workbook.createSheet(user.getName(), numberUser);
                //write the header of the sheet
                writeHeader();
                //export the data of the farms
                row = exportDataFarm(row, list.get(user));
                if (row < 0) {
                    return false;
                }
                numberUser++;
                row++;
                //recalculate size of cells
                resizeCells();
            }
        } catch (ExcelException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //get the last cell and write color
            WritableCell c = actualSheet.getWritableCell(0, 18);
            WritableCellFormat newFormat = new WritableCellFormat();
            try {
                newFormat.setBackground(Colour.BLUE_GREY);
            } catch (WriteException ex) {
                System.out.println(ex.toString());
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.setCellFormat(newFormat);

            workbook.write();
            workbook.close();
        } catch (IOException | WriteException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Resize cell
     */
    private void resizeCells() {
        for (int x = 0; x < ColumnExcel.values().length; x++) {
            CellView cell = actualSheet.getColumnView(x);
            cell.setAutosize(true);
            actualSheet.setColumnView(x, cell);
        }
    }

    /**
     * Write the header of the sheet
     * @throws ExcelException 
     */
    private void writeHeader() throws ExcelException {
        for (ColumnExcel columnExcel : ColumnExcel.values()) {

            Label name = new Label(columnExcel.ordinal(), 0,
                    ColumnExcel.getName(columnExcel), redBackground);
            try {
                actualSheet.addCell(name);
            } catch (WriteException ex) {
                throw new ExcelException("Error escribiendo la granja");
            }
        }
    }

    /**
     * Paint the cells given
     * @param row
     * @param init
     * @param end
     * @param format 
     */
    private void paintCells(int row, int init, int end, WritableCellFormat format) {
        for (int i = init; i < end; i++) {
            WritableCell c = actualSheet.getWritableCell(i, row);
            CellFormat oldFormat = c.getCellFormat();
            if (oldFormat != null) {
                WritableCellFormat newFormat = new WritableCellFormat(oldFormat);
                try {
                    newFormat.setBackground(format.getBackgroundColour());
                } catch (WriteException ex) {
                    Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.setCellFormat(newFormat);
            } else {
                WritableCellFormat newFormat = new WritableCellFormat();
                try {
                    newFormat.setBackground(format.getBackgroundColour());
                } catch (WriteException ex) {
                    System.out.println(ex.toString());
                    Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.setCellFormat(newFormat);
            }
        }
    }

    /**
     * Export the data of the farms
     * @param row
     * @param list
     * @return
     * @throws ExcelException 
     */
    private int exportDataFarm(int row, LinkedList<Farm> list) throws ExcelException {
        for (Farm farm : list) {
            //for every farm, write the farm
            row = writeFarm(row, farm);
            row++;
        }
        return row;
    }

    /**
     * Write the data of a farm
     * @param row
     * @param farm
     * @return
     * @throws ExcelException 
     */
    private int writeFarm(int row, Farm farm) throws ExcelException {

        Label name = new Label(ColumnExcel.FARM_NAME.ordinal(), row,
                farm.getFarmName(), yellowBackground);
        try {
            actualSheet.addCell(name);
        } catch (WriteException ex) {
            throw new ExcelException("Error escribiendo la granja");
        }

        paintCells(row, ColumnExcel.FARM_NAME.ordinal(), ColumnExcel.values().length, yellowBackground);
        row++;
        //for every evaluation it has
        for (InformationEvaluation interfaceEvaluationModel : Database.getInstance().getListEvaluations(farm.getIdFarm())) {
            //write the evaluation
            row = writeEvaluation(row, new DefaultEvaluationModel(
                    Database.getInstance().getEvaluation(interfaceEvaluationModel.getIdEvaluation())));
            row++;
        }

        return row;
    }

    /**
     * Write the given evaluation
     * @param row
     * @param evaluation
     * @return
     * @throws ExcelException 
     */
    private int writeEvaluation(int row, IEvaluationModel evaluation) throws ExcelException {
        jxl.write.Number number = new jxl.write.Number(ColumnExcel.COW_NUMBER.ordinal(),
                row, evaluation.getInformation().getNumberCows());
        //escribir fecha evaluacion
        DateFormat customDateFormat = new DateFormat("dd MMM yyyy");

        WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
        DateTime dateCell = new DateTime(ColumnExcel.EVALUATION_DATE.ordinal(),
                row, evaluation.getInformation().getFecha(), dateFormat);

        try {
            actualSheet.addCell(number);
            actualSheet.addCell(dateCell);
        } catch (WriteException ex) {
            throw new ExcelException("Error escribiendo la evaluación");
        }

        //coloreo la celda
        paintCells(row, ColumnExcel.EVALUATION_DATE.ordinal(), ColumnExcel.values().length, blueBackground);
        row++;
        for (Category category : Category.values()) {
            row = writeCategory(row, category, evaluation);
        }

        return row;
    }

    /**
     * Write the data under a category
     * @param row
     * @param category
     * @param evaluation
     * @return
     * @throws ExcelException 
     */
    private int writeCategory(int row, Category category, IEvaluationModel evaluation) throws ExcelException {
        //escribir la categoria y su ponderacion
        Label name = new Label(ColumnExcel.CATEGORY.ordinal(), row,
                Category.getName(category));
        jxl.write.Number number = new jxl.write.Number(ColumnExcel.PONDERATION_CATEGORY.ordinal(),
                row, evaluation.getWeighing(new IdCategory(category)));
        try {
            actualSheet.addCell(name);
            actualSheet.addCell(number);
        } catch (WriteException ex) {
            throw new ExcelException("Error escribiendo la categoria");
        }
        paintCells(row, ColumnExcel.CATEGORY.ordinal(), ColumnExcel.values().length, goldBackground);
        LinkedList<Criterion> criterions = evaluation.getListCriterion(category);
        for (Criterion criterion : criterions) {
            row = writeCriterion(row, evaluation, criterion);
        }
        return row;
    }

    /**
     * Write the data under a criterion
     * @param row
     * @param evaluation
     * @param criterion
     * @return
     * @throws ExcelException 
     */
    private int writeCriterion(int row,
            IEvaluationModel evaluation, Criterion criterion) throws ExcelException {
        LinkedList<Valoration> valorations = evaluation.listOfCriterion(criterion.getId());
        //escribir criterio y su ponderacion
        Label name = new Label(ColumnExcel.CRITERION.ordinal(), row,
                criterion.getName());
        jxl.write.Number number = new jxl.write.Number(ColumnExcel.PONDERATION_CRITERION.ordinal(),
                row, criterion.getWeighing());

        try {
            actualSheet.addCell(name);
            actualSheet.addCell(number);
        } catch (WriteException ex) {
            throw new ExcelException("Error escribiendo el criterio");
        }

        int rowBackup = row;
        for (Valoration valoration : valorations) {
            writeValoration(row, valoration);
            row++;
        }
        paintCells(rowBackup, ColumnExcel.CRITERION.ordinal(), ColumnExcel.values().length, blueBackground);
        return row;
    }

    /**
     * write the valoration
     * @param row
     * @param valoration
     * @return
     * @throws ExcelException 
     */
    private int writeValoration(int row, Valoration valoration) throws ExcelException {
        //escribir valoracion
        jxl.write.Number number = new jxl.write.Number(ColumnExcel.VALORATION.ordinal(),
                row, valoration.getNota());

        try {
            actualSheet.addCell(number);
        } catch (WriteException ex) {
            throw new ExcelException("Error escribiendo la valoración");
        }
        return row;
    }

}
