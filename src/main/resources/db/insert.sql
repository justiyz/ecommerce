SET FOREIGN_KEY_CHECKS = 0;

truncate table  address;
truncate table  customer;
truncate table customer_addresses;
truncate table card;
truncate table product;
truncate table order_tb;


INSERT INTO address (`id`, `city`, `country`, `state`, `street`, `zipcode`)
VALUES (1, "Yaba", "Nigeria", "Lagos", "312 Herbert Macualey Way, Sabo.", "10011"),
    (2, "Ikeja", "Nigeria", "Lagos", "312 Broad Street, Ikeja", "10021");

INSERT INTO customer (`id`, `contact`, `email`, `first_name`, `last_name`, `password`)
VALUES (1, "090887765", "jay@yahoo.com", "John", "Travolta", "jay123"),
    (2, "080994433", "josh@gmail.com", "Joshua", "Nebed", "josh123");

INSERT INTO customer_addresses (`customer_id`, `addresses_id`)
VALUES (1, 1),
        (1, 2),
        (2, 2);

INSERT INTO card (`id`, `card_name`, `card_number`, `card_type`, `cvv`, `exp_date`, `customer_id`)
VALUES      (1, "Inyang Ernest", "09856434", "Visa", 333-555-222-11, "12-11-2021", 1),
            (2, "Inyang Israel", "09853419", "Verve", 333-666-222-11, "12-11-2022", 1);


INSERT INTO product (`id`, `name`, `description`, `price`, `quantity`, `exp_date`)
VALUES              (1, "Onion", "It is a vegetable", 200.0, 4, "02-11-2022"),
                    (2, "Maggi", "seasoning cube", 100.0, 5, "02-02-2021");




SET FOREIGN_KEY_CHECKS = 1;