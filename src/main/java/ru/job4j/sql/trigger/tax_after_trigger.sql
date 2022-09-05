create trigger tax_after_trigger
    after insert on products
    referencing new table as inserted
    for each statement
execute procedure tax_after();