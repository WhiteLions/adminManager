/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golflozano.entradas;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author dideleo
 */
@ManagedBean
@ViewScoped
public class BriefEntradaView {
    
  
    @EJB
    private NuevaEntradaEJB entradaSvc;
    
    private List<EntradaBean> facturaList;
    private List<EntradaBean> filteredFacturas;
    private List<FacturaDetalleProdBean> detalleList;
    
    
    private EntradaBean selectedFactura;

    
    @PostConstruct
    public void initView(){
        this.facturaList = this.entradaSvc.loadFacturas();
        this.detalleList = new ArrayList<FacturaDetalleProdBean>();
    }

    
    public void onRowSelect(SelectEvent event){
        
        if (this.selectedFactura != null){
            this.detalleList = this.entradaSvc.loadProductsFactura(this.selectedFactura);
        }          
    }

    public List<FacturaDetalleProdBean> getDetalleList() {
        return detalleList;
    }
    
    
    
    public EntradaBean getSelectedFactura() {
        return selectedFactura;
    }

    public void setSelectedFactura(EntradaBean selectedFactura) {
        this.selectedFactura = selectedFactura;
    }
    
   
    public List<EntradaBean> getFacturaList() {
        return facturaList;
    }

    public List<EntradaBean> getFilteredFacturas() {
        return filteredFacturas;
    }

    public void setFilteredFacturas(List<EntradaBean> filteredFacturas) {
        this.filteredFacturas = filteredFacturas;
    }
    
    public boolean filterbyName(Object value, Object filter, Locale locale){
          
        String filterText = (filter == null) ? null : filter.toString().trim();
        
        if(filterText == null || filterText.equals("")){
            return true;
        }
        
        String  temp_value = value.toString().toLowerCase(locale);
        
        return temp_value.contains(filterText);
        
    }
    
}
