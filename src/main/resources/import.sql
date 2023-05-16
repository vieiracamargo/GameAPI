-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- CREATE TABLE tb_game(
--     id SERIAL PRIMARY KEY,
--     title VARCHAR(255),
--     description VARCHAR(255),
--     release_date DATE,
--     trailer_url VARCHAR(255)
-- );

insert into tb_game (
    title,
    description,
    releaseDate,
    trailerUrl
)values
(
        'Cyberpunk 2077',
        'Em 2077, a América está arruinada. Megacorporações controlam a vida em todos os aspectos, do topo de seus arranha-céus até as atividades ilícitas que acontecem nas ruas. O mundo entre esses dois extremos é onde a decadência, sexo e a cultura popular se misturam com crimes violentos, pobreza extrema e a promessa inatingível do Sonho Americano.Em um mundo em que você não tem futuro, o que importa é ter controle sob o que você é. Para sobreviver e proteger sua independência, você modifica seu corpo com itens cibernéticos e faça trabalhos que jamais ousaria. Escolha viver livremente, independente de sistemas ou controles – você obedece às suas regras. Em Cyberpunk você joga como V – um assassino(a) de alguel em ascensão – e você acabou de conseguir seu primeiro contrato sério. Em um mundo de guerreiros aprimorados cibernéticamente, gênios da tecnologia e lifehackers corporativos, você pode dar o primeiro passo para se tornar uma lenda urbana.',
        '2020-12-10',
        'https://www.youtube.com/watch?v=UnA7tepsc7s'
),
(
    'Control',
    ' Jesse Faden (Courtney Hope), após uma experiência traumática durante sua infância lhe ter concedido poderes sobrenaturais, procura respostas no Departamento Federal de Controle, uma agência governamental clandestina encarregada de estudar e conter fenômenos sobrenaturais.',
    '2019-08-27',
    'https://www.youtube.com/watch?v=xni05j8SH_I'
),
(
    'Resident Evil 4 Remake',
    'O game acompanha o protagonista Leon S. Kennedy em uma missão de resgate à filha do Presidente dos Estados Unidos, Ashley, enquanto enfrenta novos vírus e ameaças em um vilarejo no interior da Espanha',
    '2023-03-23',
    'https://www.youtube.com/watch?v=8fcVPVbVbXs'
);
