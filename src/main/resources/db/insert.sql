set foreign_key_checks = 0;

truncate table customer;
truncate table address;
truncate table customer_addresses;
truncate table card;
truncate table product;

insert into address (`id`, `city`, `country`, `state`, `street`, `zipcode`)
values (1, "Yaba", "Nigeria", "Lagos", "312 Herbert Macaulay way, Sabo", "100110"),
       (2, "Mushin", "Nigeria", "Lagos", "19 wey street, Mushin", "001001");


insert into customer (`id`, `contact`, `email`, `first_name`, `last_name`, `password`)
values (1, "09031861100", "ern@gmail.com", "Ernest", "Inyang", "ern123"),
       (2, "08023237911", "tobifemi@gmail.com", "Antony", "Joshua", "tony123");


insert into customer_addresses (`customer_id`, `addresses_id`)
values (1, 1),
       (1, 2),
       (2, 2);


insert into card (`id`,`card_name`, `card_number`, `card_type`, `cvv`, `exp_date`, `customer_id`)
values (1, "Samuel Jackson", "20139456222", "Verve", 154, "2-5-21", 1 ),
       (2, "Akpan Ifiok", "5655800015546", "Master card", 102, "12-10-22", null );


insert into product (`id`, `name`, `description`, `price`, `quantity`, `exp_date`)
values (1, "Onion", "Sweet onion", 100.00, 4, "10-10-21"),
       (2, "Tomato", "Agino Moto tomato", 100.00, 20, "02-04-22");


set foreign_key_checks = 1;
