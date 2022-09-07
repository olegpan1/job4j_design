delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    language 'plpgsql'
as $$
BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
END
$$;

call insert_data('product_2', 'producer_2', 15, 32);
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);


create or replace procedure delete_data(d_id integer)
    language 'plpgsql'
as $$
BEGIN
    delete FROM products where id > d_id and count < 10;
END
$$;

call  delete_data(2);

create or replace function delete_data_low_price()
returns INTEGER
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        delete from products where price < 100;
        select into result count(name) from products where price >= 100;
        RETURN result;
    end;
$$;

select delete_data_low_price();