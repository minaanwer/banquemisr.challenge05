INSERT INTO public.user (email,"password",username) VALUES
                                                        ('admin@mail.com','$2a$10$lJY4ny/WZXv1wvr2dgCK3.60G31jkr555b0Gw4OVs3ZTXNO5WoWAq','admin_user'),
                                                        ('user@mail.com','$2a$10$lJY4ny/WZXv1wvr2dgCK3.60G31jkr555b0Gw4OVs3ZTXNO5WoWAq','normal_user');

INSERT INTO public."role" ("name",user_id) VALUES
                                               ('admin',1),
                                               ('user',2);

