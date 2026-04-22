INSERT INTO categories (name)
VALUES ('Fruits'),
       ('Vegetables'),
       ('Dairy'),
       ('Beverages'),
       ('Snack'),
       ('Meat'),
       ('Bakery'),
       ('Frozen'),
       ('Pantry'),
       ('Personal Care');
INSERT INTO products (name, price, description, category_id)
VALUES
-- Fruits

('Banana (1kg)', 2.99, 'Fresh Cavendish bananas, rich in potassium and perfect for daily snacks.', 1),

-- Vegetables
('Broccoli (500g)', 1.89, 'Fresh green broccoli packed with vitamins and fiber.', 2),

-- Dairy
('Milk - Whole (1L)', 3.49, 'Organic whole milk sourced from grass-fed cows.', 3),

-- Beverages
('Coca-Cola 330ml Can', 1.25, 'Classic Coca-Cola soft drink in a convenient can.', 4),

-- Snacks
('Lay''s Classic Potato Chips 150g', 2.79, 'Crispy salted potato chips, perfect for snacking.', 5),

-- Meat
('Chicken Breast (500g)', 5.99, 'Fresh boneless, skinless chicken breast, high in protein.', 6),

-- Bakery
('Whole Wheat Bread Loaf', 2.49, 'Soft and healthy whole wheat bread baked fresh daily.', 7),

-- Frozen
('Frozen Margherita Pizza', 6.99, 'Classic frozen pizza with mozzarella cheese and tomato sauce.', 8),

-- Pantry
('Spaghetti Pasta 500g', 1.99, 'Italian durum wheat spaghetti, ideal for pasta dishes.', 9),

-- Personal Care
('Colgate Toothpaste 120g', 3.29, 'Fluoride toothpaste for strong teeth and fresh breath.', 10);