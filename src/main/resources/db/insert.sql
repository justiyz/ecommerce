set foreign_key_checks = 0;

truncate table customer;
truncate table address;
truncate table customer_addresses;
truncate table card;
truncate table product;
truncate table order_tb;

insert into order_tb (`id`, `canceled`, `date`, `delivered`, `customer_id`)
values (1, 0, "10-12-2021", 1, 3),
        (2, 0, "20-02-2021", 1, 2);

insert into address (`id`, `city`, `country`, `state`, `street`, `zipcode`)
values (1, "Yaba", "Nigeria", "Lagos", "312 Herbert Macaulay way, Sabo", "100110"),
       (2, "Mushin", "Nigeria", "Lagos", "19 way street, Mushin", "001001"),
       (3, "Lekki", "Nigeria", "Ekpe", "41 road, Lekki-Ekpe", "112233"),
       (4, "Ajah", "Nigeria", "Ogun", "1024 way, Ajah", "223344");



insert into customer (`id`, `contact`, `email`, `first_name`, `last_name`, `password`)
values (1, "09031861100", "ern@gmail.com", "Ernest", "Inyang", "ern123"),
       (2, "08023237911", "tobifemi@gmail.com", "Antony", "Joshua", "tony123"),
       (3, "123-456-789", "Antking@yahoo.com", "Anthony", "King", "ant123");


insert into customer_addresses (`customers_id`, `addresses_id`)
values (1, 1),
       (1, 2),
       (2, 2);


insert into card (`id`,`card_name`, `card_number`, `card_type`, `cvv`, `exp_date`, `customer_id`)
values (1, "Samuel Jackson", "20139456222", "Verve", 154, "2-5-21", null ),
       (2, "Akpan Ifiok", "5655800015546", "Master card", 102, "12-10-22", null );


insert into product (`id`, `name`, `description`, `price`, `quantity`, `exp_date`)
values (1, "Onion", "Sweet onion", 100.00, 4, "10-10-21"),
       (2, "Tomato", "Agino Moto tomato", 100.00, 20, "02-04-22");


set foreign_key_checks = 1;
