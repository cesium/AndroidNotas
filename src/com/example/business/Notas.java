package com.example.business;

import java.util.ArrayList;

public class Notas {
	private ArrayList<Nota> notas;
	
	public Notas() {
		notas = new ArrayList<Nota>();
	}
	
	public void addNota(Nota n){
		this.notas.add(n);
	}
	
	public void removeNota(Nota n){
		this.notas.remove(n);
	}
	
	public ArrayList<Nota> getNotas() {
		return notas;
	}
}
