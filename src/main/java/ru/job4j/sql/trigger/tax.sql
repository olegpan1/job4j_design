create or replace function tax()
    returns trigger as
$$
BEGIN
    update products
    set price = price - price * 0.2
    where id = (select id from inserted) and count <= 5;
    return new;
END;
$$
    LANGUAGE 'plpgsql';