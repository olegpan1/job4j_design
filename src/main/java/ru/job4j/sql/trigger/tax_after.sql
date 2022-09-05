create or replace function tax_after()
    returns trigger as
$$
BEGIN
    update products
    set price = price * 1.1
    where id = (select id from inserted);
    return new;
END;
$$
    LANGUAGE 'plpgsql';