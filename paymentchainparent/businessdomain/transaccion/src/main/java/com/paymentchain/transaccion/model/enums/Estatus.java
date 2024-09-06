package com.paymentchain.transaccion.model.enums;


public enum Estatus {
    PENDIENTE(1, "Pendiente"),
    LIQUIDADA(2, "Liquidada"),
    RECHAZADA(3, "Rechazada"),
    CANCELADA(4, "Cancelada");
    
    private int numero;
    private String estatus ;

    Estatus(int numero, String estatus) {
        this.numero = numero;
        this.estatus = estatus;
    }
    
    public static String getStatusByNumber(int numero){
        for(Estatus status: Estatus.values()){
            if(status.numero == numero){
                return status.estatus;
            }
        }
        return "Numero Invalido";
    }
}
