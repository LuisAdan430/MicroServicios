package com.paymentchain.transaccion.model.enums;
public enum Channel {
    
    // ( Indica el canal por el que se realiza la transacción y debe ser uno de los siguientes valores
    // WEB
    // CAJERO
    // OFICINA
    
    WEB(1,"WEB"),
    CAJERO(2,"CAJERO"),
    OFICINA(3,"OFICINA");
    
    private int numero;
    private String channel;

    Channel(int numero, String channel) {
        this.numero = numero;
        this.channel = channel;
    }
    
    public static String getChannelByNumber(int numero){
        for(Channel lugar: Channel.values()){
            if(lugar.numero == numero){
                return lugar.channel;
            }
        }
          return "Numero Invalido";
    }
 

    
    
}
