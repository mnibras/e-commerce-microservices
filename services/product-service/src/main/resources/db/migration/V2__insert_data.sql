INSERT INTO category (id, name, description) VALUES (NEXTVAL('category_seq'), 'Keyboards', 'Variety of keyboards for different needs');
INSERT INTO category (id, name, description) VALUES (NEXTVAL('category_seq'), 'Monitors', 'High-resolution monitors for crisp display');
INSERT INTO category (id, name, description) VALUES (NEXTVAL('category_seq'), 'Headsets', 'Headsets for immersive audio experience during gaming or calls');
INSERT INTO category (id, name, description) VALUES (NEXTVAL('category_seq'), 'Mic', 'Various types of computer mice for precision control');
INSERT INTO category (id, name, description) VALUES (nextval('category_seq'), 'Accessories', 'Different Computer Accessories');

-- Insert products for the 'Keyboards' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 10, 'Mechanical keyboard with RGB lighting', 'Mechanical Keyboard', 99.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 15, 'Wireless compact keyboard', 'Wireless Compact Keyboard ', 79.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 20, 'Backlit gaming keyboard with customizable keys', 'Gaming Keyboard', 129.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 25, 'Mechanical keyboard with wrist rest', 'Ergonomic Keyboard', 109.99, (SELECT id FROM category WHERE name = 'Keyboards')),
    (nextval('product_seq'), 18, 'Wireless keyboard and mouse combo', 'Wireless Combo', 69.99, (SELECT id FROM category WHERE name = 'Keyboards'));

-- Insert products for the 'Monitors' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 30, '27-inch IPS monitor with 4K resolution', '4K Monitor', 399.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 25, 'Ultra-wide gaming monitor with HDR support', 'Ultra-wide Gaming Monitor', 499.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 22, '24-inch LED monitor for office use', 'Office Monitor', 179.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 28, '32-inch curved monitor with AMD FreeSync', 'Curved Monitor', 329.99, (SELECT id FROM category WHERE name = 'Monitors')),
    (nextval('product_seq'), 35, 'Portable USB-C monitor for laptops', 'Portable Monitor', 249.99, (SELECT id FROM category WHERE name = 'Monitors'));

-- Insert products for the 'Headsets' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (NEXTVAL('product_seq'), 200, 'Wireless Gaming Headset with surround sound', 'Wireless Gaming Headset', 89.99, (SELECT id FROM category WHERE name = 'Headsets')),
    (NEXTVAL('product_seq'), 180, 'Noise-Canceling Headphones for clear audio', 'Noise-Canceling Headphones', 99.99, (SELECT id FROM category WHERE name = 'Headsets')),
    (NEXTVAL('product_seq'), 150, 'Bluetooth Headset for hands-free calls', 'Bluetooth Headset', 49.99, (SELECT id FROM category WHERE name = 'Headsets')),
    (NEXTVAL('product_seq'), 100, 'Wired Gaming Headset with microphone', 'Wired Gaming Headset', 69.99, (SELECT id FROM category WHERE name = 'Headsets')),
    (NEXTVAL('product_seq'), 120, 'In-Ear Earphones for on-the-go use', 'In-Ear Earphones', 19.99, (SELECT id FROM category WHERE name = 'Headsets'));


-- Insert products for the 'Mice' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 30, 'Wireless gaming mouse with customizable RGB lighting', 'RGB Gaming Mouse', 59.99, (SELECT id FROM category WHERE name = 'Mic')),
    (nextval('product_seq'), 28, 'Ergonomic wired mouse for productivity', 'Ergonomic Wired Mouse', 29.99, (SELECT id FROM category WHERE name = 'Mic')),
    (nextval('product_seq'), 32, 'Ambidextrous gaming mouse with high DPI', 'Ambidextrous Gaming Mouse', 69.99, (SELECT id FROM category WHERE name = 'Mic')),
    (nextval('product_seq'), 26, 'Travel-sized compact mouse for laptops', 'Travel Mouse', 19.99, (SELECT id FROM category WHERE name = 'Mic')),
    (nextval('product_seq'), 35, 'Vertical ergonomic mouse for reduced strain', 'Vertical Ergonomic Mouse', 39.99, (SELECT id FROM category WHERE name = 'Mic'));

-- Insert products for the 'Accessories' category
INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
VALUES
    (nextval('product_seq'), 25, 'Adjustable laptop stand with cooling fan', 'Adjustable Laptop Stand', 34.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 20, 'Wireless charging pad for smartphones', 'Wireless Charging Pad', 24.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 28, 'Gaming headset stand with RGB lighting', 'RGB Headset Stand', 49.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 22, 'Bluetooth mechanical keypad for tablets', 'Bluetooth Keypad', 39.99, (SELECT id FROM category WHERE name = 'Accessories')),
    (nextval('product_seq'), 30, 'External hard drive enclosure with USB-C', 'External Hard Drive Enclosure', 29.99, (SELECT id FROM category WHERE name = 'Accessories'));
