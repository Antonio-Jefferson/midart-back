# MidArt

## Descrição
MidArt é uma comunidade online voltada para artistas e entusiastas de arte, especialmente aqueles que apreciam animes, mangás e HQs. A plataforma oferece uma variedade de recursos para os usuários compartilharem suas obras, interagirem com outros membros da comunidade, participarem de desafios semanais, eventos e competições temáticas, além de se conectarem através de grupos temáticos. Este documento descreve as funcionalidades principais do MidArt e fornece informações sobre as rotas de API para desenvolvedores interessados em contribuir para o projeto.

## Funcionalidades do MidArt
- Possibilidade de criação de conta de usuário
- Login de usuário
- Postagem de desenhos através do upload de imagem
- Criação ou participação em grupos temáticos baseados em animes, mangás e HQs
- Comentários sobre os desenhos dos usuários
- Desafios semanais para incentivar a prática e aprimoramento das habilidades de desenho
- Eventos e competições temáticas com prêmios para os vencedores
- Seguir outros usuários e visualizar seus trabalhos
- Sistema de pontuação para reconhecer e destacar usuários mais ativos e talentosos
- Integração com outras redes sociais para compartilhar desenhos e convidar amigos

## Modelagem do Banco de Dados
O banco de dados do MidArt é projetado com as seguintes tabelas:
- **Users**: Armazena informações dos usuários registrados.
- **Drawings**: Contém informações sobre os desenhos postados pelos usuários.
- **Comments**: Registra os comentários feitos pelos usuários em desenhos.
- **Favorites**: Mantém o registro dos desenhos favoritos de cada usuário.
- **Groups**: Armazena informações sobre os grupos temáticos.
- **Followers**: Registra os seguidores de cada usuário na plataforma.
- **Notifications**: Gerencia as notificações enviadas aos usuários.
- **Events**: Tabela para armazenar informações sobre os eventos organizados pela plataforma.
- **Challenges**: Contém informações sobre os desafios semanais.

## Rotas da API
A API do MidArt fornece diversas rotas para interação com a plataforma. Abaixo estão algumas das principais rotas:
- **Login/Signup**: Rotas para autenticação e registro de usuários.
- **Drawings**: Rotas para visualização, postagem e exclusão de desenhos.
- **Users**: Rotas para atualização de perfil e busca de usuários.
- **Likes, Comments, Favorites**: Rotas para interação com desenhos, como curtir, comentar e favoritar.
- **Groups**: Rotas para gerenciamento de grupos temáticos.
- **Followers**: Rotas para seguir e deixar de seguir outros usuários.
- **Notifications**: Rotas para gerenciamento de notificações.

Para obter detalhes completos sobre todas as rotas disponíveis, consulte a documentação da API.

## Contribuindo
Se você está interessado em contribuir para o desenvolvimento do MidArt, sinta-se à vontade para explorar o código-fonte e enviar pull requests. Qualquer contribuição é bem-vinda!

## Contato
Se você tiver alguma dúvida ou sugestão, entre em contato conosco através do email: [antjeffersonbatista@gmail.com](antjeffersonbatista@gmail.com)

Obrigado por escolher o MidArt como sua comunidade de arte online!
