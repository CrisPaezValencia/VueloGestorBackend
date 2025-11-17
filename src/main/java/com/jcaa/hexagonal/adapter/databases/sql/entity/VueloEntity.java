package com.jcaa.hexagonal.adapter.databases.sql.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vuelo")
public class VueloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaCompra")
    private LocalDate fechaCompra;

    @Column(name = "fechaSalida")
    private LocalDateTime fechaSalida;

    @Column(name = "fechaLlegada")
    private LocalDateTime fechaLlegada;

    @Column(name = "agenciaViajes")
    private String agenciaViajes;

    @Column(name = "aerolinea")
    private String aerolinea;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "clienteId")
    private Long clienteId;

    @Column(name = "puesto")
    private String puesto;

    @Column(name = "avionId")
    private Long avionId;

    @Column(name = "aeropuertoSalidaId")
    private Long aeropuertoSalidaId;

    @Column(name = "aeropuertoLlegadaId")
    private Long aeropuertoLlegadaId;

    @Column(name = "pilotoId")
    private Long pilotoId;

    // getters y setters

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getAgenciaViajes() {
        return agenciaViajes;
    }

    public void setAgenciaViajes(String agenciaViajes) {
        this.agenciaViajes = agenciaViajes;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Long getAvionId() {
        return avionId;
    }

    public void setAvionId(Long avionId) {
        this.avionId = avionId;
    }

    public Long getAeropuertoSalidaId() {
        return aeropuertoSalidaId;
    }

    public void setAeropuertoSalidaId(Long aeropuertoSalidaId) {
        this.aeropuertoSalidaId = aeropuertoSalidaId;
    }

    public Long getAeropuertoLlegadaId() {
        return aeropuertoLlegadaId;
    }

    public void setAeropuertoLlegadaId(Long aeropuertoLlegadaId) {
        this.aeropuertoLlegadaId = aeropuertoLlegadaId;
    }

    public Long getPilotoId() {
        return pilotoId;
    }

    public void setPilotoId(Long pilotoId) {
        this.pilotoId = pilotoId;
    }
}