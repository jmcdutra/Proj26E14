# **Product Backlog**

## **Sistema de Gestão de Biblioteca Universitária**

## **1\. Stakeholders**

### **1.1 Stakeholders primários**

Os stakeholders primários são os utilizadores que interagem diretamente com o sistema.

* Aluno  
* Bibliotecário  
* Gestor

### **1.2 Stakeholders secundários**

Os stakeholders secundários são entidades ou grupos afetados pelo funcionamento do sistema, ainda que não utilizem a aplicação de forma direta e frequente.

* Direção da Biblioteca  
* Serviços Académicos

---

## **2\. Perfis de utilizador**

### **Aluno**

Utilizador que consulta o catálogo, requisita livros, acompanha as suas requisições, renova empréstimos e gere os seus pedidos de lista de espera.

### **Bibliotecário**

Utilizador responsável pela gestão operacional da biblioteca, incluindo o registo de livros, registo de alunos, consulta de requisições e processamento de devoluções.

### **Gestor**

Utilizador com permissões administrativas, responsável por registar bibliotecários e também por executar as operações disponíveis ao bibliotecário, quando necessário.

---

## **3\. Requisitos Funcionais**

| RF01 | O sistema deve permitir a autenticação de utilizadores através de email e password. |
| :---- | :---- |
| RF02 | O sistema deve apresentar um menu diferente de acordo com o perfil do utilizador autenticado. |
| RF03 | O sistema deve permitir pesquisar livros por título, autor ou ISBN. |
| RF04 | O sistema deve permitir consultar a disponibilidade de um livro. |
| RF05 | O sistema deve permitir registar novos livros no catálogo. |
| RF06 | O sistema deve permitir registar novos alunos. |
| RF07 | O sistema deve permitir registar novos bibliotecários. |
| RF08 | O sistema deve permitir que um aluno requisite um livro disponível. |
| RF09 | O sistema deve permitir consultar requisições ativas de um aluno. |
| RF10 | O sistema deve permitir consultar o histórico de requisições de um aluno. |
| RF11 | O sistema deve permitir que o bibliotecário consulte e filtre requisições. |
| RF12 | O sistema deve permitir registar a devolução de livros. |
| RF13 | O sistema deve permitir renovar uma requisição, quando aplicável. |
| RF14 | O sistema deve permitir criar, consultar e cancelar pedidos de lista de espera. |
| RF15 | O sistema deve permitir gerar notificações internas para os alunos. |
| RF16 | O sistema deve permitir consultar notificações internas. |

---

## **4\. Regras de Negócio**

| RN01 | Apenas alunos com inscrição ativa podem requisitar livros. |
| :---- | :---- |
| RN02 | Um aluno pode ter, no máximo, 5 requisições ativas em simultâneo. |
| RN03 | A data limite de devolução é calculada 30 dias após a data da requisição. |
| RN04 | Uma requisição só pode ser renovada uma vez. |
| RN05 | A renovação só pode ser efetuada antes da data limite de devolução. |
| RN06 | A renovação não é permitida se existir pedido ativo ou pendente em lista de espera para o mesmo livro. |
| RN07 | Um aluno com requisições em atraso não pode requisitar novos livros. |
| RN08 | Um livro só pode ser requisitado se existir pelo menos um exemplar disponível. |
| RN09 | Ao requisitar um livro, o número de exemplares disponíveis deve diminuir em uma unidade. |
| RN10 | Ao devolver um livro, o número de exemplares disponíveis deve aumentar em uma unidade, salvo se existir lista de espera pendente para esse livro. |
| RN11 | Um aluno não pode entrar na lista de espera de um livro que já tem requisitado. |
| RN12 | Um aluno só pode cancelar pedidos de lista de espera feitos por si. |
| RN13 | Um pedido de lista de espera só pode ser cancelado enquanto estiver no estado Ativo. |
| RN14 | Emails de utilizadores devem ser únicos no sistema. |
| RN15 | O número de aluno deve ser único no sistema. |
| RN16 | O ISBN deve ser único no catálogo. |
| RN17 | Sempre que uma operação não puder ser realizada, o sistema deve apresentar uma mensagem de erro clara. |

---

## **5\. Requisitos Não Funcionais**

| RNF01 | A aplicação deve ser executada em terminal, através de menus textuais. |
| :---- | :---- |
| RNF02 | O sistema deve apresentar os resultados de pesquisa em tempo aceitável para uma aplicação local de terminal. |
| RNF03 | O sistema deve validar entradas obrigatórias antes de concluir qualquer registo. |
| RNF04 | O sistema deve impedir que um utilizador aceda a funcionalidades de outro perfil. |
| RNF05 | O sistema deve apresentar mensagens claras de sucesso, erro e confirmação. |
| RNF06 | O sistema deve organizar as opções de menu de forma simples e compreensível. |
| RNF07 | O sistema deve proteger a autenticação, não indicando se o erro está no email ou na password. |
| RNF08 | O sistema deve manter a consistência dos dados após operações de requisição, renovação, devolução e cancelamento. |

---

# **6\. Product Backlog**

## **\[US01\] Autenticação de utilizador**

**Ator:** Aluno, Bibliotecário ou Gestor  
**Prioridade:** Alta

**User Story:**  
Como utilizador do sistema, quero autenticar-me com email e password, para que possa aceder às funcionalidades correspondentes ao meu perfil.

**Critérios de Aceitação:**

* O utilizador introduz email e password no terminal.  
* Se as credenciais forem válidas, o sistema apresenta o menu correspondente ao perfil do utilizador: Aluno, Bibliotecário ou Gestor.  
* Se as credenciais forem inválidas, o sistema apresenta a mensagem: “Credenciais inválidas.”  
* A mensagem de erro não deve indicar se o problema está no email ou na password.  
* O utilizador pode terminar sessão a partir do menu principal.  
* Após terminar sessão, o sistema volta ao menu de autenticação.  
* Funcionalidades protegidas só podem ser usadas por utilizadores autenticados.

---

## **\[US02\]  Registo de aluno**

**Ator:** Bibliotecário ou Gestor  
**Prioridade:** Alta

**User Story:**  
Como bibliotecário ou gestor, quero registar um novo aluno, para que este possa autenticar-se e utilizar o sistema da biblioteca.

**Critérios de Aceitação:**

* O utilizador tem de estar autenticado como Bibliotecário ou Gestor.  
* O sistema solicita nome, email, password, número de aluno e estado da inscrição.  
* O sistema valida que todos os campos obrigatórios foram preenchidos.  
* O sistema valida que o email ainda não existe no sistema.  
* O sistema valida que o número de aluno ainda não existe no sistema.  
* Se existir algum erro de validação, o sistema apresenta uma mensagem clara e não conclui o registo.  
* Após o registo com sucesso, o aluno pode autenticar-se imediatamente.  
* O sistema apresenta a mensagem: “Aluno registado com sucesso.”

---

## **\[US03\] Registo de bibliotecário**

**Ator:** Gestor  
**Prioridade:** Média  
**Sprint sugerido:** Sprint 2

**User Story:**  
Como gestor, quero registar um novo bibliotecário, para que este possa aceder às funcionalidades de gestão operacional da biblioteca.

**Critérios de Aceitação:**

* O utilizador tem de estar autenticado como Gestor.  
* O sistema solicita nome, email e password.  
* O sistema valida que todos os campos obrigatórios foram preenchidos.  
* O sistema valida que o email ainda não existe no sistema.  
* Se o email já existir, o sistema apresenta uma mensagem de erro e não conclui o registo.  
* Após o registo com sucesso, o bibliotecário pode autenticar-se imediatamente.  
* O sistema apresenta a mensagem: “Bibliotecário registado com sucesso.”

---

## **\[US04\]  Registo de livro**

**Ator:** Bibliotecário ou Gestor  
**Prioridade:** Alta  
**Sprint sugerido:** Sprint 1

**User Story:**  
Como bibliotecário ou gestor, quero registar novos livros no catálogo, para que fiquem disponíveis para consulta e requisição.

**Critérios de Aceitação:**

* O utilizador tem de estar autenticado como Bibliotecário ou Gestor.  
* O sistema solicita título, autor, ISBN e número de exemplares.  
* O sistema valida que todos os campos obrigatórios foram preenchidos.  
* O sistema valida que o ISBN ainda não existe no catálogo.  
* O sistema valida que o número de exemplares é superior a zero.  
* Quando o livro é registado, o número de exemplares disponíveis fica igual ao número total de exemplares.  
* Se os exemplares disponíveis forem superiores a zero, o livro é apresentado como Disponível.  
* O sistema apresenta a mensagem: “Livro registado com sucesso.”

---

## **\[US05\]  Pesquisa de livro**

**Ator:** Aluno, Bibliotecário ou Gestor  
**Prioridade:** Alta  
**Sprint sugerido:** Sprint 1

**User Story:**  
Como utilizador autenticado, quero pesquisar um livro por título, autor ou ISBN, para que possa consultar a sua existência e disponibilidade na biblioteca.

**Critérios de Aceitação:**

* O utilizador tem de estar autenticado.  
* A pesquisa pode ser feita por título, autor ou ISBN.  
* A pesquisa deve aceitar palavras parciais, por exemplo: “Harry” deve encontrar “Harry Potter”.  
* O sistema apresenta os livros encontrados no terminal.  
* Cada resultado apresenta, pelo menos, título, autor, ISBN, número total de exemplares e número de exemplares disponíveis.  
* Se existir pelo menos um exemplar disponível, o livro é apresentado como Disponível.  
* Se não existirem exemplares disponíveis, o livro é apresentado como Indisponível.  
* Se nenhum livro for encontrado, o sistema apresenta a mensagem: “Nenhum livro encontrado.”

---

## **\[US06\] Requisição de livro**

**Ator:** Aluno  
**Prioridade:** Alta  
**Sprint sugerido:** Sprint 1

**User Story:**  
Como aluno, quero requisitar um livro disponível, para que possa levantá-lo e utilizá-lo durante o prazo permitido.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O aluno tem de ter inscrição ativa.  
* O sistema impede a requisição se o aluno já tiver 5 requisições ativas.  
* O sistema impede a requisição se o aluno tiver alguma requisição em atraso.  
* O sistema impede a requisição se não existir nenhum exemplar disponível do livro.  
* Após a requisição, é criada uma requisição no estado Ativa.  
* A data de requisição corresponde à data atual do sistema.  
* A data limite de devolução é calculada 30 dias após a data de requisição.  
* O número de exemplares disponíveis do livro diminui em uma unidade.  
* O sistema apresenta uma mensagem de confirmação com o título do livro e a data limite de devolução.

---

## **\[US07\] Consulta de requisições ativas do aluno**

**Ator:** Aluno  
**Prioridade:** Alta

**User Story:**  
Como aluno, quero consultar as minhas requisições ativas, para que possa saber quais livros tenho comigo e quando devo devolvê-los.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O sistema apresenta apenas as requisições do aluno autenticado.  
* A listagem apresenta título do livro, autor, data de requisição e data limite de devolução.  
* As requisições em atraso devem aparecer com a indicação textual “\[EM ATRASO\]”.  
* A listagem é ordenada pela data limite de devolução, da mais próxima para a mais distante.  
* Se o aluno não tiver requisições ativas, o sistema apresenta a mensagem: “Não existem requisições ativas.”

---

## **\[US08\] Consulta de todas as requisições**

**Ator:** Bibliotecário ou Gestor  
**Prioridade:** Alta

**User Story:**  
Como bibliotecário ou gestor, quero consultar as requisições registadas no sistema, para que possa acompanhar o estado dos empréstimos da biblioteca.

**Critérios de Aceitação:**

* O utilizador tem de estar autenticado como Bibliotecário ou Gestor.  
* O sistema permite listar todas as requisições.  
* O sistema permite filtrar requisições por estado: Ativa, Devolvida ou Em atraso.  
* O sistema permite pesquisar requisições por nome do aluno ou por título do livro.  
* Cada requisição apresenta nome do aluno, título do livro, data de requisição, data limite de devolução, data de devolução efetiva quando existir e estado.  
* Requisições em atraso devem aparecer com a indicação textual “\[EM ATRASO\]”.  
* Se não existirem resultados para o filtro/pesquisa, o sistema apresenta uma mensagem adequada.

---

## **\[US09\] Registo de devolução de livro**

**Ator:** Bibliotecário ou Gestor  
**Prioridade:** Alta

**User Story:**  
Como bibliotecário ou gestor, quero registar a devolução de um livro, para que a requisição seja concluída e o exemplar possa voltar a ficar disponível ou ser encaminhado para a lista de espera.

**Critérios de Aceitação:**

* O utilizador tem de estar autenticado como Bibliotecário ou Gestor.  
* O sistema permite procurar a requisição ativa pelo nome do aluno ou pelo título do livro.  
* O sistema apenas permite devolver requisições que estejam no estado Ativa ou Em atraso.  
* Após a devolução, a requisição passa para o estado Devolvida.  
* O sistema regista a data efetiva de devolução.  
* Se não existir lista de espera ativa para o livro, o número de exemplares disponíveis aumenta em uma unidade.  
* Se existir lista de espera ativa para o livro, o sistema gera uma notificação interna para o primeiro aluno da lista.  
* Quando for gerada uma notificação para lista de espera, o pedido passa para o estado Pendente.  
* O sistema apresenta a mensagem: “Devolução registada com sucesso.”

---

## **\[US10\] Consulta do histórico de requisições**

**Ator:** Aluno  
**Prioridade:** Média

**User Story:**  
Como aluno, quero consultar o meu histórico de requisições, para que possa acompanhar os livros que já requisitei anteriormente.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O sistema apresenta apenas o histórico do aluno autenticado.  
* A listagem apresenta todas as requisições do aluno, independentemente do estado.  
* Cada entrada apresenta título do livro, autor, data de requisição, data limite de devolução, data de devolução efetiva quando existir e estado.  
* A listagem é ordenada da requisição mais recente para a mais antiga.  
* Se não existir histórico, o sistema apresenta a mensagem: “Não existem requisições no histórico.”

---

## **\[US11\] Renovação de requisição**

**Ator:** Aluno  
**Prioridade:** Média

**User Story:**  
Como aluno, quero renovar uma requisição, para que possa prolongar o prazo de devolução do livro.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O aluno apenas pode renovar requisições próprias.  
* A requisição tem de estar no estado Ativa.  
* A renovação só pode ser feita antes da data limite de devolução.  
* Cada requisição só pode ser renovada uma vez.  
* A renovação não é permitida se existir pedido ativo ou pendente em lista de espera para o mesmo livro.  
* Após a renovação, a nova data limite é calculada 30 dias após a data da renovação.  
* O sistema regista que a requisição já foi renovada.  
* O sistema apresenta uma mensagem de confirmação com a nova data limite.  
* Se a renovação não for permitida, o sistema apresenta uma mensagem de erro com o motivo.

---

## **\[US12\] Pedido de lista de espera**

**Ator:** Aluno  
**Prioridade:** Média

**User Story:**  
Como aluno, quero fazer um pedido de lista de espera para um livro indisponível, para que possa ser avisado quando houver um exemplar disponível.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O aluno pesquisa o livro por título ou ISBN.  
* Se a pesquisa devolver mais do que um livro, o aluno seleciona o livro pretendido.  
* O sistema impede o pedido se o livro tiver exemplares disponíveis.  
* O sistema impede o pedido se o aluno já tiver o mesmo livro requisitado.  
* O sistema impede o pedido se o aluno já tiver um pedido ativo ou pendente para o mesmo livro.  
* Após o pedido, o sistema cria uma entrada na lista de espera no estado Ativo.  
* O sistema apresenta a mensagem: “Pedido de lista de espera registado com sucesso.”  
* O sistema gera uma notificação interna de confirmação para o aluno.

---

## **\[US13\] Cancelamento de pedido de lista de espera**

**Ator:** Aluno  
**Prioridade:** Baixa

**User Story:**  
Como aluno, quero cancelar um pedido de lista de espera, para que possa desistir de um livro que já não pretendo requisitar.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O aluno apenas pode cancelar pedidos de lista de espera feitos por si.  
* O cancelamento só é possível quando o pedido estiver no estado Ativo.  
* Após o cancelamento, o pedido passa para o estado Cancelado ou é removido da lista ativa.  
* O sistema mantém a ordem dos restantes pedidos da lista de espera.  
* O sistema apresenta a mensagem: “Pedido de lista de espera cancelado com sucesso.”

---

## **\[US14\] Consulta de notificações**

**Ator:** Aluno  
**Prioridade:** Baixa

**User Story:**  
Como aluno, quero consultar as minhas notificações internas, para que possa ver avisos enviados pelo sistema.

**Critérios de Aceitação:**

* O aluno tem de estar autenticado.  
* O sistema apresenta apenas as notificações do aluno autenticado.  
* A listagem apresenta assunto, data de criação e estado: Lida ou Não lida.  
* O aluno pode selecionar uma notificação para consultar o conteúdo completo.  
* Ao abrir uma notificação, o estado passa automaticamente para Lida.  
* A listagem é ordenada da notificação mais recente para a mais antiga.  
* Se não existirem notificações, o sistema apresenta a mensagem: “Não existem notificações.”

---

# **7\. Regras técnicas de funcionamento**

As regras abaixo não são User Stories, porque não representam uma funcionalidade solicitada diretamente por um ator humano. São comportamentos internos necessários para manter a consistência da aplicação.

O sistema deve atualizar o estado das requisições cuja data limite já tenha passado.

**Critérios de funcionamento:**

* A atualização pode ocorrer quando a aplicação é iniciada ou antes de apresentar listagens de requisições.  
* Requisições ativas cuja data limite seja anterior à data atual passam para o estado Em atraso.  
* Requisições devolvidas não são alteradas.  
* O sistema mantém a consistência das informações apresentadas ao aluno, bibliotecário e gestor.  
* A indicação de atraso aparece nas consultas de requisições ativas, histórico e listagens administrativas.

---

# **8\. Definition of Done**

Uma User Story é considerada concluída quando:

* A funcionalidade está implementada em Java.  
* A funcionalidade pode ser executada através do terminal.  
* As permissões do perfil do utilizador são respeitadas.  
* Os critérios de aceitação da User Story foram cumpridos.  
* As entradas inválidas são tratadas com mensagens de erro claras.  
* As operações mantêm os dados consistentes.  
* A funcionalidade foi testada manualmente pela equipa.  
* O código foi submetido no repositório GitHub da equipa.

# **9\. Sprint Backlog \- Sprint 1**

## **Objetivo do Sprint** 

Implementar a base do sistema, permitindo a autenticação de todos os perfis, o registo inicial de utilizadores e livros, e a funcionalidade essencial de requisição e consulta de requisições ativas por parte do Aluno.

## **User Stories Selecionadas**

As User Stories selecionadas para o Sprint 1 são as seguintes:

* **\[US01\] Autenticação de utilizador**  
* **\[US02\] Registo de aluno**  
* **\[US03\] Registo de bibliotecário**  
* **\[US04\] Registo de livro**  
* **\[US05\] Pesquisa de livro**  
* **\[US06\] Requisição de livro**  
* **\[US07\] Consulta de requisições ativas do aluno**