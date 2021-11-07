<h1 align="center">💸 Bluebank API </h1>


<p align="center"> Criado no dia 29/10/2021 às 18 horas com prazo para o dia 8/11/2021 </p>

O Projeto tem como objetivo entregar uma aplicação da <b>IBM Blue Academy</b>, para construir uma API Rest e alguns Endpoints no backend, sendo possível criar uma nova conta, fazer transferências de saldo entre contas Bluebank, Pesquisar dados cadastrados, Atualizar e deletar dados, transações e informações básicas e Visualizar um histórico de transações efetuadas.

Nesse projeto foi utilizado o Spring Boot Versão <b>2.5.6</b>, o <b>Maven</b> como gerenciador de dependências, o Banco de dados <b> H2 Database</b> e os initializers:

<ul>
<li> Spring Boot DevTools </li>
<li> Spring Web </li>
<li> Spring Security </li>
<li> Spring Data JPA </li>
<li> Spring Validation </li>
<li> H2 Database </li>
<li> Lombock </li>
</ul>

Utilizamos o <b> Swagger </b> para centralizar, documentar e testar as Endpoints da aplicação.


## 🤔 Como começar?  <a name = "getting_started"></a>

Precisará ter os seguintes requisitos:

- Java 11
- Maven
- E uma boa IDE ou Editor de Texto
- Ter conhecimento sobre como utilizar o Swagger

- Clique no Link para os <a href="https://github.com/ZenCoderz/BlueBank/wiki/Getting-Started"> próximos passos</a>

## ⚙️ Banco de Dados

Inicialmente havíamos planejado em utilizar um versionador de banco de dados, como liquibase, porém devido a divergência de ambientes e o curto prazo, acabamos por optar em deixar a responsabilidade da criação do Banco de Dados para o Spring Data JPA.

- Clique no Link para baixar o <a href="">Diagrama</a>


## 📝 Documentação

A documentação do desenvolvimento se baseou na criação de tasks, testes, commits e diagramas.
Obs: O diagrama de relacionamento inicial foi criado e se encontra nas Issues.

<a href="https://github.com/ZenCoderz/BlueBank/wiki">WIKI</a>

<a href="https://drive.google.com/drive/u/4/folders/1KpA8uyDkZQmv5gefKnOQvWwbhWlnNdti"> DOCUMENTOS </a>


## 📣 Problema <a name = "problem_statement"></a>

O Problema em questão era criar um sistema em que estivesse disponível o Crud de Clientes, Contas, Telefones e gerasse 4 tipos diferentes de relatórios relacionado as Transações dos Clientes, isso em um prazo de 4 dias.

- IDEAL: O Ideal seria que a aplicação fizesse transações em diferentes tipos de contas não apenas entre contas bluebank, ter uma camada de separação entre Usuário e Cliente, ter autorização disponível de acordo com o tipo de usuário, possuir um versionador de Banco de Dados, ter um client responsável por salvar os Logs, ter disponível as métricas, possuir um container para auxiliar na criação de ambiente, etc.

- REALIDADE: Devido ao prazo de documentar e planejar de forma minimalista e muitas "hands on", acredito que a aplicação corresponda com as necessidades minímnas de forma que seja possível fazer transações entre contas bluebank, criar usuários/clientes, manter histório de transações, realizar login e listar usuários/clientesno backend através da API.

- CONSEQUÊNCIAS: Não temos versionador de banco de dados, estamos utilizando o JPA para criar o banco, a aplicação ainda possui exceções com falta de informação ou não formatadas, não testamos todas as rotas e poderiamos ter acrescentado mais informação no Swagger.


## 👁‍🗨 Testes <a name = "idea"></a>

Foram criados testes tanto unitários quanto de integração. Os testes unitários foram criados utilizando <b> Mockito</b> e os de integração utilizando <b>Mock MVC</b>, porém nem todos ENDPOINTS estão testados, testamos apenas os principais caminhos, devido ao tempo.


## ✍️ Autores

- [Dídimo Yokoyama](https://github.com/ticoyk)
- [Bengt Karlsson](https://github.com/bengtfk)
- [Paul Anderson](https://github.com/paulfms)
- [Nathan Oliveira](https://github.com/nathanoliveiras)

## 🎉 Reconhecimento

Gama Academy e IBM,

Agradecemos a oportunidade de sermos escolhidos no processo seletivo para conhecer uma história grandiosa que é a <b> IBM </b> e também nosso muito obrigado ao curso coordenado pela equipe da <b> Gama Academy</b>, que foi e sempre será de grande aprendizado para todos nós do grupo <b> ZenCoderz </b>.
