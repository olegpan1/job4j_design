create trigger tax_before_trigger
    before insert on products
    for each row
execute procedure tax_before();