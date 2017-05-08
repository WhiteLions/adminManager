package com.iteso.appsmovil.proyectofinal.pojos;

import com.owlike.genson.annotation.JsonProperty;

/**
 * Created by dideleo on 12/05/2015.
 */
public final class MonthlyIncomeReport {

    private final String[] reportLines;

    public MonthlyIncomeReport(@JsonProperty("reportLines") String[] reportLines) {
        this.reportLines = reportLines;
    }

    public String[] getReportLines() {
        return reportLines;
    }
}
