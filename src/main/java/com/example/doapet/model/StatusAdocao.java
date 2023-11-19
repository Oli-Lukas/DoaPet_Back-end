/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.example.doapet.model;

/**
 *
 * @author euluc
 */
public enum StatusAdocao {
    
    PENDENTE("PENDENTE"),
    APROVADO("APROVADO");
    
    private String status;

    private StatusAdocao(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
