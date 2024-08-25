package Principal;

public class PessoaFisica extends Perfil {

    private long cpf;

    public PessoaFisica(String usuario) {
	super(usuario);
    }

    public long getCpf() {
	return cpf;
    }

    public void setCpf(long cpf) {
	this.cpf = cpf;
    }

}
