package com.awsome.MotoInvEdge.viewmodel;

import com.awsome.MotoInvEdge.model.Motorcycle;

import java.math.BigDecimal;
import java.util.Objects;

public class MotoViewModel {

    private Motorcycle motorcycle;
    private BigDecimal salesTax;
    private BigDecimal documentFee;
    private BigDecimal transportCost;
    private BigDecimal totalCost;

    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getDocumentFee() {
        return documentFee;
    }

    public void setDocumentFee(BigDecimal documentFee) {
        this.documentFee = documentFee;
    }

    public BigDecimal getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoViewModel that = (MotoViewModel) o;
        return Objects.equals(motorcycle, that.motorcycle) &&
                Objects.equals(salesTax, that.salesTax) &&
                Objects.equals(documentFee, that.documentFee) &&
                Objects.equals(transportCost, that.transportCost) &&
                Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motorcycle, salesTax, documentFee, transportCost, totalCost);
    }

    @Override
    public String toString() {
        return "MotoViewModel{" +
                "motorcycle=" + motorcycle +
                ", salesTax=" + salesTax +
                ", documentFee=" + documentFee +
                ", transportCost=" + transportCost +
                ", totalCost=" + totalCost +
                '}';
    }
}
