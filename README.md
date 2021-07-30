# 

# Accent Bank

 Accenture Project

​	Projeto desenvolvido no curso Academia Accenture Industry X, pelas integrantes Jezielle Cunha e Katlen Prycilla. O projeto Accent Bank é uma API desenvolvida em Spring Boot que roda em servidor local com conexão ao banco de dados MySQL . A API simula um ambiente bancário onde é possível salvar registros de agências bancárias, clientes, contas-correntes e suas movimentações e apresentá-las em uma págima HTML customizada em CSS.

​	A organização ao longo do projeto se deu por reuniões semanais onde eram discutidas as estratégias para atacar as demandas. Também aconteciam dailies para o acompanhamento da evolução individual em cada tarefa, conhecer as dificuldades da outra pessoa e tentar nivelar o conhecimento. A divisão de tarefas e acompanhamento de execução foi registrada na plataforma Runrun.it para visualização de todas a qualquer tempo. O controle de versionamento de código aconteceu nas plataformas Git/GitHub. E as interações e reuniões aconteciam por meio das plataformas Google Meet e WhatsApp.

​	O Modelo Relacional das entidades foi desenvolvido segundo diagrama abaixo:

![img](https://lh5.googleusercontent.com/lkTXMKXiNR4qhEHaJQnXSx0czDh8VJ2axE6EOzlGp0JL2LrAz0o4Vec_HXBpzIHKo3mem_S0RqzaMXdLdxBN-gNaelA5ETsRskg09aaV3eeivjABNASVaBMLXnAWGNyDKw0CDgeZ3k0)



​	Foi confeccionado até a tada de apresentação do projeto uma batria de testes que cobriam mais de 70% do projeto. Foram priorizados os testes que resguardavam as regras de negócio e a cobertura da exceções customizadas, como é possível ver na imagem a seguir:

![img](https://lh4.googleusercontent.com/sgxVhTu0ezCbEYkR3hhxGQyAZ8XH8aPwUQeFlz5OBm6sxWTQhiHzILip0RFswwaCd7-SYGYYMzq3o3-lVLCGDsC1yco9dJCXecHV0UxNFE7SPRElNeGydct8jw_JRNAD9vdTZrQ2uAk)



​	A construção do projeto Accent Bank exigiu muita dedicação e esforço das integrantes para entregar dentro do prazo e no nível qualidade estipulado por elas. Contudo, existe a conciência de que é possível melhorar inúmeros aspectos do projeto, os quais não foram concluídos por escassez de tempo e limitação técnica das participantes, tendo em vista que para ambas foi na Academia Accenture Industry X o primeiro contato com a linguagem Java. Porém são evoluções que se espera com o aumento de experiência e poderão ser realizadas futuramente. As melhorias elencadas como prioridade são:

- Refatorar o código;
- Evoluir a implementação do front-end;
- Utilizar BigDecimal em vez de double para aumentar a precisão no valor das transações;


- Aumentar a cobertura de testes;
- Consumir API ViaCep;
- Incluir paginação na API;
- Criar ExceptionHandler para devolver response para as exceções.

