INSERT INTO places(name, image, description) VALUES('McDonalds', 'https://s3-symbol-logo.tradingview.com/mcdonalds--600.png', 'McDonalds (MCD) is a fast food, limited service restaurant with more than 35,000 restaurants in over 100 countries.'),
                                                   ('Dodo Pizza', 'https://avatars.mds.yandex.net/get-zen_doc/1640172/pub_5d09b09f936d3400aeb5597e_5d09c362bdf4b900afa3b103/scale_1200', 'Dodo Pizza uses a cloud-based system known as Dodo IS that collects and processes operations data'),
                                                   ('KFC', 'https://restoran.kz/neofiles/serve-image/5e8c5538b095450006bb0f34', 'KFC is an American fast food restaurant chain headquartered in Louisville, Kentucky that specializes in fried chicken. It is the worlds second-largest');

INSERT INTO foods(place_id, name, price, description) VALUES(1, 'McCombo', 1450.0, 'McCombo description'),
                                                            (1, 'McCombo Large', 1850.0, 'McCombo Large description'),
                                                            (1, 'Big Mac', 1250.0, 'Classic Big Mac description'),
                                                            (2, 'Pizza small', 1800.0, 'Dodo pizza small desc'),
                                                            (2, 'Pizza', 2000.0, 'Average pizza desc'),
                                                            (2, 'Pizza large', 2200.0, 'Dodo pizza large desc'),
                                                            (3, 'Chicken nugets', 1300.0, 'Classic KFC chicken'),
                                                            (3, 'kfc combo ', 2500.0, 'kfc burger combo with pepsi'),
                                                            (3, 'Shef burger', 1790.0, 'Shef burger desc');