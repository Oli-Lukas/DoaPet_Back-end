/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.example.doapet.model;

/**
 *
 * @author euluc
 */
public enum StatusSolicitacao {
    
    PENDENTE("PENDENTE"),
    APROVADO("APROVADO"),
    REJEITADO("REJEITADO");

    private String status;

    private StatusSolicitacao(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
