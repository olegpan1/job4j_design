create trigger discount_trigger
    after insert
    on products
    for each row
execute procedure discount();