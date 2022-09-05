create trigger history_function_trigger
    after insert on products
    for each row
execute procedure history_function();