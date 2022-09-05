create or replace function history_function()
    returns trigger as
$$
BEGIN
    insert into history_of_price (name, price, date) values (new.name, new.price, now());
    return new;
END;
$$
    LANGUAGE 'plpgsql';