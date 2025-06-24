import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nomeBanco;
    private List<Cliente> clientes;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nomeBanco = nome;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public String getNomeBanco() {
        return this.nomeBanco;
    }

    public void adicionarCliente(String nome, String cpf) {
        Cliente novoCliente = new Cliente(nome, cpf);

        this.clientes.add(novoCliente);

        System.out.println("Cliente " + nome + " cadastrado com sucesso!");
    }

    public void abrirConta(Cliente cliente, int numeroConta, double saldoInicial) {
        Conta novaConta = new Conta(numeroConta, saldoInicial, cliente);
        this.contas.add(novaConta);
        System.out.println("Conta " + numeroConta + "aberta para " + cliente.getNome());
    }

    public void listarClientes() {
        System.out.println("------ Clientes do " + this.nomeBanco + " ------");
        for(Cliente cliente : this.clientes) {
            System.out.println("Cliente: " + cliente.getNome() + " | CPF: " + cliente.getCpf());
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for(Cliente cliente : this.clientes) {
            if(cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }

        return null;
    }

    public Conta buscarContaPorNumero(int numeroConta) {
        for(Conta conta : this.contas) {
            if(conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
}

class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.setNome(nome);
        this.setCpf(cpf);
    }

    public void setNome(String nome) {
        if(nome != null) {
            this.nome = nome;
        } else {
            System.out.println("Erro: O nome está vazio!");
        }
    }

    public void setCpf(String cpf) {
        if(cpf != null && cpf.matches("\\d{11}")) {
            this.cpf = cpf;
        } else {
            System.out.println("Erro: CPF Inválido, deve conter 11 dígitos");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }
}

class Conta {
    private double saldoCliente;
    private int numeroConta;
    private Cliente cliente;

    public Conta(int numeroConta, double saldoInicial, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.saldoCliente = saldoInicial;
        this.cliente = cliente;
    }

    public Conta(Cliente cliente) {
        this(-1, 0.0, cliente);
    }

    public void setNumeroConta(int nc) {
        numeroConta = nc;
    }

    public double getSaldoCliente() {
        return saldoCliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    //método depositar
    public boolean depositar(double valor) {
        if(valor > 0) {
            this.saldoCliente += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
            return true;
        } else {
            System.out.println("Valor de depósito inválido. Forneça um valor positivo.");
            return false;
        }
    }

    //método sacar
   public boolean sacar(double valor) {
        if(valor <= 0) {
            System.out.println("Saque insuficiente!");
            return false;
        }

        if(this.saldoCliente >= valor) {
            this.saldoCliente -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso na conta " + this.numeroConta);
            return true;
        } else {
            System.out.println("Saldo insuficiente. Saldo atual: R$" + this.saldoCliente);
            return false;
        }
   }

    //método olhar saldo
    public void consultarSaldo() {
        System.out.println("O saldo da sua conta " + this.numeroConta + " é: R$" + this.saldoCliente);
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("------- BEM VINDO AO E-BANK -------");

        Banco banco = new Banco("E-Bank");
        System.out.println("Banco " + banco.getNomeBanco() + " criado com sucesso!");

        //Adicionando clientes no banco
        System.out.println("------- Adicionando Clientes -------\n");
        banco.adicionarCliente("Alessando Garcia", "12345678910");
        banco.adicionarCliente("Ana Paula", "89025894364");

        //Tentativa de adicionar um cliente com CPF Inválido (Menos de 11 dígitos)
        banco.adicionarCliente("Carla Dias", "854960");

        //Listar Clientes Cadastrados
        System.out.println("\n------- Clientes Cadastrados -------\n");
        banco.listarClientes();

        //Abrindo Conta Para Os Clientes
        System.out.println("\n------- Abrir Contas -------\n");
        //Verrificar se o cliente está cadastrado pelo CPF
        Cliente clienteAlessando = banco.buscarClientePorCpf("12345678910");
        if(clienteAlessando != null) {
            banco.abrirConta(clienteAlessando, 100, 500.0);
        }

        Cliente clienteAna = banco.buscarClientePorCpf("89025894364");
        if(clienteAna != null) {
            banco.abrirConta(clienteAlessando, 101, 1540.0);
        }

        //Realizando operações nas contas
        System.out.println("\n------- Realizar Transações -------\n");
        //Buscando a conta do cliente pelo número
        Conta contaAlessando = banco.buscarContaPorNumero(100);
        if(contaAlessando != null) {
            contaAlessando.consultarSaldo();
            contaAlessando.depositar(250.0);
            contaAlessando.sacar(100.0);
            contaAlessando.consultarSaldo();
        }

        Conta contaAna = banco.buscarContaPorNumero(101);
        if(contaAna != null) {
            contaAna.consultarSaldo();
            contaAna.depositar(200.0);
            contaAna.sacar(2000.0);
            contaAna.consultarSaldo();
        }
    }

}