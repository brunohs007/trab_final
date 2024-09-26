# Twitter MVC
*Essa aplicação é uma mini rede social que executa em console.

# Bibliotecas Java Utilizadas
    java.nio.*;
    java.util,*;
    java.io.*;

# Estrutura
O projeto segue o padrão MVC,uma arquitetura que separa as responsabilidades entre as diferentes camadas do software:

    ## Model: Responsável pela lógica de negócio e interação com o banco de dados.

    ## Controller: Faz a mediação entre a View e o Model, manipulando a lógica de fluxo.

    ## View: Interface com o usuário (console, GUI ou web).
    
# Diretórios
src/
│
├── controller/  
        MyTwitterController.java
├── model/ 
        Login.java
        Perfil.java
        Repósitório.java
        Tweet.java
├── view/
        TwitterView.java

# Lista de funcionalidades implementadas
    # Tela Inicial de Boas Vindas
        1. Login
        2. Criar Perfil

        # Criar Perfil
        *Cria um novo usuário, e armazena seus dados de Login no arquivo "login.txt", e suas informações públicas no arquivo "perfil.json"

        # Login
        *Acessa uma conta a partir duma senha e nome de usuário armazenado e validado no arquivo "logins.txt"

            #Menu
            *Apresenta menu de funciodades para cada usuários:
                1. Postar tweet
                    *Recebe uma mensagem do usuário e armazena em sua timelina(arquivo "perfil.json")
                2. Ver timeline
                    *Permite que o usuário veja o histórico de todos os seus "tweets" já postados;
                3. Seguir usuário
                    *Permite que o usuário siga outro usuário existente
                4. Ver quem está seguindo
                    *Permite que o usuário veja todos os outros usuários que ele segue.

   




