package model;

public class BeanUserFone {
  private String nome;
  private String numero;
  private String modelo;
  private Long id;
  private String email;
  
  
  
  
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "BeanUserFone [nome=" + nome + ", numero=" + numero + ", modelo=" + modelo + ", id=" + id + ", email="
			+ email + "]";
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public String getModelo() {
	return modelo;
}
public void setModelo(String modelo) {
	this.modelo = modelo;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
  
  
}
