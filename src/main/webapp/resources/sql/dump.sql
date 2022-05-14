
create table user_bet (
                          "id" serial NOT NULL primary key,
                          "user_id" integer references users("id"),
                          "bet_id" integer references  bets("id"),
                          "value" integer
);
create table confirmations (
                               "id" serial NOT NULL primary key,
                               "email" varchar(255),
                               "code" integer
);
