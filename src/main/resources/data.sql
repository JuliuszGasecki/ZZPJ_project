insert into user(id, age, description, email_address, first_name, is_admin, last_name, picture, location, password, username) values (9999, 20, 'Zadowolony Użytkownik', 'abcefg@gmail.com', 'Jakub', true, 'Wesołowski', null, 0, 'password', 'login')
insert into thread(thread_id, title, description, creation_date, is_closed, user_rating, author_id) values (8888, 'Wesołych Świąt Wszystkim Użytkownikom :)', '...i Szczęśliwego Nowego Roku', '2018-12-24 11:51:46.201', true, 12, 9999)
insert into post(post_id, body, creation_date, user_rating, author_id, thread_id) values (777, ':)', '2019-06-24 16:56:48.29', 4, 9999, 8888)