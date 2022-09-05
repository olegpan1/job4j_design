create or replace function tax_before()
    returns trigger as
$$
BEGIN
    new.price = new.price * 1.5;
    return new;
END;
$$
    LANGUAGE 'plpgsql';