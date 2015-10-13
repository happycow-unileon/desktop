package es.unileon.happycow.procedures;

/**
 * Enum for columns on excel
 * @author dorian
 */
public enum ColumnExcel {
    FARM_NAME,
    COW_NUMBER,
    EVALUATION_DATE,
    CATEGORY,
    PONDERATION_CATEGORY,
    CRITERION,
    PONDERATION_CRITERION,
    VALORATION;
    
    public static String getName(ColumnExcel column){
        String result="";
        switch(column){
            case CATEGORY:
                result="Categoría";
                break;
            case COW_NUMBER:
                result="Número de vacas";
                break;
            case CRITERION:
                result="Criterio";
                break;
            case EVALUATION_DATE:
                result="Fecha de evaluación";
                break;
            case FARM_NAME:
                result="Nombre de la granja";
                break;
            case PONDERATION_CATEGORY:
                result="Ponderación de categoría";
                break;
            case VALORATION:
                result="Valoración";
                break;
            case PONDERATION_CRITERION:
                result="Ponderación del criterio";
                break;
        }
        
        return result;
    }

}
